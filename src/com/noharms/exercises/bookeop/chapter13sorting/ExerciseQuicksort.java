package com.noharms.exercises.bookeop.chapter13sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExerciseQuicksort {

  public static void quicksort(List<Integer> input, int l, int r) {
    if (l >= r) {
      return;
    }
    int pivot_idx = partitionAroundRandomPivot(input, l, r);
    int n = r - l + 1;
    int nLeft = (pivot_idx - 1 - l) + 1;
    int nRght = n - nLeft - 1;
    quicksort(input.subList(l, pivot_idx), 0, nLeft - 1);
    quicksort(input.subList(pivot_idx + 1, r + 1), 0, nRght - 1);
    return;
  }

  private static int partitionAroundRandomPivot(List<Integer> input, int l, int r) {
    int piv_val = input.get(r);
    int n_less = 0, n_gtr = 0;
    while (l + n_less + n_gtr < r) {
      int val = input.get(l + n_less);
      if (val <= piv_val) {
        ++n_less;
      } else { // val > piv_val
        ++n_gtr;
        Collections.swap(input, l + n_less, r - n_gtr);
      }
    }
    int piv_idx = l + n_less;
    Collections.swap(input, piv_idx, r);
    return piv_idx;
  }

}
