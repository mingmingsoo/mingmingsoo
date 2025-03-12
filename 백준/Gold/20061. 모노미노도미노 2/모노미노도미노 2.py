'''
디버깅용 테케
5
3 2 0
3 1 0
3 2 0
3 1 0
2 2 0

5
2 0 0
2 0 1
2 0 0
2 0 1
3 0 1

5
3 0 0
3 0 1
3 0 2
3 0 0
3 0 3

5
2 0 0
2 1 0
2 2 0
2 0 0
2 3 0
'''

n = 10
grid = [[0] * n for i in range(n)]
block_num = int(input())
score = 0

def location(r, c, shape):
    if shape == 1:  # 하나짜리
        # 초록
        nr = n - 1  # 아무것도 없었으면 바닥에!
        for i in range(r, n):
            if grid[i][c] == 1:  # 있었으면 그 전에!
                nr = i - 1
                break
        grid[nr][c] = 1
        # 파랑
        nc = n - 1
        for j in range(c, n):
            if grid[r][j] == 1:
                nc = j - 1
                break
        grid[r][nc] = 1
    elif shape == 2:  # 가로 두개
        # 초록
        nr = n - 1
        for i in range(r, n):
            if grid[i][c] == 1 or grid[i][c + 1] == 1:
                nr = i - 1
                break
        grid[nr][c], grid[nr][c + 1] = 1, 1
        # 파랑
        nc = n - 1
        for j in range(c, n):
            if grid[r][j] == 1:
                nc = j - 1
                break
        grid[r][nc], grid[r][nc - 1] = 1, 1

    elif shape == 3:  # 세로 두개
        # 초록
        nr = n - 1
        for i in range(r, n):
            if grid[i][c] == 1:
                nr = i - 1
                break
        grid[nr][c], grid[nr - 1][c] = 1, 1
        # 파랑
        nc = n - 1
        for j in range(c, n):
            if grid[r][j] == 1 or grid[r + 1][j] == 1:
                nc = j - 1
                break
        grid[r][nc], grid[r + 1][nc] = 1, 1


def delete():
    global score, green_line, blue_line
    for i in range(n - 1, 5, -1):
        if grid[i][:4].count(1) == 4:
            green_line += 1
            grid[i][:4] = [0, 0, 0, 0]

    for j in range(n - 1, 5, -1):
        cnt = 0
        for i in range(4):
            if grid[i][j] == 1:
                cnt += 1
        if cnt == 4:
            blue_line += 1
            for i in range(4):
                grid[i][j] = 0

    score += green_line
    score += blue_line

def special():
    green_special = 0
    for i in (4, 5):
        if grid[i][:4].count(1) > 0:
            green_special += 1

    for i in range(n - 1, n - 1 - green_special, -1):
        for j in range(4):
            grid[i][j] = 0

    # 땡겨줌
    for w in range(green_special):
        for i in range(n - 1, 0, -1):
            for j in range(4):
                grid[i][j], grid[i - 1][j] = grid[i - 1][j], grid[i][j]  # 땡겨준다.

    blue_special = 0
    for j in (4, 5):
        for i in range(4):
            if grid[i][j] == 1:
                blue_special += 1
                break

    for j in range(n - 1, n - 1 - blue_special, -1):
        for i in range(4):
            grid[i][j] = 0

    for w in range(blue_special):
        for j in range(n - 1, 0, -1):
            for i in range(4):
                grid[i][j], grid[i][j - 1] = grid[i][j - 1], grid[i][j]  # 땡겨준다.


def fall():
    for w in range(green_line):
        for i in range(n - 1, 0, -1):
            if grid[i][:4] == [0, 0, 0, 0]:
                for j in range(4):
                    grid[i][j], grid[i - 1][j] = grid[i - 1][j], grid[i][j]
    for w in range(blue_line):
        for j in range(n - 1, 0, -1):
            cnt = 0
            for i in range(4):
                if grid[i][j] == 0:
                    cnt += 1
            if cnt == 4:
                for i in range(4):
                    grid[i][j], grid[i][j - 1] =  grid[i][j - 1], grid[i][j]


for block in range(block_num):
    green_line=0
    blue_line = 0
    shape, r, c = map(int, input().split())
    location(r, c, shape)
    delete()
    fall()
    special()

print(score)
total = 0
for i in range(n):
    for j in range(n):
        if grid[i][j] == 1:
            total += 1
print(total)
