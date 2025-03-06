'''
두번째 풀이
회전 간단히.
함수화 명료하게.
'''

from collections import deque


def rotation(size): # 회전하는 함수
    # 내가 필요한 것 시작점들과 가로의 길이
    for sr in range(0, N, 2 ** L):
        for sc in range(0, N, 2 ** L):
            for i in range(0, size):
                for j in range(0, size):
                    new_grid[i + sr][j + sc] = grid[size - j - 1 + sr][i + sc]


def remove(): # 주변 갯수 세서 줄여주는 함수
    remove_grid = [[False] * N for i in range(N)]
    for i in range(N):
        for j in range(N):
            ele_round = 0
            for k in range(4):
                nr = i + row[k]
                nc = j + col[k]
                if not (0 <= nr < N and 0 <= nc < N) or new_grid[nr][nc] <= 0:
                    continue
                ele_round += 1
            if ele_round <= 2:
                remove_grid[i][j] = True

    for i in range(N):
        for j in range(N):
            if remove_grid[i][j]:
                if new_grid[i][j]>0:
                    new_grid[i][j] -= 1
def bfs(r, c): # 군집 갯수 구하는 함수
    global cluster
    visited[r][c] = True
    q = deque([(r, c)])
    ele = 0

    while q:
        r, c = q.popleft()
        ele += 1
        for k in range(4):
            nr = r + row[k]
            nc = c + col[k]
            if not (0 <= nr < N and 0 <= nc < N) or visited[nr][nc] or grid[nr][nc]<=0:
                continue
            q.append((nr, nc))
            visited[nr][nc] = True

    cluster = max(cluster, ele)


n, m = map(int, input().split())
N = 2 ** n
grid = [list(map(int, input().split())) for i in range(N)]
L_list = map(int, input().split())
row = [-1, 1, 0, 0]
col = [0, 0, 1, -1]

# 메인로직
for L in L_list:
    new_grid = [[0] * N for i in range(N)]
    rotation(2 ** L) # 회전해
    remove() # 얼음 줄어들어
    grid = new_grid

# 출력값들 구하기
# 1. 합
summ = 0
for _ in grid:
    summ += sum(_)
# 2. 클러스터 최대 크기
cluster = 0
visited = [[False] * N for i in range(N)]
for i in range(N):
    for j in range(N):
        if not visited[i][j] and grid[i][j] > 0:
            bfs(i, j)
print(summ)
print(cluster)
