// 최단경로 (다익스트라 복습)
// https://www.acmicpc.net/problem/1753
// 입력: V E / K(시작정점) / E줄 "u v w"  (방향 그래프, 가중치 w)
// 출력: 1~V번 정점까지 최단거리 각 줄에. 도달 불가면 "INF"
//
// 제한: V<=20000, E<=300000, w<=10  → O(E log V) 다익스트라
// 11_dijkstra.txt 를 stdin으로 자동 사용 (run.sh)

import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine().trim());

        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i <= V; ++i) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < E; ++i) {
            st = new StringTokenizer(br.readLine().trim());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            adj.get(u).add(new int[]{v, w});   // 방향 그래프: u -> v 만
        }

        int[] dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[K] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{K, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            int u = cur[0];
            if (dist[u] < cur[1]) continue;

            for (int[] e : adj.get(u)) {
                int v = e[0];
                int w = e[1];

                if (dist[v] > dist[u] + w) {
                    dist[v] = dist[u] + w;
                    pq.offer(new int[]{v, dist[v]});
                }
            }
        }

        for (int i = 1; i <= V; ++i) {
            if (dist[i] == Integer.MAX_VALUE)
                sb.append("INF").append("\n");
            else
                sb.append(dist[i]).append("\n");
        }

        System.out.print(sb);
    }
}
