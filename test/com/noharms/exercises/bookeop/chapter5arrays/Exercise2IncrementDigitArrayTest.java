package com.noharms.exercises.bookeop.chapter5arrays;

import com.noharms.exercises.utils.RandomIntegerArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Exercise2IncrementDigitArrayTest {

  @Test
  void incrementDigitArrayTestRandom() {
    // input
    final int n = 10;
    final int max_val = 20;
    RandomIntegerArray randarr = new RandomIntegerArray(n, max_val);
    randarr.toRandomDigitArray();

    // actual output
    List<Integer> actual = new ArrayList<>(randarr.arrList);
    Exercise2IncrementDigitArray.incrementDigitArray(actual);

    // expected output
    Long num = digitListToLong(randarr.arrList);
    ++num;
    List<Integer> expected = longToDigitList(num);

    // compare actual and expected
    Assertions.assertEquals(expected, actual);
  }


  @Test
  void incrementDigitArrayTest9s() {
    // input
    final int n = 10;
    List<Integer> input = new ArrayList<>(Arrays.asList(9, 9, 9, 9));

    // actual output
    List<Integer> actual = new ArrayList<>(input);
    Exercise2IncrementDigitArray.incrementDigitArray(actual);

    // expected output
    Long num = digitListToLong(input);
    ++num;
    List<Integer> expected = longToDigitList(num);

    // compare actual and expected
    Assertions.assertEquals(expected, actual);
  }

  /**
   * Assuming LSD is rightmost (last) element in list.
   *
   * @param digitList - list of digits
   * @return
   */
  private Long digitListToLong(List<Integer> digitList) {
    long result = 0;
    for (int i : digitList) {
      result *= 10;
      result += digitList.get(i);
    }
    return result;
  }

  /**
   * Assuming LSD is rightmost (last) element in list.
   *
   * @param num - long to convert
   * @return
   */
  private List<Integer> longToDigitList(Long num) {
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