import sys
input = sys.stdin.readline

n = int(input())
grid = [list(map(int, input().split())) for i in range(n)]

ans = 0
# 0 : 대각선으로 왔다
# 1: 가로로 왔다
# 2: 세로로 왔다

row = [1, 0, 1]  # 대각선, 가로, 세로 이동
col = [1, 1, 0]


def dfs(r, c, er, ec, dir):  #
    global ans
    if (r == er and c == ec):
        ans += 1
        return

    # 대각선은 모든 방향에서 가능
    nr = r + row[0]
    nc = c + col[0]
    if (0 <= nr < n and 0 <= nc < n):
        if (grid[nr - 1][nc] != 1 and grid[nr][nc - 1] != 1 and grid[nr][nc] != 1):
            dfs(nr, nc, er, ec, 0)  # 대각선에서 왔다.
    if dir == 0 or dir == 1:  # 대각선이거나 가로로 왔으면 가로로도 이동 가능
        nr = r + row[1]
        nc = c + col[1]
        if (0 <= nr < n and 0 <= nc < n):
            if (grid[nr][nc] != 1):
                dfs(nr, nc, er, ec, 1)  # 가로로 왔다.
    if dir == 0 or dir == 2:  # 대각선이거나 세로로 왔으면 세로로도 이동 가능
        nr = r + row[2]
        nc = c + col[2]
        if (0 <= nr < n and 0 <= nc < n):
            if (grid[nr][nc] != 1):
                dfs(nr, nc, er, ec, 2)  # 세로로 왔다.

dfs(0, 1, n - 1, n - 1, 1)
print(ans)
