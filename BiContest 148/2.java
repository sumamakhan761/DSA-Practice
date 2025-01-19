// https://leetcode.com/problems/minimum-cost-to-make-arrays-identical/submissions/1512621902/


import java.util.Arrays;

class Solution {
    public long minCost(int[] arr, int[] brr, long k) {
        int n = arr.length;
        
        long costNoSplit = 0;
        for(int i = 0; i < n; i++) {
            costNoSplit += Math.abs((long)arr[i] - (long)brr[i]);
        }
        
        int[] sortedArr = arr.clone();
        int[] sortedBrr = brr.clone();
        Arrays.sort(sortedArr);
        Arrays.sort(sortedBrr);
        
        long costWithSplit = k;
        for(int i = 0; i < n; i++) {
            costWithSplit += Math.abs((long)sortedArr[i] - (long)sortedBrr[i]);
        }
        
        // Return the minimum of the two approaches
        return Math.min(costNoSplit, costWithSplit);
    }
}