// 의상
// https://school.programmers.co.kr/learn/courses/30/lessons/42578
// Lv.2 · Kit: 해시 — 카운팅 + 경우의 수

import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        Map<String, Integer> c = new HashMap<>();

        for (String[] cl : clothes) {
            c.merge(cl[1], 1, Integer::sum);
        }

        int x = 1;
        for (int v : c.values()) {
            x *= v + 1;
        }

        return x - 1;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(new String[][]{{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}})); // 기대: 5
    }
}
