// 전화번호 목록
// https://school.programmers.co.kr/learn/courses/30/lessons/42577
// Lv.2 · Kit: 해시 — 접두사 체크

import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book);

        for (int i = 0; i < phone_book.length - 1; ++i) {
            if (phone_book[i + 1].startsWith(phone_book[i])) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(new String[]{"119", "97674223", "1195524421"})); // 기대: false
    }
}
