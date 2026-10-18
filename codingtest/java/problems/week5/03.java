// 튜플
// https://school.programmers.co.kr/learn/courses/30/lessons/64065
// (유형 비공개 — 직접 판단해서 풀기. 막히면 힌트 요청)

import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int[] solution(String s) {
        Map<Integer, Integer> count = new HashMap<>();

        for (String sub : s.substring(2, s.length() - 2).split("[},{]")) {
            String trimmed = sub.trim();
            if (trimmed.isEmpty()) continue;

            count.merge(Integer.parseInt(trimmed), 1, Integer::sum);
        }

        return count.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).mapToInt(Map.Entry::getKey).toArray();
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(Arrays.toString(s.solution("{{2},{2,1},{2,1,3},{2,1,3,4}}"))); // 기대: [2, 1, 3, 4]
    }
}
