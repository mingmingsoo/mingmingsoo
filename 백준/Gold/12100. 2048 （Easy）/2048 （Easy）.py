'''
문제 설명
    1. 4방향으로 중력
    2. 그리고 방향을 기준으로 합쳐
print(4**5)
헷갈리는 점이 5번움직이고 최댓값 계산인지 ..
근데 당연히 많이 움직일 수록 최댓값이 나오겠져
'''

n = int(input())
grid = [list(map(int, input().split())) for i in range(n)]
row = [-1, 1, 0, 0]
col = [0, 0, -1, 1]
ans = 0

sel = [0] * 5


def gravity(d, grid):
    if d == 0:  # 북쪽
        for j in range(n):
            while True:
                down = 0
                for i in range(0, n - 1, 1):
                    if grid[i][j] == 0 and grid[i + 1][j] != 0:
                        grid[i][j], grid[i + 1][j] = grid[i + 1][j], grid[i][j]
                        down += 1
                if down == 0:
                    break
    elif d == 1:  # 남쪽
        for j in range(n):
            while True:
                down = 0
                for i in range(n - 1, 0, -1):
                    if grid[i][j] == 0 and grid[i - 1][j] != 0:
                        grid[i][j], grid[i - 1][j] = grid[i - 1][j], grid[i][j]
                        down += 1
                if down == 0:
                    break
    elif d == 2:  # 왼쪽으로 당겨
        for i in range(n):
            while True:
                down = 0
                for j in range(0, n - 1, 1):
                    if grid[i][j] == 0 and grid[i][j + 1] != 0:
                        grid[i][j], grid[i][j + 1] = grid[i][j + 1], grid[i][j]
                        down += 1
                if down == 0:
                    break
    elif d == 3:  # 오른쪽으로 당겨
        for i in range(n):
            while True:
                down = 0
                for j in range(n - 1, 0, -1):
                    if grid[i][j] == 0 and grid[i][j - 1] != 0:
                        grid[i][j], grid[i][j - 1] = grid[i][j - 1], grid[i][j]
                        down += 1
                if down == 0:
                    break


def merge(d, grid):
    if d == 0:
        for j in range(n):
            i = 0
            while i < n - 1:
                if grid[i][j] == grid[i + 1][j]:
                    grid[i][j] = grid[i][j] * 2
                    grid[i + 1][j] = 0
                    i += 1
                i += 1
    elif d == 1:
        for j in range(n):
            i = n - 1
            while i > 0:
                if grid[i][j] == grid[i - 1][j]:
                    grid[i][j] = grid[i][j] * 2
                    grid[i - 1][j] = 0
                    i -= 1
                i -= 1
    elif d == 2:
        for i in range(n):
            j = 0
            while j < n - 1:
                if grid[i][j] == grid[i][j + 1]:
                    grid[i][j] = grid[i][j] * 2
                    grid[i][j + 1] = 0
                    j += 1
                j += 1
    elif d == 3:
        for i in range(n):
            j = n - 1
            while j > 0:
                if grid[i][j] == grid[i][j - 1]:
                    grid[i][j] = grid[i][j] * 2
                    grid[i][j - 1] = 0
                    j -= 1
                j -= 1


def perm(idx):
    global ans, grid
    if idx == 5:
        grid_origin = [_[:] for _ in grid]
        for d in sel:
            gravity(d, grid)
            merge(d, grid)
            gravity(d, grid)

        for i in range(n):
            for j in range(n):
                ans = max(ans, grid[i][j])
        grid = [_[:] for _ in grid_origin]
        return
    for i in range(4):
        sel[idx] = i
        perm(idx + 1)


perm(0)
print(ans)