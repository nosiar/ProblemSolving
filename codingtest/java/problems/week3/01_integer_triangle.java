// 정수 삼각형
// https://school.programmers.co.kr/learn/courses/30/lessons/43105
// Lv.3 · Kit: 동적계획법 — 경로 DP

import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int max = triangle[0][0];
        for (int i = 1; i < triangle.length; ++i) {
            for (int j = 0; j < triangle[i].length; ++j) {
                int left = j == 0 ? Integer.MIN_VALUE : triangle[i - 1][j - 1];
                int right = j == triangle[i].length - 1 ? Integer.MIN_VALUE : triangle[i - 1][j];

                triangle[i][j] += Math.max(left, right);

                max = Math.max(max, triangle[i][j]);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(new int[][]{{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}})); // 기대: 30
    }
}
