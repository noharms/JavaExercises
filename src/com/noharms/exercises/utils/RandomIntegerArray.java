package com.noharms.exercises.utils;

import java.util.ArrayList;
import java.util.Random;

public class RandomIntegerArray {

  private int nelems;
  private int maxNumAllowed;
  public final ArrayList<Integer> arrList = new ArrayList<>();


  public RandomIntegerArray(int n, int max) {
    nelems = n;
    maxNumAllowed = max;
    fillArrayListAtRandom();
  }

  private void fillArrayListAtRandom() {
    Random randgenerator = new Random();
    for (int i = 0; i < nelems; ++i) {
      arrList.add(randgenerator.nextInt(maxNumAllowed + 1));
    }
  }

  public void toRandomDigitArray() {
    for (int i = 0; i < arrList.size(); ++i) {
      arrList.set(i, arrList.get(i) % 10);
    }
    while (!arrList.isEmpty() && arrList.get(0).equals(0)) {
      arrList.remove(0);
    }
    if (arrList.isEmpty()) {
      fillArrayListAtRandom();
      toRandomDigitArray();
    }
    nelems = arrList.size();
    maxNumAllowed = 9;
    return;
  }

}
