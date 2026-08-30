// 타겟 넘버
// https://school.programmers.co.kr/learn/courses/30/lessons/43165
// Kit: DFS/BFS — DFS 완전탐색
#include <bits/stdc++.h>
using namespace std;

int solution(vector<int> numbers, int target) {
    stack<pair<unsigned int, int>> s;
    s.push({0, 0});

    int cnt = 0;
    while (!s.empty()) {
        auto [p, v] = s.top();
        s.pop();

        if (p == numbers.size()) {
            if (v == target) {
                cnt++;
            }
            continue;
        }

        s.push({p + 1, v + numbers[p]});
        s.push({p + 1, v - numbers[p]});
    }

    return cnt;
}

int dfs(unsigned int p, int v, vector<int>& numbers, int target) {
    if (p == numbers.size()) {
        return v == target ? 1 : 0;
    }
    return dfs(p + 1, v - numbers[p], numbers, target) + dfs(p + 1, v + numbers[p], numbers, target);
}

int solution2(vector<int> numbers, int target) {
    return dfs(0, 0, numbers, target);
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout << solution2({1, 1, 1, 1, 1}, 3) << '\n';  // 기대: 5
    return 0;
}
