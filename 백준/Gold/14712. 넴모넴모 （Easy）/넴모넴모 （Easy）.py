'''
일단 부분집합으로 검사해
'''

n, m = map(int, input().split())
grid = [[0] * m for i in range(n)]

ans = 0


def dfs(idx):
    global ans

    if (idx == n * m):
        ans += 1
        return

    r = idx // m
    c = idx % m

    if grid[r - 1][c - 1] == 0 or grid[r][c - 1] == 0 or grid[r - 1][c] == 0:
        # 놓을 수 있으면
        grid[r][c] = 1  # 일단놔
        dfs(idx + 1)
        grid[r][c] = 0  # 아냐 안놔
    dfs(idx + 1)


dfs(0)
print(ans)
