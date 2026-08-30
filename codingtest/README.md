# 코딩테스트 연습 환경

코테 복귀/면접 대비용. **언어별 트랙**으로 나뉘어 있고, 커리큘럼과 치트시트는 공유한다.

```
codingtest/
├── PROBLEMS.md              # 공유 커리큘럼 (프로그래머스 유형별 추천, 1~3주차)
├── cpp/                     # C++ 트랙
│   ├── run.sh               # clang++ 컴파일·실행 (ASan/UBSan, bits/stdc++.h)
│   ├── cheatsheet.md        # C++ STL 치트시트
│   ├── template_*.cpp
│   ├── .gen_skeletons.sh    # 스켈레톤 생성기
│   └── problems/week{1,2,3}/*.cpp
└── java/                    # Java 트랙 (백엔드 면접용)
    ├── run.sh               # java 단일 소스 런처 (-Xss64m/-ea)
    ├── cheatsheet.md        # Java 치트시트 (C++ PS 경험자용 대응표)
    ├── template_*.java
    ├── .gen_skeletons.sh    # 스켈레톤 생성기
    └── problems/week{1,2,3}/*.java
```

## 어느 트랙?

- **백엔드 면접이 목표** → `java/` (실무 스택과 언어 일치). → [java/README.md](java/README.md)
- **알고리즘/성능 위주, C++ 실무** → `cpp/`. → [cpp/README.md](cpp/README.md)

두 트랙 모두 **같은 문제(`PROBLEMS.md`)를 같은 순서로** 스켈레톤화해 두었다.
C++로 이미 푼 문제를 Java로 다시 푸는 식으로 병행하기 좋다.

## 공통 워크플로

IntelliJ는 **에디터**로만, 실행은 각 트랙의 `run.sh`로 한다. 스켈레톤을 열어 `// TODO`만 채우면 된다.

```bash
cd codingtest/java && ./run.sh problems/week1/07_unfinished_player.java   # Java
cd codingtest/cpp  && ./run.sh problems/week1/07_unfinished_player.cpp    # C++
```

각 `run.sh`는 소스와 같은 이름의 입력파일(`prob01.txt`/`.in`) 또는 `input.txt`를 자동으로 stdin에 넣는다.
