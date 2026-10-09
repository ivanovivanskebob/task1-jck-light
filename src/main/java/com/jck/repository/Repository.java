package com.jck.repository;

import com.jck.entity.IntegerArray;
import com.jck.specification.Specification;
import java.util.Comparator;
import java.util.List;

public interface Repository {
    void add(IntegerArray array);
    void remove(String id);
    void clear();
    IntegerArray findById(String id);
    List<IntegerArray> findAll();
    List<IntegerArray> findBySpecification(Specification<IntegerArray> spec);
    List<IntegerArray> findAllSorted(Comparator<IntegerArray> comparator);
}