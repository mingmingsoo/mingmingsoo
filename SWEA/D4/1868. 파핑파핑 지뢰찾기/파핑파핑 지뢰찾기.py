from collections import deque


def count_pang(r, c):  # 주변에 몇개의 지뢰가 있는지 세주는 함수
    for k in range(8):
        nr = r + row[k]
        nc = c + col[k]
        if (not (0 <= nr < n and 0 <= nc < n)):
            continue
        if (grid[nr][nc] == "*"):
            return False
    return True


def bfs(i, j):
    q = deque([(i, j)])

    while q:
        r, c = q.popleft()
        for k in range(8):
            nr = r + row[k]
            nc = c + col[k]
            if (not (0 <= nr < n and 0 <= nc < n)):
                continue
            elif (grid[nr][nc]=="." and count_pang(nr,nc) and not visited[nr][nc]):
                visited[nr][nc] = True
                q.append((nr,nc))
            else:
                visited[nr][nc] = True

T = int(input())
for tc in range(T):

    n = int(input())
    grid = [list(input()) for i in range(n)]
    info = [[-1] * n for i in range(n)]
    row = [1, -1, 0, 0, 1, 1, -1, -1]
    col = [0, 0, 1, -1, 1, -1, 1, -1]

    ans = 0
    visited = [[False]*n for i in range(n)]
    for i in range(n):
        for j in range(n):
            if grid[i][j] =="." and count_pang(i, j) and not visited[i][j]:
                visited[i][j] = True
                bfs(i, j)
                ans += 1

    # info 가 -1이 아닌애들 세주기 (== 0에 의해 못터진 애들)
    for i in range(n):
        for j in range(n):
            if grid[i][j]=="." and not visited[i][j]:
                ans += 1

    print(f"#{tc + 1} {ans}")
