// 가장 큰 수
// https://school.programmers.co.kr/learn/courses/30/lessons/42746
// Lv.2 · Kit: 정렬 — 문자열 정렬 비교자

import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public String solution(int[] numbers) {
        String result = Arrays.stream(numbers)
                .mapToObj(String::valueOf)
                .sorted((x, y) -> (y + x).compareTo(x + y))
                .collect(Collectors.joining());

        return result.charAt(0) == '0' ? "0" : result;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(new int[]{6, 10, 2})); // 기대: 6210
    }
}
