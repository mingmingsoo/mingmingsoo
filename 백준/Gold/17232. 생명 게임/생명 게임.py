n, m, time_num = map(int, input().split())
limit, a, b = map(int, input().split())
grid = [list(input()) for i in range(n)]

for time in range(time_num):
    new_grid = [["."] * m for i in range(n)]
    for i in range(n):
        for j in range(m):
            cnt = 0
            for r in range(i - limit, i + limit + 1):
                for c in range(j - limit, j + limit + 1):
                    if r < 0 or r >= n or c < 0 or c >= m:
                        continue
                    if (r, c) == (i, j):
                        continue
                    if grid[r][c] == "*":
                        cnt += 1
            if grid[i][j] == "*":
                if a <= cnt <= b:
                    new_grid[i][j] = "*"
            else:
                if a < cnt <= b:
                    new_grid[i][j] = "*"
    grid = new_grid
for _ in grid:
    print("".join(_))
