package com.jck.specification;

import com.jck.entity.IntegerArray;
import com.jck.warehouse.Warehouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SpecificationTest {
    private static final int[] VALUES_1 = {1, 2, 3};
    private static final int[] VALUES_2 = {10, 20, 30};

    private Warehouse warehouse;

    @BeforeEach
    void setUp() {
        warehouse = Warehouse.getInstance();
        warehouse.update("id1", VALUES_1);
        warehouse.update("id2", VALUES_2);
    }

    @Test
    void byId_matchingId_returnsTrue() {
        // given
        IntegerArray array = new IntegerArray("id1", "name1", VALUES_1);
        ByIdSpecification spec = new ByIdSpecification("id1");

        // when
        boolean result = spec.isSatisfiedBy(array);

        // then
        assertTrue(result);
    }

    @Test
    void byId_nonMatchingId_returnsFalse() {
        // given
        IntegerArray array = new IntegerArray("id1", "name1", VALUES_1);
        ByIdSpecification spec = new ByIdSpecification("id2");

        // when
        boolean result = spec.isSatisfiedBy(array);

        // then
        assertFalse(result);
    }

    @Test
    void bySumGreaterThan_sumAboveThreshold_returnsTrue() {
        // given
        IntegerArray array = new IntegerArray("id2", "name2", VALUES_2);
        BySumGreaterThanSpecification spec = new BySumGreaterThanSpecification(10, warehouse);

        // when
        boolean result = spec.isSatisfiedBy(array);

        // then
        assertTrue(result);
    }

    @Test
    void bySumGreaterThan_sumBelowThreshold_returnsFalse() {
        // given
        IntegerArray array = new IntegerArray("id1", "name1", VALUES_1);
        BySumGreaterThanSpecification spec = new BySumGreaterThanSpecification(10, warehouse);

        // when
        boolean result = spec.isSatisfiedBy(array);

        // then
        assertFalse(result);
    }

    @Test
    void andSpecification_bothTrue_returnsTrue() {
        // given
        IntegerArray array = new IntegerArray("id2", "name2", VALUES_2);
        ByIdSpecification idSpec = new ByIdSpecification("id2");
        BySumGreaterThanSpecification sumSpec = new BySumGreaterThanSpecification(10, warehouse);
        AndSpecification<IntegerArray> andSpec = new AndSpecification<>(idSpec, sumSpec);

        // when
        boolean result = andSpec.isSatisfiedBy(array);

        // then
        assertTrue(result);
    }

    @Test
    void orSpecification_oneTrue_returnsTrue() {
        // given
        IntegerArray array = new IntegerArray("id1", "name1", VALUES_1);
        ByIdSpecification idSpec = new ByIdSpecification("id1");
        BySumGreaterThanSpecification sumSpec = new BySumGreaterThanSpecification(100, warehouse);
        OrSpecification<IntegerArray> orSpec = new OrSpecification<>(idSpec, sumSpec);

        // when
        boolean result = orSpec.isSatisfiedBy(array);

        // then
        assertTrue(result);
    }

    @Test
    void notSpecification_invertsResult() {
        // given
        IntegerArray array = new IntegerArray("id1", "name1", VALUES_1);
        ByIdSpecification spec = new ByIdSpecification("id1");
        NotSpecification<IntegerArray> notSpec = new NotSpecification<>(spec);

        // when
        boolean result = notSpec.isSatisfiedBy(array);

        // then
        assertFalse(result);
    }

    @Test
    void byCountGreaterThan_countAboveThreshold_returnsTrue() {
        // given
        IntegerArray array = new IntegerArray("id1", "name1", VALUES_1);
        ByCountGreaterThanSpecification spec = new ByCountGreaterThanSpecification(2);

        // when
        boolean result = spec.isSatisfiedBy(array);

        // then
        assertTrue(result);
    }
}