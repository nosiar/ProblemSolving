// 합승 택시 요금
// https://school.programmers.co.kr/learn/courses/30/lessons/72413
// Lv.3 · 그래프 — 플로이드 워셜 (2021 카카오, 분기점 탐색)

import java.util.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int INF = 100_000_000;

        int[][] dist = new int[n + 1][n + 1];
        for (int i = 1; i <= n; ++i) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }
        for (int[] fare : fares) {
            dist[fare[0]][fare[1]] = fare[2];
            dist[fare[1]][fare[0]] = fare[2];
        }

        for (int k = 1; k <= n; ++k) {
            for (int i = 1; i <= n; ++i) {
                for (int j = 1; j <= n; ++j) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        int minValue = INF;
        for (int i = 1; i <= n; ++i) {
            int value = dist[s][i] + dist[i][a] + dist[i][b];
            if (minValue > value) {
                minValue = value;
            }
        }
        return minValue;
    }
}
