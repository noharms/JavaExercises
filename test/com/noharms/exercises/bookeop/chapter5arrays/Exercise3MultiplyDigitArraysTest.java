package com.noharms.exercises.bookeop.chapter5arrays;

import com.noharms.exercises.utils.DigitList2LongConverter;
import com.noharms.exercises.utils.RandomIntegerArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class Exercise3MultiplyDigitArraysTest {

  @Test
  void multiplyDigitArraysTest() {
    // input
    final int n = 5, m = 3;
    final int max_val = 9;
    RandomIntegerArray randarr1 = new RandomIntegerArray(n, max_val);
    RandomIntegerArray randarr2 = new RandomIntegerArray(m, max_val);

    // actual
    List<Integer> actual = Exercise3MultiplyDigitArrays.multiplyDigitArrays(randarr1.arrList, randarr2.arrList);

    // expected
    Long num1 = DigitList2LongConverter.digitListToLong(randarr1.arrList);
    Long num2 = DigitList2LongConverter.digitListToLong(randarr2.arrList);
    Long result = num1 * num2;
    List<Integer> expected = DigitList2LongConverter.longToDigitList(result);

    Assertions.assertEquals(expected, actual);

    return;
  }

}