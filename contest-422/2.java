
// https://leetcode.com/problems/find-minimum-time-to-reach-last-room-i/submissions/1519639603/

class Solution {

  class Node {
      int x;
      int y;
      int time;

      public Node(int x, int y, int time) {
          this.x = x;
          this.y = y;
          this.time = time;
      }
  }

  public int minTimeToReach(int[][] moveTime) {
      int n = moveTime.length;
      int m = moveTime[0].length;
      int[][] time = new int[n][m];
      boolean[][] visited = new boolean[n][m];
      int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
      for (int i = 0; i < n; i++) {
          Arrays.fill(time[i], Integer.MAX_VALUE);
      }
      PriorityQueue<Node> pq = new PriorityQueue<>((node1, node2) -> node1.time - node2.time);
      pq.add(new Node(0, 0, 0));
      time[0][0] = 0;

      while (!pq.isEmpty()) {
          Node node = pq.poll();
          int x = node.x;
          int y = node.y;
          visited[x][y] = true;

          for (int[] dir : dirs) {
              int nodex = x + dir[0];
              int nodey = y + dir[1];
              if (nodex >= 0 && nodey >= 0 && nodex < n && nodey < m) {
                  if (!visited[nodex][nodey]) {
                      int newtime = 1 + Math.max(node.time, moveTime[nodex][nodey]);
                      if (newtime < time[nodex][nodey]) {
                          time[nodex][nodey] = newtime;
                          pq.add(new Node(nodex, nodey, newtime));
                      }
                  }
              }
          }
      }
      return time[n-1][m-1];
  }
}