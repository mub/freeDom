package test.giwaintl.freeDom.parser;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.of;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestJdk8_FP {
    private static interface Tester {void testIt();} // single test case

    // lambda interfaces
    private interface Lengther<T extends Number> { T apply(T i, CharSequence what); }
    private interface Speller<T extends Number> { String apply(T i, CharSequence what); }

    /**
     * Utility method to extract a textual of interfaces implemented by a class of the passed object, useful to debug lambdas.
     * Notice the usage of J8 functional features: this one-liner gets list of interfaces, transforms them into simple class names
     * and builds a comma-delimited list, in one return operator.
     *
     * That's what you get in the log:
     * <pre>
     * Applied >>>Lengther<<< to 3 and "defg", result: 12
     * Applied >>>Lengther<<< to 9.0 and "uvwxyz", result: 54.0
     * Applied >>>Speller<<< to 12.345 and "FedEx", result: "received via FedEx: 12.345, that's it"
     * </pre>
     * @param ofWhat an instance of a lambda to report on
     * @return textual view of interfaces implemented by the argument's class.
     */
    private static String interfacesTextual(final Object ofWhat) {
        return asList(
            ofWhat.getClass().getInterfaces()
        ).stream().flatMap(i -> of(i.getSimpleName())).collect(joining(", ", ">>>", "<<<"));
    }

    // lambda tester
    private static class LengtherTestCase<T extends Number> implements Tester {
        final Lengther<T> lengther; final T num; final String str; final T expected;

        LengtherTestCase(Lengther<T> lengther, T num, String str, T expected) {
            this.lengther = lengther; this.num = num; this.str = str; this.expected = expected;
        }

        public void testIt() {
            T result = lengther.apply(num, str);
            // need list of interfaces to figure out which lambda was applied. Just class.name shows only numbered $$Lambda
            // and you won't have any idea which one
            L.info("Applied {} to {} and \"{}\", result: {}", interfacesTextual(lengther),
                num, str, result);
            assertThat(result, is(expected));
        }
    }

    // another lambda tester
    private static class SpellerTestCase<T extends Number> implements Tester {
        final Speller<T> speller; final T num; final String str; final String expected;

        SpellerTestCase(Speller<T> speller, T num, String str, String expected) {
            this.speller = speller; this.num = num; this.str = str; this.expected = expected;
        }

        public void testIt() {
            String result = speller.apply(num, str);
            L.info("Applied {} to {} and \"{}\", result: \"{}\"", interfacesTextual(speller), num, str, result);
            assertThat(result, is(expected));
        }
    }

    private static Logger L = LoggerFactory.getLogger(TestJdk8_FP.class);

    // a couple of stubs
    @Before public void init() throws Exception { } @After public void destroy() throws Exception { }

    /**
     * Get a grip on J8 lambdas, see how they actually work in real life
     *
     * @throws Exception
     */
    @Test public void testLambdas() throws Exception {
        final int multiplier = 10; // closure variable used by lambdas!
        // the closure and the 1st class function in plain java. Today:
        // create a variety of lambdas
        final Lengther<Integer> upTeen = (i, s) -> i * multiplier + s.length();
        final Lengther<Integer> mulLen = (i, s) ->
            i *  // space it out, try to set up a breakpoint in mid-lambda: it works in IntelliJ 13
                s.length()
            ;
        final Lengther<Float> fMulLen = (f, s) -> f * s.length();
        final Speller<Double> speller = (d, s) -> "received via " + s + ": " + d + ", that's it";

        // build and run testcases for each
        Arrays.<Tester>asList(// need to give the generic hint to the asList method, otherwise it gets confused
            // and does not find the testIt method. Generics do need some more work in Java.
            new LengtherTestCase<>(upTeen, 7, "abc", 7 * multiplier + 3),
            new LengtherTestCase<>(mulLen, 3, "defg", 12),
            new LengtherTestCase<>(fMulLen, 9.0f, "uvwxyz", 54.0f),
            new SpellerTestCase<>(speller, 12.345, "FedEx", "received via FedEx: 12.345, that's it")
        ).forEach(Tester::testIt);

    }
}