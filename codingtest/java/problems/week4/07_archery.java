// 양궁대회
// https://school.programmers.co.kr/learn/courses/30/lessons/92342
// Lv.2 · 백트래킹 — 화살 배분 완전탐색 (2022 카카오, 동점 처리)

import java.util.*;

class Solution {
    public int[] solution(int n, int[] info) {
        int[] answer = {};
        // TODO: 라이언의 화살 배분을 백트래킹으로 전부 시도 → 점수차 최대 (동점이면 낮은 점수 많이)
        return answer;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(Arrays.toString(s.solution(5, new int[]{2,1,1,1,0,0,0,0,0,0,0}))); // 기대: [0,2,2,0,1,0,0,0,0,0,0]
    }
}
