
// https://leetcode.com/problems/maximum-subarray-with-equal-products/

class Solution {
  long gcd(long a , long b) {
      return (b==0)?a : gcd(b,a%b);
  }

  long lcm (long a , long b){
      return (a*b)/gcd(a,b);
  }
  public int maxLength(int[] nums) {
      int ans = 0;
      for(int i = 0 ; i < nums.length; i++){
          long gcdVal = nums[i], lcmVal = nums[i], prod =  nums[i];
          for(int j = i+1 ; j < nums.length; j++){
              prod = prod * (long)nums[j];
              gcdVal =gcd(gcdVal, nums[j]); 
              lcmVal =lcm(lcmVal, nums[j]);
              
              if(prod >100000000000L) break;

              if(prod == gcdVal * lcmVal){
                  ans = Math.max(ans , j-i+1);
              }
          }
      }
      return ans;
  }
}