r, c, n = map(int, input().split())
grid = [list(input()) for _ in range(r)]
fullgrid = [['O'] * c for _ in range(r)]  # 폭탄이 모두 깔린 상태

# 폭탄 폭발 후 상태 계산 함수
def get_bombgrid(grid):
    bombgrid = [['O'] * c for _ in range(r)]
    directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]  # 상하좌우

    for i in range(r):
        for j in range(c):
            if grid[i][j] == 'O':  # 폭탄이 있으면
                bombgrid[i][j] = '.'
                for di, dj in directions:
                    ni, nj = i + di, j + dj
                    if 0 <= ni < r and 0 <= nj < c:  # 격자 범위 내에서 폭발
                        bombgrid[ni][nj] = '.'
    return bombgrid

# n에 따른 결과 출력
if n == 1:
    # 초기 상태 출력
    for row in grid:
        print("".join(row))
elif n % 2 == 0:
    # 폭탄이 모두 깔린 상태 출력
    for row in fullgrid:
        print("".join(row))
else:
    # n이 3 이상일 때 (n % 4 == 3 또는 n % 4 == 1)
    first_bomb = get_bombgrid(grid)  # 첫 폭발 후 상태
    second_bomb = get_bombgrid(first_bomb)  # 두 번째 폭발 후 상태

    if n % 4 == 3:
        for row in first_bomb:
            print("".join(row))
    elif n % 4 == 1:
        for row in second_bomb:
            print("".join(row))