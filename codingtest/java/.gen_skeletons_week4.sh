#!/usr/bin/env bash
# week4 스켈레톤 생성기 (그래프 + 백트래킹).
# .gen_skeletons.sh 와 동일한 형식. 재생성 시 기존 파일은 덮어쓰지 않는다.
set -euo pipefail
cd "$(dirname "${BASH_SOURCE[0]}")"

# 인자: 파일경로  문제명  링크  카테고리/메모  solution메서드(여러줄)  main본문(여러줄)
emit() {
    local path="$1" title="$2" url="$3" note="$4" sigbody="$5" mainbody="$6"
    if [ -e "$path" ]; then
        echo "skip (이미 있음): $path"
        return
    fi
    mkdir -p "$(dirname "$path")"
    {
        echo "// $title"
        echo "// $url"
        echo "// $note"
        echo ""
        echo "import java.util.*;"
        echo ""
        echo "class Solution {"
        printf '%s\n' "$sigbody"
        echo ""
        echo "    public static void main(String[] args) {"
        echo "        Solution s = new Solution();"
        printf '%s\n' "$mainbody"
        echo "    }"
        echo "}"
    } > "$path"
    echo "created: $path"
}

W=problems/week4

# ──────────────────────────────────────────────────────────
# 그래프 — 다익스트라 / BFS / 유니온파인드
# ──────────────────────────────────────────────────────────

emit "$W/01_delivery.java" "배달" \
"https://school.programmers.co.kr/learn/courses/30/lessons/12978" "Lv.2 · 그래프 — 다익스트라 (시작점 1개 최단거리, K 이하 개수)" \
"    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        // TODO: 1번 마을에서 각 마을까지 최단거리 → K 이하인 마을 수
        return answer;
    }" \
"        System.out.println(s.solution(5, new int[][]{{1,2,1},{2,3,3},{5,2,2},{1,4,2},{5,3,1},{5,4,2}}, 3)); // 기대: 4"

emit "$W/02_taxi_fare.java" "합승 택시 요금" \
"https://school.programmers.co.kr/learn/courses/30/lessons/72413" "Lv.3 · 그래프 — 플로이드 워셜 (2021 카카오, 분기점 탐색)" \
"    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        // TODO: 모든 쌍 최단거리 후, 합승 분기점 k에 대해 dist(s,k)+dist(k,a)+dist(k,b) 최소
        return answer;
    }" \
"        System.out.println(s.solution(6, 4, 6, 2, new int[][]{{4,1,10},{3,5,24},{5,6,2},{3,1,41},{5,1,24},{4,6,50},{2,4,66},{2,3,22},{1,6,25}})); // 기대: 82"

emit "$W/03_farthest_node.java" "가장 먼 노드" \
"https://school.programmers.co.kr/learn/courses/30/lessons/49189" "Lv.3 · 그래프 — BFS (가중치 없는 최단거리, 최대 거리 노드 수)" \
"    public int solution(int n, int[][] edge) {
        int answer = 0;
        // TODO: 1번 노드에서 BFS 거리 → 가장 먼 거리의 노드 개수
        return answer;
    }" \
"        System.out.println(s.solution(6, new int[][]{{3,6},{4,3},{3,2},{1,3},{1,2},{2,4},{5,2}})); // 기대: 3"

emit "$W/04_connect_islands.java" "섬 연결하기" \
"https://school.programmers.co.kr/learn/courses/30/lessons/42861" "Lv.3 · 그래프 — MST (크루스칼 + 유니온파인드)" \
"    public int solution(int n, int[][] costs) {
        int answer = 0;
        // TODO: 최소 신장 트리 비용 (간선 오름차순 정렬 + union-find로 사이클 방지)
        return answer;
    }" \
"        System.out.println(s.solution(4, new int[][]{{0,1,1},{0,2,2},{1,2,5},{1,3,1},{2,3,8}})); // 기대: 4"

emit "$W/05_ranking.java" "순위" \
"https://school.programmers.co.kr/learn/courses/30/lessons/49191" "Lv.3 · 그래프 — 플로이드 워셜 (승패 도달성; 자신과 나머지 모두 비교 가능하면 순위 확정)" \
"    public int solution(int n, int[][] results) {
        int answer = 0;
        // TODO: 이긴/진 관계 도달성 계산 → (이긴수 + 진수 == n-1)인 선수 카운트
        return answer;
    }" \
"        System.out.println(s.solution(5, new int[][]{{4,3},{4,2},{3,2},{1,2},{2,5}})); // 기대: 2"

# ──────────────────────────────────────────────────────────
# 백트래킹 — 완전탐색 심화
# ──────────────────────────────────────────────────────────

emit "$W/06_fatigue.java" "피로도" \
"https://school.programmers.co.kr/learn/courses/30/lessons/87946" "Lv.2 · 백트래킹 — 던전 순열 완전탐색" \
"    public int solution(int k, int[][] dungeons) {
        int answer = 0;
        // TODO: 던전 방문 순서를 백트래킹으로 전부 시도 → 최대 탐험 개수
        return answer;
    }" \
"        System.out.println(s.solution(80, new int[][]{{80,20},{50,40},{30,10}})); // 기대: 3"

emit "$W/07_archery.java" "양궁대회" \
"https://school.programmers.co.kr/learn/courses/30/lessons/92342" "Lv.2 · 백트래킹 — 화살 배분 완전탐색 (2022 카카오, 동점 처리)" \
"    public int[] solution(int n, int[] info) {
        int[] answer = {};
        // TODO: 라이언의 화살 배분을 백트래킹으로 전부 시도 → 점수차 최대 (동점이면 낮은 점수 많이)
        return answer;
    }" \
"        System.out.println(Arrays.toString(s.solution(5, new int[]{2,1,1,1,0,0,0,0,0,0,0}))); // 기대: [0,2,2,0,1,0,0,0,0,0,0]"

emit "$W/08_n_queen.java" "N-Queen" \
"https://school.programmers.co.kr/learn/courses/30/lessons/12952" "Lv.3 · 백트래킹 — N-Queen 배치 경우의 수" \
"    public int solution(int n) {
        int answer = 0;
        // TODO: n x n 체스판에 퀸 n개 서로 공격 못 하게 놓는 경우의 수 (백트래킹)
        return answer;
    }" \
"        System.out.println(s.solution(4)); // 기대: 2"

echo ""
echo "완료."
