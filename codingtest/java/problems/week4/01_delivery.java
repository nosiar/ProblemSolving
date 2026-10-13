// 배달
// https://school.programmers.co.kr/learn/courses/30/lessons/12978
// Lv.2 · 그래프 — 다익스트라 (시작점 1개 최단거리, K 이하 개수)

import java.util.*;

class Solution {
    public int solution(int N, int[][] road, int K) {
        List<List<int[]>> edges = new ArrayList<>();
        for (int i = 0; i <= N; ++i) {
            edges.add(new ArrayList<>());
        }

        for (int[] r : road) {
            edges.get(r[0]).add(new int[]{r[1], r[2]});
            edges.get(r[1]).add(new int[]{r[0], r[2]});
        }

        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        pq.offer(new int[]{1, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            int u = cur[0];
            int d = cur[1];

            if (d > dist[u]) {
                continue;
            }

            for (int[] edge : edges.get(u)) {
                int v = edge[0];
                int w = edge[1];

                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    pq.offer(new int[]{v, dist[v]});
                }
            }
        }

        int count = 0;
        for (int i = 1; i <= N; ++i) {
            if (dist[i] <= K) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(5, new int[][]{{1, 2, 1}, {2, 3, 3}, {5, 2, 2}, {1, 4, 2}, {5, 3, 1}, {5, 4, 2}}, 3)); // 기대: 4
    }
}
