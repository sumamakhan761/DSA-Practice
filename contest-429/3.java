// https://leetcode.com/problems/smallest-substring-with-identical-characters-i/description/

class Solution {
  public boolean canTransformAlt(String s, int numOps, int targetLength, char startChar) {
      for (char ch : s.toCharArray()) {
          if (ch != startChar) numOps--;
          startChar = (char)(startChar ^ 1);
      }
      return (numOps >= 0);
  }

  public boolean isPossible(String s, int numOps, int maxLength) {
      if (maxLength == 1) 
          return canTransformAlt(s, numOps, maxLength, '1') || 
                 canTransformAlt(s, numOps, maxLength, '0');

      int repeatCount = 0;
      char lastChar = ' ';
      
      for (char ch : s.toCharArray()) {
          if (ch == lastChar) {
              repeatCount++;
          } else {
              if (lastChar != ' ') {
                  numOps -= repeatCount / (maxLength + 1);
              }
              lastChar = ch;
              repeatCount = 1;
          }
      }
      
      numOps -= repeatCount / (maxLength + 1);
      return (numOps >= 0);
  }

  public int minLength(String s, int numOps) {
      int left = 1, right = s.length(), ans = s.length();
      
      while (left <= right) {
          int mid = left + (right - left) / 2;
          if (isPossible(s, numOps, mid)) {
              ans = mid;
              right = mid - 1;
          } else {
              left = mid + 1;
          }
      }
      return ans;
  }
}