package com.github.mub.freeDom.model;

/**
 * @author elim
 */
public class StringType extends SizedType<String> {

    private final boolean isFixed;

    public StringType(final int size, final boolean isFixed) {
        super(size);
        this.isFixed = isFixed;
    }

    public boolean isFixed() { return isFixed; }

    @Override public final Class<String> getKlass() {
        return String.class;
    }
}
