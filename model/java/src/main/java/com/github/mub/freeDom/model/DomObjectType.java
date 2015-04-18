package com.github.mub.freeDom.model;

/**
 * Ancestor for all Dom objects: enums, structs and whatever we will implement later
 */
public class DomObjectType implements FreeDomType {
    private final Fqn name;

    public DomObjectType(Fqn name) {
        this.name = name;
    }

    /**
     * Dumb accessor for the name; since {@link Fqn}
     */
    public Fqn getName() {
        return name;
    }
}
