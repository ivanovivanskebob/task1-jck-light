package com.jck.entity;

public class IntegerArray extends NumberArray {
    private final int[] values;

    public IntegerArray(int[] values) {
        this.values = values;
    }

    public int[] getValues() {
        return values;
    }
}
