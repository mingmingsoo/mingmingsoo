'''
2 1
5 5
5 5
'''
import sys

input = sys.stdin.readline

n, limit = map(int, input().split())
grid = [[9] + list(map(int, input().split())) + [9] for i in range(n)]  # 9로 패딩
m = n + 2
grid[n - 1][m - 1] = grid[0][0] = 5
ans = 50 * 50 + 1
row = [-1, 0, 1, 0]
col = [0, 1, 0, -1]
dirs = {0: set((0, 1, 4)), 1: set((1, 3, 5)), 2: set((2, 3, 4)), 3: set((0, 2, 5))}
possible_go = {0: (1, 2), 1: (2, 3), 2: (0, 1), 3: (0, 3), 4: (0, 2), 5: (1, 3)}
r, c, er, ec = 0, 0, n - 1, m - 1


# print("--------------------")
# for i in range(n):
#     for j in range(m):
#         print("┌┐└┘│─   X"[grid[i][j]], end=" ")
#     print()


def valid():
    global ans
    r, c, er, ec = 0, 0, n - 1, m - 1
    visited = [[False] * m for i in range(n)]
    visited[r][c] = True
    ele = 0
    while True:
        if ele >= ans:
            return False
        ele += 1
        if (r, c) == (er, ec):
            ans = min(ans, ele)
            return True
        possible = False
        for k in possible_go[grid[r][c]]:
            nr = r + row[k]
            nc = c + col[k]
            if not (0 <= nr < n and 0 <= nc < m) or grid[nr][nc] == 9:
                continue
            if not visited[nr][nc] and grid[nr][nc] in dirs[k]:  # 이거면 갈 수 있음
                visited[nr][nc] = True
                r = nr
                c = nc
                possible = True
                break
        if not possible:
            break
    return False


if limit == 0:
    if (valid()):
        print(ans - 2)
    else:
        print(-1)
else:
    possible = False
    for i in range(n):
        for j in range(1, m - 1):
            origin = grid[i][j]
            for w in range(0, 6):
                if w == origin:
                    continue
                grid[i][j] = w
                if (valid()):
                    possible = True
                    # print("--------------------")
                    # for i in range(n):
                    #     for j in range(m):
                    #         print("┌┐└┘│─   X"[grid[i][j]], end=" ")
                    #     print()
                grid[i][j] = origin
    if possible:
        print(ans - 2)
    else:
        print(-1)
