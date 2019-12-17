package com.noharms.exercises.bookeop.chapter13sorting;

import com.noharms.exercises.utils.Partitioner;

import java.util.List;

public class ExerciseQuicksort {

  public static void quicksort(List<Integer> input, int l, int r) {
    if (l >= r) {
      return;
    }
    int pivot_idx = Partitioner.partitionAroundRandomPivot(input, l, r);
    int n = r - l + 1;
    int nLeft = (pivot_idx - 1 - l) + 1;
    int nRght = n - nLeft - 1;
    quicksort(input.subList(l, pivot_idx), 0, nLeft - 1);
    quicksort(input.subList(pivot_idx + 1, r + 1), 0, nRght - 1);
    return;
  }


}
