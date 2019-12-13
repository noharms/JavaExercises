package com.noharms.exercises.bookeop.chapter4primitivetypes;

/**
 * Task: Reverse the digits of an integer.
 *
 * Example:    int x = 1234;
 *             int y = reverseDigits(x);  // --> y = 4321
 *
 * Algorithm:
 *             get digits of x, starting from least significant digit (rightmost),
 *             by doing iterative modulo, and building y with it,
 *             multiplying with 10 for each further iteration
 *
 *             -> O(1) in space, O(n) in time, where n is the
 *             number of digits
 *
 */
public class Exercise1 {

  private static final int BASE = 10;

  public static int reverseDigits(int x) {
    int reverse = 0;
    while (x > 0) {
      reverse *= BASE;
      reverse += x % BASE;
      x /= BASE;
    }
    return reverse;
  }

}
