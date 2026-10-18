// 요격 시스템
// https://school.programmers.co.kr/learn/courses/30/lessons/181188
// (유형 비공개 — 직접 판단해서 풀기. 막히면 힌트 요청)

import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        Arrays.sort(targets, Comparator.comparingInt(a -> a[0]));

        int count = 0;

        int s = 0, e = 0;

        for (int[] target : targets) {
            if (target[0] >= e) {
                count++;
                s = target[0];
                e = target[1];
            } else {
                s = target[0];
                e = Math.min(e, target[1]);
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(new int[][]{{4, 5}, {4, 8}, {10, 14}, {11, 13}, {5, 12}, {3, 7}, {1, 4}})); // 기대: 3
    }
}
