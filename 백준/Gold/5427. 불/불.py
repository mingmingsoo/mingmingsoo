from collections import deque

T = int(input())
for tc in range(T):
    '''
    불!!!!!!!!!!!!!!!!
    상근이가 이동하고 불이 퍼진다
    아니다.. 불이 먼저 퍼진다..
    테케 1번을 잘못 봤다.
    '''

    m, n = map(int, input().split())
    grid = [list(input()) for i in range(n)]
    player_q = deque()
    fire_q = deque()
    visited = [[False] * m for i in range(n)]
    for i in range(n):
        for j in range(m):
            if grid[i][j] == "@":
                player_q.append((i, j, 0))
                visited[i][j] = True
                grid[i][j] = "."
            elif grid[i][j] == "*":
                fire_q.append((i, j))
    ans = "IMPOSSIBLE"
    row = [-1, 1, 0, 0]
    col = [0, 0, 1, -1]


    def bfs():
        global ans

        while player_q:
            if fire_q:
                fq_size = len(fire_q)
                for fq in range(fq_size):
                    fr, fc = fire_q.popleft()
                    for k in range(4):
                        nr = fr + row[k]
                        nc = fc + col[k]
                        if not (0 <= nr < n and 0 <= nc < m):
                            continue
                        if grid[nr][nc] == ".":
                            grid[nr][nc] = "*"
                            fire_q.append((nr, nc))

            pq_size = len(player_q)
            for pq in range(pq_size):
                r, c, time = player_q.popleft()
                for k in range(4):
                    nr = r + row[k]
                    nc = c + col[k]

                    if not (0 <= nr < n and 0 <= nc < m):
                        ans = time + 1
                        return
                    if grid[nr][nc] != "." or visited[nr][nc]:
                        continue
                    player_q.append((nr, nc, time + 1))
                    visited[nr][nc] = True


    bfs()
    print(ans)
