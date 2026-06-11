// 같은 숫자는 싫어
// https://school.programmers.co.kr/learn/courses/30/lessons/12906
// vector 순회/비교
#include <bits/stdc++.h>
using namespace std;

vector<int> solution(vector<int> arr) {
    arr.erase(unique(arr.begin(), arr.end()), arr.end());
    return arr;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    for (int x : solution({1,1,3,3,0,1,1})) cout << x << ' ';  // 기대: 1 3 0 1
    cout << '\n';
    return 0;
}
