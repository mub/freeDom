package com.github.mub.freeDom.model;

/**
 * Doubles as a type declaration with the given size.
 * @author azimsk Azim Sankarbekov
 */
public abstract class SizedType<T> extends StdType<T> {

    /**
     * Stock type: <tt>int[4]</tt>
     */
    private static final SizedType<Integer> INT = new SizedType<Integer>(Integer.SIZE / 8) {
        @Override public Class<Integer> getKlass() { return Integer.class; }
    };

    /**
     * Stock type: <tt>int[8]</tt>
     */
    private static final SizedType<Long> LONG = new SizedType<Long>(Long.SIZE / 8) {
        @Override public Class<Long> getKlass() { return Long.class; }
    };

    private final int size;

    protected SizedType(int size) { this.size = size; }


    public int getSize() { return size; }

}
