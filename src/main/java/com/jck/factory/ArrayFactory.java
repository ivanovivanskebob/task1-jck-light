package com.jck.factory;

import com.jck.entity.NumberArray;

public abstract class ArrayFactory {
    public abstract NumberArray create(String line);
}
