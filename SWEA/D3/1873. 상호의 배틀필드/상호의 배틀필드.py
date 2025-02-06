T = int(input())
for tc in range(T):
    '''
    .	평지(전차가 들어갈 수 있다.)
    *	벽돌로 만들어진 벽
    #	강철로 만들어진 벽
    -	물(전차는 들어갈 수 없다.)
    ^	위쪽을 바라보는 전차(아래는 평지이다.)
    v	아래쪽을 바라보는 전차(아래는 평지이다.)
    <	왼쪽을 바라보는 전차(아래는 평지이다.)
    >	오른쪽을 바라보는 전차(아래는 평지이다.)

    북0 동1 남2 서3
    '''

    n, m = map(int, input().split())
    r, c, d = -1, -1, -1
    grid = [list(input()) for i in range(n)]
    for i in range(n):  # 초기 위치와 방향 확인
        for j in range(m):
            if (grid[i][j] == "^"):
                r, c, d = i, j, 0
            elif (grid[i][j] == ">"):
                r, c, d = i, j, 1
            elif (grid[i][j] == "v"):
                r, c, d = i, j, 2
            elif (grid[i][j] == "<"):
                r, c, d = i, j, 3
    # print(grid)
    grid[r][c] = "."
    int(input())
    order_list = list(input())
    row = [-1, 0, 1, 0]
    col = [0, 1, 0, -1]


    def isrange(nr, nc):
        if (0 <= nr < n and 0 <= nc < m and grid[nr][nc] == "."):
            return True
        else:
            return False


    def isOut(bomb_r, bomb_c):
        if (not (0 <= bomb_r < n and 0 <= bomb_c < m)):
            return True


    def game(order):
        global r, c, d
        if (order == "U"):
            nd = 0
            nr = r + row[nd]
            nc = c + col[nd]
            if (isrange(nr, nc)):
                r = nr
                c = nc
            d = nd
        elif (order == "D"):
            nd = 2
            nr = r + row[nd]
            nc = c + col[nd]
            if (isrange(nr, nc)):
                r = nr
                c = nc
            d = nd
        elif (order == "L"):
            nd = 3
            nr = r + row[nd]
            nc = c + col[nd]
            if (isrange(nr, nc)):
                r = nr
                c = nc
            d = nd
        elif (order == "R"):
            nd = 1
            nr = r + row[nd]
            nc = c + col[nd]
            if (isrange(nr, nc)):
                r = nr
                c = nc
            d = nd
        elif (order == "S"):
            # 지금 방향은 d 위치는 r,c
            br = r
            bc = c

            while True:
                bomb_r = br + row[d]
                bomb_c = bc + col[d]
                if (isOut(bomb_r, bomb_c)):  # 폭탄이 넘어갔어요
                    break

                if (grid[bomb_r][bomb_c] == "*"):  # 벽이 부서졌어요
                    grid[bomb_r][bomb_c] = "."
                    break
                elif (grid[bomb_r][bomb_c] == "#"):  # 강철에 부딛혀서 아무일도 없어요
                    break
                br = bomb_r
                bc = bomb_c


    for order in order_list:
        game(order)

    if (d == 0):
        grid[r][c] = "^"
    elif (d == 1):
        grid[r][c] = ">"
    elif (d == 2):
        grid[r][c] = "v"
    elif (d == 3):
        grid[r][c] = "<"

    print(f"#{tc + 1}",end = " ")
    for i in range(n):
        for j in range(m):
            print(grid[i][j],end="")
        print()
