// 완주하지 못한 선수
// https://school.programmers.co.kr/learn/courses/30/lessons/42576
// Kit: 해시 — map/unordered_map 카운팅 핵심
#include <bits/stdc++.h>
using namespace std;

string solution(vector<string> participant, vector<string> completion) {
    sort(participant.begin(), participant.end());
    sort(completion.begin(), completion.end());

    for (unsigned int i = 0; i < completion.size(); ++i) {
        if (participant[i] != completion[i]) {
            return participant[i];
        }
    }

    return participant.back();
}

string solution2(vector<string> participant, vector<string> completion) {
    unordered_map<string, int> m;

    for (auto x : participant)
        m[x]++;
    for (auto x : completion)
        m[x]--;

    for (auto& [k, v] : m) {
        if (v == 1) {
            return k;
        }
    }

    return "";
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout << solution({"leo", "kiki", "eden"}, {"eden", "kiki"}) << '\n';  // 기대: leo
    return 0;
}
