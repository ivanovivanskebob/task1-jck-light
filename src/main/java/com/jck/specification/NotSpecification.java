package com.jck.specification;

public class NotSpecification<T> implements Specification<T> {
    private Specification<T> spec;

    public NotSpecification(Specification<T> spec) {
        this.spec = spec;
    }

    @Override
    public boolean isSatisfiedBy(T item) {
        return !spec.isSatisfiedBy(item);
    }
}