package com.noharms.exercises.codewars;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExerciseSortMixarrayOfIntsAndStringsTest {

  @Test
  void testdbSort() {
    // input
    String[] strs = {"A", "Hallo", "hall", "1", "xyz"};
    Integer[] nums = {5, 2, 10, 11};
    Object[] mixed = new Object[strs.length + nums.length];
    for (int i = 0; i < strs.length; ++i) {
      mixed[i] = (Object)(strs[i]);
    }
    for (int i = 0; i < nums.length; ++i) {
      mixed[strs.length + i] = (Object)nums[i];
    }

    // expected
    Object[] expected = {2, 5, 10, 11, "1", "A", "Hallo", "hall", "xyz"};

    // actual
    Object[] actual = ExerciseSortMixarrayOfIntsAndStrings.dbSort(mixed);

    // compare
    Assertions.assertArrayEquals(expected, actual);
  }

}