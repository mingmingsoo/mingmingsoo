'''
문제 설명
    사다리 정보가 주어졌을 때
    모든 i 번이 i번으로 갈 수 있게끔
    최소한의 가로 사다리를 놓아라
입력
    세로선 갯수(열), 가로선 갯수, 가로선을 놓을 수 있는 H(행)
    기존에 있는 가로선
        a, b(a와 a+1 세로선에 가로 b번째 위치)
구상
    연결된 가로가 있으면 무조건 가고
    없으면 세로로감
    btk 인데 행 위치를 담아줘야 두개 이상 연결이 안된다
'''
m, origin_num, n = map(int, input().split())
m = m * 2 - 1
n = n + 1
grid = [[0] * (m) for i in range(n)]
# 짝수번째가 세로 사다리, 홀수번째가 가로 사다리가 될 것임
for j in range(m):
    for i in range(n):
        if j % 2 == 0:
            grid[i][j] = 1
# for _ in grid:
#     print(_)

for i in range(origin_num):
    a, b = map(lambda x: int(x) - 1, input().split())  # a가 행
    grid[a][2 * b + 1] = 1
# for _ in grid:
#     print(_)

# 0인 곳은 모두 후보가 될 수 있음.
arr = []
for i in range(n - 1):
    for j in range(m):
        if grid[i][j] == 0:
            arr.append((i, j))
row = [0, 0]
col = [-1, 1]


def isOk():
    # print("-------------------")
    # for _ in grid:
    #     print(_)
    for j in range(0, m, 2):
        r, c = 0, j
        while True:
            # 만약 다 내려왔는데 c가 j 가 아니면 return False
            if r == n:
                if c != j:
                    return False
                else:
                    break
            # 오른쪽이나 왼쪽에 사다리 있으면 무조건 간다.
            nr = r + row[0]
            nc = c + col[0]
            nr2 = r + row[1]
            nc2 = c + col[1]
            if 0 <= nc < m and grid[nr][nc] == 1:
                while 0 <= nc < m and grid[nr][nc] == 1:
                    r = nr
                    c = nc
                    nr = r + row[0]
                    nc = c + col[0]
                r += 1
            elif 0 <= nc2 < m and grid[nr2][nc2] == 1:
                while 0 <= nc2 < m and grid[nr2][nc2] == 1:
                    r = nr2
                    c = nc2
                    nr2 = r + row[1]
                    nc2 = c + col[1]
                r += 1
            else:
                # 아니면 내려만 간다.
                r += 1
    # for _ in grid:
    #     print(_)
    return True


def combi(sidx, idx, M):
    global find
    if find:
        return
    if sidx == M:
        # 검증
        for i in range(M - 1):
            r, c = sel[i]
            nr, nc = sel[i + 1]
            if r == nr and nc - c == 2:
                return
        for r, c in sel:
            grid[r][c] = 1
        if isOk():
            find = True
            return
        for r, c in sel:  # 원상복구
            grid[r][c] = 0

        return
    if idx == len(arr):
        return

    sel[sidx] = arr[idx]
    combi(sidx + 1, idx + 1, M)
    combi(sidx, idx + 1, M)


ans = -1
find = False
for i in range(0, 4):
    sel = [0] * i  # 후보 선택
    combi(0, 0, i)
    if find:
        ans = i
        break
print(ans)
