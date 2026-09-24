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
    public int solution(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        int[][] visited = new int[n][m];
        Deque<int[]> d = new ArrayDeque<>();

        if(grid[0][0] == 1)
            d.push(new int[]{0,0, 0,  grid[0][0] ,   0});
        else
            d.push(new int[]{0,0, 1, grid[0][0],   0});


        Deque<int[]> path = new ArrayDeque<>();

        // 왼:0 위:1 오:2 아래:3

        int count = 0;
        while(!d.isEmpty()) {

            int[] cur = d.pop();

            while(cur[4] > 0 && path.peek()[4] >= cur[4]) {
                int[] p = path.pop();

                if(visited[p[0]][p[1]] == 9) visited[p[0]][p[1]] = 3 ;
                else visited[p[0]][p[1]] = 0;
            }

            path.push(cur);
            if(cur[3] == 3 && visited[cur[0]][cur[1]] > 0) {
                visited[cur[0]][cur[1]] = visited[cur[0]][cur[1]] * 3;
            } else {
                visited[cur[0]][cur[1]] = cur[3];
            }

            List<int[]> nexts = next(cur);

            for(int[] nx : nexts) {

                // index 넘어감
                if(nx[0] <0 || nx[1]<0 || nx[0] >=n || nx[1] >=m)  continue;
                // 막다른길 생성
                if (nx[1] ==0  && (nx[3] == 1 || nx[3] == 3 || nx[3] == 4 || nx[3] == 7  )) continue;
                if (nx[0] ==0  && (nx[3] == 2 || nx[3] == 3 || nx[3] == 4 || nx[3] == 5  )) continue;
                if (nx[1] ==m-1 && nx[0] != n-1 && (nx[3] == 1 || nx[3] == 3 || nx[3] == 5 || nx[3] == 6  )) continue;
                if (nx[0] ==n-1 && nx[1] != m-1 && (nx[3] == 2 || nx[3] == 3 || nx[3] == 6 || nx[3] == 7  )) continue;
                // grid 검사
                if(grid[nx[0]][nx[1]] == -1) continue;
                if(grid[nx[0]][nx[1]] != 0 && grid[nx[0]][nx[1]] != nx[3]) continue;

                if(visited[nx[0]][nx[1]] != 0 && !(visited[nx[0]][nx[1]] ==3 && nx[3] == 3)) {
                    continue;
                }

                if(nx[0]==n-1 && nx[1] == m-1 ) {
                    boolean good = true;
                    for(int i = 0; i < n; ++i){
                        for(int j =0; j < m; ++j) {
                            if(grid[i][j] > 0 && visited[i][j] == 0 && !(i==n -1 && j== m-1)) {
                                good=false;
                            }
                            if(visited[i][j] >= 27 || visited[i][j] == 3) {
                                good = false;
                            }
                        }
                    }
                    if(good) count++ ;
                } else {
                    d.push(nx);
                }
            }

        }
        return count;
    }

    private List<int[]> next(int[] cur) {

        int i = cur[0];
        int j = cur[1];
        int from = cur[2];
        int type = cur[3];
        int pathCount = cur[4];

        List<int[]> r = new ArrayList<>();

        switch(type) {
           case 1:
               if(from == 0) {
                   r.add(new int[]{i, j+1, 0, 1, pathCount+1});
                   r.add(new int[]{i, j+1, 0, 3, pathCount+1});
                   r.add(new int[]{i, j+1, 0, 4, pathCount+1});
                   r.add(new int[]{i, j+1, 0, 7, pathCount+1});
               } else {
                   r.add(new int[]{i, j-1, 2, 1, pathCount+1});
                   r.add(new int[]{i, j-1, 2, 3, pathCount+1});
                   r.add(new int[]{i, j-1, 2, 5, pathCount+1});
                   r.add(new int[]{i, j-1, 2, 6, pathCount+1});
                }
                break;
            case 2:
               if(from == 1) {
                   r.add(new int[]{i+1, j, 1, 2, pathCount+1});
                   r.add(new int[]{i+1, j, 1, 3, pathCount+1});
                   r.add(new int[]{i+1, j, 1, 4, pathCount+1});
                   r.add(new int[]{i+1, j, 1, 5, pathCount+1});
               } else {
                   r.add(new int[]{i-1, j, 3, 2, pathCount+1});
                   r.add(new int[]{i-1, j, 3, 3, pathCount+1});
                   r.add(new int[]{i-1, j, 3, 6, pathCount+1});
                   r.add(new int[]{i-1, j, 3, 7, pathCount+1});
                }
                break;
            case 3:
               if(from == 0) {
                   r.add(new int[]{i, j+1, 0, 1, pathCount+1});
                   r.add(new int[]{i, j+1, 0, 3, pathCount+1});
                   r.add(new int[]{i, j+1, 0, 4, pathCount+1});
                   r.add(new int[]{i, j+1, 0, 7, pathCount+1});
               } else if(from==1) {
                   r.add(new int[]{i+1, j, 1, 2, pathCount+1});
                   r.add(new int[]{i+1, j, 1, 3, pathCount+1});
                   r.add(new int[]{i+1, j, 1, 4, pathCount+1});
                   r.add(new int[]{i+1, j, 1, 5, pathCount+1});
                } else if(from==2) {
                   r.add(new int[]{i, j-1, 2, 1, pathCount+1});
                   r.add(new int[]{i, j-1, 2, 3, pathCount+1});
                   r.add(new int[]{i, j-1, 2, 5, pathCount+1});
                   r.add(new int[]{i, j-1, 2, 6, pathCount+1});
                } else  {
                   r.add(new int[]{i-1, j, 3, 2, pathCount+1});
                   r.add(new int[]{i-1, j, 3, 3, pathCount+1});
                   r.add(new int[]{i-1, j, 3, 6, pathCount+1});
                   r.add(new int[]{i-1, j, 3, 7, pathCount+1});
                }
                break;
            case 4:
               if(from == 1) {
                  r.add(new int[]{i, j-1, 2, 1, pathCount+1});
                  r.add(new int[]{i, j-1, 2, 3, pathCount+1});
                  r.add(new int[]{i, j-1, 2, 5, pathCount+1});
                  r.add(new int[]{i, j-1, 2, 6, pathCount+1});
               } else {
                   r.add(new int[]{i-1, j, 3, 2, pathCount+1});
                   r.add(new int[]{i-1, j, 3, 3, pathCount+1});
                   r.add(new int[]{i-1, j, 3, 6, pathCount+1});
                   r.add(new int[]{i-1, j, 3, 7, pathCount+1});
                }
                break;
            case 5:
                if(from == 1) {
                  r.add(new int[]{i, j+1, 0, 1, pathCount+1});
                  r.add(new int[]{i, j+1, 0, 3, pathCount+1});
                  r.add(new int[]{i, j+1, 0, 4, pathCount+1});
                  r.add(new int[]{i, j+1, 0, 7, pathCount+1});
               } else {
                   r.add(new int[]{i-1, j, 3, 2, pathCount+1});
                   r.add(new int[]{i-1, j, 3, 3, pathCount+1});
                   r.add(new int[]{i-1, j, 3, 6, pathCount+1});
                   r.add(new int[]{i-1, j, 3, 7, pathCount+1});
                }
                break;
            case 6:
                if(from == 2) {
                  r.add(new int[]{i+1, j, 1, 2, pathCount+1});
                  r.add(new int[]{i+1, j, 1, 3, pathCount+1});
                  r.add(new int[]{i+1, j, 1, 4, pathCount+1});
                  r.add(new int[]{i+1, j, 1, 5, pathCount+1});
               } else {
                   r.add(new int[]{i, j+1, 0, 1, pathCount+1});
                   r.add(new int[]{i, j+1, 0, 3, pathCount+1});
                   r.add(new int[]{i, j+1, 0, 4, pathCount+1});
                   r.add(new int[]{i, j+1, 0, 7, pathCount+1});
                }
                break;
            case 7:
               if(from == 0) {
                  r.add(new int[]{i+1, j, 1, 2, pathCount+1});
                  r.add(new int[]{i+1, j, 1, 3, pathCount+1});
                  r.add(new int[]{i+1, j, 1, 4, pathCount+1});
                  r.add(new int[]{i+1, j, 1, 5, pathCount+1});
               } else {
                   r.add(new int[]{i, j-1, 2, 1, pathCount+1});
                   r.add(new int[]{i, j-1, 2, 3, pathCount+1});
                   r.add(new int[]{i, j-1, 2, 5, pathCount+1});
                   r.add(new int[]{i, j-1, 2, 6, pathCount+1});
                }
                break;
        }
        return r;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(new int[][]{{1,0,-1},{0,0,7},{0,0,2}})); // 기대: 2
        System.out.println(s.solution(new int[][]{{1,0,0,0,0,-1,-1},{-1,0,0,1,0,0,1}})); // 기대: 2
        System.out.println(s.solution(new int[][]{{1,0,0,0,0},{0,0,3,0,2},{0,0,0,0,2}})); // 기대: 4
        System.out.println(s.solution(new int[][]{{1,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,1}})); // 기대: 644
        System.out.println(s.solution(new int[][]{{1,7},{0,2}})); // 기대: 1
        System.out.println(s.solution(new int[][]{{1,-1,0,0},{-1,0,0,0},{0,0,0,-1},{0,0,-1,1}})); // 기대: 0
    }
}
