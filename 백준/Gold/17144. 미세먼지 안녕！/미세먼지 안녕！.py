'''
문제설명
    먼지는 상하좌우 인접하게 퍼져나간다
    주변은 먼지//5 가되고 중앙은 중앙 - 먼지//5 * 확산한 칸

    그리고 시계방향으로 돈다.

구상

필요한 메서드
    wind - 확산
        얼마나 더해지는지 필요한 2차원배열
        남은 값을 기록해주는 배열
    rotate_up - 위 회전
    rotate_down - 아래 회전
    count - 먼지 갯수
'''



def count_round(r, c): # 주변 몇개에 확산할 수 있는지 계산하는 함수
    ele_count = 0
    for k in range(4):
        nr = r + row[k]
        nc = c + col[k]

        if not (0 <= nr < n and 0 <= nc < m):
            continue
        if (nr, nc) == (up_r, 0) or (nr, nc) == (down_r, 0):
            continue
        ele_count += 1

    return ele_count


def plus(r, c, num): # 나의 주변에 위치한 애들한테 내 먼지들을 확산하는 함수
    global origin_grid, plus_grid
    for k in range(4):
        nr = r + row[k]
        nc = c + col[k]

        if not (0 <= nr < n and 0 <= nc < m):
            continue
        if (nr, nc) == (up_r, 0) or (nr, nc) == (down_r, 0):
            continue
        plus_grid[nr][nc] += num


def wind(): # 바람을 일으키는 전체 함수
    global origin_grid, plus_grid
    origin_grid = [[0] * m for i in range(n)] # 내가 얼만큼 남는지
    plus_grid = [[0] * m for i in range(n)] # 내가 얼마나 더해질건지

    for i in range(n):
        for j in range(m):
            if (i, j) == (up_r, 0) or (i, j) == (down_r, 0): # 에어컨이면 넘어가
                continue

            round = count_round(i, j)
            wind_num = grid[i][j] // 5
            origin_grid[i][j] = grid[i][j] - wind_num * round
            plus(i, j, wind_num)
    # for _ in origin_grid:
    #     print(_)


    # 원본배열에 반영
    for i in range(n):
        for j in range(m):
            if (i, j) == (up_r, 0) or (i, j) == (down_r, 0):
                continue
            grid[i][j] = origin_grid[i][j] + plus_grid[i][j] # 나 = 나에서 남아있는 먼지 + 확산받은 먼지


def rotate_up():
    # 위로 회전!
    # up_r을 기준으로 댕겨주기

    for i in range(up_r, 0, -1):
        grid[i][0] = grid[i - 1][0]
    for j in range(m - 1):
        grid[0][j] = grid[0][j + 1]
    for i in range(0, up_r):
        grid[i][m - 1] = grid[i + 1][m - 1]
    for j in range(m - 1, 0, -1):
        grid[up_r][j] = grid[up_r][j - 1]

    grid[up_r][0] = -1 # 공기청정기 복구!
    grid[up_r][1] = 0 # 청소!


def rotate_down():
    # 아래로 회전!
    # down_r을 기준으로 댕겨주기

    for i in range(down_r, n - 1):
        grid[i][0] = grid[i + 1][0]

    for j in range(m - 1):
        grid[n - 1][j] = grid[n - 1][j + 1]

    for i in range(n - 1, down_r, -1):
        grid[i][m - 1] = grid[i - 1][m - 1]

    for j in range(m - 1, 0, -1):
        grid[down_r][j] = grid[down_r][j - 1]

    grid[down_r][0] = -1 # 공기청정기 복구!
    grid[down_r][1] = 0 # 청소!

def count(): # t초 후에 총 몇개의 미세먼지가 있는지 세주는 함수
    sum = 0
    for i in range(n):
        for j in range(m):
            if (i, j) == (up_r, 0) or (i, j) == (down_r, 0):
                continue
            sum += grid[i][j]
    return sum


n, m, T = map(int, input().split())
grid = [list(map(int, input().split())) for i in range(n)]

# 공기청정기 위치를 찾음
up_r = n
down_r = -1

for i in range(n):
    if grid[i][0] == -1:
        up_r = min(up_r, i)
        down_r = max(down_r, i)

# print(up_r, down_r)
row = [-1, 1, 0, 0]
col = [0, 0, 1, -1]

for t in range(T):  # T 시간 만큼

    origin_grid = [[0] * m for i in range(n)]
    plus_grid = [[0] * m for i in range(n)]

    # 미세먼지 확장
    wind()

    # 위에 회전
    rotate_up()
    # 아래 회전
    rotate_down()


ans = count()
print(ans)
