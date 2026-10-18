// 두 큐 합 같게 만들기
// https://school.programmers.co.kr/learn/courses/30/lessons/118667
// (유형 비공개 — 직접 판단해서 풀기. 막히면 힌트 요청)

import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Long> q1 = new ArrayDeque<>();
        Queue<Long> q2 = new ArrayDeque<>();
        long sum1 = 0, sum2 = 0;
        for (int i = 0; i < queue1.length; ++i) {
            q1.offer((long) queue1[i]);
            sum1 += queue1[i];
            q2.offer((long) queue2[i]);
            sum2 += queue2[i];
        }

        for (int i = 0; i < queue1.length * 3; ++i) {
            if (sum1 < sum2) {
                long cur = q2.poll();
                q1.offer(cur);
                sum1 += cur;
                sum2 -= cur;
            } else if (sum1 > sum2) {
                long cur = q1.poll();
                q2.offer(cur);
                sum2 += cur;
                sum1 -= cur;
            } else {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(new int[]{3, 2, 7, 2}, new int[]{4, 6, 5, 1})); // 기대: 2
        System.out.println(s.solution(new int[]{1, 1}, new int[]{1, 5})); // 기대: -1
    }
}
