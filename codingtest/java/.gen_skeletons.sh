#!/usr/bin/env bash
# PROBLEMS.md 순서대로 문제별 Java 스켈레톤(.java)을 생성한다.
# C++ 쪽 ../.gen_skeletons.sh 의 자바 버전 (같은 문제/순서/메모, 시그니처만 Java).
# 재생성 시 기존 파일은 덮어쓰지 않는다.
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

# ──────────────────────────────────────────────────────────
# 1주차
# ──────────────────────────────────────────────────────────
W=problems/week1

emit "$W/01_two_sum_pairs.java" "두 개 뽑아서 더하기" \
"https://school.programmers.co.kr/learn/courses/30/lessons/68644" "완전탐색 + set 중복제거" \
"    public int[] solution(int[] numbers) {
        int[] answer = {};
        // TODO
        return answer;
    }" \
"        System.out.println(Arrays.toString(s.solution(new int[]{2, 1, 3, 4, 1}))); // 기대: [2, 3, 4, 5, 6, 7]"

emit "$W/02_no_same_number.java" "같은 숫자는 싫어" \
"https://school.programmers.co.kr/learn/courses/30/lessons/12906" "배열 순회/비교" \
"    public int[] solution(int[] arr) {
        int[] answer = {};
        // TODO
        return answer;
    }" \
"        System.out.println(Arrays.toString(s.solution(new int[]{1, 1, 3, 3, 0, 1, 1}))); // 기대: [1, 3, 0, 1]"

emit "$W/03_digit_sum.java" "자릿수 더하기" \
"https://school.programmers.co.kr/learn/courses/30/lessons/12931" "String<->int" \
"    public int solution(int n) {
        int answer = 0;
        // TODO
        return answer;
    }" \
"        System.out.println(s.solution(123)); // 기대: 6"

emit "$W/04_reverse_digits.java" "자연수 뒤집어 배열로 만들기" \
"https://school.programmers.co.kr/learn/courses/30/lessons/12932" "숫자 분해" \
"    public int[] solution(long n) {
        int[] answer = {};
        // TODO
        return answer;
    }" \
"        System.out.println(Arrays.toString(s.solution(12345))); // 기대: [5, 4, 3, 2, 1]"

emit "$W/05_count_p_y.java" "문자열 내 p와 y의 개수" \
"https://school.programmers.co.kr/learn/courses/30/lessons/12916" "카운팅 (대소문자 무시)" \
"    public boolean solution(String str) {
        boolean answer = true;
        // TODO
        return answer;
    }" \
"        System.out.println(s.solution(\"pPoooyY\")); // 기대: true"

emit "$W/06_string_desc.java" "문자열 내림차순으로 배치하기" \
"https://school.programmers.co.kr/learn/courses/30/lessons/12917" "sort + 비교자" \
"    public String solution(String str) {
        String answer = \"\";
        // TODO
        return answer;
    }" \
"        System.out.println(s.solution(\"Zbcdefg\")); // 기대: gfedcbZ"

emit "$W/07_unfinished_player.java" "완주하지 못한 선수" \
"https://school.programmers.co.kr/learn/courses/30/lessons/42576" "Kit: 해시 — HashMap 카운팅 핵심" \
"    public String solution(String[] participant, String[] completion) {
        String answer = \"\";
        // TODO
        return answer;
    }" \
"        System.out.println(s.solution(new String[]{\"leo\", \"kiki\", \"eden\"}, new String[]{\"eden\", \"kiki\"})); // 기대: leo"

emit "$W/08_kth_number.java" "K번째수" \
"https://school.programmers.co.kr/learn/courses/30/lessons/42748" "Kit: 정렬 — 부분 배열 + sort" \
"    public int[] solution(int[] array, int[][] commands) {
        int[] answer = {};
        // TODO
        return answer;
    }" \
"        System.out.println(Arrays.toString(s.solution(new int[]{1, 5, 2, 6, 3, 7, 4}, new int[][]{{2, 5, 3}, {4, 4, 1}, {1, 7, 3}}))); // 기대: [5, 6, 3]"

emit "$W/09_mock_exam.java" "모의고사" \
"https://school.programmers.co.kr/learn/courses/30/lessons/42840" "Kit: 완전탐색 — 패턴 매칭" \
"    public int[] solution(int[] answers) {
        int[] answer = {};
        // TODO
        return answer;
    }" \
"        System.out.println(Arrays.toString(s.solution(new int[]{1, 2, 3, 4, 5}))); // 기대: [1]"

emit "$W/10_pokemon.java" "폰켓몬" \
"https://school.programmers.co.kr/learn/courses/30/lessons/1845" "Kit: 해시 — set으로 종류 세기" \
"    public int solution(int[] nums) {
        int answer = 0;
        // TODO
        return answer;
    }" \
"        System.out.println(s.solution(new int[]{3, 1, 2, 3})); // 기대: 2"

emit "$W/11_gym_clothes.java" "체육복" \
"https://school.programmers.co.kr/learn/courses/30/lessons/42862" "Kit: 탐욕법 — 그리디 워밍업" \
"    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        // TODO
        return answer;
    }" \
"        System.out.println(s.solution(5, new int[]{2, 4}, new int[]{1, 3, 5})); // 기대: 5"

# ──────────────────────────────────────────────────────────
# 2주차
# ──────────────────────────────────────────────────────────
W=problems/week2

emit "$W/01_carpet.java" "카펫" \
"https://school.programmers.co.kr/learn/courses/30/lessons/42842" "Kit: 완전탐색 — 약수 탐색" \
"    public int[] solution(int brown, int yellow) {
        int[] answer = {};
        // TODO
        return answer;
    }" \
"        System.out.println(Arrays.toString(s.solution(10, 2))); // 기대: [4, 3]"

emit "$W/02_find_primes.java" "소수 찾기" \
"https://school.programmers.co.kr/learn/courses/30/lessons/42839" "Kit: 완전탐색 — 순열 + 소수판정 (Java엔 next_permutation 없음, 직접 구현)" \
"    public int solution(String numbers) {
        int answer = 0;
        // TODO
        return answer;
    }" \
"        System.out.println(s.solution(\"17\")); // 기대: 3"

emit "$W/03_vowel_dict.java" "모음 사전" \
"https://school.programmers.co.kr/learn/courses/30/lessons/84512" "Kit: 완전탐색 — DFS" \
"    public int solution(String word) {
        int answer = 0;
        // TODO
        return answer;
    }" \
"        System.out.println(s.solution(\"AAAAE\")); // 기대: 6"

emit "$W/04_distance_check.java" "거리두기 확인하기" \
"https://school.programmers.co.kr/learn/courses/30/lessons/81302" "2021 카카오 — 격자 BFS" \
"    public int[] solution(String[][] places) {
        int[] answer = {};
        // TODO
        return answer;
    }" \
"        System.out.println(\"run on programmers\"); // 예제 입력이 길어 생략, 채점은 프로그래머스에서"

emit "$W/05_target_number.java" "타겟 넘버" \
"https://school.programmers.co.kr/learn/courses/30/lessons/43165" "Kit: DFS/BFS — DFS 완전탐색" \
"    public int solution(int[] numbers, int target) {
        int answer = 0;
        // TODO
        return answer;
    }" \
"        System.out.println(s.solution(new int[]{1, 1, 1, 1, 1}, 3)); // 기대: 5"

emit "$W/06_shortest_path_map.java" "게임 맵 최단거리" \
"https://school.programmers.co.kr/learn/courses/30/lessons/1844" "Kit: DFS/BFS — 격자 BFS 정석" \
"    public int solution(int[][] maps) {
        int answer = 0;
        // TODO
        return answer;
    }" \
"        System.out.println(s.solution(new int[][]{{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 1}, {0, 0, 0, 0, 1}})); // 기대: 11"

emit "$W/07_network.java" "네트워크" \
"https://school.programmers.co.kr/learn/courses/30/lessons/43162" "Kit: DFS/BFS — 연결요소" \
"    public int solution(int n, int[][] computers) {
        int answer = 0;
        // TODO
        return answer;
    }" \
"        System.out.println(s.solution(3, new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}})); // 기대: 2"

emit "$W/08_word_transform.java" "단어 변환" \
"https://school.programmers.co.kr/learn/courses/30/lessons/43163" "Kit: DFS/BFS — 상태 그래프 BFS" \
"    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        // TODO
        return answer;
    }" \
"        System.out.println(s.solution(\"hit\", \"cog\", new String[]{\"hot\", \"dot\", \"dog\", \"lot\", \"log\", \"cog\"})); // 기대: 4"

emit "$W/09_make_big_number.java" "큰 수 만들기" \
"https://school.programmers.co.kr/learn/courses/30/lessons/42883" "Kit: 탐욕법 — 스택 그리디" \
"    public String solution(String number, int k) {
        String answer = \"\";
        // TODO
        return answer;
    }" \
"        System.out.println(s.solution(\"1924\", 2)); // 기대: 94"

emit "$W/10_lifeboat.java" "구명보트" \
"https://school.programmers.co.kr/learn/courses/30/lessons/42885" "Kit: 탐욕법 — 투포인터 + 정렬" \
"    public int solution(int[] people, int limit) {
        int answer = 0;
        // TODO
        return answer;
    }" \
"        System.out.println(s.solution(new int[]{70, 50, 80, 50}, 100)); // 기대: 3"

emit "$W/11_joystick.java" "조이스틱" \
"https://school.programmers.co.kr/learn/courses/30/lessons/42860" "Kit: 탐욕법" \
"    public int solution(String name) {
        int answer = 0;
        // TODO
        return answer;
    }" \
"        System.out.println(s.solution(\"JAN\")); // 기대: 23"

emit "$W/12_biggest_number.java" "가장 큰 수" \
"https://school.programmers.co.kr/learn/courses/30/lessons/42746" "Kit: 정렬 — 문자열 정렬 비교자" \
"    public String solution(int[] numbers) {
        String answer = \"\";
        // TODO
        return answer;
    }" \
"        System.out.println(s.solution(new int[]{6, 10, 2})); // 기대: 6210"

emit "$W/13_h_index.java" "H-Index" \
"https://school.programmers.co.kr/learn/courses/30/lessons/42747" "Kit: 정렬" \
"    public int solution(int[] citations) {
        int answer = 0;
        // TODO
        return answer;
    }" \
"        System.out.println(s.solution(new int[]{3, 0, 6, 1, 5})); // 기대: 3"

emit "$W/14_phone_book.java" "전화번호 목록" \
"https://school.programmers.co.kr/learn/courses/30/lessons/42577" "Kit: 해시 — 접두사 체크" \
"    public boolean solution(String[] phoneBook) {
        boolean answer = true;
        // TODO
        return answer;
    }" \
"        System.out.println(s.solution(new String[]{\"119\", \"97674223\", \"1195524421\"})); // 기대: false"

emit "$W/15_clothes.java" "의상" \
"https://school.programmers.co.kr/learn/courses/30/lessons/42578" "Kit: 해시 — 카운팅 + 경우의 수" \
"    public int solution(String[][] clothes) {
        int answer = 0;
        // TODO
        return answer;
    }" \
"        System.out.println(s.solution(new String[][]{{\"yellow_hat\", \"headgear\"}, {\"blue_sunglasses\", \"eyewear\"}, {\"green_turban\", \"headgear\"}})); // 기대: 5"

emit "$W/16_valid_parens.java" "올바른 괄호" \
"https://school.programmers.co.kr/learn/courses/30/lessons/12909" "Kit: 스택/큐 — 스택 기본" \
"    public boolean solution(String s) {
        boolean answer = true;
        // TODO
        return answer;
    }" \
"        System.out.println(new Solution().solution(\"()()\")); // 기대: true"

emit "$W/17_feature_dev.java" "기능개발" \
"https://school.programmers.co.kr/learn/courses/30/lessons/42586" "Kit: 스택/큐 — 큐 시뮬레이션" \
"    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        // TODO
        return answer;
    }" \
"        System.out.println(Arrays.toString(s.solution(new int[]{93, 30, 55}, new int[]{1, 30, 5}))); // 기대: [2, 1]"

emit "$W/18_processes.java" "프로세스" \
"https://school.programmers.co.kr/learn/courses/30/lessons/42587" "Kit: 스택/큐 — 우선순위 큐" \
"    public int solution(int[] priorities, int location) {
        int answer = 0;
        // TODO
        return answer;
    }" \
"        System.out.println(s.solution(new int[]{2, 1, 3, 2}, 2)); // 기대: 1"

emit "$W/19_immigration.java" "입국심사" \
"https://school.programmers.co.kr/learn/courses/30/lessons/43238" "Kit: 이분탐색 — 파라메트릭 서치 정석" \
"    public long solution(int n, int[] times) {
        long answer = 0;
        // TODO
        return answer;
    }" \
"        System.out.println(s.solution(6, new int[]{7, 10})); // 기대: 28"

emit "$W/20_stepping_stones.java" "징검다리" \
"https://school.programmers.co.kr/learn/courses/30/lessons/43236" "Kit: 이분탐색 — 파라메트릭 서치" \
"    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        // TODO
        return answer;
    }" \
"        System.out.println(s.solution(25, new int[]{2, 14, 11, 21, 17}, 2)); // 기대: 4"

# ──────────────────────────────────────────────────────────
# 3주차
# ──────────────────────────────────────────────────────────
W=problems/week3

emit "$W/01_integer_triangle.java" "정수 삼각형" \
"https://school.programmers.co.kr/learn/courses/30/lessons/43105" "Kit: 동적계획법 — 경로 DP" \
"    public int solution(int[][] triangle) {
        int answer = 0;
        // TODO
        return answer;
    }" \
"        System.out.println(s.solution(new int[][]{{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}})); // 기대: 30"

emit "$W/02_school_path.java" "등굣길" \
"https://school.programmers.co.kr/learn/courses/30/lessons/42898" "Kit: 동적계획법 — 격자 경로 DP + 모듈러" \
"    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        // TODO
        return answer;
    }" \
"        System.out.println(s.solution(4, 3, new int[][]{{2, 2}})); // 기대: 4"

emit "$W/03_thief.java" "도둑질" \
"https://school.programmers.co.kr/learn/courses/30/lessons/42897" "Kit: 동적계획법 — 원형 DP (Lv.3)" \
"    public int solution(int[] money) {
        int answer = 0;
        // TODO
        return answer;
    }" \
"        System.out.println(s.solution(new int[]{1, 2, 3, 1})); // 기대: 4"

emit "$W/04_express_with_n.java" "N으로 표현" \
"https://school.programmers.co.kr/learn/courses/30/lessons/42895" "Kit: 동적계획법 — 점화식 세우기" \
"    public int solution(int N, int number) {
        int answer = 0;
        // TODO
        return answer;
    }" \
"        System.out.println(s.solution(5, 12)); // 기대: 4"

emit "$W/05_closest_same_char.java" "가장 가까운 같은 글자" \
"https://school.programmers.co.kr/learn/courses/30/lessons/142086" "해시 + 인덱스" \
"    public int[] solution(String str) {
        int[] answer = {};
        // TODO
        return answer;
    }" \
"        System.out.println(Arrays.toString(s.solution(\"banana\"))); // 기대: [-1, -1, -1, 2, 2, 2]"

emit "$W/06_parking_fee.java" "주차 요금 계산" \
"https://school.programmers.co.kr/learn/courses/30/lessons/92341" "2022 카카오 — 시뮬레이션 + 파싱" \
"    public int[] solution(int[] fees, String[] records) {
        int[] answer = {};
        // TODO
        return answer;
    }" \
"        int[] r = s.solution(new int[]{180, 5000, 10, 600}, new String[]{\"05:34 5961 IN\", \"06:00 0000 IN\", \"06:34 0000 OUT\", \"07:59 5961 OUT\", \"07:59 0148 IN\", \"18:59 0000 IN\", \"19:09 0148 OUT\", \"22:59 5961 IN\", \"23:00 5961 OUT\"});
        System.out.println(Arrays.toString(r)); // 기대: [14600, 34400, 5000]"

echo ""
echo "완료."
