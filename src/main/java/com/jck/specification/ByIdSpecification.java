package com.jck.specification;

import com.jck.entity.IntegerArray;

public class ByIdSpecification implements Specification<IntegerArray> {
    private String targetId;

    public ByIdSpecification(String targetId) {
        this.targetId = targetId;
    }

    @Override
    public boolean isSatisfiedBy(IntegerArray item) {
        String itemId = item.getId();
        return itemId.equals(targetId);
    }
}