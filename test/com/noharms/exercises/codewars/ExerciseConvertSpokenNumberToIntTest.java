package com.noharms.exercises.codewars;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExerciseConvertSpokenNumberToIntTest {

  @Test
  void test1ConvertSpokenNumberToInt() {
    // input
    String input = "one";

    // expected
    int expected = 1;

    // actual
    int actual = ExerciseConvertSpokenNumberToInt.convertSpokenNumberToInt(input);

    // compare
    Assertions.assertEquals(expected, actual);
  }


  @Test
  void test2ConvertSpokenNumberToInt() {
    // input
    String input = "thirty-three";

    // expected
    int expected = 33;

    // actual
    int actual = ExerciseConvertSpokenNumberToInt.convertSpokenNumberToInt(input);

    // compare
    Assertions.assertEquals(expected, actual);
  }


  @Test
  void test3ConvertSpokenNumberToInt() {
    // input
    String input = "one hundred and fifty-five";

    // expected
    int expected = 155;

    // actual
    int actual = ExerciseConvertSpokenNumberToInt.convertSpokenNumberToInt(input);

    // compare
    Assertions.assertEquals(expected, actual);
  }


  @Test
  void test4ConvertSpokenNumberToInt() {
    // input
    String input = "eight hundred thousand one";

    // expected
    int expected = 800001;

    // actual
    int actual = ExerciseConvertSpokenNumberToInt.convertSpokenNumberToInt(input);

    // compare
    Assertions.assertEquals(expected, actual);
  }


  @Test
  void test5ConvertSpokenNumberToInt() {
    // input
    String input = "sixty thousand two hundred and ninety-five";

    // expected
    int expected = 60295;

    // actual
    int actual = ExerciseConvertSpokenNumberToInt.convertSpokenNumberToInt(input);

    // compare
    Assertions.assertEquals(expected, actual);
  }

}