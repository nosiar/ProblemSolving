// 문자열 내 p와 y의 개수
// https://school.programmers.co.kr/learn/courses/30/lessons/12916
// 카운팅 (대소문자 무시)
#include <bits/stdc++.h>
using namespace std;

bool solution(string s) {
    int p = 0;
    int y = 0;
    for (char x : s) {
        if (x == 'p' || x == 'P') {
            p++;
        }
        if (x == 'y' || x == 'Y') {
            y++;
        }
    }

    return p == y;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout << boolalpha << solution("pPoooyY") << '\n';  // 기대: true
    return 0;
}
