package com.jck.comparator;

import com.jck.entity.IntegerArray;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ComparatorTest {
    private static final int[] VALUES_SMALL = {1, 2};
    private static final int[] VALUES_LARGE = {10, 20, 30, 40};
    private static final int[] VALUES_MEDIUM = {5, 10, 15};

    @Test
    void byIdComparator_sortsById() {
        // given
        List<IntegerArray> list = new ArrayList<>();
        list.add(new IntegerArray("id3", "name3", VALUES_SMALL));
        list.add(new IntegerArray("id1", "name1", VALUES_LARGE));
        list.add(new IntegerArray("id2", "name2", VALUES_MEDIUM));
        ByIdComparator comparator = new ByIdComparator();

        // when
        list.sort(comparator);

        // then
        assertEquals("id1", list.get(0).getId());
        assertEquals("id2", list.get(1).getId());
        assertEquals("id3", list.get(2).getId());
    }

    @Test
    void bySizeComparator_sortsBySize() {
        // given
        List<IntegerArray> list = new ArrayList<>();
        list.add(new IntegerArray("id1", "name1", VALUES_LARGE));
        list.add(new IntegerArray("id2", "name2", VALUES_SMALL));
        list.add(new IntegerArray("id3", "name3", VALUES_MEDIUM));
        BySizeComparator comparator = new BySizeComparator();

        // when
        list.sort(comparator);

        // then
        assertEquals(2, list.get(0).getSize());
        assertEquals(3, list.get(1).getSize());
        assertEquals(4, list.get(2).getSize());
    }

    @Test
    void byFirstElementComparator_sortsByFirstElement() {
        // given
        List<IntegerArray> list = new ArrayList<>();
        list.add(new IntegerArray("id1", "name1", VALUES_LARGE));
        list.add(new IntegerArray("id2", "name2", VALUES_SMALL));
        list.add(new IntegerArray("id3", "name3", VALUES_MEDIUM));
        ByFirstElementComparator comparator = new ByFirstElementComparator();

        // when
        list.sort(comparator);

        // then
        assertEquals(1, list.get(0).getFirstElement());
        assertEquals(5, list.get(1).getFirstElement());
        assertEquals(10, list.get(2).getFirstElement());
    }
}