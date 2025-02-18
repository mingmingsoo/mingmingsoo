T = int(input())
for tc in range(T):
    '''
    문제
        지훈이가 불에 타기전에 미로를 탈출할 수 있는가?
        불은 4방향으로 확산
    입력
        R,C
        맵 정보

        #: 벽
        .: 지나갈 수 있는 공간
        J: 지훈이의 미로에서의 초기위치 (지나갈 수 있는 공간)
        F: 불이 난 공간
    출력
        미로를 탈출할 수 있으면 빠른 탈출시간
        없으면 IMPOSSIBLE
    구상
        지훈이 q와 불 q를 따로 관리.
        지훈이 먼저 움직인다.
        지훈이가 범위 밖으로 벗어날 수 있으면 q는 종료

    헷갈리는 것,,, 불이 지훈이를 덮칠 수 있는가?
    '''
    from collections import deque

    m, n = map(int, input().split())
    grid = [list(input()) for i in range(n)]

    player_q = deque()
    fire_q = deque()
    visited = [[False] * m for i in range(n)]  # 지훈이 visited

    for i in range(n):
        for j in range(m):
            if grid[i][j] == "@":
                player_q.append((i, j, 1))
                visited[i][j] = True
                grid[i][j] = "."
            elif grid[i][j] == "*":
                fire_q.append((i, j))
    ans = "IMPOSSIBLE"


    def bfs():
        global ans
        row = [-1, 1, 0, 0]
        col = [0, 0, 1, -1]

        while player_q:
            if fire_q:
                fq_size = len(fire_q)
                for s in range(fq_size):
                    fr, fc = fire_q.popleft()
                    for k in range(4):
                        fnr = fr + row[k]
                        fnc = fc + col[k]
                        if not (0 <= fnr < n and 0 <= fnc < m):
                            continue
                        if grid[fnr][fnc] == ".":
                            grid[fnr][fnc] = "*"
                            fire_q.append((fnr, fnc))

            pq_size = len(player_q)
            for ps in range(pq_size):
                r, c, time = player_q.popleft()

                for k in range(4):
                    nr = r + row[k]
                    nc = c + col[k]
                    if not (0 <= nr < n and 0 <= nc < m):
                        ans = time
                        return
                    if not visited[nr][nc] and grid[nr][nc] == ".":
                        visited[nr][nc] = True
                        player_q.append((nr, nc, time + 1))



    bfs()
    print(ans)
    # for _ in grid:
    #     print(_)