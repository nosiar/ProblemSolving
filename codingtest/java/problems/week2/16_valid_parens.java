// 올바른 괄호
// https://school.programmers.co.kr/learn/courses/30/lessons/12909
// Lv.2 · Kit: 스택/큐 — 스택 기본

import java.util.*;

class Solution {
    public boolean solution(String s) {
        Deque<Character> d = new ArrayDeque<>();

        for (Character c : s.toCharArray()) {
            if (c == '(') {
                d.push(c);
            } else {
                if (d.isEmpty()) {
                    return false;
                }
                d.pop();
            }
        }
        return d.isEmpty();
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(new Solution().solution("()()")); // 기대: true
    }
}
