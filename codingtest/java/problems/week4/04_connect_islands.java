// 섬 연결하기
// https://school.programmers.co.kr/learn/courses/30/lessons/42861
// Lv.3 · 그래프 — MST (크루스칼 + 유니온파인드)

import java.util.*;

class Solution {
    public int solution(int n, int[][] costs) {
        int answer = 0;
        // TODO: 최소 신장 트리 비용 (간선 오름차순 정렬 + union-find로 사이클 방지)
        return answer;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(4, new int[][]{{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}})); // 기대: 4
    }
}
