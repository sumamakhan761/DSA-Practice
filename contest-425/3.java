// https://leetcode.com/problems/minimum-array-sum/submissions/1508017960/

class Solution {
  public int minArraySum(int[] nums, int k, int op1, int op2) {

      int n = nums.length;
      int[][][] dp = new int[n + 1][op1 + 1][op2 + 1];
      
      // Initialize the DP table
      for (int i = 0; i <= n; i++) {
          for (int j = 0; j <= op1; j++) {
              Arrays.fill(dp[i][j], Integer.MAX_VALUE);
          }
      }
      
      // Base case: When idx == n, the sum is 0
      for (int j = 0; j <= op1; j++) {
          for (int l = 0; l <= op2; l++) {
              dp[n][j][l] = 0;
          }
      }
      
      // Fill the DP table iteratively
      for (int idx = n - 1; idx >= 0; idx--) {
          for (int o1 = 0; o1 <= op1; o1++) {
              for (int o2 = 0; o2 <= op2; o2++) {
                  int ans = Integer.MAX_VALUE;
                  
                  // Case-1: Only op1
                  if (o1 > 0) {
                      int op1Ans = ((int) Math.ceil(nums[idx] / 2.00)) + dp[idx + 1][o1 - 1][o2];
                      ans = Math.min(ans, op1Ans);
                  }
                  
                  // Case-2: Only op2
                  if (o2 > 0 && nums[idx] >= k) {
                      int op2Ans = (nums[idx] - k) + dp[idx + 1][o1][o2 - 1];
                      ans = Math.min(ans, op2Ans);
                  }
                  
                  // Case-3 and Case-4: Both operations
                  if (o1 > 0 && o2 > 0) {
                      // Case-3: op1 then op2
                      int afterOp1 = (int) Math.ceil(nums[idx] / 2.00);
                      if (afterOp1 >= k) {
                          int op1Aop2 = (afterOp1 - k) + dp[idx + 1][o1 - 1][o2 - 1];
                          ans = Math.min(ans, op1Aop2);
                      }

                      // Case-4: op2 then op1
                      if (nums[idx] >= k) {
                          int op2Aop1 = (int) Math.ceil((nums[idx] - k) / 2.00) + dp[idx + 1][o1 - 1][o2 - 1];
                          ans = Math.min(ans, op2Aop1);
                      }
                  }
                  
                  // Case-5: No operation
                  int noOp = nums[idx] + dp[idx + 1][o1][o2];
                  ans = Math.min(ans, noOp);    
                  dp[idx][o1][o2] = ans;
              }
          }
      }
      return dp[0][op1][op2];
  }
}