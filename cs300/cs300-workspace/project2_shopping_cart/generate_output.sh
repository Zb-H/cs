#!/usr/bin/env bash
set -euo pipefail

# Run this script from the cs300-workspace root directory.
PROJECT_DIR="project2_shopping_cart"
TEST_EXE="build/${PROJECT_DIR}/tests/project2_tests"
APP_EXE="build/${PROJECT_DIR}/src/project2_app"
OUTPUT_FILE="${PROJECT_DIR}/project2_output.txt"

cmake -S . -B build
cmake --build build

{
  echo "=== Project 2 GoogleTest output ==="
  "${TEST_EXE}"
  echo
  echo "=== Project 2 sample program run ==="
  printf 'Apple\n1.25\n2\nBread\n3.50\n1\ndone\n' | "${APP_EXE}"
} 2>&1 | tee "${OUTPUT_FILE}"
