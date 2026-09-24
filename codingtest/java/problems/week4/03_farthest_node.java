// 가장 먼 노드
// https://school.programmers.co.kr/learn/courses/30/lessons/49189
// Lv.3 · 그래프 — BFS (가중치 없는 최단거리, 최대 거리 노드 수)

import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        int answer = 0;
        // TODO: 1번 노드에서 BFS 거리 → 가장 먼 거리의 노드 개수
        return answer;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(6, new int[][]{{3,6},{4,3},{3,2},{1,3},{1,2},{2,4},{5,2}})); // 기대: 3
    }
}
