package com.jck.service.impl;

import com.jck.service.SortingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SortingServiceImpl implements SortingService {
    private static final Logger LOGGER = LogManager.getLogger(SortingServiceImpl.class);

    @Override
    public int[] sortBubble(int[] array) {
        if (array == null) {
            return null;
        }
        int length = array.length;
        int[] result = array.clone();

        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                int current = result[j];
                int next = result[j + 1];
                if (current > next) {
                    result[j] = next;
                    result[j + 1] = current;
                }
            }
        }
        LOGGER.info("Array sorted with Bubble sort");
        return result;
    }

    @Override
    public int[] sortQuick(int[] array) {
        if (array == null) {
            return null;
        }
        int[] result = array.clone();
        quickSort(result, 0, result.length - 1);
        LOGGER.info("Array sorted with Quick sort");
        return result;
    }

    private void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            int current = array[j];
            if (current < pivot) {
                i++;
                int temp = array[i];
                array[i] = current;
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }
}
