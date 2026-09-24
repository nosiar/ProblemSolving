// 도둑질
// https://school.programmers.co.kr/learn/courses/30/lessons/42897
// Lv.4 · Kit: 동적계획법 — 원형 DP

import java.util.*;

class Solution {
    public int solution(int[] money) {
        int n = money.length;
        return Math.max(dp(money, 0, n - 1), dp(money, 1, n));
    }

    private int dp(int[] money, int l, int h) {
        int p = 0;
        int pp = 0;
        for (int i = l; i < h; ++i) {
            int cur = Math.max(p, pp + money[i]);
            pp = p;
            p = cur;
        }
        return p;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(new int[]{1, 2, 3, 1})); // 기대: 4
    }
}
