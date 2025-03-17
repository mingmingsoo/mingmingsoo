'''
문제설명
    해커는 딱 1개만 지웠다.
    답은 딱 1가지다.
    M에서 시작해서 가다가 쭉쭉 가다가
    .을 만나면 경우수를 계산
    그다음에 dfs 연결 확인해줘야함

'''
n, m = map(int, input().split())
grid = [list(input()) for i in range(n)]
sr = sc = er = ec = -1
for i in range(n):
    for j in range(m):
        if grid[i][j] == "M":
            sr, sc = i, j
        elif grid[i][j] == "Z":
            er, ec = i, j
row = [-1, 0, 1, 0]
col = [0, 1, 0, -1]
target_r, target_c, target_shape = -1, -1, ""

dirs = {"|": (0, 2), "-": (1, 3), "+": (0, 1, 2, 3), "1": (1, 2), "2": (0, 1),
        "3": (0, 3), "4": (2, 3)}
dirs_reverse = {(0, 2): "|", (1, 3): "-", (0, 1, 2, 3): "+", (1, 2): "1", (0, 1): "2",
                (0, 3): "3", (2, 3): "4"}


def find_target():
    global target_r, target_c, target_shape
    for i in range(n):
        for j in range(m):
            if grid[i][j] != ".":
                if grid[i][j] == "M" or grid[i][j] == "Z":
                    continue
                for k in dirs[grid[i][j]]:
                    nr = i + row[k]
                    nc = j + col[k]
                    if grid[nr][nc] == ".":
                        target_r, target_c = nr, nc
                        shape = []
                        if 0 <= target_r + row[0] < n:
                            if grid[target_r + row[0]][target_c] in ("|", "+", "1", "4"):
                                shape.append(0)
                        if 0 <= target_r + row[2] < n:
                            if grid[target_r + row[2]][target_c] in ("|", "+", "2", "3"):
                                shape.append(2)

                        if 0 <= target_c + col[3] < m:
                            if grid[target_r][target_c + col[3]] in ("-", "+", "1", "2"):
                                shape.append(3)

                        if 0 <= target_c + col[1] < m:
                            if grid[target_r][target_c + col[1]] in ("-", "+", "3", "4"):
                                shape.append(1)
                        shape.sort()
                        target_shape = dirs_reverse[tuple(shape)]
                        return


find_target()
print(target_r + 1, target_c + 1, target_shape)
