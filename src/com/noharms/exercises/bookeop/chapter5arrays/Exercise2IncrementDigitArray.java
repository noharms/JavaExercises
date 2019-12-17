package com.noharms.exercises.bookeop.chapter5arrays;

import java.util.List;

public class Exercise2IncrementDigitArray {

  /**
   * task: increment an array of digits by 1.
   *
   * example:    1,1,3 --> 1,1,4
   *             1,1,9 --> 1,2,0
   *             9     --> 1, 0
   *
   * algo: assume LSD is rightmost element, check if it is a 9,
   *       so that adding 1 would lead to a carryover;
   *       continue so lang as we have a carryover
   *
   * complexity:  O(n) in time, O(1) in space
   *
   * @param input
   */
  public static void incrementDigitArray(List<Integer> input) {
    int carryOver = 1, pos = input.size() - 1;
    while (carryOver > 0 && pos >= 0) {
      carryOver = 0;
      input.set(pos, input.get(pos) + 1);
      if (input.get(pos) > 9) {
        input.set(pos, 0);
        carryOver = 1;
      }
      --pos;
    }
    if (carryOver > 0) {
      input.add(0, 1);
    }
    return;
  }

}
