'''
문제 설명
    뿌요는 떨어진다
    뿌요가 4개이상 상하좌우 연결되어있으면 뿌요가 없어진다 = 1연쇄
    뿌요가 소멸되면 위에 뿌요들이 떨어진다
    뿌요가 또 연쇄가 되면 또 터진다.
    4뿌요가 여러개 있으면 동시에 터진다.
입력
    12*6 배열
    . 빈공간 / 나머지는 색깔
출력
    연쇄 횟수
구상
    1.while True 안에서
    군집을 세고, 터트린다 -> 이때 갯수가 기록되어야함
    2. 터트린애들(visited = True)된 애들이 있으면 떨어뜨려준다
    3. 반복한다
    * while 탈출조건은 기록된 갯수가 없으면 탈출한다.
'''
from collections import deque

n,m = 12,6
grid = [list(input()) for i in range(n)]
ans = 0
row = [-1,1,0,0]
col = [0,0,1,-1]

def bfs(sr,sc,color):
    global pang
    visited[i][j] = True
    q = deque([(sr,sc)])
    pang_set = set()
    pang_set.add((sr,sc))
    ele_pang = 1
    while q:
        r,c = q.popleft()
        for k in range(4):
            nr = r+row[k]
            nc = c+col[k]
            if(not(0<=nr<n and 0<=nc<m)):
                continue
            if not visited[nr][nc] and grid[nr][nc] == color:
                ele_pang+=1
                visited[nr][nc] = True
                q.append((nr,nc))
                pang_set.add((nr,nc))
    if(ele_pang>=4):
        pang+=1
        for r,c in pang_set:
            grid[r][c] = "."


def isDown():
    # pang_grid 가 . 인 애들을 내려줘야함...
    for j in range(m):
        while True:
            down = 0
            for i in range(n-1,0,-1):
                if(grid[i][j]=="." and grid[i-1][j]!="."):
                    down +=1
                    grid[i][j], grid[i-1][j] = grid[i-1][j],grid[i][j]
            if(down==0):
                break


while True:
    visited = [[False]*m for i in range(n)]
    pang = 0
    for i in range(n):
        for j in range(m):
            if not visited[i][j] and grid[i][j]!=".":
                bfs(i,j,grid[i][j])
    if(pang==0): # 연속된 팡이 없으면 종료
        break
    ans +=1
    isDown()
    # print("-----------")
    # for _ in grid:
    #     print(_)
print(ans)
