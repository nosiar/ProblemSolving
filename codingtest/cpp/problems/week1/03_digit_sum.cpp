// 자릿수 더하기
// https://school.programmers.co.kr/learn/courses/30/lessons/12931
// string<->int
#include <bits/stdc++.h>
using namespace std;

int solution(int n) {
    int answer = 0;

    while (n > 0) {
        answer += n % 10;
        n = n / 10;
    }

    return answer;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout << solution(123) << '\n';  // 기대: 6
    return 0;
}
