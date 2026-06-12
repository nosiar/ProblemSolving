// 거리두기 확인하기
// https://school.programmers.co.kr/learn/courses/30/lessons/81302
// 2021 카카오 — 격자 BFS
#include <bits/stdc++.h>
using namespace std;

const int dx[] = {0, 0, 1, -1};
const int dy[] = {1, -1, 0, 0};

// 한 대기실이 거리두기를 지키는지(안전하면 true)
bool isSafe(const vector<string>& room) {
    for (int si = 0; si < 5; ++si) {
        for (int sj = 0; sj < 5; ++sj) {
            if (room[si][sj] != 'P') continue;

            // 이 P에서 BFS, 거리 2까지만
            vector<vector<int>> dist(5, vector<int>(5, -1));
            queue<pair<int, int>> q;
            q.push({si, sj});
            dist[si][sj] = 0;

            while (!q.empty()) {
                auto [i, j] = q.front();
                q.pop();
                if (dist[i][j] == 2) continue;          // 2 이상은 더 안 퍼짐

                for (int k = 0; k < 4; ++k) {
                    int ni = i + dx[k], nj = j + dy[k];
                    if (ni < 0 || ni >= 5 || nj < 0 || nj >= 5) continue;
                    if (dist[ni][nj] != -1) continue;    // 방문함
                    if (room[ni][nj] == 'X') continue;   // 칸막이는 못 지나감
                    dist[ni][nj] = dist[i][j] + 1;
                    if (room[ni][nj] == 'P') return false;  // 거리 1~2에 P → 위반
                    q.push({ni, nj});
                }
            }
        }
    }
    return true;
}

vector<int> solution(vector<vector<string>> places) {
    vector<int> answer;
    for (const auto& room : places)
        answer.push_back(isSafe(room) ? 1 : 0);
    return answer;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    // 예제는 입력이 길어 생략. 채점은 프로그래머스에서.
    cout << "run on programmers" << '\n';
    return 0;
}
