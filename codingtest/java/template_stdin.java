// 표준 입출력형 템플릿 (백준 / 일부 코테) — Java.
// 같은 이름 입력파일(prob.txt/.in) 또는 input.txt를 만들면 run.sh가 stdin으로 넣어준다.
//
// 입력은 BufferedReader + StringTokenizer, 출력은 StringBuilder 한 번에 flush 하는 것이
// Java 코테의 기본 (Scanner/System.out.println 남발은 시간초과의 주범).

import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine());

        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += Long.parseLong(st.nextToken());
        }

        sb.append(sum).append('\n');
        System.out.print(sb); // 마지막에 한 번에 출력
    }
}
