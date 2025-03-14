'''
문제설명
    1. 검은색(-1)/ 무지개(0)/ 일반 블록(1~M)이 있다
    2. 블록 그룹이 존재한다
        - 일반블록이 적어도 1개이고 그 일반블록은 색이 모두 같아야함
        - 검은블록 포함하면 안됨
        - 무지개는 얼마나 들어있든 상관 없음
        - 블록은 2보다 크거나 같음
        - 이 블록의 기준블록은 일반블록 중에서 행과 열이 가장 작은 것임
    3. 오토 플레이 기능
        크기가 가장 큰 블록을 찾음
        1.여러개면 무지개 블록의 수가 가장 많은 것
        2.그게 같다면 기준 블록의 행이 가장 큰것, 열이 가장 큰 것
        3. 블록 제거
    4. 중력/반시계/중력이 작용하는데
        -1 인 검은색 블록을 제외 한 모든 불록이 떨어진다.
구상
    - 오토플레이는 블록 그룹이 존재하는 동안 계속 반복됨;;
    1. 일단 가장 큰 블록 그룹을 찾아야한다.
        max_block = [], maxi = 0 로 두고
        각 점마다 bfs 돌려서 클 때마다 두개를 갱신해줘야함
        왜냐하면 0인애들은 일반블록이 모두 포함시킬 수 있기 때문 -> 이ㅓㄱ떄문이다
        그러므로 visited는 일반블록에만 처리한다.
    2. 떨구는게 중요한데......
        떨어지는 애들을 F로 표시하자
        아래에서부터 내가 F이고 위의 숫자가 -1이 아닌 숫자면 떨어뜨려준다.
입력
    변 크기 N, 색상 갯수 M
출력
'''
from collections import deque

score = 0
n, m = map(int, input().split())
grid = [list(map(int, input().split())) for i in range(n)]
row = [-1, 1, 0, 0]
col = [0, 0, 1, -1]


def bfs(r, c, num):
    # ele_maxi, ele_r, ele_c 이거 리턴해줘야함
    # 총 블록 수, 기준점 r,c
    # 2. 블록 그룹이 존재한다
    #     - 일반블록이 적어도 1개이고 그 일반블록은 색이 모두 같아야함 -> 이건 그런애들만 담을거라 ㄱㅊ
    #     - 검은블록 포함하면 안됨
    #     - 무지개는 얼마나 들어있든 상관 없음
    #     - 블록은 2보다 크거나 같음 ㅇㅋㅇㅋ
    #     - 이 블록의 기준블록은 일반블록 중에서 행과 열이 가장 작은 것임 -> return 으로
    mujigae = 0
    q = deque([(r, c)])  # 0인애들 visited 처리 안한다.
    location = []
    center = []
    zero_visited = set()
    while q:
        r, c = q.popleft()
        if grid[r][c] == 0:
            mujigae += 1
        location.append((r, c))
        if grid[r][c] != 0:
            center.append((r, c))
        for k in range(4):
            nr = r + row[k]
            nc = c + col[k]
            if not (0 <= nr < n and 0 <= nc < n) or visited[nr][nc]:
                continue
            if grid[nr][nc] == num:
                visited[nr][nc] = True
                q.append((nr, nc))
            if grid[nr][nc] == 0 and (nr, nc) not in zero_visited:
                zero_visited.add((nr,nc))
                q.append((nr, nc))

    if len(location) <= 1:
        return -1, -1, -1, -1, []
    else:
        center.sort()
        center_r = center[0][0]
        center_c = center[0][1]
        return len(location), mujigae, center_r, center_c, location


def delete(location):
    for r, c in location:
        grid[r][c] = 9  # 9면 떨어진다.


def fall():
    for j in range(n):
        while True:
            down = 0
            for i in range(n - 1, 0, -1):
                if grid[i][j] == 9 and 0 <= grid[i - 1][j] < 6:
                    down += 1
                    grid[i][j], grid[i - 1][j] = grid[i - 1][j], grid[i][j]
            if down == 0:
                break


def myprint():
    for i in range(n):
        for j in range(n):
            if grid[i][j] == 9:
                print(" ", end=" ")
            elif grid[i][j] == -1:
                print("X", end=" ")
            else:
                print(grid[i][j], end=" ")
        print()


def rotation():
    copy_grid = [_[:] for _ in grid]
    for i in range(n):
        for j in range(n):
            grid[i][j] = copy_grid[j][n - i - 1]


while True:
    visited = [[False] * n for i in range(n)]
    max_block = []
    for i in range(n):
        for j in range(n):
            if 0 < grid[i][j] < 6 and not visited[i][j]:
                visited[i][j] = True
                # 무지개 즉 0블록의 갯수를 세줘야함.
                ele_maxi, ele_mujigae, ele_r, ele_c, ele_location = bfs(i, j, grid[i][j])
                if ele_maxi != -1:
                    max_block.append((ele_maxi, ele_mujigae, ele_r, ele_c, ele_location))
    if not max_block:  # 오토플레이 안되면 게임 끝
        break
    max_block.sort(reverse=True)
    maxi, muji, r, c, location = max_block[0]
    score += maxi * maxi

    # print("----삭제 후----")

    delete(location)  # 이때 F로 표시
    # myprint()
    # print("----1. 떨어진 후----")
    fall()  # 떨굼
    # myprint()
    # print("----2. 회전 후----")
    rotation()  # 회전
    # myprint()
    # print("----3. 떨군  후----")
    fall()  # 떨굼
    # myprint()
print(score)
