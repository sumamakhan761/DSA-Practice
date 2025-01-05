// https://leetcode.com/problems/minimum-number-of-operations-to-make-elements-in-array-distinct/
import java.util.*;

class Solution {

  public int minimumOperations(int[] nums) {
      int n = nums.length;
      int ans = 0;
      int remove = 3;

      while (!distinct(nums)) {
          if (nums.length <= remove) {
              nums = new int[0];
          } else {
              nums = Arrays.copyOfRange(nums, remove, nums.length);
          }
          ans++;
      }
      return ans;
  }

  public boolean distinct(int[] nums) {
      Set<Integer> s = new HashSet<>();
      for (int num : nums) {
          if (!s.add(num)) {
              return false;
          }
      }
      return true;
  }
}