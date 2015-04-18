package com.github.mub.freeDom.model;

import org.joda.time.DateTime;

/**
 * Standard type - always implies a Java class either in JDK or in a popular 3<sup>rd</sup> party library such as
 * <a target="_blank" href="http://www.joda.org/joda-time">Joda</a>.
 * Classes for instances of this type should be available in the classpath of this product, included.
 */
public abstract class StdType<T> implements FreeDomType {

    /**
     * Standard type implementation: datetime.
     */
    public static final StdType<DateTime> DTTM = new StdType<DateTime>() {
        @Override public Class<DateTime> getKlass() {
            return DateTime.class;
        }
    };
    protected StdType() {}

    /**
     * Instance class for this type. Must be available in the classpath.
     */
    public abstract Class<T> getKlass();
}
