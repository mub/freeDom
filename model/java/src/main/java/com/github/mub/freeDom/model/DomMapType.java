package com.github.mub.freeDom.model;

import java.util.Map;

/**
 * @author michaelb
 */
public class DomMapType<K, V> extends DomObjectType {

    private StdType keyType;
    private StdType valType;
    private Map<K, Mapping<K, V>> mappings;

    public DomMapType(Fqn name) { super(name); }
}
