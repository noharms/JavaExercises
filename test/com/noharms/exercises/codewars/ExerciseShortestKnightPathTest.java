package com.noharms.exercises.codewars;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExerciseShortestKnightPathTest {

  @Test
  void numberMovesShortestKnightPathTest() {
    // input
    String start = "a1";
    String finish = "c1";

    // expected
    int expected = 2;

    // actual
    int actual = ExerciseShortestKnightPath.numberMovesShortestKnightPath(start, finish);

    // compare
    Assertions.assertEquals(expected, actual);
  }

  @Test
  void numberMovesShortestKnightPathTest2() {
    // input
    String start = "a1";
    String finish = "f7";

    // expected
    int expected = 5;

    // actual
    int actual = ExerciseShortestKnightPath.numberMovesShortestKnightPath(start, finish);

    // compare
    Assertions.assertEquals(expected, actual);
  }


  @Test
  void numberMovesShortestKnightPathTest3() {
    // input
    String start = "g2";
    String finish = "h1";

    // expected
    int expected = 4;

    // actual
    int actual = ExerciseShortestKnightPath.numberMovesShortestKnightPath(start, finish);

    // compare
    Assertions.assertEquals(expected, actual);
  }
}