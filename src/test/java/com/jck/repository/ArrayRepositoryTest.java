package com.jck.repository;

import com.jck.entity.IntegerArray;
import com.jck.exception.EntityNotFoundException;
import com.jck.specification.BySumGreaterThanSpecification;
import com.jck.warehouse.Warehouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ArrayRepositoryTest {
    private static final String ID_1 = "id1";
    private static final String ID_2 = "id2";
    private static final String ID_3 = "id3";
    private static final String NAME_1 = "name1";
    private static final String NAME_2 = "name2";
    private static final String NAME_3 = "name3";
    private static final int[] VALUES_1 = {1, 2, 3};
    private static final int[] VALUES_2 = {10, 20, 30};
    private static final int[] VALUES_3 = {5, 10, 15};

    private ArrayRepository repository;
    private Warehouse warehouse;

    @BeforeEach
    void setUp() {
        repository = ArrayRepository.getInstance();
        warehouse = Warehouse.getInstance();
        repository.clear();
    }

    @Test
    void add_validArray_addsToRepository() {
        // given
        IntegerArray array = new IntegerArray(ID_1, NAME_1, VALUES_1);

        // when
        repository.add(array);

        // then
        IntegerArray found = repository.findById(ID_1);
        assertEquals(array, found);
    }

    @Test
    void add_validArray_updatesWarehouse() {
        // given
        IntegerArray array = new IntegerArray(ID_1, NAME_1, VALUES_1);

        // when
        repository.add(array);

        // then
        assertNotNull(warehouse.getEntry(ID_1));
        assertEquals(6, warehouse.getEntry(ID_1).getSum());
    }

    @Test
    void findById_existingId_returnsArray() {
        // given
        IntegerArray array = new IntegerArray(ID_1, NAME_1, VALUES_1);
        repository.add(array);

        // when
        IntegerArray found = repository.findById(ID_1);

        // then
        assertNotNull(found);
        assertEquals(ID_1, found.getId());
    }

    @Test
    void findById_nonExistingId_throwsException() {
        // given
        // when & then
        assertThrows(EntityNotFoundException.class, () -> {
            repository.findById("nonexistent");
        });
    }

    @Test
    void remove_existingId_removesFromArray() {
        // given
        IntegerArray array = new IntegerArray(ID_1, NAME_1, VALUES_1);
        repository.add(array);

        // when
        repository.remove(ID_1);

        // then
        assertThrows(EntityNotFoundException.class, () -> {
            repository.findById(ID_1);
        });
    }

    @Test
    void remove_existingId_removesFromWarehouse() {
        // given
        IntegerArray array = new IntegerArray(ID_1, NAME_1, VALUES_1);
        repository.add(array);

        // when
        repository.remove(ID_1);

        // then
        assertEquals(null, warehouse.getEntry(ID_1));
    }

    @Test
    void findBySpecification_sumGreaterThan_returnsMatching() {
        // given
        repository.add(new IntegerArray(ID_1, NAME_1, VALUES_1));
        repository.add(new IntegerArray(ID_2, NAME_2, VALUES_2));
        repository.add(new IntegerArray(ID_3, NAME_3, VALUES_3));
        BySumGreaterThanSpecification spec = new BySumGreaterThanSpecification(10, warehouse);

        // when
        List<IntegerArray> result = repository.findBySpecification(spec);

        // then
        assertEquals(2, result.size());
    }

    @Test
    void findAll_returnsAllArrays() {
        // given
        repository.add(new IntegerArray(ID_1, NAME_1, VALUES_1));
        repository.add(new IntegerArray(ID_2, NAME_2, VALUES_2));

        // when
        List<IntegerArray> result = repository.findAll();

        // then
        assertEquals(2, result.size());
    }

    @Test
    void clear_removesAllArrays() {
        // given
        repository.add(new IntegerArray(ID_1, NAME_1, VALUES_1));
        repository.add(new IntegerArray(ID_2, NAME_2, VALUES_2));

        // when
        repository.clear();

        // then
        List<IntegerArray> result = repository.findAll();
        assertEquals(0, result.size());
    }
}