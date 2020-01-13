package com.noharms.exercises.codewars;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExerciseLeagueOrderTest {

  @Test
  void computeRanksTest1() {
    // input
    int nTeams = 6;
    int[][] games = {{0, 5, 2, 2},
            {1, 4, 0, 2},
            {2, 3, 1, 2},
            {1, 5, 2, 2},
            {2, 0, 1, 1},
            {3, 4, 1, 1},
            {2, 5, 0, 2},
            {3, 1, 1, 1},
            {4, 0, 2, 0}};

    // actual
    int[] ranking = ExerciseLeagueOrder.computeRanks(nTeams, games);

    // expected
    int[] expected = {4, 4, 6, 3, 1, 2};

    // Compare
    Assertions.assertArrayEquals(expected, ranking);

  }
}