package com.noharms.exercises.bookeop.chapter13sorting;

import java.util.ArrayList;
import java.util.List;

public class ExerciseMergesort {

  /**
   * task: sort input list using mergesort algorithm.
   *
   * algo:  recursively, merge two sorted halves of the array.
   *        a bottom-to-top algo, since we start with first merge
   *        once we reach halves of length 1 or 0
   *
   * complexity: O(n * log n) in time
   *             O(n) in space
   *
   * @param input - array to be sorted
   * @param l - left index of the part that shall be considered
   * @param r - rght index of the part that shall be considered
   */
  public static void mergesort(List<Integer> input, int l, int r) {
    if (l >= r) {
      return;
    }
    int n = (r - l + 1);
    int nLeft = n / 2;     // if n is odd, nLeft has one less than nRght
    int nRght = n - nLeft;
    mergesort(input.subList(l, l + nLeft), 0, nLeft - 1 );
    mergesort(input.subList(l + nLeft, r  + 1), 0, nRght - 1 );
    mergeTwoSortedSublists(input, l, nLeft, r, nRght);
    return;
  }

  private static void mergeTwoSortedSublists(List<Integer> input, int l, int nLeft, int r, int nRght) {
    List<Integer> left = new ArrayList<>(input.subList(l, l + nLeft));
    List<Integer> rght = new ArrayList<>(input.subList(l + nLeft, r + 1));
    int i = 0, j = 0, count = 0;
    while(i < nLeft && j < nRght) {
      int valLeft = left.get(i);
      int valRght = rght.get(j);
      if (valLeft < valRght) {
        input.set(l + count, valLeft);
        ++i;
      } else {
        input.set(l + count, valRght);
        ++j;
      }
      ++count;
    }
    // one of the halves has values left to copy
    while (i < nLeft) {
      input.set(l + count, left.get(i));
      ++i;
      ++count;
    }
    while (j < nRght) {
      input.set(l + count, rght.get(j));
      ++j;
      ++count;
    }
    return;
  }
}
