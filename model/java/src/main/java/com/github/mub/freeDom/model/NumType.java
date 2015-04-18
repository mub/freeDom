package com.github.mub.freeDom.model;

import java.math.BigDecimal;

/**
 * @author michaelb
 */
public class NumType extends ScaledType<BigDecimal> {

    public NumType(int size, int scale) {
        super(size, scale);
    }

    @Override public Class<BigDecimal> getKlass() { return BigDecimal.class; }
}
