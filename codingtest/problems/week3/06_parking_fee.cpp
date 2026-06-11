// 주차 요금 계산
// https://school.programmers.co.kr/learn/courses/30/lessons/92341
// 2022 카카오 — 시뮬레이션 + 파싱
#include <bits/stdc++.h>
using namespace std;

vector<int> solution(vector<int> fees, vector<string> records) {
    vector<int> answer;
    // TODO
    return answer;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    auto r = solution({180,5000,10,600}, {"05:34 5961 IN","06:00 0000 IN","06:34 0000 OUT","07:59 5961 OUT","07:59 0148 IN","18:59 0000 IN","19:09 0148 OUT","22:59 5961 IN","23:00 5961 OUT"});
    for (int x : r) cout << x << ' ';  // 기대: 14600 34400 5000
    cout << '\n';
    return 0;
}
