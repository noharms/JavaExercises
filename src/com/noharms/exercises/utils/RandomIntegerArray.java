package com.noharms.exercises.utils;

import java.util.ArrayList;
import java.util.Random;

public class RandomIntegerArray {

  public ArrayList<Integer> arr = new ArrayList<>();

  public RandomIntegerArray(int n, int max) {
    Random randgenerator = new Random();
    for (int i = 0; i < n; ++i) {
      arr.add(randgenerator.nextInt(max + 1));
    }
  }

}
