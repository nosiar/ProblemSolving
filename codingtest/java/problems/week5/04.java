// 이모티콘 할인행사
// https://school.programmers.co.kr/learn/courses/30/lessons/150368
// (유형 비공개 — 직접 판단해서 풀기. 막히면 힌트 요청)

import java.util.*;

class Solution {
    int maxPlus = 0;
    int maxAmount = 0;
    int[] emoticonDiscount;
    int[] discount = new int[]{10, 20, 30, 40};

    public int[] solution(int[][] users, int[] emoticons) {
        emoticonDiscount = new int[emoticons.length];

        dfs(0, users, emoticons);

        return new int[]{maxPlus, maxAmount};
    }

    private void dfs(int idx, int[][] users, int[] emoticons) {
        if (idx == emoticons.length) {
            int plus = 0;
            int totalAmount = 0;
            for (int[] user : users) {
                int amount = 0;
                for (int j = 0; j < emoticons.length; ++j) {
                    if (emoticonDiscount[j] >= user[0]) {
                        amount += emoticons[j] * (100 - emoticonDiscount[j]) / 100;
                    }
                }
                if (amount >= user[1]) {
                    plus++;
                } else {
                    totalAmount += amount;
                }
            }

            if (plus > maxPlus || plus == maxPlus && maxAmount < totalAmount) {
                maxPlus = plus;
                maxAmount = totalAmount;
            }

            return;
        }

        for (int i = 0; i < 4; ++i) {
            emoticonDiscount[idx] = discount[i];
            dfs(idx + 1, users, emoticons);
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(Arrays.toString(s.solution(new int[][]{{40, 10000}, {25, 10000}}, new int[]{7000, 9000}))); // 기대: [1, 5400]
        System.out.println(Arrays.toString(s.solution(new int[][]{{40,2900},{23,10000},{11,5200},{5,5900},{40,3100},{27,9200},{32,6900}}, new int[]{1300,1500,1600,4900}))); // 기대: [4, 13860]
    }
}
