// K번째수
// https://school.programmers.co.kr/learn/courses/30/lessons/42748
// Kit: 정렬 — 부분 vector + sort
#include <bits/stdc++.h>
using namespace std;

vector<int> solution(vector<int> array, vector<vector<int>> commands) {
    vector<int> answer;

    for (auto c : commands) {
        vector<int> x(array.begin() + c[0] - 1, array.begin() + c[1]);
        sort(x.begin(), x.end());
        answer.push_back(x[c[2] - 1]);
    }

    return answer;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    for (int x : solution({1, 5, 2, 6, 3, 7, 4}, {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}}))
        cout << x << ' ';  // 기대: 5 6 3
    cout << '\n';
    return 0;
}
