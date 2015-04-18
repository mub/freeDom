package com.github.mub.freeDom.model;

import java.util.LinkedHashMap;

/**
 * @author Jorge U
 */
public class Struct extends DomObjectType {

    private final LinkedHashMap<String, Field<?>> fields;

    public Struct(Fqn name) {
        super(name);
        fields = new LinkedHashMap<>();
    }

    public void add(final Field<?> fld) {
        fields.put(fld.getName(), fld);
    }

    public Field<?> getField(final String name) {
        return fields.get(name);
    }

}
