// 소수 찾기
// https://school.programmers.co.kr/learn/courses/30/lessons/42839
// Kit: 완전탐색 — next_permutation + 소수판정
#include <bits/stdc++.h>
using namespace std;

int solution(string numbers) {
    set<int> x;

    auto l = static_cast<unsigned int>(pow(10.0, numbers.size() / 2.0));

    vector<unsigned int> p({2});

    for (unsigned int i = 3; i <= l; i += 2) {
        auto s = p.size();
        for (unsigned int j = 0; j < s; ++j) {
            if (p[j] * p[j] > i) {
                p.push_back(i);
                break;
            }
            if (i % p[j] == 0)
                break;
        }
    }

    sort(numbers.begin(), numbers.end());

    do {
        for (unsigned int i = 1; i <= numbers.size(); ++i) {
            auto n = stoi(numbers.substr(0, i));
            if (n < 2)
                continue;

            auto s = static_cast<unsigned int>(sqrt(n));

            for (unsigned int i = 0; i < p.size(); ++i) {
                if (p[i] > s) {
                    x.insert(n);
                    break;
                } else if (n % p[i] == 0) {
                    break;
                } else if (i == p.size() - 1) {
                    x.insert(n);
                    break;
                }
            }
        }
    } while (next_permutation(numbers.begin(), numbers.end()));

    return x.size();
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout << solution("17") << '\n';  // 기대: 3
    return 0;
}
