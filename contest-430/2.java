// https://leetcode.com/problems/find-the-lexicographically-largest-string-from-the-box-i/


class Solution {
  public String answerString(String word, int numFriends) {
      if (numFriends == 1)
          return word;

      int mlen = word.length() - numFriends + 1;
      String s = "";
      int n = word.length();
      char mchar = word.charAt(0);
      for (int i = 0; i < n; i++) {
          if (word.charAt(i) >= mchar) {
             String prefix = word.substring(i, Math.min(i+mlen, n));
              if (prefix.compareTo(s) > 0) {
                  s = prefix;
                  mchar = word.charAt(i);
              }
          }
      }
      return s;
  }
}