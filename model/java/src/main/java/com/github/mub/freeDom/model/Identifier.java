package com.github.mub.freeDom.model;

/**
 * @author Jorge U
 * @deprecated Unclear about purpose of this class, Jorge does not remember why he created it.
 */
public class Identifier {
    private final String id;

    public Identifier(String id) {
        this.id = id;
    }
    @Override public String toString() { return id; }
}
