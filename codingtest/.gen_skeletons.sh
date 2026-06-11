#!/usr/bin/env bash
# PROBLEMS.md 순서대로 문제별 스켈레톤 .cpp를 생성한다.
# 한 번 쓰고 버리는 생성 스크립트. (재생성 시 기존 파일 덮어쓰지 않도록 건너뜀)
set -euo pipefail
cd "$(dirname "${BASH_SOURCE[0]}")"

# 공통 헤더/푸터를 만들어주는 함수
# 인자: 파일경로  문제명  링크  카테고리/메모  solution시그니처본문(여러줄)  main본문(여러줄)
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
        echo "#include <bits/stdc++.h>"
        echo "using namespace std;"
        echo ""
        printf '%s\n' "$sigbody"
        echo ""
        echo "int main() {"
        echo "    ios::sync_with_stdio(false);"
        echo "    cin.tie(nullptr);"
        printf '%s\n' "$mainbody"
        echo "    return 0;"
        echo "}"
    } > "$path"
    echo "created: $path"
}

# ──────────────────────────────────────────────────────────
# 1주차
# ──────────────────────────────────────────────────────────
W=problems/week1

emit "$W/01_two_sum_pairs.cpp" "두 개 뽑아서 더하기" \
"https://school.programmers.co.kr/learn/courses/30/lessons/68644" "완전탐색 + set 중복제거" \
"vector<int> solution(vector<int> numbers) {
    vector<int> answer;
    // TODO
    return answer;
}" \
"    for (int x : solution({2,1,3,4,1})) cout << x << ' ';  // 기대: 2 3 4 5 6 7
    cout << '\n';"

emit "$W/02_no_same_number.cpp" "같은 숫자는 싫어" \
"https://school.programmers.co.kr/learn/courses/30/lessons/12906" "vector 순회/비교" \
"vector<int> solution(vector<int> arr) {
    vector<int> answer;
    // TODO
    return answer;
}" \
"    for (int x : solution({1,1,3,3,0,1,1})) cout << x << ' ';  // 기대: 1 3 0 1
    cout << '\n';"

emit "$W/03_digit_sum.cpp" "자릿수 더하기" \
"https://school.programmers.co.kr/learn/courses/30/lessons/12931" "string<->int" \
"int solution(int n) {
    int answer = 0;
    // TODO
    return answer;
}" \
"    cout << solution(123) << '\n';  // 기대: 6"

emit "$W/04_reverse_digits.cpp" "자연수 뒤집어 배열로 만들기" \
"https://school.programmers.co.kr/learn/courses/30/lessons/12932" "숫자 분해" \
"vector<int> solution(long long n) {
    vector<int> answer;
    // TODO
    return answer;
}" \
"    for (int x : solution(12345)) cout << x << ' ';  // 기대: 5 4 3 2 1
    cout << '\n';"

emit "$W/05_count_p_y.cpp" "문자열 내 p와 y의 개수" \
"https://school.programmers.co.kr/learn/courses/30/lessons/12916" "카운팅 (대소문자 무시)" \
"bool solution(string s) {
    bool answer = true;
    // TODO
    return answer;
}" \
"    cout << boolalpha << solution(\"pPoooyY\") << '\n';  // 기대: true"

emit "$W/06_string_desc.cpp" "문자열 내림차순으로 배치하기" \
"https://school.programmers.co.kr/learn/courses/30/lessons/12917" "sort + 비교자" \
"string solution(string s) {
    string answer = \"\";
    // TODO
    return answer;
}" \
"    cout << solution(\"Zbcdefg\") << '\n';  // 기대: gfedcbZ"

emit "$W/07_unfinished_player.cpp" "완주하지 못한 선수" \
"https://school.programmers.co.kr/learn/courses/30/lessons/42576" "Kit: 해시 — map/unordered_map 카운팅 핵심" \
"string solution(vector<string> participant, vector<string> completion) {
    string answer = \"\";
    // TODO
    return answer;
}" \
"    cout << solution({\"leo\",\"kiki\",\"eden\"}, {\"eden\",\"kiki\"}) << '\n';  // 기대: leo"

emit "$W/08_kth_number.cpp" "K번째수" \
"https://school.programmers.co.kr/learn/courses/30/lessons/42748" "Kit: 정렬 — 부분 vector + sort" \
"vector<int> solution(vector<int> array, vector<vector<int>> commands) {
    vector<int> answer;
    // TODO
    return answer;
}" \
"    for (int x : solution({1,5,2,6,3,7,4}, {{2,5,3},{4,4,1},{1,7,3}})) cout << x << ' ';  // 기대: 5 6 3
    cout << '\n';"

emit "$W/09_mock_exam.cpp" "모의고사" \
"https://school.programmers.co.kr/learn/courses/30/lessons/42840" "Kit: 완전탐색 — 패턴 매칭" \
"vector<int> solution(vector<int> answers) {
    vector<int> answer;
    // TODO
    return answer;
}" \
"    for (int x : solution({1,2,3,4,5})) cout << x << ' ';  // 기대: 1
    cout << '\n';"

emit "$W/10_pokemon.cpp" "폰켓몬" \
"https://school.programmers.co.kr/learn/courses/30/lessons/1845" "Kit: 해시 — set으로 종류 세기" \
"int solution(vector<int> nums) {
    int answer = 0;
    // TODO
    return answer;
}" \
"    cout << solution({3,1,2,3}) << '\n';  // 기대: 2"

emit "$W/11_gym_clothes.cpp" "체육복" \
"https://school.programmers.co.kr/learn/courses/30/lessons/42862" "Kit: 탐욕법 — 그리디 워밍업" \
"int solution(int n, vector<int> lost, vector<int> reserve) {
    int answer = 0;
    // TODO
    return answer;
}" \
"    cout << solution(5, {2,4}, {1,3,5}) << '\n';  // 기대: 5"

# ──────────────────────────────────────────────────────────
# 2주차
# ──────────────────────────────────────────────────────────
W=problems/week2

emit "$W/01_carpet.cpp" "카펫" \
"https://school.programmers.co.kr/learn/courses/30/lessons/42842" "Kit: 완전탐색 — 약수 탐색" \
"vector<int> solution(int brown, int yellow) {
    vector<int> answer;
    // TODO
    return answer;
}" \
"    for (int x : solution(10, 2)) cout << x << ' ';  // 기대: 4 3
    cout << '\n';"

emit "$W/02_find_primes.cpp" "소수 찾기" \
"https://school.programmers.co.kr/learn/courses/30/lessons/42839" "Kit: 완전탐색 — next_permutation + 소수판정" \
"int solution(string numbers) {
    int answer = 0;
    // TODO
    return answer;
}" \
"    cout << solution(\"17\") << '\n';  // 기대: 3"

emit "$W/03_vowel_dict.cpp" "모음 사전" \
"https://school.programmers.co.kr/learn/courses/30/lessons/84512" "Kit: 완전탐색 — DFS" \
"int solution(string word) {
    int answer = 0;
    // TODO
    return answer;
}" \
"    cout << solution(\"AAAAE\") << '\n';  // 기대: 6"

emit "$W/04_distance_check.cpp" "거리두기 확인하기" \
"https://school.programmers.co.kr/learn/courses/30/lessons/81302" "2021 카카오 — 격자 BFS" \
"vector<int> solution(vector<vector<string>> places) {
    vector<int> answer;
    // TODO
    return answer;
}" \
"    // 예제는 입력이 길어 생략. 채점은 프로그래머스에서.
    cout << \"run on programmers\" << '\n';"

emit "$W/05_target_number.cpp" "타겟 넘버" \
"https://school.programmers.co.kr/learn/courses/30/lessons/43165" "Kit: DFS/BFS — DFS 완전탐색" \
"int solution(vector<int> numbers, int target) {
    int answer = 0;
    // TODO
    return answer;
}" \
"    cout << solution({1,1,1,1,1}, 3) << '\n';  // 기대: 5"

emit "$W/06_shortest_path_map.cpp" "게임 맵 최단거리" \
"https://school.programmers.co.kr/learn/courses/30/lessons/1844" "Kit: DFS/BFS — 격자 BFS 정석" \
"int solution(vector<vector<int>> maps) {
    int answer = 0;
    // TODO
    return answer;
}" \
"    cout << solution({{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}}) << '\n';  // 기대: 11"

emit "$W/07_network.cpp" "네트워크" \
"https://school.programmers.co.kr/learn/courses/30/lessons/43162" "Kit: DFS/BFS — 연결요소" \
"int solution(int n, vector<vector<int>> computers) {
    int answer = 0;
    // TODO
    return answer;
}" \
"    cout << solution(3, {{1,1,0},{1,1,0},{0,0,1}}) << '\n';  // 기대: 2"

emit "$W/08_word_transform.cpp" "단어 변환" \
"https://school.programmers.co.kr/learn/courses/30/lessons/43163" "Kit: DFS/BFS — 상태 그래프 BFS" \
"int solution(string begin, string target, vector<string> words) {
    int answer = 0;
    // TODO
    return answer;
}" \
"    cout << solution(\"hit\", \"cog\", {\"hot\",\"dot\",\"dog\",\"lot\",\"log\",\"cog\"}) << '\n';  // 기대: 4"

emit "$W/09_make_big_number.cpp" "큰 수 만들기" \
"https://school.programmers.co.kr/learn/courses/30/lessons/42883" "Kit: 탐욕법 — 스택 그리디" \
"string solution(string number, int k) {
    string answer = \"\";
    // TODO
    return answer;
}" \
"    cout << solution(\"1924\", 2) << '\n';  // 기대: 94"

emit "$W/10_lifeboat.cpp" "구명보트" \
"https://school.programmers.co.kr/learn/courses/30/lessons/42885" "Kit: 탐욕법 — 투포인터 + 정렬" \
"int solution(vector<int> people, int limit) {
    int answer = 0;
    // TODO
    return answer;
}" \
"    cout << solution({70,50,80,50}, 100) << '\n';  // 기대: 3"

emit "$W/11_joystick.cpp" "조이스틱" \
"https://school.programmers.co.kr/learn/courses/30/lessons/42860" "Kit: 탐욕법" \
"int solution(string name) {
    int answer = 0;
    // TODO
    return answer;
}" \
"    cout << solution(\"JAN\") << '\n';  // 기대: 23"

emit "$W/12_biggest_number.cpp" "가장 큰 수" \
"https://school.programmers.co.kr/learn/courses/30/lessons/42746" "Kit: 정렬 — 문자열 정렬 비교자" \
"string solution(vector<int> numbers) {
    string answer = \"\";
    // TODO
    return answer;
}" \
"    cout << solution({6,10,2}) << '\n';  // 기대: 6210"

emit "$W/13_h_index.cpp" "H-Index" \
"https://school.programmers.co.kr/learn/courses/30/lessons/42747" "Kit: 정렬" \
"int solution(vector<int> citations) {
    int answer = 0;
    // TODO
    return answer;
}" \
"    cout << solution({3,0,6,1,5}) << '\n';  // 기대: 3"

emit "$W/14_phone_book.cpp" "전화번호 목록" \
"https://school.programmers.co.kr/learn/courses/30/lessons/42577" "Kit: 해시 — 접두사 체크" \
"bool solution(vector<string> phone_book) {
    bool answer = true;
    // TODO
    return answer;
}" \
"    cout << boolalpha << solution({\"119\",\"97674223\",\"1195524421\"}) << '\n';  // 기대: false"

emit "$W/15_clothes.cpp" "의상" \
"https://school.programmers.co.kr/learn/courses/30/lessons/42578" "Kit: 해시 — 카운팅 + 경우의 수" \
"int solution(vector<vector<string>> clothes) {
    int answer = 0;
    // TODO
    return answer;
}" \
"    cout << solution({{\"yellow_hat\",\"headgear\"},{\"blue_sunglasses\",\"eyewear\"},{\"green_turban\",\"headgear\"}}) << '\n';  // 기대: 5"

emit "$W/16_valid_parens.cpp" "올바른 괄호" \
"https://school.programmers.co.kr/learn/courses/30/lessons/12909" "Kit: 스택/큐 — 스택 기본" \
"bool solution(string s) {
    bool answer = true;
    // TODO
    return answer;
}" \
"    cout << boolalpha << solution(\"()()\") << '\n';  // 기대: true"

emit "$W/17_feature_dev.cpp" "기능개발" \
"https://school.programmers.co.kr/learn/courses/30/lessons/42586" "Kit: 스택/큐 — 큐 시뮬레이션" \
"vector<int> solution(vector<int> progresses, vector<int> speeds) {
    vector<int> answer;
    // TODO
    return answer;
}" \
"    for (int x : solution({93,30,55}, {1,30,5})) cout << x << ' ';  // 기대: 2 1
    cout << '\n';"

emit "$W/18_processes.cpp" "프로세스" \
"https://school.programmers.co.kr/learn/courses/30/lessons/42587" "Kit: 스택/큐 — 우선순위 큐" \
"int solution(vector<int> priorities, int location) {
    int answer = 0;
    // TODO
    return answer;
}" \
"    cout << solution({2,1,3,2}, 2) << '\n';  // 기대: 1"

emit "$W/19_immigration.cpp" "입국심사" \
"https://school.programmers.co.kr/learn/courses/30/lessons/43238" "Kit: 이분탐색 — 파라메트릭 서치 정석" \
"long long solution(int n, vector<int> times) {
    long long answer = 0;
    // TODO
    return answer;
}" \
"    cout << solution(6, {7,10}) << '\n';  // 기대: 28"

emit "$W/20_stepping_stones.cpp" "징검다리" \
"https://school.programmers.co.kr/learn/courses/30/lessons/43236" "Kit: 이분탐색 — 파라메트릭 서치" \
"int solution(int distance, vector<int> rocks, int n) {
    int answer = 0;
    // TODO
    return answer;
}" \
"    cout << solution(25, {2,14,11,21,17}, 2) << '\n';  // 기대: 4"

# ──────────────────────────────────────────────────────────
# 3주차
# ──────────────────────────────────────────────────────────
W=problems/week3

emit "$W/01_integer_triangle.cpp" "정수 삼각형" \
"https://school.programmers.co.kr/learn/courses/30/lessons/43105" "Kit: 동적계획법 — 경로 DP" \
"int solution(vector<vector<int>> triangle) {
    int answer = 0;
    // TODO
    return answer;
}" \
"    cout << solution({{7},{3,8},{8,1,0},{2,7,4,4},{4,5,2,6,5}}) << '\n';  // 기대: 30"

emit "$W/02_school_path.cpp" "등굣길" \
"https://school.programmers.co.kr/learn/courses/30/lessons/42898" "Kit: 동적계획법 — 격자 경로 DP + 모듈러" \
"int solution(int m, int n, vector<vector<int>> puddles) {
    int answer = 0;
    // TODO
    return answer;
}" \
"    cout << solution(4, 3, {{2,2}}) << '\n';  // 기대: 4"

emit "$W/03_thief.cpp" "도둑질" \
"https://school.programmers.co.kr/learn/courses/30/lessons/42897" "Kit: 동적계획법 — 원형 DP (Lv.3)" \
"int solution(vector<int> money) {
    int answer = 0;
    // TODO
    return answer;
}" \
"    cout << solution({1,2,3,1}) << '\n';  // 기대: 4"

emit "$W/04_express_with_n.cpp" "N으로 표현" \
"https://school.programmers.co.kr/learn/courses/30/lessons/42895" "Kit: 동적계획법 — 점화식 세우기" \
"int solution(int N, int number) {
    int answer = 0;
    // TODO
    return answer;
}" \
"    cout << solution(5, 12) << '\n';  // 기대: 4"

emit "$W/05_closest_same_char.cpp" "가장 가까운 같은 글자" \
"https://school.programmers.co.kr/learn/courses/30/lessons/142086" "해시 + 인덱스" \
"vector<int> solution(string s) {
    vector<int> answer;
    // TODO
    return answer;
}" \
"    for (int x : solution(\"banana\")) cout << x << ' ';  // 기대: -1 -1 -1 2 2 2
    cout << '\n';"

emit "$W/06_parking_fee.cpp" "주차 요금 계산" \
"https://school.programmers.co.kr/learn/courses/30/lessons/92341" "2022 카카오 — 시뮬레이션 + 파싱" \
"vector<int> solution(vector<int> fees, vector<string> records) {
    vector<int> answer;
    // TODO
    return answer;
}" \
"    auto r = solution({180,5000,10,600}, {\"05:34 5961 IN\",\"06:00 0000 IN\",\"06:34 0000 OUT\",\"07:59 5961 OUT\",\"07:59 0148 IN\",\"18:59 0000 IN\",\"19:09 0148 OUT\",\"22:59 5961 IN\",\"23:00 5961 OUT\"});
    for (int x : r) cout << x << ' ';  // 기대: 14600 34400 5000
    cout << '\n';"

echo ""
echo "완료."
