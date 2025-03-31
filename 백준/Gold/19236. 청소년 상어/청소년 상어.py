'''
코드 리팩토링 함수화
'''


def myprint():
    print("----------------------")
    for i in range(n):
        for j in range(n):
            if grid[i][j]:
                print(grid[i][j][0], end="")
                print("↑↖←↙↓↘→↗"[grid[i][j][1]], end=" ")
            else:
                print("XX", end=" ")
        print()


def move(r, c):  # 물고기 이동
    for num in range(1, 17):
        find = False
        for i in range(n):
            for j in range(n):
                if grid[i][j] and grid[i][j][0] == num:
                    find = True
                    ofr, ofc, ofd = i, j, grid[i][j][1]  # 원래 위치
                    fr, fc, fd = ofr, ofc, ofd  # 바뀔 위치
                    move = False
                    for k in range(8):
                        nfr, nfc = fr + row[fd], fc + col[fd]
                        if not (0 <= nfr < n and 0 <= nfc < n) or (nfr, nfc) == (r, c):
                            fd = (fd + 1) % 8  # 못가는 곳이면 방향 전환
                        else:  # 갈 수 있는 곳이면 위치 갱신
                            fr = nfr
                            fc = nfc
                            move = True
                            break
                    if move:  # 갈 수 있으면 간다.
                        if grid[fr][fc]:  # 물고기 있는 곳이면 swap
                            grid[ofr][ofc] = (grid[fr][fc][0], grid[fr][fc][1])  # 원래 위치에 가는 곳의 물고기를..
                            grid[fr][fc] = (num, fd)  # 갈 곳에 가는 애를..
                        else:  # 빈 공간으로 가면 나만 넣어줌
                            grid[fr][fc] = (num, fd)
                            grid[ofr][ofc] = ()
                    break  # 다음 번호 물고기 탐색을 위해 break 1
            if find:  # 다음 번호 물고기 탐색을 위해 break 2
                break


def shark_navi(r, c, d):  # 어디 갈 수 있는지 넣어줄 거임
    shark_go = []
    ori_r, ori_c = r, c
    while True:
        nr = ori_r + row[d]
        nc = ori_c + col[d]
        if 0 <= nr < n and 0 <= nc < n and grid[nr][nc]:  # 물고기 있는 곳 넣어줌
            shark_go.append((nr, nc, grid[nr][nc][0], grid[nr][nc][1]))
            ori_r = nr
            ori_c = nc
        elif 0 <= nr < n and 0 <= nc < n and not grid[nr][nc]:  # 물고기 없는 곳이면 일단 위치만 갱신
            ori_r = nr
            ori_c = nc
        else:  # 범위 벗어났으면 while 탈출
            break
    return shark_go


def btk(r, c, d, eat):
    global ans, grid
    move(r, c)
    grid_origin = [_[:] for _ in grid]  # btk 원상복귀를 위해 카피본 만들기

    shark_go = shark_navi(r, c, d)
    if not shark_go:  # 상어 집가.
        ans = max(ans, eat)
        return
    else:  # 상어 물고기 먹어
        for nr, nc, num, nd in shark_go:
            grid[nr][nc] = ()
            btk(nr, nc, nd, eat + num)
            grid = [_[:] for _ in grid_origin]  # 원상복구


n = 4
grid = [[[0] for _ in range(4)] for _ in range(4)]

for i in range(4):
    tmp = list(map(int, input().split()))
    for j in range(0, 8, 2):
        grid[i][j // 2] = (tmp[j], tmp[j + 1] - 1)  # 상어 번호, 방향

sr, sc, sd, eat = 0, 0, grid[0][0][1], grid[0][0][0]  # 상어 초기 위치, 방향, 처음 먹은 양
grid[0][0] = ()  # 처음 물고기 먹혔다.
row = [-1, -1, 0, 1, 1, 1, 0, -1]
col = [0, -1, -1, -1, 0, 1, 1, 1]
ans = 0
btk(sr, sc, sd, eat)
print(ans)
