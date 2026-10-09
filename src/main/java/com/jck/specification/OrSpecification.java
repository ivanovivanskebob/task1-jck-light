package com.jck.specification;

public class OrSpecification<T> implements Specification<T> {
    private Specification<T> first;
    private Specification<T> second;

    public OrSpecification(Specification<T> first, Specification<T> second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean isSatisfiedBy(T item) {
        boolean firstResult = first.isSatisfiedBy(item);
        if (firstResult) {
            return true;
        }
        return second.isSatisfiedBy(item);
    }
}