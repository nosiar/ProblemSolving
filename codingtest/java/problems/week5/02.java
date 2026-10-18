// 베스트앨범
// https://school.programmers.co.kr/learn/courses/30/lessons/42579
// (유형 비공개 — 직접 판단해서 풀기. 막히면 힌트 요청)

import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> count = new HashMap<>();
        Map<String, PriorityQueue<int[]>> songs = new HashMap<>(); // 플레이수, 번호

        for (int i = 0; i < genres.length; ++i) {
            count.merge(genres[i], plays[i], Integer::sum);

            PriorityQueue<int[]> pq = songs.computeIfAbsent(genres[i], (k) -> new PriorityQueue<>(
                    (a, b) -> a[0] != b[0] ? Integer.compare(b[0], a[0]) : Integer.compare(a[1], b[1])));
            pq.offer(new int[]{plays[i], i});
        }

        List<Integer> answer = new ArrayList<>();

        List<String> sorted = count.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).map(Map.Entry::getKey).toList();

        for (String genre : sorted) {

            if (!songs.get(genre).isEmpty()) {
                answer.add(songs.get(genre).poll()[1]);
            }
            if (!songs.get(genre).isEmpty()) {
                answer.add(songs.get(genre).poll()[1]);
            }
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();

    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(Arrays.toString(s.solution(new String[]{"classic", "pop", "classic", "classic", "pop"}, new int[]{500, 600, 150, 800, 2500}))); // 기대: [4, 1, 3, 0]
    }
}
