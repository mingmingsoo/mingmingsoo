'''
문제설명
    왼오왼오 화살날린다.
    중력영향받는다.

구상
필요한 메서드
    ash
    fall : 클러스터가 전부 떨어져야함..
        1. 이 클러스터가 얼마나 떨어질 수 있는지 검사해야되고
            그게 0이 아니면
        2. 그 클러스터가 모두 -1이 되야함.
        3. 바닥에서부터 검사해서 바닥에 있는 클러스터는 검사안해도됨
        4. 그게 아닌 클러스터를 검사
        5. 일단 떨구고 불가능한지 검사 ㅋ

........
........
.....x..
...xxx..
...xx...
..x.xx..
..x...x.
.xxx..x.
5
6 6 4 3 1
'''
from collections import deque

n, m = map(int, input().split())
grid = [list(input()) for i in range(n)]
ash_num = int(input())
ash_list = list(map(lambda x: int(x) - 1, input().split()))
row = [-1, 1, 0, 0]
col = [0, 0, 1, -1]


def bfs(i, j):
    q = deque([(i, j)])
    while q:
        r, c = q.popleft()

        for k in range(4):
            nr = r + row[k]
            nc = c + col[k]

            if not (0 <= nr < n and 0 <= nc < m):
                continue
            if not visited[nr][nc] and grid[nr][nc] == "x":
                visited[nr][nc] = 1
                q.append((nr, nc))

def bfs2(i, j):
    q = deque([(i, j)])
    while q:
        r, c = q.popleft()
        cluster.add((r,c))
        for k in range(4):
            nr = r + row[k]
            nc = c + col[k]

            if not (0 <= nr < n and 0 <= nc < m):
                continue
            if not visited[nr][nc] and grid[nr][nc] == "x":
                visited[nr][nc] = num
                q.append((nr, nc))

def fall(cluster):
    l = 1
    while True:
        isfall = True
        for r, c in cluster:
            if r+l>=n:
                isfall = False
                break
            if grid[r+l][c] =="x" and visited[r+l][c] != num: # 내 클러스터가 아니면
                isfall = False
                break
        if not isfall:
            break
        # 이게 아니면 l 증가
        if isfall:
            l+=1

    # 이제 l만큼 떨구자  밑에서 부터 swap c 오름차순 r 내림차순
    cluster = list(cluster)
    cluster.sort(key=lambda x:x[1])
    cluster.sort(key=lambda x: x[0], reverse=True)

    for r, c in cluster: # 여기 수정하면됨
        grid[r][c],grid[r+l-1][c] = grid[r+l-1][c], grid[r][c]
        visited[r][c], visited[r + l - 1][c] = visited[r + l - 1][c], visited[r][c]

for i in range(ash_num):

    # 화살날림
    target = ash_list[i]
    if i % 2 == 0:  # 왼쪽부터
        for j in range(m):
            if grid[n-target-1][j] == "x":
                grid[n-target-1][j] = "."
                break
    else:
        for j in range(m - 1, -1, -1):
            if grid[n-target-1][j] == "x":
                grid[n-target-1][j] = "."
                break

    visited = [[0] * m for i in range(n)]
    # print("======화살후======")
    # for _ in grid:
    #     print(*_)

    # 바닥먼저 = 얘네는 안떨어진다.
    for j in range(m):
        if not visited[n-1][j]:
            visited[n-1][j] = 1
            bfs(n-1, j)

    # 떨어질 수 있는애들은 grid가 x이면서 not visited인 애들
    # 일단 클러스터를 찾고 떨어뜨려바야함.. 하 시간초과 안날랑가
    # for _ in visited:
    #     print(*_)
    num = 2
    for i in range(n-2,-1,-1):
        for j in range(m):
            if grid[i][j]=='x' and not visited[i][j]:
                cluster = set()
                # 열에서 가장 행이 큰애들의 기록을 해줘야함.
                visited[i][j]= num
                bfs2(i,j)
                fall(cluster)
                num +=1
    # print("=====떨군후=======")
    # for _ in grid:
    #     print(*_)

for _ in grid:
    print("".join(_))