package com.jck.specification;

public interface Specification<T> {
    boolean isSatisfiedBy(T item);
}