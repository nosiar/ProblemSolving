// 숫자 카드 나누기
// https://school.programmers.co.kr/learn/courses/30/lessons/135807
// (유형 비공개 — 직접 판단해서 풀기. 막히면 힌트 요청)

import java.util.*;

class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        return Math.max(getA(arrayA, arrayB), getA(arrayB, arrayA));
    }

    private int getA(int[] arrayA, int[] arrayB) {
        int gcdA = arrayA[0];
        for (int i = 1; i < arrayA.length; ++i) {
            gcdA = gcd(gcdA, arrayA[i]);
        }

        boolean good = true;
        for (int j : arrayB) {
            if (j % gcdA == 0) {
                good = false;
                break;
            }
        }

        return good ? gcdA : 0;
    }

    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(new int[]{10, 17}, new int[]{5, 20})); // 기대: 0
        System.out.println(s.solution(new int[]{10, 20}, new int[]{5, 17})); // 기대: 10
    }
}
