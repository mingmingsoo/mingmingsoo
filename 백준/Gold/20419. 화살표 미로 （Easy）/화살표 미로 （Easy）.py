'''
갈수 있는 방향을 제외하고
추가
'''
import sys
from collections import deque

input = sys.stdin.readline

n, m, card = map(int, input().split())
grid = [list(input().rstrip()) for i in range(n)]
for i in range(n):
    for j in range(m):
        grid[i][j] = "URDL".index(grid[i][j])
ans = "No"
row = [-1, 0, 1, 0]
col = [0, 1, 0, -1]


def bfs(sr, sc, er, ec):
    global ans
    q = deque()
    q.append((sr, sc, grid[sr][sc], card, card))
    visited = [[[[[False] * (card + 1) for c in range(card + 1)] for d in range(4)] for mm in range(m)] for nn in
               range(n)]
    visited[sr][sc][grid[sr][sc]][card][card] = True
    for i in range(1, card + 1):
        if (0 <= sr + row[(grid[sr][sc] + i) % 4] < n and 0 <= sc + col[(grid[sr][sc] + i) % 4] < m):
            visited[sr][sc][(grid[sr][sc] + i) % 4][card][card - i] = True
            q.append((sr, sc, (grid[sr][sc] + i) % 4, card, card - i))
        if (0 <= sr + row[(grid[sr][sc] - i + 4) % 4] < n and 0 <= sc + col[(grid[sr][sc] - i + 4) % 4] < m):
            visited[sr][sc][(grid[sr][sc] - i + 4) % 4][card - i][card] = True
            q.append((sr, sc, (grid[sr][sc] - i + 4) % 4, card - i, card))
    while q:
        r, c, d, left, right = q.popleft()
        # print( r, c,"URDL"[d], left, right)
        if r == er and c == ec:
            ans = "Yes"
            return
        nr = r + row[d]
        nc = c + col[d]
        if nr == er and nc == ec:
            ans = "Yes"
            return
        if not (0 <= nr < n and 0 <= nc < m):
            continue
        next_d = grid[nr][nc]

        if not visited[nr][nc][grid[nr][nc]][left][right]:
            if (0 <= nr + row[next_d] < n and 0 <= nc + col[next_d] < m):
                visited[nr][nc][grid[nr][nc]][left][right] = True
                q.append((nr, nc, next_d, left, right))
        for i in range(1, left + 1):
            if not visited[nr][nc][(next_d - i + 4) % 4][left - i][right]:
                if (0 <= nr + row[(next_d - i + 4) % 4] < n and 0 <= nc + col[(next_d - i + 4) % 4] < m):
                    visited[nr][nc][(next_d - i + 4) % 4][left - i][right] = True
                    q.append((nr, nc, (next_d - i + 4) % 4, left - i, right))
        for i in range(1, right + 1):
            if not visited[nr][nc][(next_d + i) % 4][left][right - i]:
                if (0 <= nr + row[(next_d + i) % 4] < n and 0 <= nc + col[(next_d + i) % 4] < m):
                    visited[nr][nc][(next_d + i) % 4][left][right - i] = True
                    q.append((nr, nc, (next_d + i) % 4, left, right - i))


bfs(0, 0, n - 1, m - 1)
print(ans)
