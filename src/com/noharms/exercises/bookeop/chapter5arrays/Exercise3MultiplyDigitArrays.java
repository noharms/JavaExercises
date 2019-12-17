package com.noharms.exercises.bookeop.chapter5arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Exercise3MultiplyDigitArrays {

  /**
   * task: multiply two digit arrays as though they were integers.
   *
   * example:  {1, 2} * {1, 0} = {1, 2, 0}
   *           {9}    *  {9}   = {8, 1}
   *
   * algo: multiply each digit with each other digit and store results in an intermediate array
   *       at the slot representing the added powers of 10 of the two digits.
   *       e.g. in {1, 2, 3} * {4, 5, 6} both multiplications 1 * 6 and 3 * 4 add up to
   *       the slot with power 2.
   *
   *       finally, make one pass through intermediate result, shifting the parts > 10 at each slot
   *       to the next.
   *
   *  complexity:  O(n*n) in time,
   *
   * @param l1
   * @param l2
   * @return
   */
  public static List<Integer> multiplyDigitArrays(List<Integer> l1, List<Integer> l2) {
    // 0. compute slot by slot in the result, neglecting > 10
    final int n1 = l1.size();
    final int n2 = l2.size();
    int nProduct = n1 + n2;
    int[] digitProducts = new int[nProduct];
    for (int i = 0; i < n1; ++i) {
      int digit1 = l1.get(i);
      int power1 = n1 - 1 - i;
      for (int j = 0; j < n2; ++j) {
        int digit2 = l2.get(j);
        int power2 = n2 - 1 - j;
        digitProducts[nProduct - 1 - power1 - power2] += digit1 * digit2;
      }
    }
    // 1. pass from right to left through intermediate result and process overflow above 10
    for (int k = nProduct - 1; k >= 0; --k) {
      int val = digitProducts[k];
      if (val >= 10) {
        digitProducts[k - 1] += val / 10;
        digitProducts[k] = val % 10;
      }
    }
    // 2. convert to arrayList, ignoring leading 0s
    int starti = 0;
    while (digitProducts[starti] == 0) {++starti;}
    List<Integer> product = new ArrayList<>();
    for (int i =  starti; i < nProduct; ++i) {
      product.add(digitProducts[i]);
    }
    return product;
  }
}
