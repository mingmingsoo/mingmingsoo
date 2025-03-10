'''
시간초과 났던 이유 분석
new_grid가 필요없음 어차피 1인애들은 번식이 안됨., remomve에서 pop으로 수정
sort 빼버리고 insert 0하기

1.sort가 없든
2. die_idx를 기록하든 둘중에 하는 해야 시간초과 안난다


## 실험
죽은애들을 pop 하지말고 슬라이싱 해보기
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
                if die_idx != -1:
                    for w in range(die_idx, len(grid[i][j])):
                        nutrition[i][j] += grid[i][j][w] // 2
                    # 죽은애들 제외
                    grid[i][j] = grid[i][j][:die_idx]

    for i in range(n):
        for j in range(n):
            nutrition[i][j] += fill[i][j]
            if grid[i][j]:
                for tree in grid[i][j]:
                    if tree % 5 == 0:
                        for k in range(8):
                            nr = i + row[k]
                            nc = j + col[k]
                            if not (0 <= nr < n and 0 <= nc < n):
                                continue
                            grid[nr][nc].append(1)

ans = 0
for i in range(n):
    for j in range(n):
        if grid[i][j]:
            ans += len(grid[i][j])
print(ans)
