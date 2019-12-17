package com.noharms.exercises.bookeop.chapter5arrays;

import java.util.ArrayList;
import java.util.List;

public class Exercise18MatrixToSpiralOrdering {

  /**
   * task:  given a 2d matrix with nxn elements,  produce a sequence of elements
   *        in spiral ordering
   *
   * example:  4  6  3
   *           1  2  5    ->    4, 6, 3, 5, 1, 3, 4, 1, 2
   *           4  3  1
   *
   *  algo:   work through matrix layer by layer from outwards inwards.
   *          in each layer use 4 loops, one for each side
   *
   *
   *  complexity: O(n*n) in time, O(n) in space for result list
   *
   *  note: assume matrix is of nxn dimensions.
   *
   * @param mat
   * @return
   */
  public static List<Integer> toListInSpiralOrder(int[][] mat) {
    List<Integer> list = new ArrayList<>();
    int n = mat.length;
    int nLayers = (int) Math.ceil((n / 2.0));
    for (int i = 0; i < nLayers; ++i) {
      for (int j = i; j < n - i - 1; ++j) {
        list.add(mat[i][j]);
      }
      for (int j = i; j < n - i - 1; ++j) {
        list.add(mat[j][n - 1 - i]);
      }
      for (int j = i; j < n - i - 1; ++j) {
        list.add(mat[n - 1 - i][n - 1 - j]);
      }
      for (int j = i; j < n - i - 1; ++j) {
        list.add(mat[n - 1 - j][i]);
      }
      if (i == nLayers - 1 && n % 2 == 1) {
        list.add(mat[nLayers - 1][nLayers - 1]);
      }
    }
    return list;
  }
}
