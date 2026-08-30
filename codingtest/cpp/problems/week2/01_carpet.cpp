// 카펫
// https://school.programmers.co.kr/learn/courses/30/lessons/42842
// Kit: 완전탐색 — 약수 탐색
#include <bits/stdc++.h>
using namespace std;

vector<int> solution(int brown, int yellow) {
    auto s = static_cast<int>(sqrt(yellow));

    for (int i = 1; i <= s; ++i) {
        if (yellow % i == 0) {
            int n = i + 2;
            int m = yellow / i + 2;

            if (n * 2 + m * 2 - 4 == brown) {
                return vector<int>({m, n});
            }
        }
    }

    return vector<int>();
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    for (int x : solution(10, 2))
        cout << x << ' ';  // 기대: 4 3
    cout << '\n';
    return 0;
}
