// https://leetcode.com/problems/sum-of-good-subsequences/submissions/1516550069/



class Solution {
    public int sumOfGoodSubsequences(int[] nums) {
        final int MOD = 1_000_000_007;
        // Since nums[i] can be up to 10^5
        int maxNum = 100001;
        long[] count = new long[maxNum + 2]; // +2 to prevent index out of bounds for x+1
        long[] sum = new long[maxNum + 2];
        long total = 0;
        
        for (int x : nums) {
            long newCount = 1; // Starting a new subsequence with x
            long newSum = x;
            
            // If there's a subsequence ending with x-1, extend it
            if (x - 1 >= 0) {
                newCount = (newCount + count[x - 1]) % MOD;
                newSum = (newSum + sum[x - 1] + (count[x - 1] * x) % MOD) % MOD;
            }
            
            // If there's a subsequence ending with x+1, extend it
            if (x + 1 <= maxNum) {
                newCount = (newCount + count[x + 1]) % MOD;
                newSum = (newSum + sum[x + 1] + (count[x + 1] * x) % MOD) % MOD;
            }
            
            // Update the DP arrays
            count[x] = (count[x] + newCount) % MOD;
            sum[x] = (sum[x] + newSum) % MOD;
            
            // Add to the total sum
            total = (total + newSum) % MOD;
        }
        
        return (int) total;
    }
}