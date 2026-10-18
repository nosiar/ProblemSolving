// 석유 시추
// https://school.programmers.co.kr/learn/courses/30/lessons/250136
// (유형 비공개 — 직접 판단해서 풀기. 막히면 힌트 요청)

import java.util.*;

class Solution {

    int n = 0, m = 0;

    int[][] direction = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int solution(int[][] land) {
        n = land.length;
        m = land[0].length;

        int[][] group = new int[n][m];
        int[] groupAmount = new int[n * m + 1];

        int answer = 0;

        int curGroup = 0;
        for (int j = 0; j < m; ++j) {
            for (int i = 0; i < n; ++i) {
                if (land[i][j] == 1 && group[i][j] == 0) {
                    int count = bfs(i, j, ++curGroup, group, land);
                    groupAmount[curGroup] = count;
                }
            }

            int total = 0;
            Set<Integer> foundGroup = new HashSet<>();
            for (int i = 0; i < n; ++i) {
                if (!foundGroup.contains(group[i][j])) {
                    foundGroup.add(group[i][j]);
                    total += groupAmount[group[i][j]];
                }
            }
            if (total > answer) {
                answer = total;
            }
        }

        return answer;
    }

    private int bfs(int x, int y, int groupId, int[][] group, int[][] land) {

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x, y});
        group[x][y] = groupId;

        int count = 1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 4; ++i) {
                int nextX = cur[0] + direction[i][0];
                int nextY = cur[1] + direction[i][1];

                if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < m &&
                        land[nextX][nextY] == 1 && group[nextX][nextY] == 0) {
                    q.offer(new int[]{nextX, nextY});
                    group[nextX][nextY] = groupId;
                    count++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(new int[][]{{0, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 0}, {1, 1, 0, 0, 0, 1, 1, 0}, {1, 1, 1, 0, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 0, 1, 1}})); // 기대: 9
        System.out.println(s.solution(new int[][]{{1, 0, 1, 0, 1, 1}, {1, 0, 1, 0, 0, 0}, {1, 0, 1, 0, 0, 1}, {1, 0, 0, 1, 0, 0}, {1, 0, 0, 1, 0, 1}, {1, 0, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 1}})); // 기대: 16
    }
}
