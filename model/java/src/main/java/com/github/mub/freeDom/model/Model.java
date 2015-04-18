package com.github.mub.freeDom.model;

import java.util.List;
import java.util.Map;

/**
 * @author mb
 */
public class Model {
    private AnyVersion version;
    /**
     * FIXME merge?
     */
    private List<Model> includes;

    private Map<String, Struct> structs;

    private Map<String, DomEnumType> enums;

    private Map<String, DomMapType<?, ?>> maps;

    private Map<String, BitSetBase> bitsets;


}
