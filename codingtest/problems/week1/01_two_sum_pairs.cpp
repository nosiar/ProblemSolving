// 두 개 뽑아서 더하기
// https://school.programmers.co.kr/learn/courses/30/lessons/68644
// 완전탐색 + set 중복제거
#include <bits/stdc++.h>
using namespace std;

vector<int> solution(vector<int> numbers) {
    vector<int> answer;

    sort(numbers.begin(), numbers.end());

    for (unsigned int i = 0; i < numbers.size(); ++i) {
        for (unsigned int j = i + 1; j < numbers.size(); ++j) {
            answer.push_back(numbers[i] + numbers[j]);
        }
    }

    sort(answer.begin(), answer.end());

    answer.erase(unique(answer.begin(), answer.end()), answer.end());

    return answer;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    for (int x : solution({2,1,3,4,1})) cout << x << ' ';  // 기대: 2 3 4 5 6 7
    cout << '\n';
    return 0;
}
