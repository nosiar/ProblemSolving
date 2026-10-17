// 양궁대회
// https://school.programmers.co.kr/learn/courses/30/lessons/92342
// Lv.2 · 백트래킹 — 화살 배분 완전탐색 (2022 카카오, 동점 처리)

import java.util.*;

class Solution {
    int[] peach, ryan = new int[11], best = new int[11];
    int bestDiff = 0;

    public int[] solution(int n, int[] info) {
        this.peach = info.clone();

        dfs(n, 0);

        if (bestDiff > 0) {
            return best;
        } else {
            return new int[]{-1};
        }
    }

    private void dfs(int remain, int i) {
        if (i == 10) {
            ryan[i] = remain;

            int p = 0, r = 0;
            for (int k = 0; k <= 10; ++k) {
                if (ryan[k] > peach[k]) {
                    r += 10 - k;
                } else if (peach[k] > 0) {
                    p += 10 - k;
                }
            }
            int diff = r - p;

            if (diff > bestDiff) {
                best = ryan.clone();
                bestDiff = diff;
            } else if (diff == bestDiff) {

                boolean better = false;
                for (int k = 10; k >= 0; --k) {
                    if (best[k] != ryan[k]) {
                        better = ryan[k] > best[k];
                        break;
                    }
                }

                if (better) {
                    best = ryan.clone();
                    bestDiff = diff;
                }
            }

            ryan[i] = 0;
            return;
        }

        if (remain >= peach[i] + 1) {
            ryan[i] = peach[i] + 1;
            dfs(remain - ryan[i], i + 1);
            ryan[i] = 0;
        }

        dfs(remain, i + 1);
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(Arrays.toString(s.solution(5, new int[]{2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0}))); // 기대: [0,2,2,0,1,0,0,0,0,0,0]
    }
}
