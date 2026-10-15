// 피로도
// https://school.programmers.co.kr/learn/courses/30/lessons/87946
// Lv.2 · 백트래킹 — 던전 순열 완전탐색

import java.util.*;

class Solution {
    public int solution(int k, int[][] dungeons) {
        boolean[] visited = new boolean[dungeons.length];

        return sub(k, dungeons, visited);
    }

    private int sub(int k, int[][] dungeons, boolean[] visited) {
        int result = 0;
        for (int i = 0; i < visited.length; ++i) {
            if (!visited[i] && dungeons[i][0] <= k) {
                visited[i] = true;
                result = Math.max(result, sub(k - dungeons[i][1], dungeons, visited) + 1);
                visited[i] = false;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(80, new int[][]{{80, 20}, {50, 40}, {30, 10}})); // 기대: 3
    }
}
