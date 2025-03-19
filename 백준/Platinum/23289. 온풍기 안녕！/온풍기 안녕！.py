'''
코드 리팩토링............................
dict로 간소화 하지만 하드코딩이나 다름 없음
'''
from collections import deque


def warm():
    for r, c, d in robot_list:
        new_grid = [[0] * m for i in range(n)]

        # 일단 퍼져나가
        sr, sc = r + row[d], c + col[d]
        new_grid[sr][sc] = 5  # 5인 곳 표시
        q = deque([(sr, sc, 4)])
        while q:
            r, c, power = q.popleft()
            if power == 0:
                break

            # 5 옆에
            nr = r + row[d]
            nc = c + col[d]
            if (r, c, nr, nc, d) not in block_list and 0 <= nr < n and 0 <= nc < m:
                new_grid[nr][nc] = power
                q.append((nr, nc, power - 1))

            nr1 = r + row[dir_dict[d][0]]
            nc1 = c + col[dir_dict[d][0]]

            nr2 = nr + row[dir_dict[d][0]]
            nc2 = nc + col[dir_dict[d][0]]

            if 0 <= nr2 < n and 0 <= nc2 < m and (r, c, nr1, nc1, dir_dict[d][0]) not in block_list and \
                    (nr1, nc1, nr2, nc2, dir_dict[d][1]) not in block_list:
                new_grid[nr2][nc2] = power
                q.append((nr2, nc2, power - 1))

            nr1 = r + row[dir_dict[d][2]]
            nc1 = c + col[dir_dict[d][2]]

            nr2 = nr + row[dir_dict[d][2]]
            nc2 = nc + col[dir_dict[d][2]]
            if 0 <= nr2 < n and 0 <= nc2 < m and (r, c, nr1, nc1, dir_dict[d][2]) not in block_list and \
                    (nr1, nc1, nr2, nc2, dir_dict[d][1]) not in block_list:
                new_grid[nr2][nc2] = power
                q.append((nr2, nc2, power - 1))

        for i in range(n):
            for j in range(m):
                grid[i][j] += new_grid[i][j]


def control():
    plus_grid = [[0] * m for i in range(n)]
    minus_grid = [[0] * m for i in range(n)]
    for i in range(n):
        for j in range(m):
            if grid[i][j] > 0:
                total_minus = 0
                for k in range(4):
                    nr = i + row[k]
                    nc = j + col[k]
                    if 0 <= nr < n and 0 <= nc < m and grid[nr][nc] < grid[i][j] and \
                            (i, j, nr, nc, k) not in block_list:
                        minus = grid[i][j] - grid[nr][nc]
                        plus_grid[nr][nc] += minus // 4
                        total_minus += minus // 4
                minus_grid[i][j] = total_minus

    for i in range(n):
        for j in range(m):
            grid[i][j] += plus_grid[i][j] - minus_grid[i][j]


def edge():
    for i in range(n):
        for j in range(m):
            if i == 0 or i == n - 1 or j == 0 or j == m - 1:
                if grid[i][j] > 0:
                    grid[i][j] -= 1


def valid(grid):
    for r, c in valid_location:
        if grid[r][c] < limit:
            return False
    return True


n, m, limit = map(int, input().split())  # limit 이상
grid = [list(map(int, input().split())) for i in range(n)]
valid_location = set()
robot_list = []
for i in range(n):
    for j in range(m):
        if grid[i][j] == 5:
            valid_location.add((i, j))
            grid[i][j] = 0
        elif 0 < grid[i][j] < 5:
            robot_list.append((i, j, grid[i][j] - 1))
            grid[i][j] = 0

block_num = int(input())
block_list = set()
row = [0, 0, -1, 1]
col = [1, -1, 0, 0]
for b in range(block_num):
    r, c, d = map(int, input().split())
    if d == 0:
        block_list.add((r - 1, c - 1, r - 2, c - 1, 2))
        block_list.add((r - 2, c - 1, r - 1, c - 1, 3))
    else:
        block_list.add((r - 1, c - 1, r - 1, c, 0))
        block_list.add((r - 1, c, r - 1, c - 1, 1))
dir_dict = {0: [2, 0, 3], 1: [2, 1, 3], 2: [1, 2, 0], 3: [1, 3, 0]}
end = False
ans = 101

for choco in range(1, 101):
    warm()
    control()
    edge()
    if valid(grid):
        ans = choco
        break
print(ans)
