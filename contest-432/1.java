// https://leetcode.com/problems/zigzag-grid-traversal-with-skip/submissions/1505850313/

import java.util.*;

class Solution {
  public List<Integer> zigzagTraversal(int[][] grid) {
      int rows = grid.length;
      int cols = grid[0].length;
      List<Integer> result = new ArrayList<>();
      
      for (int i = 0; i < rows; i++) {
          if (i % 2 == 0) {
              for (int j = 0; j < cols; j++) {
                  if (j % 2 == 0) {  
                      result.add(grid[i][j]);
                  }
              }
          } else {
              for (int j = cols - 1; j >= 0; j--) {
                  if (j % 2 == 1) {  
                      result.add(grid[i][j]);
                  }
              }
          }
      }
      return result;
  }
}