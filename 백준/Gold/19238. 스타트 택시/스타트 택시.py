'''
문제설명
    1. 최단경로에 있는 승객 태우는데 행, 열 우선순위
    2. 승객태우고~ 도착까지 사용된 연료*2 를 목적지에 도달하면 채워짐(갈 때가 아니라)
    3. 이동 중 바닥나면 실패 업무끝 -1
    4. 모든 손님 이동할수 없으면 -1
구상
    bfs 힙큐 쓰고 다 돌아도 괜찮을듯?
    2차원 배열에 시작위치에만 손님 표시하고(인덱스 넘버링)
    인덱스와 도착위치를 담은 딕셔너리 생성
입력
    맵 크기, 손님 수, 처음 연료
    맵 정보
    택시 시작위치
    승객 위치

반례
6 1 15
0 0 1 0 0 0
1 1 1 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
5 5
5 5 0 0
정답 -1


6 1 15
0 0 1 0 0 0
1 1 1 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
5 5
5 5 3 3
19
택시 시작 위치가 승객일 때



'''
import heapq
from collections import deque

n, m, power = map(int, input().split())
grid = [list(map(int, input().split())) for i in range(n)]
for i in range(n):
    for j in range(n):
        if grid[i][j] == 1:
            grid[i][j] = -1
r, c = map(lambda x: int(x) - 1, input().split())
end_info = {}
for mm in range(m):
    sr, sc, er, ec = map(lambda x: int(x) - 1, input().split())
    end_info[mm + 1] = (er, ec)
    grid[sr][sc] = mm + 1

row = [-1, 1, 0, 0]
col = [0, 0, 1, -1]

ans = -1
for mm in range(m):
    used_power, next_r, next_c, next_numbering = -1, -1, -1, -1
    # 혹시 내 위치가 손님 위치..?
    if grid[r][c] !=0 and grid[r][c] != -1:
        used_power = 0
        next_r = r
        next_c = c
        next_numbering = grid[r][c]
    else:
        # 최단경로 손님 찾기(bfs) -> 힙큐 # 연료 검사
        user = []
        visited = [[False] * n for i in range(n)]
        visited[r][c] = True
        q = deque([(r, c, 0)])
        used_power, next_r, next_c, next_numbering = -1, -1, -1, -1
        while q:
            r, c, dist = q.popleft()
            for k in range(4):
                nr = r + row[k]
                nc = c + col[k]
                if not (0 <= nr < n and 0 <= nc < n) or visited[nr][nc] or grid[nr][nc] == -1:
                    continue
                if grid[nr][nc] == 0:
                    visited[nr][nc] = True
                    q.append((nr, nc, dist + 1))
                elif grid[nr][nc] != 0:
                    visited[nr][nc] = True
                    q.append((nr, nc, dist + 1))
                    heapq.heappush(user, (dist + 1, nr, nc, grid[nr][nc]))
        if user:
            used_power, next_r, next_c, next_numbering = heapq.heappop(user)
        else:
            power = -1
            break
        if used_power > power:
            power = -1
            break
    grid[next_r][next_c] = 0
    power -= used_power
    # print(used_power, power)
    # 손님 데려다주기(bfs) -> # 얘도 연료 검사
    er, ec = end_info[next_numbering]
    r, c = next_r, next_c
    used_power = 0

    visited = [[False] * n for i in range(n)]
    visited[r][c] = True
    q = deque([(r, c, 0)])
    end = False
    while q:
        r, c, dist = q.popleft()
        if r == er and c == ec:
            used_power = dist
            end = True
            break
        for k in range(4):
            nr = r + row[k]
            nc = c + col[k]
            if not (0 <= nr < n and 0 <= nc < n) or visited[nr][nc] or grid[nr][nc] == -1:
                continue
            visited[nr][nc] = True
            q.append((nr, nc, dist + 1))
    if used_power > power:
        power = -1
        break
    if not end:
        power = -1
        break
    power -= used_power
    power += used_power * 2
    # print(used_power, power)
    r, c = er, ec
print(power)
