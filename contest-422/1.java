// https://leetcode.com/submissions/detail/1519610171/
class Solution {
  public boolean isBalanced(String nums) {
     int oddsum = 0 , evensum = 0;
     int digit = 0;
     for(int i = 0 ; i < nums.length() ; i++){
          digit =  nums.charAt(i) - '0';
          if(i%2==0){
              evensum +=digit;
          }else{
              oddsum +=digit;
          }
     }
     return evensum == oddsum;   
  }
}