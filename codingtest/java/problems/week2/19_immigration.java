// 입국심사
// https://school.programmers.co.kr/learn/courses/30/lessons/43238
// Lv.3 · Kit: 이분탐색 — 파라메트릭 서치 정석

import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long l = 1;
        long h = (long) Arrays.stream(times).max().getAsInt() * n;

        while (l < h) {
            long mid = (l + h) / 2;

            long handled = 0;
            for (int t : times) {
                handled += mid / t;
            }

            if (handled >= n) {
                h = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(6, new int[]{7, 10})); // 기대: 28
    }
}
