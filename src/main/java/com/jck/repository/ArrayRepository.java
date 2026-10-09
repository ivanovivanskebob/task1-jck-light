package com.jck.repository;

import com.jck.entity.IntegerArray;
import com.jck.exception.EntityNotFoundException;
import com.jck.observer.Observer;
import com.jck.observer.impl.WarehouseObserver;
import com.jck.specification.Specification;
import com.jck.warehouse.Warehouse;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArrayRepository implements Repository {
    private static ArrayRepository instance;
    private Map<String, IntegerArray> arrays;
    private Observer warehouseObserver;

    private ArrayRepository() {
        arrays = new HashMap<>();
        warehouseObserver = new WarehouseObserver(Warehouse.getInstance());
    }

    public static ArrayRepository getInstance() {
        if (instance == null) {
            instance = new ArrayRepository();
        }
        return instance;
    }

    @Override
    public void add(IntegerArray array) {
        String id = array.getId();
        arrays.put(id, array);
        Warehouse.getInstance().update(id, array.getValues());
        array.getObservableDelegate().addObserver(warehouseObserver);
    }

    @Override
    public void remove(String id) {
        IntegerArray array = arrays.remove(id);
        if (array != null) {
            array.getObservableDelegate().removeObserver(warehouseObserver);
            Warehouse.getInstance().remove(id);
        }
    }

    @Override
    public void clear() {
        for (String id : new ArrayList<>(arrays.keySet())) {
            remove(id);
        }
    }

    @Override
    public IntegerArray findById(String id) {
        IntegerArray array = arrays.get(id);
        if (array == null) {
            throw new EntityNotFoundException("Array with id " + id + " not found");
        }
        return array;
    }

    @Override
    public List<IntegerArray> findAll() {
        return new ArrayList<>(arrays.values());
    }

    @Override
    public List<IntegerArray> findBySpecification(Specification<IntegerArray> spec) {
        List<IntegerArray> result = new ArrayList<>();
        for (IntegerArray array : arrays.values()) {
            boolean satisfied = spec.isSatisfiedBy(array);
            if (satisfied) {
                result.add(array);
            }
        }
        return result;
    }

    @Override
    public List<IntegerArray> findAllSorted(Comparator<IntegerArray> comparator) {
        List<IntegerArray> result = new ArrayList<>(arrays.values());
        result.sort(comparator);
        return result;
    }
}