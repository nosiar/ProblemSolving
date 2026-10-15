// 섬 연결하기
// https://school.programmers.co.kr/learn/courses/30/lessons/42861
// Lv.3 · 그래프 — MST (크루스칼 + 유니온파인드)

import java.util.*;

class Solution {
    private int find(int a, int[] parent) {
        return parent[a] == a ? a : (parent[a] = find(parent[a], parent));
    }

    private boolean union(int a, int b, int[] parent) {
        int pa = find(a, parent);
        int pb = find(b, parent);

        parent[pa] = pb;

        return pa != pb;
    }

    public int solution(int n, int[][] costs) {
        int[] parent = new int[n];
        for (int i = 0; i < n; ++i) {
            parent[i] = i;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        for (int[] cost : costs) {
            pq.offer(cost);
        }

        int total = 0;
        int count = 0;
        while (count < n - 1) {

            int[] cur = pq.poll();

            if (!union(cur[0], cur[1], parent)) {
                continue;
            }

            count++;
            total += cur[2];
        }
        return total;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(4, new int[][]{{0, 1, 1}, {0, 2, 2}, {1, 2, 5}, {1, 3, 1}, {2, 3, 8}})); // 기대: 4
    }
}
