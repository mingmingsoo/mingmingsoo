'''
문제시작 14:57

백신은 하나만 놓을 수 있음
근데 번호가 랜덤이라
전 후 차이가 없을 수 있음

그럼 도대체 뭐가 NO냐?
전과 후에서 다른 군집인데 둘 다 값이 바뀌었으면 아님.
군집 모양이 다름


비포에프터 같으면 ok
다를거면 그 좌표들이 같아야함.
다른 비포의 딱 그 크기만큼만 같고 나머지 다르면 안댐.

'''
from collections import deque

n,m = map(int, input().split())
before = [list(map(int, input().split())) for i in range(n)]
after = [list(map(int, input().split())) for i in range(n)]

ans = "YES"

row = [-1,1,0,0]
col = [0,0,1,-1]


def same(before, after):
    for i in range(n):
        for j in range(m):
            if(before[i][j]!=after[i][j]):
                return False
    return True


if(same(before, after)):
    print(ans)
    exit()


def bfs(i, j,bef,aft):
    q = deque([(i,j)])
    visited = [[False]*m for i in range(n)]
    visited[i][j] = True
    while q:
        r,c = q.pop()
        before[r][c] = aft
        for k in range(4):
            nr = r+row[k]
            nc = c+col[k]
            if(not(0<=nr<n and 0<=nc<m)):
                continue
            if(not visited[nr][nc] and before[nr][nc] == bef):
                q.append((nr,nc))
                visited[nr][nc] = True
                before[nr][nc] = aft



def valid():
    global ans
    for i in range(n):
        for j in range(m):
            if(before[i][j] != after[i][j]):
                bfs(i,j,before[i][j],after[i][j])
                if(not same(before,after)):
                    ans = "NO"
                    return

valid()
print(ans)