package com.jck.comparator;

import com.jck.entity.IntegerArray;
import java.util.Comparator;

public class ByFirstElementComparator implements Comparator<IntegerArray> {
    @Override
    public int compare(IntegerArray first, IntegerArray second) {
        int firstElement = first.getFirstElement();
        int secondElement = second.getFirstElement();
        return Integer.compare(firstElement, secondElement);
    }
}