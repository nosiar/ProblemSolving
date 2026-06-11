// 체육복
// https://school.programmers.co.kr/learn/courses/30/lessons/42862
// Kit: 탐욕법 — 그리디 워밍업
#include <bits/stdc++.h>
using namespace std;

int solution(int n, vector<int> lost, vector<int> reserve) {
    vector<int> t(n);

    for (auto x : lost) {
        t[x - 1]--;
    }
    for (auto x : reserve) {
        t[x - 1]++;
    }

    for (unsigned int i = 0; i < t.size(); ++i) {
        if (t[i] == -1) {
            if (i > 0 && t[i - 1] == 1)
                t[i]++;
            else if (i < t.size() - 1 && t[i + 1] == 1) {
                t[i]++;
                t[i + 1]--;
            }
        }
    }
    return n - count(t.begin(), t.end(), -1);
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout << solution(5, {2, 4}, {1, 3, 5}) << '\n';  // 기대: 5
    return 0;
}
