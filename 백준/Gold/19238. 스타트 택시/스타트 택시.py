
import heapq
from collections import deque

n, m, power = map(int, input().split())
grid = [list(map(int, input().split())) for i in range(n)]
for i in range(n):
    for j in range(n):
        if grid[i][j] == 1:
            grid[i][j] = -1 # 택시 넘버링해줄거라 못가는 곳은 -1처리
r, c = map(lambda x: int(x) - 1, input().split())
end_info = {}
for mm in range(m):
    sr, sc, er, ec = map(lambda x: int(x) - 1, input().split())
    end_info[mm + 1] = (er, ec) # 도착지 정보 기록해두기
    grid[sr][sc] = mm + 1 # 맵에 넘버링 해주기

row = [-1, 1, 0, 0]
col = [0, 0, 1, -1]

ans = -1
for mm in range(m):
    used_power, next_r, next_c, next_numbering = -1, -1, -1, -1
    # [1-1] 혹시 내 위치가 손님 위치..?
    if grid[r][c] != 0 and grid[r][c] != -1:
        used_power = 0
        next_r = r
        next_c = c
        next_numbering = grid[r][c]
    # [1-2] 아니라면 최단경로 손님 찾기(bfs) -> 힙큐에 후보 담아줌
    else:
        user = []
        visited = [[False] * n for i in range(n)]
        visited[r][c] = True
        q = deque([(r, c, 0)])
        used_power, next_r, next_c, next_numbering = -1, -1, -1, -1
        while q:
            q_size = len(q)
            for qs in range(q_size):
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
            if user: # 손님 태울 수 있었으면 데리러 간다
                used_power, next_r, next_c, next_numbering = heapq.heappop(user)
                break
    if next_r == -1:
        power = -1
        break
    if used_power > power: # 근데 가는길에 연료 바닥났었으면 불가함 종료
        power = -1
        break

    grid[next_r][next_c] = 0 # 태웠으니까 맵에 0처리
    power -= used_power # 연료 소모 반영
    r, c = next_r, next_c # 택시 위치 반영

    # [2] 손님 데려다주기(bfs)
    er, ec = end_info[next_numbering] # 목적지
    used_power = 0

    visited = [[False] * n for i in range(n)]
    visited[r][c] = True
    q = deque([(r, c, 0)])
    end = False # 못데려다줬으면 납치이므로 -1 처리 해줄거임 그래서 필요한 플래그
    while q:
        r, c, dist = q.popleft()
        if r == er and c == ec:
            used_power = dist
            end = True # 데려다줬습니다!
            break
        for k in range(4):
            nr = r + row[k]
            nc = c + col[k]
            if not (0 <= nr < n and 0 <= nc < n) or visited[nr][nc] or grid[nr][nc] == -1:
                continue
            visited[nr][nc] = True
            q.append((nr, nc, dist + 1))
    if used_power > power: # 연료바닥났으면.. 미션 실패
        power = -1
        break
    if not end: # 납치 했어도 미션 실패 ..
        power = -1
        break
    # 연료 반영
    power -= used_power
    power += used_power * 2
    # 택시 위치 반영
    r, c = er, ec
print(power)
