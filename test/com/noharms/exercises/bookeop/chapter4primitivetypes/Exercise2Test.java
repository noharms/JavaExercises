package com.noharms.exercises.bookeop.chapter4primitivetypes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Exercise2Test {

  @Test
  void isPalindromicIntegerTest1() {
    int input = 12321;
    boolean actualOutput = Exercise2.isPalindromicInteger(input);
    final boolean expectedOutput = true;
    Assertions.assertEquals(expectedOutput, actualOutput);
  }

  @Test
  void isPalindromicIntegerTest2() {
    int input = 1;
    boolean actualOutput = Exercise2.isPalindromicInteger(input);
    final boolean expectedOutput = true;
    Assertions.assertEquals(expectedOutput, actualOutput);
  }

  @Test
  void isPalindromicIntegerTest3() {
    int input = 12;
    boolean actualOutput = Exercise2.isPalindromicInteger(input);
    final boolean expectedOutput = false;
    Assertions.assertEquals(expectedOutput, actualOutput);
  }

}