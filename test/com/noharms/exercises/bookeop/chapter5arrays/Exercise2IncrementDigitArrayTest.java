package com.noharms.exercises.bookeop.chapter5arrays;

import com.noharms.exercises.utils.DigitList2LongConverter;
import com.noharms.exercises.utils.RandomIntegerArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.noharms.exercises.utils.DigitList2LongConverter.longToDigitList;

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
    Long num = DigitList2LongConverter.digitListToLong(randarr.arrList);
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
    Long num = DigitList2LongConverter.digitListToLong(input);
    ++num;
    List<Integer> expected = longToDigitList(num);

    // compare actual and expected
    Assertions.assertEquals(expected, actual);
  }


}