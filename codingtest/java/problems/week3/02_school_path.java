// 등굣길
// https://school.programmers.co.kr/learn/courses/30/lessons/42898
// Lv.3 · Kit: 동적계획법 — 격자 경로 DP + 모듈러

import java.util.*;

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        boolean[][] p = new boolean[n + 1][m + 1];
        for (int[] puddle : puddles) {
            p[puddle[1]][puddle[0]] = true;
        }

        int[][] x = new int[n + 1][m + 1];

        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= m; ++j) {

                if (i == 1 && j == 1) {
                    x[i][j] = 1;
                } else if (p[i][j]) {
                    x[i][j] = 0;
                } else {
                    x[i][j] = (x[i - 1][j] + x[i][j - 1]) % 1000000007;
                }
            }
        }
        return x[n][m];
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(4, 3, new int[][]{{2, 2}})); // 기대: 4
    }
}
