n, m, virus_num = map(int, input().split())  # 맵 크기와 곰팡이 수
grid = [[() for i in range(m)] for i in range(n)]
row = [-1, 1, 0, 0]
col = [0, 0, 1, -1]
for v in range(virus_num):
    r, c, s, d, b = map(int, input().split())  # 위치, 속력, 방향, 크기
    if d in (1,2):
        grid[r - 1][c - 1] = (b, d - 1,s % (2 * (n - 1)))  # 크기, 방향, 속력 순으로 넣는다
    else:
        grid[r - 1][c - 1] = (b, d - 1, s % (2 * (m - 1)))
move = {0: 1, 1: 0, 2: 3, 3: 2}
jdx = 0
eat = 0
while jdx < m:  # 승용이 움직임
    for i in range(n):
        if grid[i][jdx]:  # 곰팡이 있으면
            eat += grid[i][jdx][0]  # 크기만큼 먹는다
            grid[i][jdx] = ()  # 비었다!
            break
    # 2. 움직인다.
    move_list = []
    for i in range(n):
        for j in range(m):
            if grid[i][j]:
                virus = grid[i][j]
                b, d, s = virus[0], virus[1], virus[2]  # 크기, 방향, 속력
                grid[i][j] = ()
                nr = i + row[d] * s
                nc = j + col[d] * s
                if d in (0, 1):
                    size = 2 * (n - 1)
                    nr = (nr + size) % size
                    if nr >= n:
                        nr = size - nr
                        d = move[d]
                elif d in (2, 3):
                    size = 2 * (m - 1)
                    nc = (nc + size) % size
                    if nc >= m:
                        nc = size - nc
                        d = move[d]
                move_list.append((nr, nc, b, d, s))
    for r, c, b, d, s in move_list:
        if not grid[r][c]:
            grid[r][c] = (b, d, s)
        else:
            if b > grid[r][c][0]:
                grid[r][c] = (b, d, s)

    jdx += 1  # 다음 칸 탐색
print(eat)
