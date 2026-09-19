// 큰 수 만들기
// https://school.programmers.co.kr/learn/courses/30/lessons/42883
// Kit: 탐욕법 — 스택 그리디

import java.util.*;

class Solution {
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();

        int cur = 0;
        while (cur < number.length()) {

            char n = number.charAt(cur);

            while (k > 0 && !sb.isEmpty() && sb.charAt(sb.length() - 1) < n) {
                sb.deleteCharAt(sb.length() - 1);
                k--;
            }

            sb.append(n);

            ++cur;
        }

        while (k > 0) {
            sb.deleteCharAt(sb.length() - 1);
            k--;
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution("1924", 2)); // 기대: 94
    }
}
