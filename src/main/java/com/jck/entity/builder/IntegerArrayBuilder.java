package com.jck.entity.builder;

import com.jck.entity.IntegerArray;

public class IntegerArrayBuilder {
    private String id;
    private String name;
    private int[] values;

    public IntegerArrayBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public IntegerArrayBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public IntegerArrayBuilder setValues(int[] values) {
        this.values = values;
        return this;
    }

    public IntegerArray build() {
        return new IntegerArray(id, name, values);
    }
}