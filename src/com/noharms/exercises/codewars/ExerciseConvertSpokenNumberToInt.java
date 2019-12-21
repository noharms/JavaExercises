package com.noharms.exercises.codewars;

import java.util.AbstractMap;
import java.util.Map;

public class ExerciseConvertSpokenNumberToInt {

  private static final Map<String, Integer> spoken2Integer = Map.ofEntries(
    new AbstractMap.SimpleImmutableEntry<>("zero", 0),
    new AbstractMap.SimpleImmutableEntry<>("one", 1),
    new AbstractMap.SimpleImmutableEntry<>("two", 2),
    new AbstractMap.SimpleImmutableEntry<>("three", 3),
    new AbstractMap.SimpleImmutableEntry<>("four", 4),
    new AbstractMap.SimpleImmutableEntry<>("five", 5),
    new AbstractMap.SimpleImmutableEntry<>("six", 6),
    new AbstractMap.SimpleImmutableEntry<>("seven", 7),
    new AbstractMap.SimpleImmutableEntry<>("eight", 8),
    new AbstractMap.SimpleImmutableEntry<>("nine", 9),
    new AbstractMap.SimpleImmutableEntry<>("ten", 10),
    new AbstractMap.SimpleImmutableEntry<>("eleven", 11),
    new AbstractMap.SimpleImmutableEntry<>("twelve", 12),
    new AbstractMap.SimpleImmutableEntry<>("thirteen", 13),
    new AbstractMap.SimpleImmutableEntry<>("fourteen", 14),
    new AbstractMap.SimpleImmutableEntry<>("fifteen", 15),
    new AbstractMap.SimpleImmutableEntry<>("sixteen", 16),
    new AbstractMap.SimpleImmutableEntry<>("seventeen", 17),
    new AbstractMap.SimpleImmutableEntry<>("eighteen", 18),
    new AbstractMap.SimpleImmutableEntry<>("nineteen", 19),
    new AbstractMap.SimpleImmutableEntry<>("twenty", 20),
    new AbstractMap.SimpleImmutableEntry<>("thirty", 30),
    new AbstractMap.SimpleImmutableEntry<>("forty", 40),
    new AbstractMap.SimpleImmutableEntry<>("fifty", 50),
    new AbstractMap.SimpleImmutableEntry<>("sixty", 60),
    new AbstractMap.SimpleImmutableEntry<>("seventy", 70),
    new AbstractMap.SimpleImmutableEntry<>("eighty", 80),
    new AbstractMap.SimpleImmutableEntry<>("ninety", 90)
  );

  private static final Map<String, Integer> multiplicator2value = Map.ofEntries(
    new AbstractMap.SimpleImmutableEntry<>("hundred", 100),
    new AbstractMap.SimpleImmutableEntry<>("thousand", 1000),
    new AbstractMap.SimpleImmutableEntry<>("million", 1000000)
  );

  /**
   * task: given a spoken number as a string, convert it to an integer.
   *       the final piece may or may not be preceeded by an "and".
   *
   * assumptions: all input valid; maximum input number 10^6
   *
   * example:  "one" -> 1
   *            "twenty-one" -> 21
   *            "eight hundred fifty-three thousand nine hundred and eleven" -> 853911
   *
   * algo:  split spoken number into pieces;
   *        one - ten are special
   *        eleven - nineteen are special
   *        twenty, thirty, forty, ... , ninety are special
   *        rest is built as combination of those
   *
   *        parsing the spokenNumber from right to left allows to accumulate powers of 10
   *        powers of ten: "hundred", "thousand", "million"
   *
   *        one hundred thousand million two hundred ninety thousand five hundred three
   *         = 1 * (10^2 * 10^3 * 10^6)
   *          + 2 * (10^2 * 10^3)
   *          + 90 * (10^3)
   *          + 5 * (10^2)
   *          + 3
   *         = 1000002905003
   *
   *        powers accumulate if next one in left direction is smaller than previous,
   *        otherwise they start over again, e.g. all left of million does not
   *        know of powers that appeared right of million
   *        
   * @param spokenNumber
   * @return
   */
  public static int convertSpokenNumberToInt(String spokenNumber) {
    String[] pieces = spokenNumber.split("\\s|-", -1);
    int hundreds = 0;
    int result = 0;
    int multiplicator = 1;
    for (int i = pieces.length - 1; i >= 0; --i) { // go from right to left to cumulate multiplicators correctly
      String piece = pieces[i];
      if (piece.isEmpty() || piece.compareTo("and") == 0) {continue;}
      if (multiplicator2value.containsKey(piece)) {
        if (multiplicator > multiplicator2value.get(piece)) {
          // e.g. one hundred million --> one needs factor 10^2 * 10^6
          multiplicator *= multiplicator2value.get(piece);
        } else {
          // e.g. one million two hundred -> one needs factor 10^6
          multiplicator = multiplicator2value.get(piece);
        }
        if (piece.compareTo("thousand") == 0) {
          result += hundreds;
          hundreds = 0;
        } else if (piece.compareTo("million") == 0) {
          result += hundreds;
          hundreds = 0;
        }
      } else {
        hundreds += spoken2Integer.get(piece) * multiplicator;
      }
    }
    result += hundreds;
    return result;
  }

}
