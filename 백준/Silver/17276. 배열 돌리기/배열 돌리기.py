T = int(input())
for tc in range(T):
    n, degree = map(int, input().split())
    grid = [list(map(int, input().split())) for i in range(n)]

    '''        - 일 경우
    45 1        7
    90 2        6
    135 3       5
    180 4       4
    225 5       3
    270 6       2
    315 7       1
    360 0       0
    '''
    degree = degree // 45


    def rotation45():
        rotatedGrid = [[0] * n for i in range(n)]
        for i in range(n):
            for j in range(n):
                rotatedGrid[i][j] = grid[i][j]
        # 세로
        for i in range(n):
            if (i == j == n // 2):
                continue
            rotatedGrid[i][n // 2] = grid[i][i]
        # 가로
        for j in range(n):
            if (i == j == n // 2):
                continue
            rotatedGrid[n // 2][j] = grid[n - j - 1][j]
        # 왼쪽 대각선
        for i in range(n):
            if (i == j == n // 2):
                continue
            rotatedGrid[i][i] = grid[n // 2][i]
        # 오른쪽 대각선
        for i in range(n):
            if (i == j == n // 2):
                continue
            rotatedGrid[i][n - i - 1] = grid[i][n // 2]
        for i in range(n):
            for j in range(n):
                grid[i][j] = rotatedGrid[i][j]


    if (degree == 1 or degree == -7):
        rotation45()
    elif (degree == 2 or degree == -6):
        rotation45()
        rotation45()
    elif (degree == 3 or degree == -5):
        rotation45()
        rotation45()
        rotation45()
    elif (degree == 4 or degree == -4):
        rotation45()
        rotation45()
        rotation45()
        rotation45()
    elif (degree == 5 or degree == -3):
        rotation45()
        rotation45()
        rotation45()
        rotation45()
        rotation45()
    elif (degree == 6 or degree == -2):
        rotation45()
        rotation45()
        rotation45()
        rotation45()
        rotation45()
        rotation45()
    elif (degree == 7 or degree == -1):
        rotation45()
        rotation45()
        rotation45()
        rotation45()
        rotation45()
        rotation45()
        rotation45()

    for row in grid:
        print(*row)
