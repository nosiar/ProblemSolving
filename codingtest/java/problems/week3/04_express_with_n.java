// N으로 표현
// https://school.programmers.co.kr/learn/courses/30/lessons/42895
// Lv.3 · Kit: 동적계획법 — 점화식 세우기

import java.util.*;

class Solution {
    public int solution(int N, int number) {
        Set<Integer>[] x = (Set<Integer>[]) new HashSet[9];
        for (int i = 1; i <= 8; ++i) {
            x[i] = new HashSet<>();
            if (i == 1) {
                x[i].add(N);
            } else {
                x[i].add(x[i - 1].iterator().next() * 10 + N);
            }
        }
        for (int i = 1; i <= 8; ++i) {
            for (int j = 1; j < i; ++j) {
                int k = i - j;
                for (int s0 : x[j]) {
                    for (int s1 : x[k]) {
                        x[i].add(s0 + s1);
                        x[i].add(s0 - s1);
                        x[i].add(s0 * s1);
                        if (s1 != 0) {
                            x[i].add(s0 / s1);
                        }
                    }
                }
            }

            if (x[i].contains(number)) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(5, 12)); // 기대: 4
    }
}
