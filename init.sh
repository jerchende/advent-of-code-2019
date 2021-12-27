#!/bin/sh

DAY=$1
MONTH=12

if [ $MONTH != 12 ] || [ $DAY -gt 25 ]  ; then
  echo "init work only in december 🤶 ;-)"
  exit 1;
fi

mkdir -p src/main/java/net/erchen/adventofcode2019/day$DAY
mkdir -p src/test/java/net/erchen/adventofcode2019/day$DAY
mkdir -p src/test/resources/day$DAY

touch src/test/resources/day$DAY/sample.txt && \
git add src/test/resources/day$DAY/sample.txt

curl "https://adventofcode.com/2019/day/${DAY#0}/input" -H "cookie: session=$AOC_SESSION"  --output src/test/resources/day$DAY/input.txt && \
git add src/test/resources/day$DAY/input.txt