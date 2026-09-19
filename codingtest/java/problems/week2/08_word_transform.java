// 단어 변환
// https://school.programmers.co.kr/learn/courses/30/lessons/43163
// Lv.3 · Kit: DFS/BFS — 상태 그래프 BFS

import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        int[] dist = new int[words.length];
        boolean[] visited = new boolean[words.length];
        Deque<Integer> d = new ArrayDeque<>();

        for (int i = 0; i < words.length; ++i) {
            if (isAdjacent(words[i], begin)) {
                d.offer(i);
                dist[i] = 1;
                visited[i] = true;
            }
        }

        while (!d.isEmpty()) {
            int c = d.poll();

            if (words[c].equals(target)) {
                return dist[c];
            }

            for (int i = 0; i < words.length; ++i) {
                if (!visited[i] && isAdjacent(words[i], words[c])) {
                    d.offer(i);
                    dist[i] = dist[c] + 1;
                    visited[i] = true;
                }
            }
        }
        return 0;
    }

    private boolean isAdjacent(String a, String b) {
        int diff = 0;
        for (int i = 0; i < a.length(); ++i) {
            if (a.charAt(i) != b.charAt(i) && ++diff > 1) {
                return false;
            }
        }
        return diff == 1;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution("hit", "cog", new String[]{"hot", "dot", "dog", "lot", "log", "cog"})); // 기대: 4
    }
}
