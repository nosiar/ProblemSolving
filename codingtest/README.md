# 코딩테스트 연습 환경 (Mac / IntelliJ + 터미널)

IntelliJ는 **에디터**로만 쓰고, 컴파일·실행은 터미널의 `run.sh`로 한다.
실제 코테 환경(파일 하나 짜서 컴파일/실행)과 거의 동일하다.

## 빠른 시작

```bash
cd codingtest

# 1) 템플릿 복사해서 문제별 파일 만들기
cp template_programmers.cpp prob01.cpp     # 프로그래머스 함수형
cp template_stdin.cpp       prob02.cpp     # 표준 입출력형

# 2) IntelliJ로 prob01.cpp 열어서 풀기

# 3) 터미널에서 컴파일 & 실행
./run.sh prob01.cpp
```

## 입력이 필요한 문제 (표준 입출력형)

`run.sh`는 다음 우선순위로 입력 파일을 자동으로 찾아 stdin에 넣어준다.

1. 인자로 직접 지정한 파일
2. **소스와 같은 이름**의 `.txt` 또는 `.in`  (예: `prob01.cpp` → `prob01.txt`)
3. 폴더 공용 `input.txt`  (하위호환)

문제마다 입력을 따로 두려면 **소스와 같은 이름**으로 만드는 게 편하다.

```
prob01.cpp  ←→  prob01.txt
prob02.cpp  ←→  prob02.txt
```

```bash
# prob01.txt 예:
#   3
#   10 20 30
./run.sh prob01.cpp              # prob01.txt 자동 사용
./run.sh prob01.cpp other.txt    # 다른 입력 파일로 바꿔 끼우기
```

실행 시 `(입력 파일: ...)`로 어떤 입력을 썼는지 표시해주므로, 엉뚱한 입력으로
돌리는 실수를 방지할 수 있다.

## 환경 메모

- 컴파일러: Apple clang (`clang++`), C++17.
- `#include <bits/stdc++.h>`는 `include/bits/stdc++.h`를 만들어 `-I`로 잡아준다 (GCC 흉내).
  → **주의**: 채점 서버는 보통 GCC다. 로컬에서 되는 게 서버에서도 되지만, 반대로
    libc++/libstdc++ 차이로 드물게 동작이 다를 수 있다. 핵심 STL은 동일하니 신경 안 써도 됨.
- `run.sh`는 AddressSanitizer + UB Sanitizer를 켜서 **메모리 오류·정수 오버플로를 런타임에 잡아준다**.
  실전 제출 코드엔 영향 없고, 로컬 디버깅 품질만 올라간다.

## 프로그래머스 제출 팁

`template_programmers.cpp`에서 `solution` 함수만 복사해 붙여넣는다.
`main()`은 로컬 테스트용이므로 제출 시 빼도 되고, 그냥 둬도 채점에는 보통 무관하다
(프로그래머스는 `solution`만 호출).
