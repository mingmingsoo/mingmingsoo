# 풀이시간: 50분
# 제출횟수: 1회
# 시간: 176ms
# 메모리: 75,196kb

'''
빠른 탈출 조건은 없음 완탐해야함
벽 부수고 이동하기랑 다른 것.
벽부수기는 벽을 부셨냐 안부셨냐만 따지면 되는데 -> 부서지기만 하면 다른 곳에서도 갈 수 있음
이 문제는 어디서 왔냐에 따라서 높이가 달라지기 때문에 어디서 왔는지의 방문체크도 해야줘야함

'''
import copy
from collections import deque

T = int(input())


def bfs():

    while q:
        global ans
        r, c, cnt, bomb, hei, visited = q.popleft()  # 부모 높이를 담아줌
        visited2 = copy.deepcopy(visited)
        # print(visited,cnt,bomb)
        ans = max(ans, cnt)

        for k in range(4):
            nr = r + row[k]
            nc = c + col[k]
            if (not (0 <= nr < n and 0 <= nc < n)):
                continue
            # 0이면 안부시고 갔음
            # 부셨든 안부셨든 나보다 낮으면 가.
            if (r * n + c, nr * n + nc) not in visited2 and grid[nr][nc] < hei:
                visited2.add((r * n + c, nr * n + nc))
                visited2.add((nr * n + nc, r * n + c))
                q.append((nr, nc, cnt + 1, bomb, grid[nr][nc],visited2))

            elif (r * n + c, nr * n + nc) not in visited2 and grid[nr][nc] - kk < hei and bomb:
                visited2.add((r * n + c,nr * n + nc))
                visited2.add((nr * n + nc, r * n + c))
                q.append((nr, nc, cnt + 1, False, hei - 1,visited2))



for tc in range(T):

    n, kk = map(int, input().split())  # 맵 크기와 깎을 수 있는 높이
    grid = [[] for i in range(n)]
    maxH = -1
    for i in range(n):
        tmp = list(map(int, input().split()))
        for j in range(n):
            grid[i] = tmp
            if (grid[i][j] > maxH):
                maxH = grid[i][j]
    ans = 0
    row = [0, 1, -1, 0]
    col = [-1, 0, 0, 1]

    for i in range(n):
        for j in range(n):
            if (grid[i][j] == maxH):
                q = deque()  # q에 바로담기
                v = set()
                for k in range(4):
                    nr = i+row[k]
                    nc = j+col[k]
                    if(0<=nr<n and 0<=nc<n):
                        v.add((nr*n+nc,i*n+j))
                q.append((i, j, 1, True, maxH, v))
                bfs()
    # print(q)

    print(f"#{tc + 1} {ans}")
