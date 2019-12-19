package com.noharms.exercises.codewars;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExerciseSortMixarrayOfIntsAndStrings {

  public static Object[] dbSort(Object[] a){
    List<Integer> nums = new ArrayList<>();
    List<String> strs = new ArrayList<>();
    for (Object o : a) {
      if (o instanceof Integer) {
        nums.add((Integer)o);
      } else {
        strs.add((String)o);
      }
    }
    Collections.sort(nums);
    Collections.sort(strs);
    Object[] result = new Object[nums.size() + strs.size()];
    for (int i = 0; i < nums.size(); ++i) {
      result[i] = (Object)nums.get(i);
    }
    for (int i = 0; i < strs.size(); ++i) {
      result[nums.size() + i] = (Object)strs.get(i);
    }
    return result;
  }

}
