// https://leetcode.com/submissions/detail/1503779012/

import java.util.*;

class Solution {

  private int bfsCount(List<List<Integer>> adj, int start, int k, int size) {
      boolean[] visited = new boolean[size];
      Queue<Integer> queue = new LinkedList<>();
      queue.add(start);
      visited[start] = true;
      int count = 0, distance = 0;

      while (!queue.isEmpty() && distance <= k) {
          int sz = queue.size();
          while (sz-- > 0) {
              int node = queue.poll();
              count++;
              for (int neighbor : adj.get(node)) {
                  if (!visited[neighbor]) {
                      visited[neighbor] = true;
                      queue.add(neighbor);
                  }
              }
          }
          distance++;
      }
      return count;
  }

  public int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
      int n = edges1.length;
      int m = edges2.length;

      List<List<Integer>> gr1 = new ArrayList<>();
      for (int i = 0; i <= n; i++) {
          gr1.add(new ArrayList<>());
      }
      for (int[] edge : edges1) {
          int u = edge[0], v = edge[1];
          gr1.get(u).add(v);
          gr1.get(v).add(u);
      }

      // Build adjacency list for the second tree
      List<List<Integer>> gr2 = new ArrayList<>();
      for (int i = 0; i <= m; i++) {
          gr2.add(new ArrayList<>());
      }
      for (int[] edge : edges2) {
          int u = edge[0], v = edge[1];
          gr2.get(u).add(v);
          gr2.get(v).add(u);
      }

      // BFS count for each node in the first tree
      int[] cnt1 = new int[n + 1];
      for (int i = 0; i <= n; i++) {
          cnt1[i] = bfsCount(gr1, i, k, n + 1);
      }

      // Find the maximum reachable nodes in the second tree
      int nexttreecnt = 0;
      for (int v = 0; v <= m; v++) {
          int count = bfsCount(gr2, v, k - 1, m + 1);
          nexttreecnt = Math.max(nexttreecnt, count);
      }

      int ans[] = new int[n + 1];
      for (int i = 0; i <= n; i++) {
          ans[i] = cnt1[i] + nexttreecnt;
      }

      return ans;
  }
}