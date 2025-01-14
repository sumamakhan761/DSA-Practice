//https://leetcode.com/problems/rearrange-k-substrings-to-form-target-string/submissions/1506877857/


import java.util.*;

class Solution {
  public boolean isPossibleToRearrange(String s, String t, int k) {
      List<String> l1 = new ArrayList<>();
      List<String> l2 = new ArrayList<>();
      int inc = s.length()/k;
      for(int i = 0 ; i < s.length(); i+=inc){
          l1.add(s.substring(i, i+inc));
          l2.add(t.substring(i, i+inc));
      }
      Collections.sort(l1);
      Collections.sort(l2);

      if(l1.equals(l2)){
          return true;
      }
      return false;
  }
}