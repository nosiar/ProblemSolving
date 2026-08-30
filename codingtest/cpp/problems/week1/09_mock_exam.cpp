// 모의고사
// https://school.programmers.co.kr/learn/courses/30/lessons/42840
// Kit: 완전탐색 — 패턴 매칭
#include <bits/stdc++.h>
using namespace std;

vector<int> solution(vector<int> answers) {
    vector<vector<int>> x = {
        {1, 2, 3, 4, 5}, {2, 1, 2, 3, 2, 4, 2, 5}, {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}};
    auto l = vector<int>({5, 8, 10});
    auto t = vector<pair<int, int>>({pair(0, -1), pair(0, -2), pair(0, -3)});

    for (unsigned int j = 0; j < answers.size(); ++j) {
        for (unsigned int i = 0; i < 3; ++i) {
            if (x[i][j % l[i]] == answers[j]) {
                t[i].first++;
            }
        }
    }

    sort(t.rbegin(), t.rend());

    vector<int> answer;

    for (unsigned int i = 0; i < t.size(); ++i) {
        if (i == 0) {
            answer.push_back(-t[i].second);
        } else if (t[i - 1].first == t[i].first) {
            answer.push_back(-t[i].second);
        } else {
            break;
        }
    }

    return answer;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    for (int x : solution({1, 2, 3, 4, 5}))
        cout << x << ' ';  // 기대: 1
    cout << '\n';
    return 0;
}
