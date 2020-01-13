package com.noharms.exercises.codewars;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExerciseBowlingScoreTest {

  @Test
  void computeBowlingScoreTest1() {
    // input
    String playerScore = "11 11 11 11 11 11 11 11 11 11";

    // expected
    int expected = 20;

    // actual
    int actual = ExerciseBowlingScore.computeBowlingScore(playerScore);

    // compare
    Assertions.assertEquals(expected, actual);
  }

  @Test
  void computeBowlingScoreTest2() {
    // input
    String playerScore = "X X X X X X X X X XXX";

    // expected
    int expected = 300;

    // actual
    int actual = ExerciseBowlingScore.computeBowlingScore(playerScore);

    // compare
    Assertions.assertEquals(expected, actual);
  }


  @Test
  void computeBowlingScoreTest3() {
    // input
    String playerScore = "00 5/ 4/ 53 33 22 4/ 5/ 45 XXX";

    // expected
    int expected = 115;

    // actual
    int actual = ExerciseBowlingScore.computeBowlingScore(playerScore);

    // compare
    Assertions.assertEquals(expected, actual);
  }



  @Test
  void computeBowlingScoreTest4() {
    // input
    String playerScore = "5/ 4/ 3/ 2/ 1/ 0/ X 9/ 4/ 8/8";

    // expected
    int expected = 150;

    // actual
    int actual = ExerciseBowlingScore.computeBowlingScore(playerScore);

    // compare
    Assertions.assertEquals(expected, actual);
  }




}