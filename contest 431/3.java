// https://leetcode.com/problems/maximum-coins-from-k-consecutive-bags/description/

import java.util.*;

class Solution {
  public long maximumCoins(int[][] coins, int k) {
      Arrays.sort(coins, (a, b) -> a[0] - b[0]);
      int n = coins.length;
      long ans = 0, currsum = 0;

      // Case 1: forward
      for (int i = 0, j = 0; i < n; i++) {

          while (j < n && coins[j][1] <= coins[i][0] + k - 1) {
              currsum += (long) (coins[j][1] - coins[j][0] + 1) * coins[j][2];
              j++;
          }

          if (j < n) {
              long partial = (long) Math.max(0, coins[i][0] + k - 1 - coins[j][0] + 1) * coins[j][2];
              ans = Math.max(ans, currsum + partial);
          }

          currsum -= (long) (coins[i][1] - coins[i][0] + 1) * coins[i][2];
      }

      // Case 2: backword
      currsum = 0;
      for (int i = 0, j = 0; i < n; i++) {
          currsum += (long) (coins[i][1] - coins[i][0] + 1) * coins[i][2];

          while (coins[j][1] < coins[i][1] - k + 1) {
              currsum -= (long) (coins[j][1] - coins[j][0] + 1) * coins[j][2];
              j++;
          }

          long partial = (long) Math.max(0, coins[i][1] - k - coins[j][0] + 1) * coins[j][2];
          ans = Math.max(ans, currsum - partial);
      }

      return ans;
  }
}