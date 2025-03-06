'''
# 네번째 풀이
    new_grid를 안쓰고
    visited를 활용해보겠서요
# 세번째 풀이
    굳이 넘버링이 필요하지 않겠어요
    set말고 q써서 new_grid 반영해주기
# 두번째 풀이
    탈출 두개의 배열 비교하지 않고 flag

문제설명
    2차원배열이 있을때
    상하좌우 인접한 칸과 내 차이가 기준에 해당하면 합쳐준다.
    -> 넘버링 필요...
    계란 이동이 없을떄까지 반복한다.

구상
    bfs로 넘버링 한다.
    넘버링 후 계산.
출력
    계란의 이동이 일어난 총 횟수
'''
from collections import deque

n, small, big = map(int, input().split())
grid = [list(map(int, input().split())) for i in range(n)]
time = 0
row = [-1, 1, 0, 0]
col = [0, 0, 1, -1]


def bfs(r, c):
    global is_exit
    q = deque([(r, c)])
    cnt = 0
    location = deque()
    summ = 0
    while q:
        r, c = q.popleft()

        location.append((r, c))
        summ += grid[r][c]
        cnt += 1
        for k in range(4):
            nr = r + row[k]
            nc = c + col[k]
            if not (0 <= nr < n and 0 <= nc < n) or visited[nr][nc]:
                continue
            if small <= abs(grid[r][c] - grid[nr][nc]) <= big:
                visited[nr][nc] = True
                q.append((nr, nc))
    if cnt > 1:
        is_exit = False
    ele_num = summ // cnt

    while cnt > 1 and location:
        r, c = location.popleft()
        grid[r][c] = ele_num


while True:

    visited = [[False] * n for i in range(n)]
    is_exit = True
    for i in range(n):
        for j in range(n):
            if not visited[i][j]:
                visited[i][j] = True
                bfs(i, j)

    if is_exit:
        break
    time += 1

print(time)
