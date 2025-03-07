'''
문제설명
    여러개의 섬들 중
    딱 두개만 연결하면 되는데
    그 거리가 최소가 되게끔 해라...
구상
    일단 섬에 넘버링 한다.
    넘버링 하고나서는
    뭘 연결할지는 조합으로 2개 뽑아야할듯
    최단거리는 어떻게 구할까요
    모든 점을 q에 넣고
    퍼져나가다가 다음 섬 만나면 끝

필요한 메서드
bfs() # 넘버링 필요
bfs() # 거리 계산.
'''
from collections import deque
import sys
input = sys.stdin.readline

n = int(input())
grid = [list(map(int, input().split())) for i in range(n)]
visited = [[0] * n for i in range(n)]
num = 1
row = [-1, 1, 0, 0]
col = [0, 0, 1, -1]


def bfs(r, c, num):
    q = deque([(r, c)])
    while q:
        r, c = q.popleft()
        for k in range(4):
            nr = r + row[k]
            nc = c + col[k]
            if not (0 <= nr < n and 0 <= nc < n) or grid[nr][nc] != 1:
                continue
            if visited[nr][nc] == 0:
                visited[nr][nc] = num
                q.append((nr, nc))

for i in range(n):
    for j in range(n):
        if grid[i][j] == 1 and visited[i][j] == 0:
            visited[i][j] = num
            bfs(i, j, num)
            num += 1

ans = 100 * 100 + 1


def bfs2(numbering):
    v = [[False]* n for i in range(n)]
    q = deque()
    for i in range(n):
        for j in range(n):
            if visited[i][j] == numbering:
                v[i][j]= True
                q.append((i, j,0))

    while q:
        r, c,dist = q.popleft()
        # print("-------------")
        # for _ in d:
        #     print(_)
        for k in range(4):
            nr = r + row[k]
            nc = c + col[k]
            if not (0 <= nr < n and 0 <= nc < n) or v[nr][nc]:
                continue
            if grid[nr][nc] == 0:
                q.append((nr, nc,dist+1))
                v[nr][nc] = True
            elif visited[nr][nc] != numbering:
                return dist

for i in range(1, num):
    ans = min(ans, bfs2(i))

print(ans)
