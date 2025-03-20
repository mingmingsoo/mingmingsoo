'''
답이 0 일 수 있는지 실험
'''
n, limit = map(int, input().split())
grid = [[0] * n for i in range(n)]
ans = 0
tmp = list(map(int, input().split()))
for j in range(n):
    grid[n - 1][j] = tmp[j]


maxi, mini = 0, int(1e9)
for i in range(n):
    for j in range(n):
        if grid[i][j] > 0:
            maxi = max(maxi, grid[i][j])
            mini = min(mini, grid[i][j])


while True:
    mini = min(grid[n - 1])

    # 1. 가장 작은 값을 가지는 애들한테 +1 씩 해주기
    for j in range(n):
        if grid[n - 1][j] == mini:
            grid[n - 1][j] += 1

    # 2. 1차 어항 쌓기
    # 1. 일단 한개
    grid[n - 1][0], grid[n - 2][1] = grid[n - 2][1], grid[n - 1][0]



    def rotation():
        sn, sm = len(small_grid), len(small_grid[0])
        # 회전...
        small = [[0] * sn for i in range(sm)]

        for i in range(sm):
            for j in range(sn):
                small[i][j] = small_grid[sn - j - 1][i]

        return small


    cnt = 0

    while True:
        cnt += 1
        sr, sc, er, ec = n, n, -1, -1
        for i in range(n - 1):
            for j in range(n):
                if grid[i][j] != 0:
                    sr = min(sr, i)
                    sc = min(sc, j)
                    er = max(er, i)
                    ec = max(ec, j)


        small_grid = [[0] * (ec - sc + 1) for i in range(er - sr + 2)]
        for i in range(sr, er + 2):
            for j in range(sc, ec + 1):
                small_grid[i - sr][j - sc] = grid[i][j]
        small_ro = rotation()


        if len(small_ro[0]) + ec >= n:
            break
        for i in range(sr, er + 2):
            for j in range(sc, ec + 1):
                grid[i][j] = 0

        start_jdx = ec + 1
        for i in range(n - len(small_ro) - 1, n - 1):
            for j in range(start_jdx, start_jdx + len(small_ro[0])):
                grid[i][j] = small_ro[i - n + len(small_ro) + 1][j - start_jdx]


    # 3. 물고기 수 조절하기
    row = [-1, 1, 0, 0]
    col = [0, 0, 1, -1]
    plus_grid = [[0] * n for i in range(n)]
    for i in range(n):
        for j in range(n):
            if grid[i][j] > 0:
                for k in range(4):
                    nr = i + row[k]
                    nc = j + col[k]
                    if 0 <= nr < n and 0 <= nc < n and grid[nr][nc] > 0 and grid[i][j] > grid[nr][nc]:
                        minus = grid[i][j] - grid[nr][nc]
                        diff = minus // 5
                        if diff > 0:
                            plus_grid[i][j] -= diff
                            plus_grid[nr][nc] += diff

    for i in range(n):
        for j in range(n):
            grid[i][j] += plus_grid[i][j]

    # 4. 다시 펼치기

    new_grid = [0] * n
    jdx = 0
    for j in range(n):
        for i in range(n - 1, -1, -1):
            if grid[i][j] != 0:
                new_grid[jdx] = grid[i][j]
                jdx += 1


    sero_grid = [[0] * (n // 2) for i in range(2)]
    for j in range(n // 2):
        sero_grid[1][j] = new_grid[j + n // 2]
        sero_grid[0][j] = new_grid[n // 2 - j - 1]

    half = [[0] * (n // 4) for i in range(2)]
    for i in range(2):
        for j in range(n // 4):
            half[i][j] = sero_grid[i][j]

    half_copy = [_[:] for _ in half]
    namuji_half = [[0] * (n // 4) for i in range(2)]

    for i in range(2):
        for j in range(n // 4):
            namuji_half[i][j] = sero_grid[i][j + n // 4]

    for i in range(2):
        for j in range(n // 4):
            half[i][j] = half_copy[2 - i - 1][n // 4 - j - 1]

    sero_ro = half + namuji_half


    plus_grid = [[0] * len(sero_ro[0]) for i in range(len(sero_ro))]

    for i in range(len(sero_ro)):
        for j in range(len(sero_ro[0])):
            if sero_ro[i][j] > 0:
                for k in range(4):
                    nr = i + row[k]
                    nc = j + col[k]
                    if 0 <= nr < len(sero_ro) and 0 <= nc < len(sero_ro[0]) and sero_ro[nr][nc] > 0 and sero_ro[i][j] > \
                            sero_ro[nr][nc]:
                        minus = sero_ro[i][j] - sero_ro[nr][nc]
                        diff = minus // 5
                        if diff > 0:
                            plus_grid[i][j] -= diff
                            plus_grid[nr][nc] += diff

    for i in range(len(sero_ro)):
        for j in range(len(sero_ro[0])):
            sero_ro[i][j] += plus_grid[i][j]


    grid = [[0] * n for i in range(n)]
    jdx = 0
    for j in range(len(sero_ro[0])):
        for i in range(len(sero_ro) - 1, -1, -1):
            grid[n - 1][jdx] = sero_ro[i][j]
            jdx += 1


    maxi, mini = 0, int(1e9)
    for i in range(n):
        for j in range(n):
            if grid[i][j] > 0:
                maxi = max(maxi, grid[i][j])
                mini = min(mini, grid[i][j])

    ans += 1
    if maxi - mini <= limit:
        break
print(ans)
