package com.noharms.exercises.codewars;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExerciseStringPermutationsDictionaryOrderingTest {

  @Test
  void getMiddleElementOfPermutationsInDictionaryOrderingImprovedTest1() {
    // input
    String input = "abc";

    // expected
    String expected = "bac";

    // actual
    String actual = ExerciseStringPermutationsDictionaryOrdering.getMiddleElementOfPermutationsInDictionaryOrdering(input);

    // compare
    Assertions.assertEquals(expected, actual);
  }

  @Test
  void getMiddleElementOfPermutationsInDictionaryOrderingTest1() {
    // input
    String input = "abc";

    // expected
    String expected = "bac";

    // actual
    String actual = ExerciseStringPermutationsDictionaryOrdering.getMiddleElementOfPermutationsInDictionaryOrderingImproved(input);

    // compare
    Assertions.assertEquals(expected, actual);
  }
}