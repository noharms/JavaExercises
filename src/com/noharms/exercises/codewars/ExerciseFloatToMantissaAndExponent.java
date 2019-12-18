package com.noharms.exercises.codewars;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ExerciseFloatToMantissaAndExponent {

  private static final BigDecimal kBD10 = new BigDecimal(10);

  /**
   * task: given a floating point number, convert the number to
   *       a    mantissa * 10^x    representation, written in a string;
   *       the number of digits to be shown in the string is also given as input
   *
   * examples:    convert(0.0000141, 5) = "14100p-9"
   *              convert(0.1410, 5) = "14100p-5"
   *              convert(14.1, 5) = "14100p-3"
   *              convert(1410, 5) = "14100p-1"
   *              convert(141009, 5) = "14100p0"    (if ndigits less than present, function cuts off !)
   *
   * algo:  0. if  |num| > 1 : find number of digits before dot
   *           if  |num| < 1 : find number of 0s before first digit other than 0
   *
   *           -->  can be found using logarithm  log 1000 = 3, log 100 = 2, all nums x
   *                with 3 digits have  2 < log x <= 3
   *
   *                e.g.  213.123 --> log10 (213.123) = 2.3286 ..
   *                      0.2     --> log10 (0.2)  = - 0.7
   *                      0.1     --> log10 (0.1)  = - 1
   *                      0.09    --> log10 (0.09) = - 1.04
   *                      0.01    --> log10 (0.01) = - 2
   *
   *
   *
   *        1. multiply number by 10^p, with p such that the number has ndigits as specified before the dot
   *
   *          --> -p is the exponent for the result
   *          --> that integer is the mantissa
   *
   *
   *
   * @param num
   * @param ndigits
   * @return
   */
  public static String floatToMantissaAndExponent(BigDecimal num, int ndigits) {
    if (num.compareTo(BigDecimal.ZERO) == 0) {
      return "";  // TODO: input 0 is ruled out by task
    }

    // 0. handle negative numbers
    boolean isNegative = false;
    if (num.compareTo(BigDecimal.ZERO) < 0) {
      isNegative = true;
      num = num.abs();
    }
    // 1. find first significant digit
    BigDecimal cpy = new BigDecimal(num.toString());
    int pow10FirstSignificantDigit = 0;
    if (cpy.compareTo(BigDecimal.ONE) >= 0) {   // num >= 1
      while (cpy.compareTo(BigDecimal.ONE) >= 0) {
        ++pow10FirstSignificantDigit;
        cpy = cpy.divide(kBD10);
      }
      // power10 is 1 if 1 < num <= 10  , it is 2 if 10 < num <= 100
    } else if (cpy.compareTo(BigDecimal.ZERO) > 0) {  // 0 < num < 1
      while (cpy.compareTo(BigDecimal.ONE) <= 0) {
        --pow10FirstSignificantDigit;
        cpy = cpy.multiply(kBD10);
      }
    } else {  // num == 0
      // TODO
    }

    // 2. multiply number to have ndigits before dot
    BigInteger numAsInt = null;
    int power10ForString = 0;
    if (ndigits == pow10FirstSignificantDigit) {
      // do nothing
    } else if (ndigits < pow10FirstSignificantDigit) {
      int divisionsNeeded = pow10FirstSignificantDigit - ndigits;
      while (divisionsNeeded > 0) {
        num = num.divide(kBD10);
        --divisionsNeeded;
        --power10ForString;
      }
    } else if (ndigits > pow10FirstSignificantDigit) {
      int multiplicationsNeeded = ndigits - pow10FirstSignificantDigit;
      if (pow10FirstSignificantDigit <= 0) {
        --multiplicationsNeeded;
      }
      while (multiplicationsNeeded > 0) {
        num = num.multiply(kBD10);
        --multiplicationsNeeded;
        ++power10ForString;
      }
    }
    numAsInt = num.toBigInteger();


    // 3. build string
    StringBuilder strbuilder = new StringBuilder();
    strbuilder.append(isNegative ? "-" : "");
    strbuilder.append(numAsInt.toString());
    strbuilder.append("P");
    strbuilder.append(-power10ForString);
    return strbuilder.toString();
  }
}
