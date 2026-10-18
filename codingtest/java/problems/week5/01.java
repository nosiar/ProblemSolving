// 숫자 변환하기
// https://school.programmers.co.kr/learn/courses/30/lessons/154538
// (유형 비공개 — 직접 판단해서 풀기. 막히면 힌트 요청)

import java.util.*;

class Solution {
    public int solution(int x, int y, int n) {
        int[] made = new int[1000001];
        Arrays.fill(made, Integer.MAX_VALUE);

        made[x] = 0;

        for (int i = x; i <= y; ++i) {
            if (made[i] < Integer.MAX_VALUE) {
                if (i + n <= y && made[i + n] > made[i] + 1) made[i + n] = made[i] + 1;
                if (i * 2 <= y && made[i * 2] > made[i] + 1) made[i * 2] = made[i] + 1;
                if (i * 3 <= y && made[i * 3] > made[i] + 1) made[i * 3] = made[i] + 1;
            }
        }

        return made[y] == Integer.MAX_VALUE ? -1 : made[y];
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(10, 40, 5));   // 기대: 2
        System.out.println(s.solution(10, 40, 30));  // 기대: 1
        System.out.println(s.solution(2, 5, 4));     // 기대: -1
    }
}
