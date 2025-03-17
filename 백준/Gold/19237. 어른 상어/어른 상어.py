'''
문제가 잘 이해 안된다.ㅇ....
문제 설명
    1. 모든 상어가 자신의 위치에 자신의 냄새를 뿌린다.
    2. 상어가 이동한다-> 냄새는 k번 이동하면 사라짐
    3. 이동 방향
        1. 인접한 칸 중 아무냄새 없는 칸
        2. 그런 칸이 없으면 자신의 냄새가 있는 칸
        3. 우선순위는 상어마다 다르고 현재 상어가 보고있는 방향에 따라 다름
        ㅇㅋㅇㅋ....
구상
    1. 상어 이동
    2. 상어 냄새표시
    3. 상어 삭제
    냄새표시는 따로 해줘야할 것 같고(상어번호와 k)
2 2 1
0 1
2 0
1 2
4 4 3 1
2 3 1 4
4 1 2 3
3 4 2 1
4 3 1 2
2 4 3 1
2 1 3 4
3 4 1 2
'''
n, shark_num, limit = map(int, input().split())
grid = [list(map(int, input().split())) for i in range(n)]
shark_first_d_list = list(map(lambda x: int(x) - 1, input().split()))
shark_d = []
for i in range(shark_num):
    tmp = [list(map(lambda x: int(x) - 1, input().split())) for i in range(4)]
    shark_d.append(tmp)
shark_area = [[[] for i in range(n)] for i in range(n)]  # limit과 상어 번호를 남겨줌
ans = -1
row = [-1, 1, 0, 0]
col = [0, 0, -1, 1]


def shark_move():
    global grid
    new_grid = [[0] * n for i in range(n)]

    # 일단 영역표시
    for i in range(n):
        for j in range(n):
            if grid[i][j] > 0:
                shark_area[i][j] = [grid[i][j], limit]

    # 그 다음에 움직임
    for i in range(n):
        for j in range(n):
            if grid[i][j] > 0:
                # 빈 곳 중에 우선순위...
                shark_dir = shark_d[grid[i][j] - 1][shark_first_d_list[grid[i][j] - 1]]
                empty = False
                next_r, next_c, next_d = i, j, shark_first_d_list[grid[i][j] - 1]
                for k in shark_dir:
                    nr = i + row[k]
                    nc = j + col[k]
                    if not (0 <= nr < n and 0 <= nc < n):
                        continue
                    if not shark_area[nr][nc]:
                        empty = True
                        next_r, next_c, next_d = nr, nc, k
                        break
                if not empty:
                    # 내가 온 곳 중에서...
                    for k in shark_dir:
                        nr = i + row[k]
                        nc = j + col[k]
                        if not (0 <= nr < n and 0 <= nc < n):
                            continue
                        if shark_area[nr][nc] and shark_area[nr][nc][0] == grid[i][j]:
                            next_r, next_c, next_d = nr, nc, k
                            break
                if new_grid[next_r][next_c] == 0:
                    new_grid[next_r][next_c] = grid[i][j]
                else:
                    if grid[i][j] < new_grid[next_r][next_c]:
                        new_grid[next_r][next_c] = grid[i][j]
                shark_first_d_list[grid[i][j] - 1] = next_d
    # 이제.. 시간이 지난 애들 표시
    for i in range(n):
        for j in range(n):
            if shark_area[i][j]:
                shark_area[i][j][1] -= 1
                if shark_area[i][j][1] == 0:
                    shark_area[i][j] = []
    grid = new_grid


for t in range(1, 1001):
    # print(ans)
    shark_move()
    cnt = 0
    # print("-----------상어-------------")
    # for _ in grid:
    #     print(*_)
    # print("-----------냄새-------------")
    # for _ in shark_area:
    #     print(*_)
    for i in range(n):
        for j in range(n):
            if grid[i][j] > 0:
                cnt += 1
    if cnt == 1:
        ans = t
        break
print(ans)
