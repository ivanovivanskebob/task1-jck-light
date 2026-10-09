package com.jck.warehouse;

import com.jck.entity.IntegerArray;
import com.jck.observer.impl.WarehouseObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class WarehouseTest {
    private static final String ID_1 = "test1";
    private static final String NAME_1 = "name1";
    private static final int[] VALUES = {1, 2, 3, 4, 5};
    private static final int MODIFIED_VALUE = 100;
    private static final int MODIFIED_INDEX = 0;
    private static final int EXPECTED_SUM_AFTER_MODIFICATION = 114;

    private Warehouse warehouse;

    @BeforeEach
    void setUp() {
        warehouse = Warehouse.getInstance();
        warehouse.clear();
    }

    @Test
    void update_validValues_calculatesCorrectly() {
        // given
        // when
        warehouse.update(ID_1, VALUES);

        // then
        WarehouseEntry entry = warehouse.getEntry(ID_1);
        assertNotNull(entry);
        assertEquals(15, entry.getSum());
        assertEquals(3.0, entry.getAvg());
        assertEquals(5, entry.getMax());
        assertEquals(1, entry.getMin());
    }

    @Test
    void update_emptyValues_removesEntry() {
        // given
        warehouse.update(ID_1, VALUES);

        // when
        warehouse.update(ID_1, new int[0]);

        // then
        WarehouseEntry entry = warehouse.getEntry(ID_1);
        assertNull(entry);
    }

    @Test
    void observer_onElementChange_updatesWarehouse() {
        // given
        IntegerArray array = new IntegerArray(ID_1, NAME_1, VALUES.clone());
        WarehouseObserver observer = new WarehouseObserver(warehouse);
        array.getObservableDelegate().addObserver(observer);
        warehouse.update(ID_1, VALUES);

        // when
        array.setElement(MODIFIED_INDEX, MODIFIED_VALUE);

        // then
        WarehouseEntry entry = warehouse.getEntry(ID_1);
        assertEquals(EXPECTED_SUM_AFTER_MODIFICATION, entry.getSum());
    }

    @Test
    void remove_existingId_removesEntry() {
        // given
        warehouse.update(ID_1, VALUES);

        // when
        warehouse.remove(ID_1);

        // then
        WarehouseEntry entry = warehouse.getEntry(ID_1);
        assertNull(entry);
    }

    @Test
    void clear_removesAllEntries() {
        // given
        warehouse.update(ID_1, VALUES);
        warehouse.update("test2", new int[]{10, 20});

        // when
        warehouse.clear();

        // then
        assertNull(warehouse.getEntry(ID_1));
        assertNull(warehouse.getEntry("test2"));
    }
}