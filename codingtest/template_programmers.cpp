// 프로그래머스 함수 구현형 템플릿.
// 실제 제출 시엔 solution 함수만 복사해서 붙여넣으면 된다.
// main()은 로컬 테스트용 (제출할 때는 빼도 됨).
#include <bits/stdc++.h>
using namespace std;

// ── 여기에 문제의 solution 시그니처를 맞춰 구현 ──
// 예시: 정수 배열을 받아 합을 반환
int solution(vector<int> arr) {
    int answer = 0;
    for (int x : arr) answer += x;
    return answer;
}

// ── 로컬 테스트 (제출 시 제거) ──
int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    // 테스트 케이스 직접 호출
    cout << solution({1, 2, 3, 4, 5}) << "\n";  // 기대값: 15
    return 0;
}
