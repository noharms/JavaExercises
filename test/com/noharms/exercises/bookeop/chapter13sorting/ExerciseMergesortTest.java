package com.noharms.exercises.bookeop.chapter13sorting;

import com.noharms.exercises.utils.RandomIntegerArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class ExerciseMergesortTest {

  @Test
  void mergesortTest() {
    // input
    final int n = 10;
    final int max_val = 20;
    RandomIntegerArray randarr = new RandomIntegerArray(n, max_val);

    // actual output
    List<Integer> actual = new ArrayList<>(randarr.arrList);
    ExerciseMergesort.mergesort(actual, 0, n - 1);

    // expected output
    List<Integer> expected = new ArrayList<>(randarr.arrList);
    Collections.sort(expected);

    // compare actual and expected
    Assertions.assertEquals(expected, actual);
  }
}