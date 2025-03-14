'''
두번째 풀이
    내가 풀면서도 변수명 헷갈렸음...
    변수명 다시
문제설명
    동서남북으로 기울여서 빨간구슬 빼기
    1. 파란 구슬이 먼저 빠지면 실패
    2. 빨간 구슬과 파란 구슬이 동시에 빠져도 실패
    3. 10번까지만 가능

구상
    while로 동서남북을 보내고 가다가 O를 만나는지 검사한다.
    1. 이때 빨간것도 O고 파란것도 O면 continue
    2. 이때 빨간건 아닌데 파란거가 O면 continue
    3. 중복 위치를 검사하는게 어려운데
        보내면서 다른 공의 위치를 각자 검사해준다.
    visited는 쓰지 않겠다.
'''
from collections import deque

n, m = map(int, input().split())
grid = [list(input()) for i in range(n)]
red_r = red_c = blue_r = blue_c = -1
for i in range(n):
    for j in range(m):
        if grid[i][j] == "R":
            red_r, red_c = i, j
            grid[i][j] = "."
        elif grid[i][j] == "B":
            blue_r, blue_c = i, j
            grid[i][j] = "."

q = deque([(red_r, red_c, blue_r, blue_c, 0)])
ans = -1
row = [-1, 1, 0, 0]
col = [0, 0, 1, -1]


def game():
    global ans
    while q:
        red_r, red_c, blue_r, blue_c, cnt = q.popleft()
        if cnt > 10:
            return
        if grid[blue_r][blue_c] == "O":  # 파란거 왔으면 동시에 왔든, 파랑이만 왔든 다음꺼 탐색.
            continue
        if grid[red_r][red_c] == "O":
            ans = cnt
            return

        origin_red_r, origin_red_c, origin_blue_r, origin_blue_c = red_r, red_c, blue_r, blue_c
        for k in range(4):
            red_r, red_c, blue_r, blue_c = origin_red_r, origin_red_c, origin_blue_r, origin_blue_c
            # 빨강이 움직여
            while True:
                next_red_r = red_r + row[k]
                next_red_c = red_c + col[k]
                if 0 <= next_red_r < n and 0 <= next_red_c < m and grid[next_red_r][next_red_c] != "#":
                    red_r = next_red_r
                    red_c = next_red_c
                    if grid[red_r][red_c] == "O":
                        break
                else:
                    break
            # 파랑이 움직여
            while True:
                next_blue_r = blue_r + row[k]
                next_blue_c = blue_c + col[k]
                # 파랑이 검사.
                if 0 <= next_blue_r < n and 0 <= next_blue_c < m and grid[next_blue_r][next_blue_c] != "#":  #
                    blue_r = next_blue_r
                    blue_c = next_blue_c
                    if grid[blue_r][blue_c] == "O":
                        break
                else:
                    break

            # 중복되는 위치 검사.
            if red_r == blue_r and red_c == blue_c and not grid[red_r][red_c] == "O":  # 목적지말고
                if k == 0:  # 북쪽으로 갔음
                    if origin_blue_r > origin_red_r:
                        blue_r += 1
                    else:
                        red_r += 1
                elif k == 1:  # 남쪽으로 갔음
                    if origin_blue_r < origin_red_r:
                        blue_r -= 1
                    else:
                        red_r -= 1
                elif k == 2:  # 오른쪽으로 갔음
                    if origin_blue_c < origin_red_c:  # 파란색이 왼쪽에 있었으면
                        blue_c -= 1
                    else:
                        red_c -= 1
                elif k == 3:  # 왼쪽으로 갔음
                    if origin_blue_c > origin_red_c:  # 파란색이 오른쪽에 있었으면
                        blue_c += 1
                    else:
                        red_c += 1
            # 빨, 파 둘 다 가만히 있었으면 q에 안넣어줌
            if not (
                    red_r == origin_red_r and red_c == origin_red_c and blue_r == origin_blue_r and blue_c == origin_blue_c):
                q.append((red_r, red_c, blue_r, blue_c, cnt + 1))


game()
print(ans)
