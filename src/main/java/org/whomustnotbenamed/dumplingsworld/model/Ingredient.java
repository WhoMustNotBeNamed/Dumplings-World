package org.whomustnotbenamed.dumplingsworld.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Ingredient {
    private final String id;
    private final String name;
    private final Type type;

    public enum Type {
        MINCED, SPICE, ADDITION,
    }
}
