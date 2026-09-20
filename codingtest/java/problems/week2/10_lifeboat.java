// 구명보트
// https://school.programmers.co.kr/learn/courses/30/lessons/42885
// Lv.2 · Kit: 탐욕법 — 투포인터 + 정렬

import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);

        int start = 0;
        int end = people.length - 1;
        int x = 0;
        while (start <= end) {
            if (people[start] + people[end] <= limit) {
                x++;
                start++;
                end--;
            } else {
                x++;
                end--;
            }
        }

        return x;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(new int[]{70, 50, 80, 50}, 100)); // 기대: 3
    }
}
