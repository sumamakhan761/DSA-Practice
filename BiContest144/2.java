// https://leetcode.com/problems/shift-distance-between-two-strings/submissions/1509143816/

class Solution {
  public long shiftDistance(String s, String t, int[] nextCost, int[] previousCost) {
    long ans = 0 ;
    for(int i = 0 ; i< s.length();i++){
      int j = s.charAt(i)-'a';
      long sumnext = 0;
      long sumprev = 0;
      while(j!=t.charAt(i)-'a') {
          sumnext+=nextCost[j];
          j++;
          j = j%26;
      }
       j = s.charAt(i)-'a';
      while(j!=t.charAt(i)-'a') {
          sumprev+=previousCost[j];
          j--;
          if(j == -1){
              j =25;
          }
      } 
      ans += Math.min(sumnext,sumprev);
    }

    return ans;
  }
}