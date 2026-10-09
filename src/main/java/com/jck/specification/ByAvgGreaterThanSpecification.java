package com.jck.specification;

import com.jck.entity.IntegerArray;
import com.jck.warehouse.Warehouse;
import com.jck.warehouse.WarehouseEntry;

public class ByAvgGreaterThanSpecification implements Specification<IntegerArray> {
    private double threshold;
    private Warehouse warehouse;

    public ByAvgGreaterThanSpecification(double threshold, Warehouse warehouse) {
        this.threshold = threshold;
        this.warehouse = warehouse;
    }

    @Override
    public boolean isSatisfiedBy(IntegerArray item) {
        WarehouseEntry entry = warehouse.getEntry(item.getId());
        if (entry == null) {
            return false;
        }
        double avg = entry.getAvg();
        return avg > threshold;
    }
}