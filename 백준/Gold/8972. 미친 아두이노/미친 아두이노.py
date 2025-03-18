n, m = map(int, input().split())
grid = [list(input()) for i in range(n)]

r, c = -1, -1
for i in range(n):
    for j in range(m):
        if grid[i][j] == "I":
            r, c = i, j
d_list = list(input())
for i in range(len(d_list)):
    d_list[i] = int(d_list[i]) - 1
row = [1, 1, 1, 0, 0, 0, -1, -1, -1]
col = [-1, 0, 1, -1, 0, 1, -1, 0, 1]
time = 0
end = False
for dd in range(1, len(d_list) + 1):

    crazy_list = []
    for i in range(n):
        for j in range(m):
            if grid[i][j] == "R":
                crazy_list.append((i, j))

    d = d_list[dd - 1]
    nr = r + row[d]
    nc = c + col[d]
    if grid[nr][nc] == "R":
        time = dd
        end = True
        break
    if d != 4:
        grid[nr][nc] = "I"
        grid[r][c] = "."
    r = nr
    c = nc
    check = [[0] * m for i in range(n)]
    for i in range(len(crazy_list)):
        cr, cc = crazy_list[i]
        grid[cr][cc] = "."
        min_d = abs(r - cr) + abs(c - cc)
        d = -1
        for k in range(9):
            ncr = cr + row[k]
            ncc = cc + col[k]
            new_d = abs(r - ncr) + abs(c - ncc)
            if new_d < min_d:
                min_d = new_d
                d = k
        if (cr + row[d], cc + col[d]) == (r, c):
            time = dd
            end = True
            break
        check[cr + row[d]][cc + col[d]] += 1
    if end:
        break
    for i in range(n):
        for j in range(m):
            if check[i][j] == 1:
                grid[i][j] = "R"
if end:
    print(f"kraj {time}")
else:
    for _ in grid:
        print("".join(_))
