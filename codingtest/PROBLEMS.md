# 프로그래머스 유형별 추천 문제 (코테 복귀용)

> 대상: PS 경험은 있으나 인출이 녹슨 상태 → **감각 회복 순서**대로 배치.
> 목표: 코테 합격. 화려한 알고리즘 말고 **자주 나오는 좁은 범위**를 빠르게 복구.
> **문제별 스켈레톤 .cpp가 `problems/week{1,2,3}/` 에 순서대로 미리 만들어져 있다.**
> 정확한 `solution` 시그니처 + 예제 main이 채워져 있으니, `// TODO`만 구현하면 된다.
> 풀이 루틴: `problems/week1/07_*.cpp` 를 IntelliJ로 열어 풀기 → `./run.sh problems/week1/07_*.cpp`
>
> 체크박스는 풀면 `[x]`로 바꾸기. 막혀도 30분 넘기면 답 보고 → 다음날 다시 풀기.
> 2주차 이후 대부분은 프로그래머스 **"코딩테스트 고득점 Kit"** 카테고리에 속한다 (각 항목에 표시).

---

## 1주차 — C++/STL 손풀기 (Lv.1 중심)

목표: 정답보다 **vector / string / map / sort가 손에 붙는 것**.

- [x] [두 개 뽑아서 더하기](https://school.programmers.co.kr/learn/courses/30/lessons/68644) — 완전탐색 + set 중복제거
- [x] [같은 숫자는 싫어](https://school.programmers.co.kr/learn/courses/30/lessons/12906) — vector 순회/비교
- [x] [자릿수 더하기](https://school.programmers.co.kr/learn/courses/30/lessons/12931) — string↔int
- [x] [자연수 뒤집어 배열로 만들기](https://school.programmers.co.kr/learn/courses/30/lessons/12932) — 숫자 분해
- [x] [문자열 내 p와 y의 개수](https://school.programmers.co.kr/learn/courses/30/lessons/12916) — 카운팅
- [x] [문자열 내림차순으로 배치하기](https://school.programmers.co.kr/learn/courses/30/lessons/12917) — sort + 비교자
- [x] [완주하지 못한 선수](https://school.programmers.co.kr/learn/courses/30/lessons/42576) — **map/unordered_map 카운팅 핵심** (Kit: 해시)
- [x] [K번째수](https://school.programmers.co.kr/learn/courses/30/lessons/42748) — 부분 vector + sort (Kit: 정렬)
- [x] [모의고사](https://school.programmers.co.kr/learn/courses/30/lessons/42840) — 패턴 매칭, 순회 (Kit: 완전탐색)
- [x] [폰켓몬](https://school.programmers.co.kr/learn/courses/30/lessons/1845) — set으로 종류 세기 (Kit: 해시)
- [x] [체육복](https://school.programmers.co.kr/learn/courses/30/lessons/42862) — 그리디 워밍업 (Kit: 탐욕법)

> 이 11개를 막힘없이 풀면 "문법 까먹었다"는 느낌은 대부분 사라진다.

---

## 2주차 — 유형별 회복 (Lv.2 중심)

하루에 한 유형씩. "이 유형은 이렇게 접근한다"는 인출 속도 올리기.

### 완전탐색 / 시뮬레이션 (Kit: 완전탐색)
- [x] [카펫](https://school.programmers.co.kr/learn/courses/30/lessons/42842) — 약수 탐색
- [x] [소수 찾기](https://school.programmers.co.kr/learn/courses/30/lessons/42839) — next_permutation + 소수판정
- [x] [모음 사전](https://school.programmers.co.kr/learn/courses/30/lessons/84512) — DFS/완전탐색 (수식 + 스택DFS 둘 다)
- [x] [거리두기 확인하기](https://school.programmers.co.kr/learn/courses/30/lessons/81302) — 격자 BFS (2021 카카오)

### BFS / DFS (Kit: 깊이/너비 우선 탐색)
- [x] [타겟 넘버](https://school.programmers.co.kr/learn/courses/30/lessons/43165) — DFS 완전탐색 (스택 + 재귀 둘 다)
- [ ] [게임 맵 최단거리](https://school.programmers.co.kr/learn/courses/30/lessons/1844) — **격자 BFS 정석**
- [ ] [네트워크](https://school.programmers.co.kr/learn/courses/30/lessons/43162) — DFS/Union-Find 연결요소
- [ ] [단어 변환](https://school.programmers.co.kr/learn/courses/30/lessons/43163) — BFS (상태 그래프)

### 그리디 (Kit: 탐욕법)
- [ ] [큰 수 만들기](https://school.programmers.co.kr/learn/courses/30/lessons/42883) — 스택 그리디
- [ ] [구명보트](https://school.programmers.co.kr/learn/courses/30/lessons/42885) — 투포인터 + 정렬 그리디
- [ ] [조이스틱](https://school.programmers.co.kr/learn/courses/30/lessons/42860) — 그리디 + 약간의 케이스

### 정렬 / 해시 (Kit: 정렬, 해시)
- [ ] [가장 큰 수](https://school.programmers.co.kr/learn/courses/30/lessons/42746) — **문자열 정렬 비교자** ("3"+"30" vs "30"+"3" 함정)
- [ ] [H-Index](https://school.programmers.co.kr/learn/courses/30/lessons/42747) — 정렬 후 탐색
- [ ] [전화번호 목록](https://school.programmers.co.kr/learn/courses/30/lessons/42577) — 접두사 체크 (해시)
- [ ] [의상](https://school.programmers.co.kr/learn/courses/30/lessons/42578) — 카운팅 + 경우의 수 (해시)

### 스택 / 큐 (Kit: 스택/큐)
- [ ] [올바른 괄호](https://school.programmers.co.kr/learn/courses/30/lessons/12909) — 스택 기본
- [ ] [기능개발](https://school.programmers.co.kr/learn/courses/30/lessons/42586) — 큐 시뮬레이션
- [ ] [프로세스](https://school.programmers.co.kr/learn/courses/30/lessons/42587) — 우선순위 큐

### 이분탐색 / 투포인터 (Kit: 이분탐색)
- [ ] [입국심사](https://school.programmers.co.kr/learn/courses/30/lessons/43238) — **파라메트릭 서치 정석**
- [ ] [징검다리](https://school.programmers.co.kr/learn/courses/30/lessons/43236) — 파라메트릭 서치

---

## 3주차 — DP + 실전 모드 (Lv.2~3)

### DP (Kit: 동적계획법)
- [ ] [정수 삼각형](https://school.programmers.co.kr/learn/courses/30/lessons/43105) — 기초 경로 DP
- [ ] [등굣길](https://school.programmers.co.kr/learn/courses/30/lessons/42898) — 격자 경로 DP + 모듈러
- [ ] [도둑질](https://school.programmers.co.kr/learn/courses/30/lessons/42897) — 원형 DP (Lv.3)
- [ ] [N으로 표현](https://school.programmers.co.kr/learn/courses/30/lessons/42895) — 점화식 세우기

### 실전 세트 (시간 재고 풀기)
- [ ] [가장 가까운 같은 글자](https://school.programmers.co.kr/learn/courses/30/lessons/142086) — 해시 + 인덱스
- [ ] [주차 요금 계산](https://school.programmers.co.kr/learn/courses/30/lessons/92341) — 시뮬레이션 + 파싱 (2022 카카오)
- [ ] 카카오 블라인드 기출 세트 — 프로그래머스 → "코딩테스트 연습" → 검색창에 "카카오"

> 3주차 핵심은 난이도보다 **시간 압박 적응**. 실제 코테는 "알지만 시간 안에 못 짠다"가 가장 큰 탈락 사유.

---

## 문제 찾는 법

1. **고득점 Kit 메뉴** (유형별로 풀 때 — 추천): 프로그래머스 → 코딩테스트 연습 → "코딩테스트 고득점 Kit". 위 카테고리 표시(Kit: ~)와 거의 일치.
2. **이름/링크**: 위 링크 클릭하면 바로 그 문제. (로그인 필요)
3. **Google**: "프로그래머스 [문제이름]" → 문제 + 풀이 블로그 같이 나옴 (막혔을 때).

---

## 우선순위가 가장 높은 유형 (시간 없으면 이것부터)

코테 출제 빈도 × 회복 효율 기준 TOP 5:

1. **해시/카운팅** (map, unordered_map) — 거의 모든 코테에 한 문제
2. **BFS/DFS (격자 포함)** — 그래프 문제의 표준
3. **정렬 + 커스텀 비교자** — "가장 큰 수" 류 함정 단골
4. **그리디 + 정렬** — 출제 쉬워서 자주 나옴
5. **파라메트릭 서치** (입국심사) — 변별력 문제로 자주

---

## 막혔을 때 원칙

1. **N 크기부터 본다** → 허용 복잡도 역산 (치트시트 10번 표)
2. 30분 고민 → 안 되면 답 보고 **이해** → 다음날 빈 화면에서 다시 구현
3. "기억"이 아니라 "다시 짤 수 있는가"가 회복의 기준
4. 자료구조 선택이 막히면: 카운팅=map / 중복제거=set / 최단거리=BFS / 우선순위=priority_queue
