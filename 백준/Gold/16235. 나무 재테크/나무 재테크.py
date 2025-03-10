'''
문제설명
    N*N 크기에 나무가 심어져 있음.
    처음엔 모두 양분 5
    M개의 나무
    1. 봄에는 나이만큼 양분 먹고 나이 증가.
        만약 한 칸에 나무가 여러개면
        어린 나무부터 양분을 먹는다.
        양분 못먹으면 양분이 죽는다
    2. 여름에는 봄에 죽은 나무가 양분으로 변함
        죽은 나무 나이 // 2가 양분에 추가

    3. 가을에는 나무 번식.
        나이가 5의 배수일때만 가능
        인접8칸에 나이가 1인애들 생김
    4. 겨울엔 양분 추가.
구상
    class로 나무 관리. 속성: 나이,살았는지 죽었는지.
    시뮬레이션임

10 10 1000
2 3 2 3 2 2 3 2 3 2
2 3 2 3 2 2 3 2 3 2
2 3 2 3 2 2 3 2 3 2
2 3 2 3 2 2 3 2 3 2
2 3 2 3 2 2 3 2 3 2
2 3 2 3 2 2 3 2 3 2
2 3 2 3 2 2 3 2 3 2
2 3 2 3 2 2 3 2 3 2
2 3 2 3 2 2 3 2 3 2
2 3 2 3 2 2 3 2 3 2
1 2 3
1 3 3
1 4 3
1 5 3
1 6 3
3 1 3
3 2 3
3 3 3
3 4 3
3 5 3

1 3 1
1
1 1 7
1 1 6
1 1 5

정답 1
'''

n, m, limit = map(int, input().split())
fill = [list(map(int, input().split())) for i in range(n)]
row = [0, 0, 1, -1, 1, 1, -1, -1]
col = [1, -1, 0, 0, 1, -1, 1, -1]
grid = [[[] for j in range(n)] for i in range(n)]
nutrition = [[5] * n for i in range(n)]
tmp = []
for mm in range(m):
    r, c, age = map(int, input().split())
    r -= 1
    c -= 1
    grid[r][c].append(age)  # 나이

for time in range(limit):
    #     1. 봄에는 나이만큼 양분 먹고 나이 증가.
    #         만약 한 칸에 나무가 여러개면
    #         어린 나무부터 양분을 먹는다.
    #         양분 못먹으면 양분이 죽는다

    # 나이순 정렬.
    for i in range(n):
        for j in range(n):
            if grid[i][j]:
                grid[i][j].sort()

    for i in range(n):
        for j in range(n):
            if grid[i][j]:
                die_idx = -1
                for w in range(len(grid[i][j])):
                    tree_age = grid[i][j][w]
                    if nutrition[i][j] >= tree_age:
                        nutrition[i][j] -= tree_age
                        grid[i][j][w] += 1
                    else:
                        die_idx = w
                        break
                # 죽은애들 없애주면서 양분 추가하기
                if die_idx != -1:
                    for w in range(len(grid[i][j]) - 1, die_idx - 1, -1):
                        nutrition[i][j] += grid[i][j][w] // 2
                        grid[i][j].pop(w)

    for i in range(n):
        for j in range(n):
            if grid[i][j]:
                for tree in grid[i][j]:
                    if tree % 5 == 0:
                        for k in range(8):
                            nr = i + row[k]
                            nc = j + col[k]
                            if not (0 <= nr < n and 0 <= nc < n):
                                continue
                            grid[nr][nc].append(1)

    #     4. 겨울엔 양분 추가.
    for i in range(n):
        for j in range(n):
            nutrition[i][j] += fill[i][j]

ans = 0
for i in range(n):
    for j in range(n):
        if grid[i][j]:
            ans += len(grid[i][j])
print(ans)
