# 코딩테스트 연습 환경 — Java (백엔드 면접용)

IntelliJ는 **에디터**로만 쓰고, 실행은 터미널의 `run.sh`로 한다.

## 빠른 시작

```bash
cd codingtest/java

# 문제 풀기: 스켈레톤 열어서 // TODO 구현 → 실행
./run.sh problems/week1/07_unfinished_player.java

# 새로 빈 파일이 필요하면 템플릿 복사
cp template_programmers.java prob01.java     # 프로그래머스 함수형 (class Solution)
cp template_stdin.java       prob02.java     # 표준 입출력형 (class Main)
./run.sh prob01.java
```

## 왜 컴파일 명령이 안 보이나

`java 파일.java` **단일 소스 런처**(Java 11+)를 쓴다. 별도 `javac` 단계도, 폴더에 남는
`.class` 파일도 없다. 핵심 이점:

- **파일명이 클래스명과 달라도 됨** → `05_target_number.java` 처럼 숫자로 시작하는
  파일명 그대로 사용. 안의 클래스는 프로그래머스식 `class Solution` 그대로 둔다.
- 단, 클래스는 **non-public** 이어야 한다 (public이면 파일명 == 클래스명 강제됨).
  템플릿/스켈레톤이 이미 그렇게 돼 있으니 신경 안 써도 됨.

## 입력이 필요한 문제 (표준 입출력형)

`run.sh`가 다음 우선순위로 입력을 찾아 stdin에 넣는다.

1. 인자로 직접 지정한 파일
2. **소스와 같은 이름**의 `.txt` 또는 `.in`  (예: `prob02.java` → `prob02.txt`)
3. 폴더 공용 `input.txt`

```bash
./run.sh prob02.java              # prob02.txt 자동 사용
./run.sh prob02.java other.txt    # 다른 입력 파일로 교체
```

## 스켈레톤 재생성

```bash
./.gen_skeletons.sh    # problems/week{1,2,3}/ 아래에 없는 파일만 생성 (기존 풀이는 안 덮음)
```

## Java 코테 필수 습관 (면접 감점 방지)

- **입력**: `Scanner` 금지. `BufferedReader` + `StringTokenizer` 사용 (stdin 템플릿 참고).
- **출력**: 반복 `System.out.println` 금지. `StringBuilder`에 모아 마지막에 한 번 출력.
- **재귀**: 깊은 DFS는 `StackOverflowError` 주의 → `run.sh`가 `-Xss64m`으로 완화해 두었다.
  (실전 채점 서버에선 스택 옵션을 못 주므로, 깊이가 크면 반복문+명시적 스택으로 바꾸는 습관.)
- **정수 오버플로**: 합/곱이 커지면 `long`. `int` 최대 약 21억.

## 자동 코드 정렬 (선택)

`google-java-format`이 깔려 있으면 실행 직전 자동 정렬한다.

```bash
brew install google-java-format
```

없어도 실행에는 지장 없다.

## 환경 메모

- 런타임: `java 25` (mise). 채점 서버는 보통 Java 11/17이지만, 이 세팅에서 쓰는
  문법/표준 API는 전부 8~11 호환이라 그대로 제출 가능.
- C++ STL ↔ Java 자료구조 대응은 `cheatsheet.md` 참고.
