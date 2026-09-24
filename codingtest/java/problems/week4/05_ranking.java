// 순위
// https://school.programmers.co.kr/learn/courses/30/lessons/49191
// Lv.3 · 그래프 — 플로이드 워셜 (승패 도달성; 자신과 나머지 모두 비교 가능하면 순위 확정)

import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        // TODO: 이긴/진 관계 도달성 계산 → (이긴수 + 진수 == n-1)인 선수 카운트
        return answer;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(5, new int[][]{{4,3},{4,2},{3,2},{1,2},{2,5}})); // 기대: 2
    }
}
