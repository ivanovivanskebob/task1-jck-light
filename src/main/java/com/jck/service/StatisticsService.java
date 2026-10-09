package com.jck.service;

import com.jck.entity.IntegerArray;

import java.util.OptionalDouble;
import java.util.OptionalInt;

public interface StatisticsService {
    OptionalInt findMin(IntegerArray array);
    OptionalInt findMax(IntegerArray array);
    OptionalInt calculateSum(IntegerArray array);
    OptionalDouble calculateAverage(IntegerArray array);
}
