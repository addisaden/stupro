package sorting;

import java.util.EnumSet;

import static sorting.Algorithm.*;

public class Main {

    public static void main(String[] args) {
        int[] a = { 7, 1, 6, 2, 3, 8, 4, 5 };
        int[] sizes = { 100, 1000, 10000, 100000 };

        TestsOfSortingAlgorithms.traceOf(
                EnumSet.of(INSERTION_SORT, SELECTION_SORT, BUBBLE_SORT), a);

        TestsOfSortingAlgorithms.performanceOf(
                EnumSet.allOf(Algorithm.class), sizes);
    }
}