// 게임 맵 최단거리
// https://school.programmers.co.kr/learn/courses/30/lessons/1844
// Lv.2 · Kit: DFS/BFS — 격자 BFS 정석

import java.util.*;

class Solution {
    public int solution(int[][] maps) {
        int[][] x = new int[maps.length][maps[0].length];
        for (int i = 0; i < maps.length; ++i) {
            Arrays.fill(x[i], -1);
        }
        x[0][0] = 1;


        int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        Deque<int[]> d = new ArrayDeque<>();
        d.offer(new int[]{0, 0});

        while (!d.isEmpty()) {
            int[] c = d.poll();

            for (int i = 0; i < 4; ++i) {
                int nx = c[0] + dir[i][0];
                int ny = c[1] + dir[i][1];

                if (nx >= 0 && nx < maps.length && ny >= 0 && ny < maps[0].length && x[nx][ny] == -1 && maps[nx][ny] == 1) {
                    x[nx][ny] = x[c[0]][c[1]] + 1;

                    if (nx == maps.length - 1 && ny == maps[0].length - 1) {
                        return x[nx][ny];
                    }

                    d.offer(new int[]{nx, ny});
                }
            }
        }

        return x[maps.length - 1][maps[0].length - 1];
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(new int[][]{{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 1}, {0, 0, 0, 0, 1}})); // 기대: 11
    }
}
