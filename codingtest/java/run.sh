#!/usr/bin/env bash
# Java 코테 풀이 실행 스크립트 (C++ 쪽 run.sh의 자바 버전).
#
# 사용법:
#   ./run.sh sol.java              # 실행 (같은 이름 입력파일 있으면 stdin으로 사용)
#   ./run.sh sol.java my_in.txt    # 지정한 파일을 stdin으로 사용
#
# 특징:
#   - `java 파일.java` 단일 소스 런처 사용 → 별도 컴파일 단계/.class 파일 없음.
#     → 05_target_number.java 처럼 숫자로 시작하는 파일명도 그대로 됨
#       (클래스명은 파일명과 무관. 프로그래머스식 `class Solution` 그대로 사용).
#   - -Xss64m : 재귀 DFS에서 StackOverflow 방지 (스택 넉넉하게).
#   - -ea     : assert 켜서 디버깅에 활용 가능.

set -euo pipefail

if [ $# -lt 1 ]; then
    echo "사용법: $0 <소스.java> [입력파일]" >&2
    exit 1
fi

SRC="$1"
if [ ! -f "$SRC" ]; then
    echo "오류: 소스 파일을 찾을 수 없음: $SRC" >&2
    exit 1
fi

# 자동 포맷 (google-java-format이 설치돼 있을 때만).
#   brew install google-java-format
if command -v google-java-format >/dev/null 2>&1; then
    google-java-format --replace "$SRC"
fi

# 입력 파일 결정 (우선순위 순):
#   1) 인자로 직접 지정한 파일   ./run.sh prob01.java myinput.txt
#   2) 소스와 같은 이름의 입력    prob01.java -> prob01.txt 또는 prob01.in
#   3) 폴더 공용 input.txt        (하위호환)
INPUT="${2:-}"
if [ -z "$INPUT" ]; then
    DIR="$(dirname "$SRC")"
    BASE="$(basename "$SRC" .java)"           # prob01.java -> prob01
    for CANDIDATE in "$DIR/$BASE.txt" "$DIR/$BASE.in" "$DIR/input.txt"; do
        if [ -f "$CANDIDATE" ]; then
            INPUT="$CANDIDATE"
            break
        fi
    done
fi

[ -n "$INPUT" ] && echo "(입력 파일: $INPUT)"

echo "──────── 실행 결과 ────────"
# -Xss64m: 재귀 깊이 여유 / -ea: assert 활성화
if [ -n "$INPUT" ] && [ -f "$INPUT" ]; then
    java -ea -Xss64m "$SRC" < "$INPUT"
else
    java -ea -Xss64m "$SRC"
fi
