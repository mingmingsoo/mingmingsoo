
n = int(input())
size = 101
grid = [[0] * size for i in range(size)]  # 답을 구하기 위해 만든 grid
row = [1, 0, -1, 0]
col = [0, -1, 0, 1]
for dragon in range(n):
    r, c, first_d, gener = map(int, input().split())
    # 방향 배열 만들기
    dirs = [first_d]
    for g in range(gener):
        for i in range(len(dirs) - 1, -1, -1):
            dirs.append((dirs[i] + 1) % 4) # 끝점을 기준으로 반시계회전한 방향을 넣어준다.
    lotation = [(r,c)]
    for d in dirs:
        nr, nc = lotation[-1] # 끝점을 기준으로 연산해서 넣어준다!
        lotation.append((nr+row[d], nc+col[d]))
    for r, c in lotation:
        grid[r][c] = 1
ans = 0
for i in range(size-1):
    for j in range(size-1):
        if grid[i][j] == 1 and grid[i][j+1] == 1 and grid[i+1][j] == 1 and grid[i+1][j+1] == 1:
            ans +=1
print(ans)