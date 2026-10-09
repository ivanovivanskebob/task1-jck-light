package com.jck.comparator;

import com.jck.entity.IntegerArray;
import java.util.Comparator;

public class BySizeComparator implements Comparator<IntegerArray> {
    @Override
    public int compare(IntegerArray first, IntegerArray second) {
        int firstSize = first.getSize();
        int secondSize = second.getSize();
        return Integer.compare(firstSize, secondSize);
    }
}