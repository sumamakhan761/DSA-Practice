// https://leetcode.com/submissions/detail/1504874345/

import  java.util.*;
class Solution {
  private int[] evenSubtree1, oddSubtree1, totalEven1, totalOdd1;
  private int[] evenSubtree2, oddSubtree2, totalEven2, totalOdd2;

  private List<List<Integer>> gr1, gr2;

  private void dfs1Tree1(int node, int parent) {
      evenSubtree1[node] = 1; // Node itself at distance 0 (even)
      oddSubtree1[node] = 0;
      for (int child : gr1.get(node)) {
          if (child != parent) {
              dfs1Tree1(child, node);
              evenSubtree1[node] += oddSubtree1[child];
              oddSubtree1[node] += evenSubtree1[child];
          }
      }
  }

  private void dfs2Tree1(int node, int parent, int evenAbove, int oddAbove) {
      totalEven1[node] = evenSubtree1[node] + evenAbove;
      totalOdd1[node] = oddSubtree1[node] + oddAbove;

      for (int child : gr1.get(node)) {
          if (child != parent) {
              int evenUp = totalOdd1[node] - evenSubtree1[child];
              int oddUp = totalEven1[node] - oddSubtree1[child];

              dfs2Tree1(child, node, evenUp, oddUp);
          }
      }
  }

  private void dfs1Tree2(int node, int parent) {
      evenSubtree2[node] = 1; // Node itself at distance 0 (even)
      oddSubtree2[node] = 0;
      for (int child : gr2.get(node)) {
          if (child != parent) {
              dfs1Tree2(child, node);
              evenSubtree2[node] += oddSubtree2[child];
              oddSubtree2[node] += evenSubtree2[child];
          }
      }
  }

  private void dfs2Tree2(int node, int parent, int evenAbove, int oddAbove) {
      totalEven2[node] = evenSubtree2[node] + evenAbove;
      totalOdd2[node] = oddSubtree2[node] + oddAbove;

      for (int child : gr2.get(node)) {
          if (child != parent) {
              int evenUp = totalOdd2[node] - evenSubtree2[child];
              int oddUp = totalEven2[node] - oddSubtree2[child];

              dfs2Tree2(child, node, evenUp, oddUp);
          }
      }
  }

  public int[] maxTargetNodes(int[][] edges1, int[][] edges2) {
      int n = edges1.length + 1;
      int m = edges2.length + 1;

      gr1 = new ArrayList<>(n);
      for (int i = 0; i < n; i++) {
          gr1.add(new ArrayList<>());
      }
      for (int[] edge : edges1) {
          int node = edge[0], child = edge[1];
          gr1.get(node).add(child);
          gr1.get(child).add(node);
      }

      gr2 = new ArrayList<>(m);
      for (int i = 0; i < m; i++) {
          gr2.add(new ArrayList<>());
      }
      for (int[] edge : edges2) {
          int node = edge[0], child = edge[1];
          gr2.get(node).add(child);
          gr2.get(child).add(node);
      }

      evenSubtree1 = new int[n];
      oddSubtree1 = new int[n];
      totalEven1 = new int[n];
      totalOdd1 = new int[n];
      dfs1Tree1(0, -1);
      dfs2Tree1(0, -1, 0, 0);

      evenSubtree2 = new int[m];
      oddSubtree2 = new int[m];
      totalEven2 = new int[m];
      totalOdd2 = new int[m];
      dfs1Tree2(0, -1);
      dfs2Tree2(0, -1, 0, 0);

      int goldenNode = 0;
      int maxTotalOdd = totalOdd2[0];
      for (int i = 1; i < m; i++) {
          if (totalOdd2[i] > maxTotalOdd) {
              maxTotalOdd = totalOdd2[i];
              goldenNode = i;
          }
      }

      int[] answer = new int[n];
      for (int i = 0; i < n; i++) {
          answer[i] = totalEven1[i] + maxTotalOdd;
      }

      return answer;
  }
}