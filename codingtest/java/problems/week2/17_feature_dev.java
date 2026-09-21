// 기능개발
// https://school.programmers.co.kr/learn/courses/30/lessons/42586
// Lv.2 · Kit: 스택/큐 — 큐 시뮬레이션

import java.util.*;


class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> r = new ArrayList<>();

        int maxD = 0;
        for (int i = 0; i < progresses.length; ++i) {

            int d = (100 - progresses[i] + speeds[i] - 1) / speeds[i];

            if (maxD < d) {
                r.add(1);
                maxD = d;
            } else {
                r.set(r.size() - 1, r.getLast() + 1);
            }
        }
        return r.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(Arrays.toString(s.solution(new int[]{93, 30, 55}, new int[]{1, 30, 5}))); // 기대: [2, 1]
    }
}
