'''
# 두번째 풀이
    count 함수 적용, input, 등

문제설명
    방화벽을 3개까지 추가로 설치할 수 있을 때
    불이 안번지는 최대 갯수는?
입력
    맵크기
    2 불 1 벽 0 퍼질 수 있음
구상
    3개 선택은 조합으로 하고
    모든 경우의 수에서 bfs로 돌리기
필요한 메서드
    combi : 방 3개 조합
    bfs : 불 퍼짐
    count : 불이 안퍼지는 갯수 세기 -> ans 갱신 필요

'''

from collections import deque
import sys
input = sys.stdin.readline

def combi(sidx, idx): # 방화벽 설치 가능한 조합으로 bfs 돌릴 거임.
    global ans, grid

    if sidx == 3: # 조합 끝
        grid_copy = [_[:] for _ in grid]

        q = deque(origin_q)  # 여기서 q = origin_q 해서 디버깅했음.. 깊은 복사 해줘야됨. 덱이라 슬라이싱은 안된다.

        for r, c in location_sel: # 선택한 곳 방화벽 처리
            grid[r][c] = 1

        bfs(q) # 불 확장

        ans = max(no_fire_count(grid), ans) # 안전한 곳 갯수 세기

        grid = [_[:] for _ in grid_copy] # 맵 원상 복구

        return

    if idx == len(location_arr):
        return

    location_sel[sidx] = location_arr[idx]
    combi(sidx + 1, idx + 1)
    combi(sidx, idx + 1)

def bfs(q):
    while q:
        r, c = q.popleft()

        for k in range(4):
            nr = r + row[k]
            nc = c + col[k]
            if not (0 <= nr < n and 0 <= nc < m):
                continue

            if grid[nr][nc] == 0: # 불 확장 가능하면
                grid[nr][nc] = 2 # 모두 퍼져나감
                q.append((nr, nc))  # visited 굳이 필요 없을듯 -> 그래서 썼다가 안썼음

def no_fire_count(grid):  # 안전구역 갯수 세주는 함수
    no_fire = 0

    for _ in grid:
        no_fire += _.count(0) # 간단히..

    # for i in range(n):
    #     for j in range(m):
    #         if grid[i][j] == 0:
    #             no_fire += 1

    return no_fire

n, m = map(int, input().split())
grid = [list(map(int, input().split())) for i in range(n)]

location_arr = [] # 벽을 세울 수 있는 전체 좌표 = 즉 0인 좌표를 모두 담을 것임
location_sel = [0] * 3  # 벽 3개만 고를거임

origin_q = deque() # 모든 경우의 수에서 q에 담지 않게 미리 2인 값(==불) 담아주기
for i in range(n):
    for j in range(m):
        if grid[i][j] == 0: # 벽을 세울 수 있는 곳 모두 담기
            location_arr.append((i, j))
        elif grid[i][j] == 2: # 불을 q에 미리 담기
            origin_q.append((i, j))

row = [-1, 1, 0, 0]
col = [0, 0, 1, -1]

ans = 0
combi(0, 0)
print(ans)
