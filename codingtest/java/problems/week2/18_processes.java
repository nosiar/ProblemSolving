// 프로세스
// https://school.programmers.co.kr/learn/courses/30/lessons/42587
// Lv.2 · Kit: 스택/큐 — 우선순위 큐

import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        Queue<int[]> queue = new ArrayDeque<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < priorities.length; ++i) {
            queue.offer(new int[]{i, priorities[i]});
            pq.offer(priorities[i]);
        }

        int r = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[1] == pq.peek()) {
                pq.poll();
                r++;
                if (cur[0] == location) {
                    return r;
                }
            } else {
                queue.offer(cur);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(new int[]{2, 1, 3, 2}, 2)); // 기대: 1
    }
}
