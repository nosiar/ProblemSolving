// 합승 택시 요금
// https://school.programmers.co.kr/learn/courses/30/lessons/72413
// Lv.3 · 그래프 — 플로이드 워셜 (2021 카카오, 분기점 탐색)

import java.util.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        // TODO: 모든 쌍 최단거리 후, 합승 분기점 k에 대해 dist(s,k)+dist(k,a)+dist(k,b) 최소
        return answer;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(6, 4, 6, 2, new int[][]{{4,1,10},{3,5,24},{5,6,2},{3,1,41},{5,1,24},{4,6,50},{2,4,66},{2,3,22},{1,6,25}})); // 기대: 82
    }
}
