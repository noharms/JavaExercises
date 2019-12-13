package com.noharms.exercises.bookeop.chapter4primitivetypes;


/**
 * task: check if given integer is a palindromic number.
 *
 * example:   isPalindrome(12321) -> true
 *            isPalindrome(1) -> true
 *            isPalindrome(12) -> false
 *
 * algorithm: create an aux variable and fill it with
 *            reversed digits. then go through digits
 *            from LSD to MSD using the modulo operator
 *            and compare the digits
 *
 *            -> if all are equal, number is palindrome,
 *               else, is not
 *
 * complexity:  no space requirements, O(1) space
 *              visit each digit once, O(n) time, with n number of digits
 *
 *              (best case O(1) in time, if early failure detected)
 */
public class Exercise2 {

  public static boolean isPalindromicInteger(int x) {
    int reversed = Exercise1.reverseDigits(x);
    while (x > 0) {
      if (x % 10 != reversed % 10) {
        return false;
      }
      x /= 10;
      reversed /= 10;
    }
    return true;
  }

}
