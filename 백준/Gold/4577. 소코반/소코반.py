'''
반례
6 6
######
#W+B+#
#bbb.#
######
######
######
RR

4 4
####
#BB#
#w.#
####
RDRDRDRDRDRDRDRDRD
'''


def valid():
    global mission
    for i in range(n):
        for j in range(m):
            if grid[i][j] == "b":
                return False
    mission = True
    return True


ans = []
row = [-1, 1, 0, 0]
col = [0, 0, -1, 1]
tc = 1
while True:
    n, m = map(int, input().split())
    if n == m == 0:
        break
    grid = [list(input()) for i in range(n)]

    r = c = -1
    for i in range(n):
        for j in range(m):
            if grid[i][j] == "w":
                r, c = i, j
                grid[i][j] = "."
            elif grid[i][j] == "W":
                r, c = i, j
                grid[i][j] = "+"

    order_lst = list(input())
    mission = False
    for order in order_lst:

        if valid():
            break

        d = "UDLR".index(order)
        nr = r + row[d]
        nc = c + col[d]
        if grid[nr][nc] == "." or grid[nr][nc] == "+":
            r = nr
            c = nc
        elif grid[nr][nc] == "b":
            if grid[nr + row[d]][nc + col[d]] == ".":
                grid[nr + row[d]][nc + col[d]] = "b"
                grid[nr][nc] = "."
                r = nr
                c = nc
            elif grid[nr + row[d]][nc + col[d]] == "+":
                grid[nr + row[d]][nc + col[d]] = "B"
                grid[nr][nc] = "."
                r = nr
                c = nc
        elif grid[nr][nc] == "B":
            if grid[nr + row[d]][nc + col[d]] == ".":
                grid[nr + row[d]][nc + col[d]] = "b"
                grid[nr][nc] = "+"
                r = nr
                c = nc
            elif grid[nr + row[d]][nc + col[d]] == "+":
                grid[nr + row[d]][nc + col[d]] = "B"
                grid[nr][nc] = "+"  # 여기서 . 으로 되어있었음
                r = nr
                c = nc

    valid()

    if grid[r][c] == "+":
        grid[r][c] = "W"
    elif grid[r][c] == ".":
        grid[r][c] = "w"
    if mission:
        ans.append(f"Game {tc}: complete")
    else:
        ans.append(f"Game {tc}: incomplete")
    for _ in grid:
        ans.append("".join(_))
    tc += 1
# RR
for _ in ans:
    print(_)
