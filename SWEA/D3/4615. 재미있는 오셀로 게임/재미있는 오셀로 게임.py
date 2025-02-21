T = int(input())
for tc in range(T):
    '''
    08:55

    예상풀이시간:40분

    문제 설명
        뭐 ,, 돌을 놓을 수 있는 곳에만 위치를 준다고 하고
        그 상대편 돌들을 바꾸는 게 관건임
    입력
        1 흑
        2 백
    '''

    n, m = map(int, input().split())
    grid = [[0] * n for i in range(n)]

    grid[n // 2 - 1][n // 2 - 1] = 2
    grid[n // 2 - 1][n // 2] = 1
    grid[n // 2][n // 2 - 1] = 1
    grid[n // 2][n // 2] = 2

    row = [-1, 1, 0, 0, 1, 1, -1, -1]
    col = [0, 0, 1, -1, 1, -1, 1, -1]


    def change(r, c, color):
        sr, sc = r, c
        for k in range(8):
            r = sr
            c = sc
            mycolor = False
            change_color = set()
            while True:
                nr = r + row[k]
                nc = c + col[k]

                if not (0 <= nr < n and 0 <= nc < n):
                    break
                if grid[nr][nc] == color:
                    mycolor = True
                    break
                elif grid[nr][nc] != 0:
                    change_color.add((nr, nc))
                    r = nr
                    c = nc
                else:
                    break
            if mycolor:
                for i, j in change_color:
                    grid[i][j] = color


    for i in range(m):
        r, c, color = map(int, input().split())
        r -= 1
        c -= 1
        grid[r][c] = color
        change(r, c, color)

        # print("===================")
        # for _ in grid:
        #     print(*_)

    b = 0
    w = 0
    for i in range(n):
        for j in range(n):
            if grid[i][j] == 1:
                b += 1
            elif grid[i][j] == 2:
                w += 1

    print(f"#{tc+1} {b} {w}")