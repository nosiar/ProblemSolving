// 가장 먼 노드
// https://school.programmers.co.kr/learn/courses/30/lessons/49189
// Lv.3 · 그래프 — BFS (가중치 없는 최단거리, 최대 거리 노드 수)

import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; ++i) {
            adj.add(new ArrayList<>());
        }
        for (int[] e : edge) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }


        Queue<Integer> q = new ArrayDeque<>();
        q.offer(1);

        boolean[] visited = new boolean[n + 1];
        visited[1] = true;

        int size = 0;
        while (!q.isEmpty()) {
            size = q.size();

            for (int i = 0; i < size; ++i) {
                int cur = q.poll();
                for (int next : adj.get(cur)) {
                    if (!visited[next]) {
                        visited[next] = true;
                        q.offer(next);
                    }
                }
            }

        }
        return size;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(6, new int[][]{{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}})); // 기대: 3
    }
}
