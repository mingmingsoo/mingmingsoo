# 최적화 -> 사실 벽만나면 게임 끝임

T = int(input())
for tc in range(T):
    from collections import defaultdict

    n = int(input())
    grid = [[5] * (n + 2)] + [[5] + list(map(int, input().split())) + [5] for i in range(n)] + [[5] * (n + 2)]
    n += 2
    ans = 0

    white_holl = defaultdict(list)
    for i in range(1, n - 1):
        for j in range(1, n - 1):
            if 6 <= grid[i][j] <= 10:
                white_holl[grid[i][j]].append((i, j))

    row = [-1, 0, 1, 0]
    col = [0, 1, 0, -1]


    def game(sr, sc, d):
        global ans
        r, c = sr, sc
        score = 0

        while True:

            nr = r + row[d]
            nc = c + col[d]

            # 종료 조건
            if grid[nr][nc] == -1 or (nr, nc) == (sr, sc):
                return score

            if d == 0:
                if grid[nr][nc] == 1 or grid[nr][nc] == 4 or grid[nr][nc] == 5:
                    return score * 2 + 1
                elif grid[nr][nc] == 2:
                    d = 1
                    score += 1
                elif grid[nr][nc] == 3:
                    d = 3
                    score += 1
            elif d == 1:
                if grid[nr][nc] == 1 or grid[nr][nc] == 2 or grid[nr][nc] == 5:
                    return score * 2 + 1
                elif grid[nr][nc] == 3:
                    d = 2
                    score += 1
                elif grid[nr][nc] == 4:
                    d = 0
                    score += 1
            elif d == 2:
                if grid[nr][nc] == 2 or grid[nr][nc] == 3 or grid[nr][nc] == 5:
                    return score * 2 + 1
                elif grid[nr][nc] == 1:
                    d = 1
                    score += 1
                elif grid[nr][nc] == 4:
                    d = 3
                    score += 1
            elif d == 3:
                if grid[nr][nc] == 3 or grid[nr][nc] == 4 or grid[nr][nc] == 5:
                    return score * 2 + 1
                elif grid[nr][nc] == 1:
                    d = 0
                    score += 1
                elif grid[nr][nc] == 2:
                    d = 2
                    score += 1

            # 웜홀
            if 6 <= grid[nr][nc] <= 10:
                (wsr, wsc), (wer, wec) = white_holl[grid[nr][nc]]
                if (nr, nc) == (wsr, wsc):
                    nr, nc = wer, wec
                else:
                    nr, nc = wsr, wsc
            r = nr
            c = nc
        return 0


    for i in range(1, n - 1):
        for j in range(1, n - 1):
            if not grid[i][j]:
                for k in range(4):
                    ans = max(game(i, j, k), ans)
    print(f"#{tc + 1} {ans}")
