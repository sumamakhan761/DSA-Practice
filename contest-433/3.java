// https://leetcode.com/submissions/detail/1515281826/


public class Solution {
  public long minCost(int n, int[][] cost) {
      int k = n / 2; // Number of pairs
      int[] mirror = new int[k];
      for (int i = 0; i < k; i++) {
          mirror[i] = n - 1 - i;
      }

      // DP arrays: previous and current
      long[][] previous_dp = new long[3][3];
      long[][] current_dp = new long[3][3];
      final long INF = Long.MAX_VALUE / 2;

      // Initialize previous_dp for the first pair
      for (int c1 = 0; c1 < 3; c1++) {
          for (int c2 = 0; c2 < 3; c2++) {
              if (c1 != c2) {
                  previous_dp[c1][c2] = (long) cost[0][c1] + cost[mirror[0]][c2];
              } else {
                  previous_dp[c1][c2] = INF;
              }
          }
      }

      // Iterate through each pair from the second pair to the last
      for (int i = 1; i < k; i++) {
          // Reset current_dp
          for (long[] row : current_dp) {
              Arrays.fill(row, INF);
          }

          for (int prev_c1 = 0; prev_c1 < 3; prev_c1++) {
              for (int prev_c2 = 0; prev_c2 < 3; prev_c2++) {
                  if (previous_dp[prev_c1][prev_c2] == INF)
                      continue;

                  for (int c1 = 0; c1 < 3; c1++) {
                      for (int c2 = 0; c2 < 3; c2++) {
                          if (c1 == c2)
                              continue; // Pair colors must be different
                          if (c1 == prev_c1 || c2 == prev_c2)
                              continue; // Adjacent constraints

                          long newCost = previous_dp[prev_c1][prev_c2] + cost[i][c1] + cost[mirror[i]][c2];
                          if (newCost < current_dp[c1][c2]) {
                              current_dp[c1][c2] = newCost;
                          }
                      }
                  }
              }
          }

          // Swap current_dp and previous_dp for next iteration
          long[][] temp = previous_dp;
          previous_dp = current_dp;
          current_dp = temp;
      }

      // Find the minimum cost in the last pair
      long minCost = INF;
      for (int c1 = 0; c1 < 3; c1++) {
          for (int c2 = 0; c2 < 3; c2++) {
              if (previous_dp[c1][c2] < minCost) {
                  minCost = previous_dp[c1][c2];
              }
          }
      }

      return minCost;
  }
}
