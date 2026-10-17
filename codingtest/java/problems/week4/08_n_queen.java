// N-Queen
// https://school.programmers.co.kr/learn/courses/30/lessons/12952
// Lv.3 · 백트래킹 — N-Queen 배치 경우의 수

import java.util.*;

class Solution {
    boolean[] col, diag1, diag2;
    int n;

    public int solution(int n) {
        col = new boolean[n];
        diag1 = new boolean[2 * n];
        diag2 = new boolean[2 * n];
        this.n = n;

        return dfs(0);
    }

    private int dfs(int r) {

        if (r == n) {
            return 1;
        }

        int total = 0;

        for (int c = 0; c < n; ++c) {
            if (col[c] || diag1[r - c + n - 1] || diag2[r + c]) continue;

            col[c] = diag1[r - c + n - 1] = diag2[r + c] = true;

            total += dfs(r + 1);

            col[c] = diag1[r - c + n - 1] = diag2[r + c] = false;
        }
        return total;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(4)); // 기대: 2
    }
}
