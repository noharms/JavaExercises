package com.noharms.exercises.bookeop.chapter13sorting;

import java.util.Collections;
import java.util.List;

public class ExerciseBubblesort {

  /**
   *
   * Task: sort a list using bubblesort algorithm
   *
   * Algo:  move the largest element to the back of the array; repeat n times
   *
   * Complexity: O(n*n) in time (in best case, worst case and average case)
   *             O(1) in space
   * @param input - array to be sorted
   * @param l - left index of the part that shall be considered
   * @param r - rght index of the part that shall be considered
   */
  public static void bubblesort(List<Integer> input, int l, int r) {
    for (int i = l; i < r; ++i) {
      for (int j = l + 1; j <= r - i; ++j) {
        if (input.get(j) < input.get(j - 1)) {
          Collections.swap(input, j, j - 1);
        }
      }
    }
    return;
  }

}
