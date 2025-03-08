'''
절벽 고르는건 백트래킹.
근데 교차는 안됨.
근데 교차가 왜안되징??
흠...
교차: 4가지 방향 탐색


오작교는 이처럼 매우 불안정하므로, 견우는 안전을 위해 두 번 연속으로 오작교를 건너지는 않기로 했다.
'''
import heapq
from collections import deque

n, my_time = map(int, input().split())
grid = [list(map(int, input().split())) for i in range(n)]


def is_range(r, c):
    if 0 <= r < n and 0 <= c < n:
        return True
    return False


plus = []
row = [-1, 0, 1, 0]
col = [0, 1, 0, -1]
for i in range(n):
    for j in range(n):
        if grid[i][j] == 0:
            isOk = True
            if is_range(i + row[0], j + col[0]) and is_range(i + row[1], j + col[1]) \
                    and grid[i + row[0]][j + col[0]] == grid[i + row[1]][j + col[1]] == 0:
                isOk = False
                continue
            if is_range(i + row[1], j + col[1]) and is_range(i + row[2], j + col[2]) \
                    and grid[i + row[1]][j + col[1]] == grid[i + row[2]][j + col[2]] == 0:
                isOk = False
                continue
            if is_range(i + row[2], j + col[2]) and is_range(i + row[3], j + col[3]) \
                    and grid[i + row[2]][j + col[2]] == grid[i + row[3]][j + col[3]] == 0:
                isOk = False
                continue
            if is_range(i + row[3], j + col[3]) and is_range(i + row[0], j + col[0]) \
                    and grid[i + row[3]][j + col[3]] == grid[i + row[0]][j + col[0]] == 0:
                isOk = False
                continue
            if isOk:
                plus.append((i, j))

ans = int(1e9)


def bfs():
    global ans
    q = []
    heapq.heappush(q, (0, 0, 0))
    d = [[int(1e9)] * n for i in range(n)]
    d[0][0] = 0

    while q:
        time, r, c = heapq.heappop(q)
        if time > d[r][c]:
            continue
        if r == n - 1 and c == n - 1:
            ans = min(ans, time)
            return
        for k in range(4):
            nr = r + row[k]
            nc = c + col[k]
            if not is_range(nr, nc):
                continue
            if grid[r][c] > 1:
                if grid[nr][nc] == 1:
                    if d[nr][nc] > time + 1:
                        heapq.heappush(q, (time + 1, nr, nc))
                        d[nr][nc] = time + 1
            else:
                if grid[nr][nc] == 1:
                    if d[nr][nc] > time + 1:
                        heapq.heappush(q, (time + 1, nr, nc))
                        d[nr][nc] = time + 1
                elif grid[nr][nc] > 1 and (time + 1) % grid[nr][nc] == 0:
                    if d[nr][nc] > time + 1:
                        heapq.heappush(q, (time + 1, nr, nc))
                        d[nr][nc] = time + 1
                # 안건너고 기다리는 경우
                elif grid[nr][nc] > 1 and (time + 1) % grid[nr][nc] != 0:
                    if d[nr][nc] > time +  (time // grid[nr][nc] + 1) * grid[nr][nc] - time:
                        heapq.heappush(q, (time +  (time // grid[nr][nc] + 1) * grid[nr][nc] - time, nr, nc))
                        d[nr][nc] = time +  (time // grid[nr][nc] + 1) * grid[nr][nc] - time

def btk(idx):
    if idx == len(plus):
        return

    # 다리 놓는다.
    r, c = plus[idx]
    grid[r][c] = my_time
    bfs()
    grid[r][c] = 0
    btk(idx + 1)


btk(0)
print(ans)
