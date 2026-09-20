// 조이스틱
// https://school.programmers.co.kr/learn/courses/30/lessons/42860
// Lv.2 · Kit: 탐욕법

import java.util.*;

class Solution {
    public int solution(String name) {
        int x = 0;
        int minMove = name.length() - 1;
        for (int i = 0; i < name.length(); ++i) {
            char c = name.charAt(i);
            x += Math.min(c - 'A', 'Z' - c + 1);

            int next = i + 1;
            while (next < name.length() && name.charAt(next) == 'A') {
                ++next;
            }

            minMove = Math.min(minMove, Math.min(2 * i + name.length() - next, 2 * (name.length() - next) + i));
        }

        return x + minMove;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution("JAN")); // 기대: 23
    }
}
