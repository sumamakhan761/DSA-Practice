// https://leetcode.com/problems/longest-subsequence-with-decreasing-adjacent-difference/description/

class Solution {
  public int longestSubsequence(int[] nums) {
      int n = nums.length;
      // Reverse the array
      for (int i = 0; i < n / 2; i++) {
          int temp = nums[i];
          nums[i] = nums[n - 1 - i];
          nums[n - 1 - i] = temp;
      }

      // Create and initialize pre array
      int[][] pre = new int[n][301];
      for (int i = 0; i < n; i++) {
          for (int j = 0; j <= 300; j++) {
              pre[i][j] = -1;
          }
      }

      // Fill pre array
      for (int i = 1; i < n; i++) {
          for (int j = 0; j <= 300; j++) {
              pre[i][j] = pre[i - 1][j];
          }
          pre[i][nums[i - 1]] = i - 1;
      }

      // Create and initialize dp array
      int[][] dp = new int[n][301];

      // Fill dp array
      for (int i = 1; i < n; i++) {
          for (int j = 0; j <= 300; j++) {
              // Check for first requirement
              int req1 = nums[i] - j;
              if (req1 > 0 && pre[i][req1] != -1) {
                  dp[i][j] = Math.max(dp[i][j], dp[pre[i][req1]][j] + 1);
              }

              // Check for second requirement
              int req2 = nums[i] + j;
              if (req2 <= 300 && pre[i][req2] != -1) {
                  dp[i][j] = Math.max(dp[i][j], dp[pre[i][req2]][j] + 1);
              }
          }

          // Update dp values
          for (int j = 1; j <= 300; j++) {
              dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
          }
      }

      // Find the maximum answer
      int ans = 1;
      for (int i = 0; i < n; i++) {
          ans = Math.max(ans, dp[i][300] + 1);
      }

      return ans;
  }
}