// https://leetcode.com/problems/minimum-operations-to-make-columns-strictly-increasing/


class Solution {
    public int minimumOperations(int[][] grid) {
        int count = 0;
        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (grid[j - 1][i] >= grid[j][i]) {
                    int increment = grid[j - 1][i] - grid[j][i] + 1;
                    count += increment;
                    grid[j][i] += increment;
                }
            }
        }

        return count; // Return the total number of operations
    }
}