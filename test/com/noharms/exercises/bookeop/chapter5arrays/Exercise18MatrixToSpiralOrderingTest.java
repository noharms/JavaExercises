package com.noharms.exercises.bookeop.chapter5arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Exercise18MatrixToSpiralOrderingTest {

  @Test
  void toListInSpiralOrderTest() {
    int[][] input = {
            {1, 2, 3},
            {8, 9, 4},
            {7, 6, 5}
    };

    // expected
    List<Integer> expected = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

    // actual
    List<Integer> actual = Exercise18MatrixToSpiralOrdering.toListInSpiralOrder(input);

    // compare
    Assertions.assertEquals(expected, actual);

    return;
  }
}