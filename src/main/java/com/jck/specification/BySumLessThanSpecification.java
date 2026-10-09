package com.jck.specification;

import com.jck.entity.IntegerArray;
import com.jck.warehouse.Warehouse;
import com.jck.warehouse.WarehouseEntry;

public class BySumLessThanSpecification implements Specification<IntegerArray> {
    private int threshold;
    private Warehouse warehouse;

    public BySumLessThanSpecification(int threshold, Warehouse warehouse) {
        this.threshold = threshold;
        this.warehouse = warehouse;
    }

    @Override
    public boolean isSatisfiedBy(IntegerArray item) {
        WarehouseEntry entry = warehouse.getEntry(item.getId());
        if (entry == null) {
            return false;
        }
        int sum = entry.getSum();
        return sum < threshold;
    }
}