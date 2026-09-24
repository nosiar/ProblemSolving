// 징검다리
// https://school.programmers.co.kr/learn/courses/30/lessons/43236
// Lv.4 · Kit: 이분탐색 — 파라메트릭 서치

import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);

        int l = 1;
        int h = distance;
        int ans = 0;
        while (l <= h) {
            int mid = (l + h) / 2;

            int removedCount = 0;
            int last = 0;
            for (int rock : rocks) {
                if (rock - last < mid) {
                    removedCount++;
                } else {
                    last = rock;
                }
            }
            if (removedCount < rocks.length && distance - last < mid) {
                removedCount++;
            }

            if (removedCount <= n) {
                ans = mid;
                l = mid + 1;
            } else {
                h = mid - 1;
            }

        }
        return ans;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(25, new int[]{2, 14, 11, 21, 17}, 2)); // 기대: 4
    }
}
