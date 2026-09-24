// 배달
// https://school.programmers.co.kr/learn/courses/30/lessons/12978
// Lv.2 · 그래프 — 다익스트라 (시작점 1개 최단거리, K 이하 개수)

import java.util.*;

class Solution {
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        // TODO: 1번 마을에서 각 마을까지 최단거리 → K 이하인 마을 수
        return answer;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(5, new int[][]{{1,2,1},{2,3,3},{5,2,2},{1,4,2},{5,3,1},{5,4,2}}, 3)); // 기대: 4
    }
}
