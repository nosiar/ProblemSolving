# C++ STL 코딩테스트 치트시트

> 코테(프로그래머스 기준)에서 **실제로 쓰는 것만** 모았다.
> 개념은 알지만 인출이 녹슨 상태 → "아, 이거 이렇게 썼지"를 빠르게 복구하는 용도.
> 화려한 알고리즘(FFT, Suffix Array 등)은 코테에 안 나오므로 제외.

---

## 0. 기본 템플릿

```cpp
#include <bits/stdc++.h>   // 코테에선 이 한 줄로 거의 다 됨 (GCC 기준)
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);      // cin/cout 섞어 쓸 때 속도 ↑
    // ...
    return 0;
}
```

> 프로그래머스는 보통 `main`이 아니라 **`solution` 함수 구현형**이다. 입출력보다 함수 시그니처에 맞추는 게 중요.
> 예: `vector<int> solution(vector<int> arr) { ... }`

---

## 1. vector — 가장 많이 쓰는 자료구조

```cpp
vector<int> v;                 // 빈 벡터
vector<int> v(n);              // 크기 n, 0으로 초기화
vector<int> v(n, -1);          // 크기 n, -1로 초기화
vector<int> v = {1, 2, 3};     // 초기화 리스트
vector<vector<int>> g(n);      // 인접 리스트 (그래프)
vector<vector<int>> dp(n, vector<int>(m, 0));  // 2차원 DP

v.push_back(x);                // 뒤에 추가
v.pop_back();                  // 뒤 제거
v.size();                      // 크기 (주의: 부호 없는 size_t)
v.empty();                     // 비었나?
v.front(); v.back();           // 첫/끝 원소
v.clear();                     // 비우기
v.resize(k);                   // 크기 변경

// 순회
for (int x : v) { ... }        // 값 복사
for (int& x : v) { x *= 2; }   // 참조 (수정 가능)
for (auto& x : v) { ... }      // 타입 길면 auto

sort(v.begin(), v.end());                  // 오름차순
sort(v.rbegin(), v.rend());                // 내림차순
sort(v.begin(), v.end(), greater<int>());  // 내림차순 (다른 방법)
reverse(v.begin(), v.end());
```

> **함정**: `v.size()`는 unsigned다. `for (int i = 0; i < v.size() - 1; i++)`에서 v가 비면 무한루프.
> → `(int)v.size()`로 캐스팅하거나 인덱스 비교에 주의.

---

## 2. 정렬 + 람다 비교자 (코테 단골)

```cpp
// 기본 정렬
sort(v.begin(), v.end());

// pair 정렬: first 기준 오름차순, 같으면 second 기준
vector<pair<int,int>> p;
sort(p.begin(), p.end());      // pair는 기본적으로 사전식 정렬됨

// 커스텀 정렬: 람다
sort(v.begin(), v.end(), [](int a, int b) {
    return a > b;              // true면 a가 b보다 앞 → 내림차순
});

// 구조체/2차원 정렬 예: [점수 내림차순, 같으면 이름 오름차순]
sort(people.begin(), people.end(), [](const auto& a, const auto& b) {
    if (a.score != b.score) return a.score > b.score;
    return a.name < b.name;
});

// 문자열을 길이순, 같으면 사전순
sort(words.begin(), words.end(), [](const string& a, const string& b) {
    if (a.size() != b.size()) return a.size() < b.size();
    return a < b;
});
```

> 비교자 규칙: **`return true` = "a를 b보다 앞에 둔다"**. 이것만 기억하면 헷갈릴 일 없음.

---

## 2.1 람다 캡처 — `[&]` vs `[=]`

캡처 = "람다가 바깥 변수를 어떻게 가져올지".

```cpp
int x = 10;
auto byRef  = [&]() { return x; };   // 참조: 바깥 x와 실시간 연결
auto byCopy = [=]() { return x; };   // 복사: 만든 순간의 값으로 고정
x = 99;
byRef();   // 99  ← 바뀐 게 보임
byCopy();  // 10  ← 찍은 순간의 스냅샷
```

| 캡처 | 의미 | 바깥 변경이 보이나 | 람다가 바깥을 바꾸나 |
|---|---|---|---|
| `[&]` | 참조(별칭) | ✅ 보임 | ✅ 가능 |
| `[=]` | 값 복사(스냅샷) | ❌ 고정 | ❌ (기본 const) |

```cpp
// 개별/혼합 캡처
[&x]        // x만 참조
[x]         // x만 복사
[&, x]      // 기본 참조, x만 복사
[=, &v]     // 기본 복사, v만 참조
[]          // 캡처 없음 (정렬 비교자처럼 바깥 안 쓸 때)
```

> **PS에선 거의 항상 `[&]`.** DFS/BFS 람다가 바깥 `visited`, `graph`를 읽고 쓰며 같이
> 갱신해야 하니까. 바깥 변수 안 쓰는 정렬 비교자는 `[]`.
>
> ⚠️ **댕글링 함정**: 참조 캡처(`[&]`)한 람다를 그 변수가 사라진 스코프 밖으로
> 내보내면 죽은 변수를 가리킨다. 스코프 밖으로 반환할 땐 `[=]`. (PS에선 같은 함수
> 안에서 만들고 바로 쓰니 거의 문제 없음.)

---

## 2.2 재귀 람다 (main/solution 안에서 재귀 함수)

```cpp
// auto는 안 됨: 람다 안에서 자기 자신을 부르려면 타입이 먼저 정해져야 함
function<void(int)> dfs = [&](int cur) {   // function<반환(인자)>로 타입 고정
    visited[cur] = true;
    for (int nxt : g[cur])
        if (!visited[nxt]) dfs(nxt);       // [&] 덕분에 자기 자신 호출 가능
};
dfs(0);
```

> `function<void(int)>` = "int 받고 void 반환". 재귀하려면 `auto` 말고 이렇게 타입을
> 박아야 한다.

---

## 3. string — 문자열 처리

```cpp
string s = "hello";
s.size(); s.length();          // 길이
s[i];                          // i번째 문자 (char)
s.substr(pos);                 // pos부터 끝까지
s.substr(pos, len);            // pos부터 len개
s.find("lo");                  // 위치 반환, 없으면 string::npos
s += "!";                      // 이어붙이기
s.push_back('x');

// 숫자 ↔ 문자열
int n = stoi("123");           // string → int
long long L = stoll("123");    // string → long long
string t = to_string(456);     // 숫자 → string

// 문자 판별 (cctype)
isdigit(c); isalpha(c); isupper(c); islower(c);
toupper(c); tolower(c);

// 문자 ↔ 숫자
int d = c - '0';               // '7' → 7
char c = d + '0';              // 7 → '7'

// 정렬
sort(s.begin(), s.end());      // 문자 정렬 (애너그램 비교에 유용)
```

> **애너그램 트릭**: 두 문자열을 각각 `sort`해서 같으면 애너그램.

---

## 4. map / unordered_map — 키-값, 카운팅

```cpp
map<string, int> m;            // 키 정렬됨, O(log n)
unordered_map<string, int> um; // 정렬 안 됨, 평균 O(1) → 보통 더 빠름

m["apple"]++;                  // 없으면 0에서 시작 후 +1 (카운팅 핵심!)
m["apple"] = 5;
m.count("apple");              // 있으면 1, 없으면 0
m.find("apple") != m.end();    // 존재 확인 (다른 방법)
m.erase("apple");

// 순회 (map은 키 정렬 순서)
for (auto& [key, val] : m) {   // C++17 구조적 바인딩
    cout << key << " " << val << "\n";
}
```

> **카운팅 패턴**: `unordered_map<int,int> cnt; for (int x : v) cnt[x]++;` → 빈도수 세기 단골.
> 코테 대부분 `unordered_map`이면 충분. 출력 순서가 정렬돼야 하면 `map`.

---

## 5. set / unordered_set — 중복 제거, 존재 확인

```cpp
set<int> s;                    // 정렬됨, 중복 없음
unordered_set<int> us;         // 정렬 안 됨, 평균 O(1)

s.insert(x);
s.count(x);                    // 있으면 1
s.erase(x);
for (int x : s) { ... }        // 정렬 순서로 순회

// vector에서 중복 제거 정석
sort(v.begin(), v.end());
v.erase(unique(v.begin(), v.end()), v.end());
```

---

## 6. queue / stack / priority_queue

```cpp
// queue (BFS 필수)
queue<int> q;
q.push(x); q.front(); q.pop(); q.empty(); q.size();

// stack (DFS, 괄호 매칭)
stack<int> st;
st.push(x); st.top(); st.pop(); st.empty();

// priority_queue (다익스트라, 우선순위)
priority_queue<int> pq;                 // 최대 힙 (기본)
pq.push(x); pq.top(); pq.pop();

priority_queue<int, vector<int>, greater<int>> minpq;  // 최소 힙

// pair 우선순위큐 (다익스트라: {거리, 정점}, 최소 거리 우선)
priority_queue<pair<int,int>, vector<pair<int,int>>, greater<>> dpq;
```

> **다익스트라 = 최소힙 + {비용, 노드}**. 이 패턴이 코테 그래프 문제의 절반.

---

## 7. BFS / DFS 템플릿

```cpp
// BFS (최단거리, 레벨 탐색)
vector<vector<int>> g(n);
vector<int> dist(n, -1);
queue<int> q;
q.push(start); dist[start] = 0;
while (!q.empty()) {
    int cur = q.front(); q.pop();
    for (int nxt : g[cur]) {
        if (dist[nxt] == -1) {       // 미방문
            dist[nxt] = dist[cur] + 1;
            q.push(nxt);
        }
    }
}

// 격자 BFS (방향 배열)
int dx[] = {0, 0, 1, -1};
int dy[] = {1, -1, 0, 0};
// ... nx = x + dx[d], ny = y + dy[d]; 범위 체크 0<=nx<H 등

// DFS (재귀)
vector<bool> visited(n, false);
function<void(int)> dfs = [&](int cur) {
    visited[cur] = true;
    for (int nxt : g[cur])
        if (!visited[nxt]) dfs(nxt);
};
```

> 재귀 DFS는 `function<void(int)>` 람다로 main 안에서도 쓸 수 있다. 깊이 깊으면 스택오버플로 주의 → BFS나 명시적 스택.

---

## 8. 자주 쓰는 알고리즘 함수 (algorithm 헤더)

```cpp
max(a, b); min(a, b);
max({a, b, c});                // 여러 개
*max_element(v.begin(), v.end());   // 최댓값 (포인터 반환 → *)
*min_element(v.begin(), v.end());
accumulate(v.begin(), v.end(), 0);  // 합 (numeric 헤더)
accumulate(v.begin(), v.end(), 0LL); // 합이 클 땐 0LL로 시작!

count(v.begin(), v.end(), x);       // x 개수
find(v.begin(), v.end(), x);        // 위치

// 이분탐색 (정렬된 상태에서)
binary_search(v.begin(), v.end(), x);          // 존재 여부 (bool)
lower_bound(v.begin(), v.end(), x) - v.begin(); // x 이상 첫 위치
upper_bound(v.begin(), v.end(), x) - v.begin(); // x 초과 첫 위치

next_permutation(v.begin(), v.end());  // 다음 순열 (완전탐색에 유용)

__gcd(a, b);                    // 최대공약수
```

---

## 9. 자주 쓰는 함정 / 실수 체크리스트

- **오버플로**: 합/곱이 크면 `long long`. `int` 범위는 약 ±21억. 합 구할 때 `0LL` 시작.
- **`size()`는 unsigned**: 음수 비교/뺄셈에서 의도치 않은 큰 값 발생.
- **2차원 배열 인덱스**: `[행][열]` vs `[열][행]` 헷갈림. 격자는 보통 `[y][x]` 또는 `[r][c]`로 통일.
- **방문 처리 시점**: BFS는 **큐에 넣을 때** 방문 표시 (중복 삽입 방지). 꺼낼 때 표시하면 같은 노드 여러 번 들어감.
- **초기값**: `dist`를 -1 또는 INF로. INF는 `1e9`(int) 또는 `1e18`(long long). `INT_MAX`끼리 더하면 오버플로.
- **프로그래머스**: `return`을 빠뜨리지 말 것. 시간복잡도 = 입력 크기 보고 추정 (N≤10^6면 O(N log N) 이하 노려야 함).

---

## 10. 시간복잡도 빠른 기준 (1초 ≈ 1억 연산)

| 입력 크기 N | 허용 복잡도 |
|---|---|
| N ≤ 10 | O(N!), O(2^N) — 완전탐색 OK |
| N ≤ 20 | O(2^N) |
| N ≤ 500 | O(N^3) |
| N ≤ 5,000 | O(N^2) |
| N ≤ 10^5 ~ 10^6 | O(N log N) |
| N ≤ 10^7 이상 | O(N) |

> 문제 보면 **N 크기부터 확인** → 어떤 복잡도까지 허용되는지 역산 → 알고리즘 결정. 이게 코테 사고의 출발점.
