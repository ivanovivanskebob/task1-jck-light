package com.jck.entity.builder;

import com.jck.entity.IntegerArray;

public class IntegerArrayBuilder {
    private int[] values;

    public IntegerArrayBuilder setValues(int[] values) {
        this.values = values;
        return this;
    }

    public IntegerArray build() {
        return new IntegerArray(values);
    }
}
