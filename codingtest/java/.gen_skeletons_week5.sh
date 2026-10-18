#!/usr/bin/env bash
# week5 블라인드 복습 세트.
# ★ 유형 비공개: 헤더에 레벨/유형 힌트 없이 문제명·링크·시그니처·예제만.
set -euo pipefail
cd "$(dirname "${BASH_SOURCE[0]}")"

emit() {
    local path="$1" title="$2" url="$3" sigbody="$4" mainbody="$5"
    if [ -e "$path" ]; then echo "skip: $path"; return; fi
    mkdir -p "$(dirname "$path")"
    {
        echo "// $title"
        echo "// $url"
        echo "// (유형 비공개 — 직접 판단해서 풀기. 막히면 힌트 요청)"
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

W=problems/week5

emit "$W/01.java" "숫자 변환하기" \
"https://school.programmers.co.kr/learn/courses/30/lessons/154538" \
"    public int solution(int x, int y, int n) {
        int answer = 0;
        // TODO
        return answer;
    }" \
"        System.out.println(s.solution(0, 5, 4));    // 기대: 2
        System.out.println(s.solution(10, 40, 5));   // 기대: 4
        System.out.println(s.solution(10, 40, 30));  // 기대: 1"

emit "$W/02.java" "베스트앨범" \
"https://school.programmers.co.kr/learn/courses/30/lessons/42579" \
"    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        // TODO
        return answer;
    }" \
"        System.out.println(Arrays.toString(s.solution(new String[]{\"classic\",\"pop\",\"classic\",\"classic\",\"pop\"}, new int[]{500,600,150,800,2500}))); // 기대: [4, 1, 3, 0]"

emit "$W/03.java" "튜플" \
"https://school.programmers.co.kr/learn/courses/30/lessons/64065" \
"    public int[] solution(String s) {
        int[] answer = {};
        // TODO
        return answer;
    }" \
"        System.out.println(Arrays.toString(s.solution(\"{{2},{2,1},{2,1,3},{2,1,3,4}}\"))); // 기대: [2, 1, 3, 4]"

emit "$W/04.java" "이모티콘 할인행사" \
"https://school.programmers.co.kr/learn/courses/30/lessons/150368" \
"    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = {};
        // TODO
        return answer;
    }" \
"        System.out.println(Arrays.toString(s.solution(new int[][]{{40,10000},{25,10000}}, new int[]{7000,9000}))); // 기대: [0, 10000]"

emit "$W/05.java" "숫자 카드 나누기" \
"https://school.programmers.co.kr/learn/courses/30/lessons/135807" \
"    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        // TODO
        return answer;
    }" \
"        System.out.println(s.solution(new int[]{10,17}, new int[]{5,20})); // 기대: 0
        System.out.println(s.solution(new int[]{10,20}, new int[]{5,17})); // 기대: 10"

emit "$W/06.java" "미로 탈출" \
"https://school.programmers.co.kr/learn/courses/30/lessons/159993" \
"    public int solution(String[] maps) {
        int answer = 0;
        // TODO
        return answer;
    }" \
"        System.out.println(s.solution(new String[]{\"SOOOL\",\"XXXXO\",\"OOOOO\",\"OXXXX\",\"OOOOE\"})); // 기대: 16
        System.out.println(s.solution(new String[]{\"LOOXS\",\"OOOOX\",\"OOOOO\",\"OOOOO\",\"EOOOO\"})); // 기대: -1"

emit "$W/07.java" "연속된 부분 수열의 합" \
"https://school.programmers.co.kr/learn/courses/30/lessons/178870" \
"    public int[] solution(int[] sequence, int k) {
        int[] answer = {};
        // TODO
        return answer;
    }" \
"        System.out.println(Arrays.toString(s.solution(new int[]{1,2,3,4,5}, 7))); // 기대: [2, 3]
        System.out.println(Arrays.toString(s.solution(new int[]{2,2,2,2,2}, 6))); // 기대: [0, 2]"

emit "$W/08.java" "요격 시스템" \
"https://school.programmers.co.kr/learn/courses/30/lessons/181188" \
"    public int solution(int[][] targets) {
        int answer = 0;
        // TODO
        return answer;
    }" \
"        System.out.println(s.solution(new int[][]{{4,5},{4,8},{10,14},{11,13},{5,12},{3,7},{1,4}})); // 기대: 3"

emit "$W/09.java" "두 큐 합 같게 만들기" \
"https://school.programmers.co.kr/learn/courses/30/lessons/118667" \
"    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        // TODO
        return answer;
    }" \
"        System.out.println(s.solution(new int[]{3,2,7,2}, new int[]{4,6,5,1})); // 기대: 2
        System.out.println(s.solution(new int[]{1,1}, new int[]{1,5})); // 기대: -1"

emit "$W/10.java" "석유 시추" \
"https://school.programmers.co.kr/learn/courses/30/lessons/250136" \
"    public int solution(int[][] land) {
        int answer = 0;
        // TODO
        return answer;
    }" \
"        System.out.println(s.solution(new int[][]{{0,0,0,1,1,1,0,0},{0,0,0,0,0,1,1,0},{1,0,0,0,0,0,1,0},{0,0,1,0,0,0,1,0},{0,0,0,0,0,0,1,0},{0,0,0,0,0,0,0,0}})); // 기대: 9"

echo ""
echo "완료."
