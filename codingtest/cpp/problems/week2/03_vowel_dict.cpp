// 모음 사전
// https://school.programmers.co.kr/learn/courses/30/lessons/84512
// Kit: 완전탐색 — DFS
#include <bits/stdc++.h>
using namespace std;

int solution(string word) {
    int answer = 0;

    vector<int> c({1, 6, 31, 156, 781});
    map<char, int> x = {
        {'A', 0},
        {'E', 1},
        {'I', 2},
        {'O', 3},
        {'U', 4}};

    for (unsigned int i = 0; i < word.size(); ++i) {
        answer += x[word[i]] * c[4 - i] + 1;
    }
    return answer;
}

int solution2(string word) {
    string v = "AEIOU";

    stack<string> s;
    s.push("");

    auto count = 0;
    while (!s.empty()) {
        auto x = s.top();
        s.pop();

        if (x == word)
            return count;

        count++;

        if (x.size() < 5) {
            for (int i = 4; i >= 0; --i) {
                s.push(x + v[i]);
            }
        }
    }

    return 0;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout << solution("AAAAE") << '\n';  // 기대: 6
    return 0;
}
