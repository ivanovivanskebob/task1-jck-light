package com.jck.specification;

import com.jck.entity.IntegerArray;

public class ByCountGreaterThanSpecification implements Specification<IntegerArray> {
    private int threshold;

    public ByCountGreaterThanSpecification(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public boolean isSatisfiedBy(IntegerArray item) {
        int size = item.getSize();
        return size > threshold;
    }
}