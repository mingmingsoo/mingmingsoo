'''
문제 설명
    뱀은 사과를 먹으면 길어짐
    0,0 에서 1의 길이로 시작. 처음엔 오른쪽
    사과를 안먹으면 머리 +1 꼬리 -1
    사과를 먹으면 머리+1 꼬리-0
    벽 만나면 게임 끝.
    몇초만에 게임이 끝나는가?

입력
    맵 크기
    사과 갯수
    사과 위치
    방향 전환 횟수
    방향 전환 시기와 방향

구상
    굳이 2차원배열로하지않고
    뱀의 머리와 꼬리를 기록

잘못생각한 점
    뱀이 엄청나게 길어져서 그러면 각 뱀의 좌표에 방향이 다 다르다...
'''

n = int(input())
grid = [[0] * n for i in range(n)]
apple = int(input())
for i in range(apple):
    r, c = map(int, input().split())
    grid[r - 1][c - 1] = 2  # 사과
rotation_list = []
rotation_list.sort(reverse=True)
dnum = int(input())
for i in range(dnum):
    t, d = input().split()
    rotation_list.append((int(t), d))
# print(rotation_list)
row = [0, 1, 0, -1]
col = [1, 0, -1, 0]
time = 0
grid[0][0] = 1  # 뱀
dir_grid = [[-1] * n for i in range(n)]


def game():
    global time
    r, c, d = 0, 0, 0
    dir_grid[r][c] = 0
    tail_r, tail_c = 0, 0

    while True:
        time += 1
        nr = r + row[d]
        nc = c + col[d]
        dir_grid[r][c] = d
        if not (0 <= nr < n and 0 <= nc < n):
            return
        if grid[nr][nc] == 1:  # 내 몸이면
            return
        if grid[nr][nc] == 2:
            # 사과 있으면 먹는다.
            grid[nr][nc] = 1
            # print(tail_r, tail_c)
        else:
            # 사과 없으면
            # 땡겨야함
            # print(tail_r, tail_c)
            grid[nr][nc] = 1
            grid[tail_r][tail_c] = 0
            tail_d = dir_grid[tail_r][tail_c]
            tail_r = tail_r + row[tail_d]
            tail_c = tail_c + col[tail_d]

        r = nr
        c = nc

        for i in range(len(rotation_list)-1,-1,-1):
            t, direction = rotation_list[i][0], rotation_list[i][1]
            if t == time:
                rotation_list.pop(i)
                if direction == "L":
                    d = (d + 3) % 4
                else:
                    d = (d + 1) % 4
                    break

game()
print(time)
