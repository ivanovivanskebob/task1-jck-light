package com.jck.warehouse;

import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    private static Warehouse instance;
    private Map<String, WarehouseEntry> entries;

    private Warehouse() {
        entries = new HashMap<>();
    }

    public static Warehouse getInstance() {
        if (instance == null) {
            instance = new Warehouse();
        }
        return instance;
    }

    public void update(String arrayId, int[] values) {
        if (values == null || values.length == 0) {
            entries.remove(arrayId);
            return;
        }

        int sum = 0;
        int max = values[0];
        int min = values[0];

        for (int value : values) {
            sum += value;
            if (value > max) {
                max = value;
            }
            if (value < min) {
                min = value;
            }
        }

        double avg = (double) sum / values.length;
        WarehouseEntry entry = new WarehouseEntry(sum, avg, max, min);
        entries.put(arrayId, entry);
    }

    public WarehouseEntry getEntry(String arrayId) {
        return entries.get(arrayId);
    }

    public void remove(String arrayId) {
        entries.remove(arrayId);
    }

    public void clear() {
        entries.clear();
    }

    public Map<String, WarehouseEntry> getAllEntries() {
        return new HashMap<>(entries);
    }
}