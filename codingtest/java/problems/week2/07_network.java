// 네트워크
// https://school.programmers.co.kr/learn/courses/30/lessons/43162
// Kit: DFS/BFS — 연결요소

import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {

        boolean[] visited = new boolean[n];

        int count = 0;

        for (int k = 0; k < n; ++k) {

            if (visited[k]) {
                continue;
            }

            count++;

            Deque<Integer> d = new ArrayDeque<>();
            visited[k] = true;
            d.offer(k);

            while (!d.isEmpty()) {
                int c = d.poll();

                for (int i = 0; i < n; ++i) {
                    if (computers[c][i] == 1 && !visited[i]) {
                        visited[i] = true;
                        d.offer(i);
                    }
                }
            }

        }
        return count;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(3, new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}})); // 기대: 2
    }
}
