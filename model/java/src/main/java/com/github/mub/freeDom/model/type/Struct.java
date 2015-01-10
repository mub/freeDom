package com.github.mub.freeDom.model.type;

import com.github.mub.freeDom.model.Field;
import com.github.mub.freeDom.model.Fqn;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Jorge U
 */
public class Struct extends DomObjectType {

    private final List<Field<?>> fields;

    public Struct(Fqn name) {
        super(name);
        fields = new LinkedList<>();
    }

    public void add(final Field<?> fld) {
        fields.add(fld);
    }

}
