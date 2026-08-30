// 프로그래머스 함수 구현형 템플릿 (Java).
// 제출 시엔 class Solution 부분만 그대로 붙여넣으면 된다 (main은 로컬 테스트용).
//
// 주의: class는 반드시 non-public 으로 둔다 (public이면 파일명과 클래스명이 같아야 하는데,
//       05_target_number.java 처럼 숫자로 시작하는 파일명은 클래스명이 될 수 없음).
//       `java 파일.java` 런처는 파일명과 무관하게 main 있는 클래스를 실행한다.

import java.util.*;

class Solution {
    // ── 여기에 문제의 solution 시그니처를 맞춰 구현 ──
    // 예시: 정수 배열을 받아 합을 반환
    public int solution(int[] arr) {
        int answer = 0;
        for (int x : arr) answer += x;
        return answer;
    }

    // ── 로컬 테스트 (제출 시 제거) ──
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(new int[]{1, 2, 3, 4, 5})); // 기대값: 15
    }
}
