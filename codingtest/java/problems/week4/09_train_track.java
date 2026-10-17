// 기차 선로
// https://school.programmers.co.kr/learn/courses/30/lessons/468381
// Lv.4~5 · 백트래킹 — 격자 위 경로 완전탐색 + 상태 되돌리기 (#3 크로스 처리)
//
// 핵심:
//  - (0,0)에서 오른쪽으로 출발하는 기차를 따라가며, 빈칸엔 호환되는 선로 타입을 "선택"해 놓는다.
//  - cur = {행, 열, 진입방향(왼0/위1/오2/아래3), 선로타입, pathCount}. 명시적 스택 DFS.
//  - 검증 5가지(디버깅으로 하나씩 찾음):
//    ① 고정 선로(grid 1~7)로 갈 땐 타입이 grid값과 일치해야 통과
//    ② 도착 시 원래 grid의 모든 선로 칸을 방문했는지 확인
//    ③ #3 재진입은 "이미 3 && 지금도 3"일 때만 (다른 타입으로 덮기 차단)
//    ④ #3는 정확히 두 번 통과(visited 9)여야 유효, 1번(3)·3번+(27+)이면 무효
//    ⑤ 백트래킹 되돌리기: #3 2차 방문(9)은 0이 아니라 3으로 복원 (1차 상태 보존)

import java.util.*;

class Solution {
    int LEFT = 1, RIGHT = 2, UP = 3, DOWN = 4;

    int[][] delta;

    int[] opposite;

    int[][] nextDir;
    boolean[][] open;

    int[][] map;

    int n, m;

    public int solution(int[][] grid) {
        n = grid.length;
        m = grid[0].length;

        map = new int[grid.length][grid[0].length];
        map[0][0] = grid[0][0];

        delta = new int[5][];
        delta[LEFT] = new int[]{0, -1};
        delta[RIGHT] = new int[]{0, 1};
        delta[UP] = new int[]{-1, 0};
        delta[DOWN] = new int[]{1, 0};

        opposite = new int[5];
        opposite[LEFT] = RIGHT;
        opposite[RIGHT] = LEFT;
        opposite[UP] = DOWN;
        opposite[DOWN] = UP;

        nextDir = new int[8][5];
        nextDir[1][LEFT] = RIGHT;
        nextDir[1][RIGHT] = LEFT;
        nextDir[2][UP] = DOWN;
        nextDir[2][DOWN] = UP;
        nextDir[3][LEFT] = RIGHT;
        nextDir[3][RIGHT] = LEFT;
        nextDir[3][UP] = DOWN;
        nextDir[3][DOWN] = UP;
        nextDir[4][LEFT] = UP;
        nextDir[4][UP] = LEFT;
        nextDir[5][RIGHT] = UP;
        nextDir[5][UP] = RIGHT;
        nextDir[6][DOWN] = RIGHT;
        nextDir[6][RIGHT] = DOWN;
        nextDir[7][DOWN] = LEFT;
        nextDir[7][LEFT] = DOWN;


        open = new boolean[8][5];
        open[1][LEFT] = open[1][RIGHT] = true;
        open[2][UP] = open[2][DOWN] = true;
        open[3][LEFT] = open[3][RIGHT] = open[3][UP] = open[3][DOWN] = true;
        open[4][UP] = open[4][LEFT] = true;
        open[5][UP] = open[5][RIGHT] = true;
        open[6][DOWN] = open[6][RIGHT] = true;
        open[7][DOWN] = open[7][LEFT] = true;

        return dfs(0, 0, LEFT, grid);
    }

    private int dfs(int x, int y, int from, int[][] grid) {

        if (x == n - 1 && y == m - 1) {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < m; ++j) {
                    if (grid[i][j] > 0 && map[i][j] % 10 != grid[i][j] || map[i][j] == 3)
                        return 0;
                }
            }
            return 1;
        }

        int cur = map[x][y] % 10;

        int[] toDelta = delta[nextDir[cur][from]];

        int nextX = x + toDelta[0];
        int nextY = y + toDelta[1];

        if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m)
            return 0;
        if (grid[nextX][nextY] == -1)
            return 0;

        int total = 0;
        int original = map[nextX][nextY];
        for (int i = 1; i <= 7; ++i) {
            if (open[i][opposite[nextDir[cur][from]]] && (map[nextX][nextY] == 0 || (map[nextX][nextY] == 3 && i == 3)) && (grid[nextX][nextY] == 0 || grid[nextX][nextY] == i)) {

                map[nextX][nextY] = original * 10 + i;
                total += dfs(nextX, nextY, opposite[nextDir[cur][from]], grid);
                map[nextX][nextY] = original;
            }
        }
        return total;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(new int[][]{{1, 0, -1}, {0, 0, 7}, {0, 0, 2}})); // 기대: 2
        System.out.println(s.solution(new int[][]{{1, 0, 0, 0, 0, -1, -1}, {-1, 0, 0, 1, 0, 0, 1}})); // 기대: 2
        System.out.println(s.solution(new int[][]{{1, 0, 0, 0, 0}, {0, 0, 3, 0, 2}, {0, 0, 0, 0, 2}})); // 기대: 4
        System.out.println(s.solution(new int[][]{{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 1}})); // 기대: 644
        System.out.println(s.solution(new int[][]{{1, 7}, {0, 2}})); // 기대: 1
        System.out.println(s.solution(new int[][]{{1, -1, 0, 0}, {-1, 0, 0, 0}, {0, 0, 0, -1}, {0, 0, -1, 1}})); // 기대: 0
    }
}
