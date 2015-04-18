package com.github.mub.freeDom.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Standard type: <tt>enum</tt>
 * @author mb
 * @author Jorge U
 */
public class DomEnumType extends DomObjectType {
    private String[] ordToWord;
    private LinkedHashMap<String, Integer> wordToOrd;


    private DomEnumType(final Fqn name) {
        super(name);
    }

    public Integer ord(final String name) {
        return wordToOrd.get(name);
    }

    /**
     * Gets the name for the given order number.
     * @param ord zero-based ordering number (index) for the name to retrieve.
     * @return the name for the given ordered number.
     */
    public String name(final int ord) {
        if(ord < 0 || ord >= ordToWord.length) throw new IllegalArgumentException("Ord " + ord
            + " of the enum " + getName().getKey() + "queried; valid ords 0 to " + (ordToWord.length - 1));
        return ordToWord[ord];
    }

    /**
     * Gets a copy of the names on the enum in their order.
     * @return defensive copy of the names as a {@link List}
     */
    public List<String> names() {
        return Arrays.asList(ordToWord);
    }

    private void registerNames(final List<String> names) {

        final int size = names.size();
        ordToWord = new String[size];
        wordToOrd = new LinkedHashMap<>(size * 3 /2 + 1);
        // zero based just like Java enums, this makes calculations easier
        for(int index = 0; index < size; index ++) {
            final String n = names.get(index);
            ordToWord[index] = n;
            wordToOrd.put(n, index);
        }
    }

    /**
     * Builds the enum of the given {@link Fqn} with the given list of enum names.
     * @param fqn the Fqn for the new enum.
     * @param names the enumerable names
     * @return the new enum built per the specs.
     */
    public static DomEnumType buildFrom(final Fqn fqn, final List<String> names) {
        if ((new HashSet<>(names).size()) != names.size()) throw new IllegalArgumentException("Duplicate names in ");
        DomEnumType result = new DomEnumType(fqn);
        result.registerNames(names);
        return result;
    }
}

