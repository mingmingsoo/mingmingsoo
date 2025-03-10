'''
문제설명
    토네이도가 중심점에서 0,0을 향해 회전한다.
    이동하는 매 칸마다 모래가 흩뿌려진다.
    이때 격자 밖으로 나가는 모래양의 합은?
구상
    성실히 구현.....
    1. 일단 달팽이 구현 -> 중앙에서 들어가는게 어려워서 0,0에서 시작해서 중앙으로 가는 좌표 담기.
    2. 그다음에 흩뿌려지는거 구현
    3. 그다음에 회전 구현
'''
n = int(input())
grid = [list(map(int, input().split())) for i in range(n)]

r, c, d = 0, 0, 0
location = []
row = [0, 1, 0, -1]
col = [1, 0, -1, 0]
# 위 3 아래 1 왼 2 우 0
dirs = [
    [(-1, 1, -2, 2, 0, -1, 1, -1, 1, 0), (-1, -1, 0, 0, 2, 0, 0, 1, 1, 1)],
    [(-1, -1, 0, 0, 2, 0, 0, 1, 1, 1), (-1, 1, -2, 2, 0, -1, 1, -1, 1, 0)],
    [(-1, 1, -2, 2, 0, -1, 1, -1, 1, 0, 0), (1, 1, 0, 0, -2, 0, 0, -1, -1, -1, -1)],
    [(1, 1, 0, 0, -2, 0, 0, -1, -1, -1), (-1, 1, -2, 2, 0, -1, 1, -1, 1, 0)]
]

visited = [[0] * n for i in range(n)]
visited[0][0] = 1
while r != n // 2 or c != n // 2:
    if not (0 <= r + row[d] < n and 0 <= c + col[d] < n) or visited[r + row[d]][c + col[d]]:
        d = (d + 1) % 4
    nr = r + row[d]
    nc = c + col[d]
    visited[nr][nc] = 1
    r = nr
    c = nc
    location.append((r, c, (d + 2) % 4))
# 위 3 아래 1 왼 2 우 0
out = 0
while location:
    r, c, d = location.pop()
    nr = r + row[d]
    nc = c + col[d]
    # nr,nc 근방으로
    origin_mungi = grid[nr][nc]
    # 1%
    # 11(01) 22(23) 5(4) 77(56) 10(78)
    munji = origin_mungi * 1 // 100
    for k in (0, 1):
        nnr = nr + dirs[d][0][k]
        nnc = nc + dirs[d][1][k]
        if not (0 <= nnr < n and 0 <= nnc < n):
            out += munji
        else:
            grid[nnr][nnc] += munji
    # 2%
    munji = origin_mungi * 2 // 100
    for k in (2, 3):
        nnr = nr + dirs[d][0][k]
        nnc = nc + dirs[d][1][k]
        if not (0 <= nnr < n and 0 <= nnc < n):
            out += munji
        else:
            grid[nnr][nnc] += munji
    # 5%
    munji = origin_mungi * 5 // 100
    for k in (4,):
        nnr = nr + dirs[d][0][k]
        nnc = nc + dirs[d][1][k]
        if not (0 <= nnr < n and 0 <= nnc < n):
            out += munji
        else:
            grid[nnr][nnc] += munji
    # 7%
    munji = origin_mungi * 7 // 100
    for k in (5, 6):
        nnr = nr + dirs[d][0][k]
        nnc = nc + dirs[d][1][k]
        if not (0 <= nnr < n and 0 <= nnc < n):
            out += munji
        else:
            grid[nnr][nnc] += munji
    # 10%
    munji = origin_mungi * 10 // 100
    for k in (7, 8):
        nnr = nr + dirs[d][0][k]
        nnc = nc + dirs[d][1][k]
        if not (0 <= nnr < n and 0 <= nnc < n):
            out += munji
        else:
            grid[nnr][nnc] += munji
    # a
    a = origin_mungi - (origin_mungi * 1 // 100) * 2 - (origin_mungi * 2 // 100) * 2 - \
        (origin_mungi * 5 // 100) * 1 - (origin_mungi * 7 // 100) * 2 - (origin_mungi * 10 // 100) * 2

    grid[nr][nc] = 0
    anr = nr + dirs[d][0][9]
    anc = nc + dirs[d][1][9]
    if not (0 <= anr < n and 0 <= anc < n):
        out += a
    else:
        grid[anr][anc] += a

print(out)