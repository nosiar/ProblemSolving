// 플로이드 (플로이드 워셜 복습)
// https://www.acmicpc.net/problem/11404
// 입력: n(도시) / m(버스) / m줄 "a b c"  (a→b 비용 c, 방향)
// 출력: n x n 행렬. i→j 최단비용, 도달 불가면 0, 자기자신 0.
//
// 제한: n<=100, m<=100000, c<=100000  → O(n^3) 플로이드
//  - 같은 (a,b) 노선 여러 개면 최소 비용만. INF는 더해도 안 터지게 크게.
// 12_floyd.txt 를 stdin으로 자동 사용 (run.sh)

import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine().trim());
        int m = Integer.parseInt(br.readLine().trim());
        int INF = Integer.MAX_VALUE / 2;

        int[][] dist = new int[n + 1][n + 1];
        for (int i = 1; i <= n; ++i) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }
        for (int i = 0; i < m; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (dist[a][b] > c) {
                dist[a][b] = c;
            }
        }

        for (int k = 1; k <= n; ++k) {
            for (int i = 1; i <= n; ++i) {
                for (int j = 1; j <= n; ++j) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                sb.append(dist[i][j] == INF ? 0 : dist[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}
