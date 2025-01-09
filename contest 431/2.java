// https://leetcode.com/problems/find-mirror-score-of-a-string/description/


import java.util.*;

class Solution {
  public long calculateScore(String s) {
      HashMap<Character, LinkedList<Integer>> map = new HashMap<>();
      long score = 0;

      for (int i = 0; i < s.length(); i++) {
          char current = s.charAt(i);
          char mirrorChar = Mirror(current);

          if (map.containsKey(mirrorChar) && !map.get(mirrorChar).isEmpty()) {
              int j = map.get(mirrorChar).removeLast();
              score += i - j;
          } else {
              map.putIfAbsent(current, new LinkedList<>());
              map.get(current).add(i);
          }
      }

      return score;
  }

  private char Mirror(char ch) {
      return (char) ('z' - (ch - 'a'));
  }
}
