package com.jck.comparator;

import com.jck.entity.IntegerArray;
import java.util.Comparator;

public class ByIdComparator implements Comparator<IntegerArray> {
    @Override
    public int compare(IntegerArray first, IntegerArray second) {
        String firstId = first.getId();
        String secondId = second.getId();
        return firstId.compareTo(secondId);
    }
}