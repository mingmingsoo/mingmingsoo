'''
# 두번째 풀이
    함수화

문제설명
    cctv와 맵 정보가 있을 때
    사각지대를 최소로해라.
입력
    사무실 크기 N, M
    맵 정보
구상
    cctv 정보를 1차원 배열에 담고
    cctv 넘버에 따라서 완탐을 해줘야함.
    cctv 정보를 담고 idx로 넘겨서 최대 갯수 기록
'''


def right(r, c, visited):
    ele_sm = 0
    for j in range(c + 1, m):
        if grid[r][j] == 0 and not visited[r][j]:
            ele_sm += 1
            visited[r][j] = True
        if grid[r][j] == 6:
            break
    return ele_sm


def left(r, c, visited):
    ele_sm = 0
    for j in range(c - 1, -1, -1):
        if grid[r][j] == 0 and not visited[r][j]:
            ele_sm += 1
            visited[r][j] = True
        if grid[r][j] == 6:
            break
    return ele_sm


def up(r, c, visited):
    ele_sm = 0
    for i in range(r - 1, -1, -1):
        if grid[i][c] == 0 and not visited[i][c]:
            ele_sm += 1
            visited[i][c] = True
        if grid[i][c] == 6:
            break
    return ele_sm


def down(r, c, visited):
    ele_sm = 0
    for i in range(r + 1, n):
        if grid[i][c] == 0 and not visited[i][c]:
            ele_sm += 1
            visited[i][c] = True
        if grid[i][c] == 6:
            break
    return ele_sm


def btk(idx, sm):
    global ans, visited
    if idx == len(cctv_list):
        ans = max(ans, sm)
        return

    r, c, num = cctv_list[idx]
    origin_visited = [_[:] for _ in visited]
    if num == 1:
        # 우
        btk(idx + 1, sm + right(r, c, visited))
        visited = [_[:] for _ in origin_visited]

        # 왼
        btk(idx + 1, sm + left(r, c, visited))
        visited = [_[:] for _ in origin_visited]

        # 위쪽을 볼 때
        btk(idx + 1, sm + up(r, c, visited))
        visited = [_[:] for _ in origin_visited]

        # 밑쪽을 볼 때
        btk(idx + 1, sm + down(r, c, visited))

    elif num == 2:
        # 좌우를 볼 때
        btk(idx + 1, sm + right(r, c, visited) + left(r, c, visited))
        visited = [_[:] for _ in origin_visited]

        # 상하를 볼 때
        btk(idx + 1, sm + up(r, c, visited) + down(r, c, visited))

    elif num == 3:
        # 북동
        btk(idx + 1, sm + up(r, c, visited) + right(r, c, visited))
        visited = [_[:] for _ in origin_visited]

        # 동남
        btk(idx + 1, sm + down(r, c, visited) + right(r, c, visited))
        visited = [_[:] for _ in origin_visited]

        # 남서
        btk(idx + 1, sm + down(r, c, visited) + left(r, c, visited))
        visited = [_[:] for _ in origin_visited]

        # 서북
        btk(idx + 1, sm + up(r, c, visited) + left(r, c, visited))
        
    elif num == 4:
        # 위에 못볼때
        btk(idx + 1, sm + down(r, c, visited) + right(r, c, visited) + left(r, c, visited))
        visited = [_[:] for _ in origin_visited]

        # 아래 못볼때
        btk(idx + 1, sm + up(r, c, visited) + right(r, c, visited) + left(r, c, visited))
        visited = [_[:] for _ in origin_visited]

        # 왼쪽 못볼떄
        btk(idx + 1, sm + up(r, c, visited) + right(r, c, visited) + down(r, c, visited))
        visited = [_[:] for _ in origin_visited]

        # 오른쪽 못볼때
        btk(idx + 1, sm + up(r, c, visited) + left(r, c, visited) + down(r, c, visited))
        
    elif num == 5:
        btk(idx + 1, sm + up(r, c, visited) + left(r, c, visited) + down(r, c, visited) + right(r, c, visited))

n, m = map(int, input().split())
grid = [list(map(int, input().split())) for i in range(n)]

# 관리할 cctv list
cctv_list = []
for i in range(n):
    for j in range(m):
        if 0 < grid[i][j] <= 5:
            cctv_list.append((i, j, grid[i][j]))

# 내가 볼 수 있는 최대 구역 갯수
total_able_view = 0
for i in range(n):
    for j in range(m):
        if grid[i][j] == 0:
            total_able_view += 1

visited = [[False] * m for i in range(n)]
ans = 0
btk(0, 0)  # cctv idx와 볼 수 있는 갯수
print(total_able_view - ans)  # 최대 - 내가 최대한 많이 본 방 갯수
