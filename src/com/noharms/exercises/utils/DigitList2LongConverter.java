package com.noharms.exercises.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DigitList2LongConverter {

  /**
   * Assuming LSD is rightmost (last) element in list.
   *
   * @param digitList - list of digits
   * @return
   */
  public static Long digitListToLong(List<Integer> digitList) {
    long result = 0;
    for (int d : digitList) {
      result *= 10;
      result += d;
    }
    return result;
  }

  /**
   * Assuming LSD is rightmost (last) element in list.
   *
   * @param num - long to convert
   * @return
   */
  public static List<Integer> longToDigitList(Long num) {
    List<Integer> digitList = new ArrayList<>();
    while (num > 0) {
      Integer digit = (int)(num % 10);
      digitList.add(digit);
      num /= 10;
    }
    Collections.reverse(digitList);
    return digitList;
  }

}
