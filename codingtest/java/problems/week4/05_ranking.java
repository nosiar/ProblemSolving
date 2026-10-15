// 순위
// https://school.programmers.co.kr/learn/courses/30/lessons/49191
// Lv.3 · 그래프 — 플로이드 워셜 (승패 도달성; 자신과 나머지 모두 비교 가능하면 순위 확정)

import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        boolean[][] win = new boolean[n + 1][n + 1];

        for (int[] result : results) {
            win[result[0]][result[1]] = true;
        }

        for (int k = 1; k <= n; ++k) {
            for (int i = 1; i <= n; ++i) {
                for (int j = 1; j <= n; ++j) {
                    win[i][j] = win[i][j] || win[i][k] && win[k][j];
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= n; ++i) {
            int count = 0;
            for (int j = 1; j <= n; ++j) {
                if (j != i && (win[i][j] || win[j][i])) {
                    count++;
                }
            }
            if (count == n - 1) {
                answer++;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(5, new int[][]{{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}})); // 기대: 2
    }
}
