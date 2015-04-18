package com.github.mub.freeDom.model;

/**
 * @author michaelb
 */
public abstract class ScaledType<T> extends SizedType<T> {

    private final int scale;

    public ScaledType(final int size, final int scale) {
        super(size);
        this.scale = scale;
    }

    public int getScale() {
        return scale;
    }
}
