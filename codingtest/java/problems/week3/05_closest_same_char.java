// 가장 가까운 같은 글자
// https://school.programmers.co.kr/learn/courses/30/lessons/142086
// Lv.1 · 해시 + 인덱스

import java.util.*;

class Solution {
    public int[] solution(String s) {
        int[] x = new int[26];
        for (int i = 0; i < 26; ++i) x[i] = -1;
        int[] r = new int[s.length()];
        for (int i = 0; i < s.length(); ++i) {
            int prevIndex = x[s.charAt(i) - 'a'];
            r[i] = prevIndex == -1 ? -1 : i - prevIndex;
            x[s.charAt(i) - 'a'] = i;
        }
        return r;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(Arrays.toString(s.solution("banana"))); // 기대: [-1, -1, -1, 2, 2, 2]
    }
}
