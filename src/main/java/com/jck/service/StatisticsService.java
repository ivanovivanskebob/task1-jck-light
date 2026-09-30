package com.jck.service;

import com.jck.entity.IntegerArray;

import java.util.Optional;

public interface StatisticsService {
    Optional<Integer> findMin(IntegerArray array);
    Optional<Integer> findMax(IntegerArray array);
    Optional<Integer> calculateSum(IntegerArray array);
    Optional<Double> calculateAverage(IntegerArray array);
}
