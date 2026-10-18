// 미로 탈출
// https://school.programmers.co.kr/learn/courses/30/lessons/159993
// (유형 비공개 — 직접 판단해서 풀기. 막히면 힌트 요청)

import java.util.*;

class Solution {
    int[][] nextMove = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int solution(String[] maps) {

        int[] start = new int[2], end = new int[2], lever = new int[2];

        for (int i = 0; i < maps.length; ++i) {
            for (int j = 0; j < maps[0].length(); ++j) {
                if (maps[i].charAt(j) == 'S') {
                    start[0] = i;
                    start[1] = j;
                } else if (maps[i].charAt(j) == 'E') {
                    end[0] = i;
                    end[1] = j;
                } else if (maps[i].charAt(j) == 'L') {
                    lever[0] = i;
                    lever[1] = j;
                }
            }
        }

        int toLever = bfs(start, lever, maps);
        int toExit = bfs(lever, end, maps);

        return toLever == -1 || toExit == -1 ? -1 : toLever + toExit;
    }

    private int bfs(int[] start, int[] end, String[] maps) {

        boolean[][] visited = new boolean[maps.length][maps[0].length()];

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{start[0], start[1], 0});
        visited[start[0]][start[1]] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            if (cur[0] == end[0] && cur[1] == end[1]) return cur[2];

            for (int i = 0; i < 4; ++i) {
                int nextX = cur[0] + nextMove[i][0];
                int nextY = cur[1] + nextMove[i][1];

                if (nextX >= 0 && nextX < maps.length &&
                        nextY >= 0 && nextY < maps[0].length() &&
                        !visited[nextX][nextY] &&
                        maps[nextX].charAt(nextY) != 'X') {

                    visited[nextX][nextY] = true;
                    q.offer(new int[]{nextX, nextY, cur[2] + 1});
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(new String[]{"SOOOL", "XXXXO", "OOOOO", "OXXXX", "OOOOE"})); // 기대: 16
        System.out.println(s.solution(new String[]{"LOOXS", "OOOOX", "OOOOO", "OOOOO", "EOOOO"})); // 기대: -1
    }
}
