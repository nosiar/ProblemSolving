#!/usr/bin/env bash
# 코테 풀이 컴파일 & 실행 스크립트.
#
# 사용법:
#   ./run.sh sol.cpp              # 컴파일 후 실행 (같은 폴더에 input.txt 있으면 stdin으로 사용)
#   ./run.sh sol.cpp my_in.txt    # 지정한 파일을 stdin으로 사용
#
# 특징:
#   - #include <bits/stdc++.h> 지원 (이 저장소의 include/ 경로를 추가)
#   - 코테와 비슷한 컴파일 옵션 (-O2 -std=c++17)
#   - 디버깅에 유용한 경고/검사 옵션 켬

set -euo pipefail

# 스크립트가 있는 디렉토리 (어디서 실행하든 include 경로를 정확히 잡기 위함)
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
INCLUDE_DIR="$SCRIPT_DIR/include"

if [ $# -lt 1 ]; then
    echo "사용법: $0 <소스.cpp> [입력파일]" >&2
    exit 1
fi

SRC="$1"
if [ ! -f "$SRC" ]; then
    echo "오류: 소스 파일을 찾을 수 없음: $SRC" >&2
    exit 1
fi

# 실행 파일은 임시 위치에 (소스 폴더를 더럽히지 않음)
BIN="$(mktemp -t cttest)"
trap 'rm -f "$BIN"' EXIT

# 컴파일
# -fsanitize=address,undefined : 메모리/오버플로 버그를 런타임에 잡아줌 (디버깅에 강력)
clang++ -std=c++17 -O2 -Wall -Wextra \
    -fsanitize=address,undefined \
    -I "$INCLUDE_DIR" \
    "$SRC" -o "$BIN"

# 입력 파일 결정 (우선순위 순):
#   1) 인자로 직접 지정한 파일   ./run.sh prob01.cpp myinput.txt
#   2) 소스와 같은 이름의 입력    prob01.cpp -> prob01.txt 또는 prob01.in
#   3) 폴더 공용 input.txt        (하위호환)
INPUT="${2:-}"
if [ -z "$INPUT" ]; then
    DIR="$(dirname "$SRC")"
    BASE="$(basename "$SRC" .cpp)"            # prob01.cpp -> prob01
    for CANDIDATE in "$DIR/$BASE.txt" "$DIR/$BASE.in" "$DIR/input.txt"; do
        if [ -f "$CANDIDATE" ]; then
            INPUT="$CANDIDATE"
            break
        fi
    done
fi

[ -n "$INPUT" ] && echo "(입력 파일: $INPUT)"

# 실행
# MallocNanoZone=0: macOS의 작은 객체 malloc 최적화(nano zone)를 끈다.
# AddressSanitizer와 충돌해 나오는 "nano zone abandoned ..." 안내 메시지를 없애는 용도.
# 동작/성능에는 영향 없음.
echo "──────── 실행 결과 ────────"
if [ -n "$INPUT" ] && [ -f "$INPUT" ]; then
    MallocNanoZone=0 "$BIN" < "$INPUT"
else
    MallocNanoZone=0 "$BIN"
fi
