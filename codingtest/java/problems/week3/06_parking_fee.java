// 주차 요금 계산
// https://school.programmers.co.kr/learn/courses/30/lessons/92341
// Lv.2 · 2022 카카오 — 시뮬레이션 + 파싱

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {

        Map<String, Integer> totalTimes = new TreeMap<>();

        for (String s : records) {

            String[] split = s.split(" ");

            int total = totalTimes.getOrDefault(split[1], 0);
            if (split[2].equals("IN")) {
                totalTimes.put(split[1], total - toMinutes(split[0]));
            } else {
                totalTimes.put(split[1], total + toMinutes(split[0]));
            }
        }

        int[] result = new int[totalTimes.size()];

        int i = 0;
        for (String key : totalTimes.keySet()) {
            if (totalTimes.get(key) <= 0) {
                totalTimes.put(key, totalTimes.get(key) + toMinutes("23:59"));
            }

            result[i++] = fees[1] + (Math.max(0, totalTimes.get(key) - fees[0]) + fees[2] - 1) / fees[2] * fees[3];
        }

        return result;
    }

    private int toMinutes(String t) {

        String[] split = t.split(":");

        return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] r = s.solution(new int[]{180, 5000, 10, 600}, new String[]{"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"});
        System.out.println(Arrays.toString(r)); // 기대: [14600, 34400, 5000]
    }
}
