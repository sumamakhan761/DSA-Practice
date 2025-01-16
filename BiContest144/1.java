// https://leetcode.com/problems/stone-removal-game/submissions/1509064185/

class Solution {
  public boolean canAliceWin(int n) {
     int pile = n;
     int stones = 10;
      for(int i = 1; i < n; i++){
          if(i%2==0){
           if(pile-stones< 0){
                  return true;
             }      
          }
          else{
              if(pile-stones<0){
                  return false;
              }
          }
          pile = pile-stones;
          stones--;
      }
      return false;
  }
}