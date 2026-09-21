// H-Index
// https://school.programmers.co.kr/learn/courses/30/lessons/42747
// Lv.2 · Kit: 정렬

import java.util.*;

class Solution {
    public int solution(int[] citations) {
        List<Integer> sorted = Arrays.stream(citations)
                .boxed()
                .sorted(Collections.reverseOrder())
                .toList();

        int i = 0;
        while (i < citations.length && sorted.get(i) >= i + 1) {
            i++;
        }
        return i;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(new int[]{3, 0, 6, 1, 5})); // 기대: 3
    }
}
