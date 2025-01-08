// https://leetcode.com/problems/substring-matching-pattern/description/

class Solution {
  public boolean hasMatch(String s, String p) {
      int startindex = p.indexOf('*');
      String suffix = p.substring(0, startindex);
      String prefix = p.substring(startindex + 1);

      for (int i = 0; i < s.length(); i++) {
          for (int j = i + 1; j <= s.length(); j++) {
              String sub = s.substring(i, j);
              if (sub.length() >= suffix.length() + prefix.length() && sub.startsWith(suffix)
                      && sub.endsWith(prefix)) {
                  return true;
              }
          }
      }

      return false;

  }
}