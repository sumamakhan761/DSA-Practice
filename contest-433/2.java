// https://leetcode.com/submissions/detail/1514137296/


import java.util.Arrays;

class Solution {
    public int minMaxSums(int[] nums, int k) {
        final int MOD = 1_000_000_007;
        int n = nums.length;
        Arrays.sort(nums);
        
        // Precompute combination counts C(n, m) for m = 0 to k
        long[][] comb = new long[n + 1][k + 1];
        for(int i = 0; i <= n; i++) {
            comb[i][0] = 1;
            for(int m = 1; m <= Math.min(i, k); m++) {
                comb[i][m] = (comb[i-1][m] + comb[i-1][m-1]) % MOD;
            }
        }

        // Calculate sum of minimums
        long sumMin = 0;
        for(int i = 0; i < n; i++) {
            int remaining = n - i - 1;
            long count = 0;
            for(int m = 0; m < k; m++) { // m elements after i
                if(m > remaining) break;
                count = (count + comb[remaining][m]) % MOD;
            }
            sumMin = (sumMin + nums[i] * count) % MOD;
        }

        // Calculate sum of maximums
        long sumMax = 0;
        for(int i = 0; i < n; i++) {
            long count = 0;
            for(int m = 0; m < k; m++) { // m elements before i
                if(m > i) break;
                count = (count + comb[i][m]) % MOD;
            }
            sumMax = (sumMax + nums[i] * count) % MOD;
        }

        // Total sum of minima and maxima
        return (int)((sumMin + sumMax) % MOD);
    }
}