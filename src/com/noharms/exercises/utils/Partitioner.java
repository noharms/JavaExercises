package com.noharms.exercises.utils;

import java.util.Collections;
import java.util.List;

public class Partitioner {

  public static int partitionAroundRandomPivot(List<Integer> input, int l, int r) {
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
