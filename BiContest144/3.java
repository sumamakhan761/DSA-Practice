// https://leetcode.com/problems/zero-array-transformation-iii/submissions/1510032528/

import java.util.*;

class Solution {
  public int maxRemoval(int[] nums, int[][] queries) {
      int n = nums.length, nquery = queries.length;
      Arrays.sort(queries, (a,b) -> a[0] - b[0]);
      PriorityQueue<Integer> pq = new PriorityQueue<>();
      int end[] = new int[n+1]; // track the where index is end
      int curr = 0 , j = 0; // curr check the how much decrement 

      for(int i = 0 ; i<n ; i++){
          curr -= end[i]; // now curr is now curr
          while(j < nquery && queries[j][0] <= i){
              pq.offer(-queries[j][1]);
              j++;
          }
          while(curr < nums[i]){
              if(pq.isEmpty() ||-pq.peek() < i){
                  return -1;
              }
              end[-pq.poll() + 1]++;
              curr++;
          }
      }
      return pq.size();
  }
}