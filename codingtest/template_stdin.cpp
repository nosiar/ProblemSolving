// 표준 입출력형 템플릿 (백준 / 일부 코테).
// input.txt를 만들어 두면 자동으로 그 파일을 입력으로 읽는다 (run.sh가 처리).
#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    cin >> n;
    vector<int> a(n);
    for (int& x : a) cin >> x;

    long long sum = 0;
    for (int x : a) sum += x;
    cout << sum << "\n";

    return 0;
}
