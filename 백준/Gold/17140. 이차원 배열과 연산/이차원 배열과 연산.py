'''
문제 설명
    갯수에 따른 연산.
    R연산: 행에 대해 정렬 단 행 갯수>=열갯수
    C연산: 열에 대해 정렬 단 열갯수>행갯수

    정렬 기준
    수의 등장 횟수가 커지는 순으로, 같다면 수가 커지는 순으로

    3 1 1 2
    2 1 3 1 1 2

    만약 가장 큰 행/열 을 기준으로 크기가 변함. 모자란 곳은 0을 넣어줌
    100을 넘어가면 100까지만.

출력
    A[r][c] 가 k가 되기위한 최소 시간.
    100초가 지나도 달성하지 못하면 -1 출력

구상
    성실히 구현
'''

er, ec, target = map(int, input().split())
er -= 1
ec -= 1
ans = -1
grid = [list(map(int, input().split())) for i in range(3)]
for time in range(101):

    # 4. 목표 달성 검사
    if 0 <= er < len(grid) and 0 <= ec < len(grid[0]) and grid[er][ec] == target:
        ans = time
        break

    # 1. 크기에 따른 R,C 연산
    r_size = len(grid)
    c_size = len(grid[0])
    if r_size >= c_size:
        new_grid = []
        max_row_size = 0
        for i in range(r_size):
            count = [0] * 101
            for j in range(c_size):
                if grid[i][j] == 0:
                    continue
                count[grid[i][j]] += 1
            sort_list = []
            for i in range(101):
                if count[i] > 0:
                    sort_list.append((i, count[i]))
            sort_list.sort(key=lambda x: (x[1], x[0]))
            tmp = []
            for count_num, idx in sort_list:
                tmp.append(count_num)
                tmp.append(idx)
            max_row_size = max(max_row_size, len(tmp))
            new_grid.append(tmp)
        # 2. 최대 크기 기준으로 맞춰주기
        for row in new_grid:
            if len(row) < max_row_size:
                row += [0] * (max_row_size - len(row))
    else:
        new_grid = []
        max_col_size = 0
        for j in range(c_size):
            count = [0] * 101
            for i in range(r_size):
                if grid[i][j] == 0:
                    continue
                count[grid[i][j]] += 1
            sort_list = []
            for i in range(101):
                if count[i] > 0:
                    sort_list.append((i, count[i]))
            sort_list.sort(key=lambda x: (x[1], x[0]))
            tmp = []
            for count_num, idx in sort_list:
                tmp.append(count_num)
                tmp.append(idx)
            max_col_size = max(max_col_size, len(tmp))
            new_grid.append(tmp)
        # 2. 최대 크기 기준으로 맞춰주기
        for row in new_grid:
            if len(row) < max_col_size:
                row += [0] * (max_col_size - len(row))
        # 전치.
        new_grid2 = [[0] * len(new_grid) for i in range(len(new_grid[0]))]
        for i in range(len(new_grid)):
            for j in range(len(new_grid[0])):
                new_grid2[j][i] = new_grid[i][j]
        new_grid = new_grid2

    # 3. 행 or 열 크기가 100 넘어가면 버려주기. # 해당만 하면 실행하게 바꾸기
    r_size = len(new_grid)
    c_size = len(new_grid[0])
    is_small_need = False
    if r_size > 100:
        is_small_need = True
        r_size = 100
    if c_size > 100:
        is_small_need = True
        c_size = 100

    if is_small_need:

        new_grid2 = [[0] * c_size for i in range(r_size)]
        for i in range(r_size):
            for j in range(c_size):
                new_grid2[i][j] = new_grid[i][j]
        grid = new_grid2
    else:
        grid = new_grid

print(ans)
