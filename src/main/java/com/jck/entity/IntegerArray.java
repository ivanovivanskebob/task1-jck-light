package com.jck.entity;

public class IntegerArray extends NumberArray {
    private int[] values;

    public IntegerArray(String id, String name, int[] values) {
        super(id, name);
        this.values = values;
    }

    public int[] getValues() {
        return values;
    }

    public void setElement(int index, int value) {
        values[index] = value;
        observableDelegate.notifyObservers(this, "elementChanged");
    }

    public int getSize() {
        return values.length;
    }

    public int getFirstElement() {
        if (values.length == 0) {
            return 0;
        }
        return values[0];
    }
}