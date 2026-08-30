# Java 코딩테스트 치트시트

> C++ STL은 손에 익었는데 Java로 코테를 보려는 사람용 **대응표 + 함정 모음**.
> 프로그래머스/백준에서 실제로 쓰는 것만. "이거 C++에선 이랬는데 Java론?"을 빠르게 인출.

---

## 0. 기본 골격

**프로그래머스 (함수형)** — `class Solution`은 non-public으로.

```java
import java.util.*;

class Solution {
    public int solution(int[] arr) {
        int answer = 0;
        // ...
        return answer;
    }
}
```

**백준 (표준 입출력)** — Scanner 금지, BufferedReader + StringBuilder.

```java
import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = Integer.parseInt(st.nextToken());
        // ...
        sb.append(answer).append('\n');
        System.out.print(sb);
    }
}
```

---

## 1. 배열 vs 리스트 — `vector` 대응

| C++ | Java 고정크기 | Java 가변 |
|---|---|---|
| `vector<int> v(n)` | `int[] v = new int[n];` (0으로 초기화) | `List<Integer> v = new ArrayList<>();` |
| `vector<int> v(n,-1)` | `Arrays.fill(v, -1);` | — |
| `v.push_back(x)` | — | `v.add(x)` |
| `v.pop_back()` | — | `v.remove(v.size()-1)` |
| `v.size()` | `v.length` | `v.size()` |
| `v[i]` | `v[i]` | `v.get(i)` / `v.set(i,x)` |
| `vector<vector<int>> g(n)` | `int[][] g` | `List<List<Integer>> g` |

```java
int[][] dp = new int[n][m];              // 2차원, 0 초기화
int[] v = {1, 2, 3};                     // 초기화 리스트
List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));

for (int x : v) { ... }                  // 값 순회
for (int i = 0; i < v.length; i++) { ... }

// 리스트 → 배열 (프로그래머스 반환용으로 자주 필요)
int[] ans = list.stream().mapToInt(Integer::intValue).toArray();
```

> **함정**: `v.size()`는 Java에서 `int`라 C++ unsigned 무한루프 함정은 없다. 대신
> `int[]`는 `.length`(필드), `ArrayList`는 `.size()`(메서드)로 **다르다**.

---

## 2. 정렬 — `sort` 대응

```java
int[] a = {3, 1, 2};
Arrays.sort(a);                          // 오름차순 (primitive는 오름차순만)

Integer[] b = {3, 1, 2};
Arrays.sort(b, Collections.reverseOrder());   // 내림차순은 래퍼 배열 필요

List<Integer> list = ...;
Collections.sort(list);                  // 오름차순
list.sort(Comparator.reverseOrder());    // 내림차순

// 커스텀 비교자 (C++ 람다 비교자 대응)
int[][] arr = ...;
Arrays.sort(arr, (x, y) -> x[0] - y[0]);            // 0번째 기준 오름차순
Arrays.sort(arr, (x, y) -> y[1] - x[1]);            // 1번째 기준 내림차순
// 두 기준: 0번째 오름 → 같으면 1번째 내림
Arrays.sort(arr, Comparator.<int[]>comparingInt(x -> x[0]).thenComparing(x -> -x[1]));
```

> **함정**: `x[0] - y[0]` 뺄셈 비교자는 오버플로 위험. 값이 크면
> `Integer.compare(x[0], y[0])` 사용. **원시타입 배열 `int[]`는 내림차순 정렬 불가** →
> `Integer[]`로 바꾸거나, 정렬 후 `reverse`.

---

## 3. map / set — `map`, `set`, `unordered_*` 대응

| C++ | Java |
|---|---|
| `unordered_map<K,V>` | `HashMap<K,V>` (순서 없음, O(1)) |
| `map<K,V>` (정렬됨) | `TreeMap<K,V>` (키 정렬, O(log n)) |
| `unordered_set<T>` | `HashSet<T>` |
| `set<T>` (정렬됨) | `TreeSet<T>` |

```java
Map<String, Integer> cnt = new HashMap<>();
cnt.put(key, cnt.getOrDefault(key, 0) + 1);   // 카운팅 (C++ cnt[key]++ 대응)
cnt.containsKey(key);
cnt.getOrDefault(key, 0);
for (Map.Entry<String, Integer> e : cnt.entrySet()) {
    e.getKey(); e.getValue();
}
for (var e : cnt.entrySet()) { ... }          // var로 짧게

Set<Integer> s = new HashSet<>();
s.add(x); s.contains(x); s.remove(x); s.size();

// TreeMap 정렬 활용 (C++ map::begin/rbegin)
TreeMap<Integer,Integer> tm = new TreeMap<>();
tm.firstKey(); tm.lastKey();
tm.floorKey(k);      // k 이하 최대   (C++ upper_bound류)
tm.ceilingKey(k);    // k 이상 최소
```

> **함정**: 키/값은 반드시 **래퍼 타입**(`Integer`, `Long`...). `int`는 못 넣는다.
> 자동 박싱되지만, `map.get(key)`가 없으면 `null` → 언박싱 시 NPE 주의.

---

## 4. 스택 / 큐 / 덱 — `stack`, `queue`, `deque` 대응

**`Stack` 클래스는 쓰지 말 것** (레거시, 느림). `ArrayDeque`로 통일.

```java
Deque<Integer> stack = new ArrayDeque<>();
stack.push(x); stack.pop(); stack.peek(); stack.isEmpty();   // 스택

Deque<Integer> queue = new ArrayDeque<>();
queue.offer(x);   // 뒤에 추가 (enqueue)
queue.poll();     // 앞에서 제거 (dequeue)
queue.peek();     // 앞 확인

// 덱 (양쪽)
Deque<Integer> dq = new ArrayDeque<>();
dq.offerFirst(x); dq.offerLast(x); dq.pollFirst(); dq.pollLast();
```

**우선순위 큐** — `priority_queue` 대응. **C++는 기본 최대힙, Java는 기본 최소힙!**

```java
PriorityQueue<Integer> minHeap = new PriorityQueue<>();               // 최소힙 (기본)
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // 최대힙
PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);  // 커스텀
pq.offer(x); pq.poll(); pq.peek();
```

> **함정**: C++ `priority_queue`는 **최대힙**이 기본, Java `PriorityQueue`는 **최소힙**이 기본.
> 뒤집힌다는 것만 기억하면 됨.

---

## 5. 문자열 — `string` 대응

```java
String s = "abc";
s.length();              // 길이 (배열은 .length, 문자열은 .length())
s.charAt(i);             // s[i]
s.substring(a, b);       // [a, b) 부분문자열
s.indexOf("x");          // 없으면 -1
s.split(" ");            // 토큰 분리 → String[]
s.toCharArray();         // char[] 로
String.valueOf(123);     // int → String  (C++ to_string)
Integer.parseInt("123"); // String → int   (C++ stoi)

// 문자열 조립은 StringBuilder (String += 는 O(n^2) 함정)
StringBuilder sb = new StringBuilder();
sb.append('a').append(3);
sb.reverse();            // 뒤집기
String result = sb.toString();

// 정렬 (C++ sort(s.begin(), s.end()))
char[] c = s.toCharArray();
Arrays.sort(c);
String sorted = new String(c);
```

> **함정**: `String`은 불변(immutable). `==`로 비교하면 안 되고 **`.equals()`** 사용.
> 문자 → 숫자: `c - '0'`, 알파벳 인덱스: `c - 'a'` (C++과 동일).

---

## 6. C++엔 있는데 Java엔 없는 것 (직접 구현)

**`next_permutation`** — Java 표준에 없다. 순열 문제(소수 찾기 등)에서 필요하면 직접:

```java
// arr를 다음 순열로 바꾸고, 마지막이면 false 반환
static boolean nextPermutation(int[] a) {
    int i = a.length - 2;
    while (i >= 0 && a[i] >= a[i + 1]) i--;
    if (i < 0) return false;
    int j = a.length - 1;
    while (a[j] <= a[i]) j--;
    int t = a[i]; a[i] = a[j]; a[j] = t;
    for (int l = i + 1, r = a.length - 1; l < r; l++, r--) {
        t = a[l]; a[l] = a[r]; a[r] = t;
    }
    return true;
}
```

**`pair`** — 보통 `int[]{a, b}` 로 대신하거나, 정렬 키가 필요하면 `int[]` + 비교자.
정 필요하면 `Map.Entry`나 간단한 record: `record P(int a, int b) {}`.

**`__gcd`** → `java.math.BigInteger`나 직접:
```java
static long gcd(long a, long b) { return b == 0 ? a : gcd(b, a % b); }
```

**`lower_bound` / `upper_bound`** → `Arrays.binarySearch`는 동작이 달라(없으면 음수 반환)
파라메트릭 서치엔 직접 `while (lo < hi)` 이분탐색을 쓰는 게 편하다.

---

## 7. 자주 밟는 함정 요약

- **최대힙/최소힙 기본값 반대** (§4).
- **오버플로**: 합·곱 커지면 `long`. `int` 한계 ≈ 21억. 비교자 뺄셈도 위험.
- **문자열 비교는 `.equals()`**, `==` 아님.
- **컬렉션은 래퍼 타입만** (`List<Integer>`, `Map<..,Integer>`). 언박싱 NPE 주의.
- **입출력**: `Scanner`/반복 `println` 느림 → `BufferedReader` + `StringBuilder`.
- **깊은 재귀 StackOverflow**: 로컬은 `run.sh`가 `-Xss64m`로 완화하지만,
  채점 서버는 옵션을 못 주므로 깊이 크면 반복문+명시적 스택으로.
- `int[].length` (필드) vs `List.size()` (메서드) vs `String.length()` (메서드) 혼동.

---

## 8. 시간복잡도 빠른 기준 (1초 ≈ 1억 연산)

| 입력 크기 N | 허용 복잡도 |
|---|---|
| N ≤ 10 | O(N!), O(2^N) — 완전탐색 OK |
| N ≤ 20 | O(2^N) |
| N ≤ 500 | O(N^3) |
| N ≤ 5,000 | O(N^2) |
| N ≤ 10^5 ~ 10^6 | O(N log N) |
| N ≤ 10^7 이상 | O(N) |

> 문제 보면 **N 크기부터 확인** → 허용 복잡도 역산 → 알고리즘 결정. 코테 사고의 출발점.
> **JVM 주의**: Java는 C++보다 상수가 크고(오토박싱·워밍업) 체감 2~3배 느릴 수 있다.
> 경계선(예: N=10^6에 O(N log N))이면 입출력을 `BufferedReader`/`StringBuilder`로 조이고,
> 핫루프에서 `Integer` 대신 `int[]`를 쓰는 등 상수를 줄여야 안전하다.
