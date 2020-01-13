package com.noharms.exercises.codewars;

import java.util.*;

public class ExerciseStringPermutationsDictionaryOrdering {

  //------------------------------------------------------------ Improved Algo
  public static String getMiddleElementOfPermutationsInDictionaryOrderingImproved(String input) {
    StringBuilder midPermutation = new StringBuilder();
    int n = input.length();
    List<Character> chars = new ArrayList<>() { // lack of conversion
      {
        for (Character c : input.toCharArray()) {
          add(c);
        }
      }
    };
    chars.sort(Character::compareTo);
    if (n % 2 != 0) {
      midPermutation.append(chars.get(n / 2));
      chars.remove(n / 2);
      --n;
    }
    // now even number of chars remain
    midPermutation.append(chars.get(n / 2 - 1));
    chars.remove(n / 2 - 1);
    --n;
    chars.sort(Character::compareTo);
    for (int i = n - 1; i >= 0; --i) {
      midPermutation.append(chars.get(i));
    }
    return midPermutation.toString();
  }


  //------------------------------------------------------------- Brute-Force Algo
  public static String getMiddleElementOfPermutationsInDictionaryOrdering(String input) {
    List<String> permutations = findStringPermutations(input);
    permutations.sort(String::compareTo);
    return permutations.get(permutations.size() / 2 - 1);
  }

  private static List<String> findStringPermutations(String input) {
    List<String> permutations = new ArrayList<>();
    StringBuilder currentPermutation = new StringBuilder();
    Set<Character> characters = new HashSet<>() {
      {
        for (int i = 0; i < input.length(); ++i) {
          add(input.charAt(i));
        }
      }
    };
    findStringPermutationsRecursion(permutations, currentPermutation, characters);
    return permutations;
  }

  private static void findStringPermutationsRecursion(List<String> results,
                                                      StringBuilder currentPerm,
                                                      Set<Character> unusedChars) {
    if (unusedChars.size() == 0) {
      results.add(currentPerm.toString());
      return;
    }
    for (Character c : unusedChars) {
      currentPerm.append(c);
      Set<Character> unusedCharsNextRecursion = new HashSet<>(unusedChars);
      unusedCharsNextRecursion.remove(c);
      findStringPermutationsRecursion(results, currentPerm, unusedCharsNextRecursion);
      currentPerm.deleteCharAt(currentPerm.length() - 1);
    }
    return;
  }

}
