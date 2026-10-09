package com.jck.observer.impl;

import com.jck.entity.IntegerArray;
import com.jck.observer.Observer;
import com.jck.warehouse.Warehouse;

public class WarehouseObserver implements Observer {
    private Warehouse warehouse;

    public WarehouseObserver(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public void update(Object source, Object eventData) {
        if (source instanceof IntegerArray) {
            IntegerArray array = (IntegerArray) source;
            warehouse.update(array.getId(), array.getValues());
        }
    }
}