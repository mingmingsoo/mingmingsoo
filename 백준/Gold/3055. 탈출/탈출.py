'''
포인트는
물확장 다음에 비버가 움직이는 것임

water visited를 쓰면 안되는 이유
: 물의 움직임이 더이상 없을 때,
고슴도치는 이동할 수 있는데 water_visited를 쓰면 그게 불가능해짐.
그래서 물의 움직임이 없어서 bfs가 끝나면 고슴도치는 갈 수 있는데 함수가 종료되어서 칵투사가 나온다.

그래서 고슴도치가 갈 때가 없으면 bfs를 종료하게 해야함.

틀린이유: 물 움직임도 visited 써도 된다.
while water_q가 아니라 고슴큐다. 고슴도치가 이동할 수 있는가? 잖아
'''
from collections import deque

n, m = map(int, input().split())
grid = [list(input()) for i in range(n)]
er,ec = -1,-1
kosum_q = deque([])
kosum_visited = [[False] * m for i in range(n)]
water_q = deque([])

for i in range(n):
    for j in range(m):
        if(grid[i][j]=="S"):
            kosum_q.append((i,j,0))
            kosum_visited[i][j] = True
        elif(grid[i][j]=="*"):
            water_q.append((i,j))
        elif(grid[i][j]=="D"):
            er,ec = i,j
ans = "KAKTUS"

def bfs():
    global ans
    row = [-1,1,0,0]
    col = [0,0,1,-1]
    while kosum_q: # water_q가 아니라.. 고슴도치가 움직일 수 있는가?임
        wq_size = len(water_q)
        for s in range(wq_size):
            wr,wc = water_q.popleft()
            for k in range(4):
                wnr = wr+row[k]
                wnc = wc+col[k]
                if(not(0<=wnr<n and 0<=wnc<m)):
                    continue
                elif(grid[wnr][wnc]=="."):
                    water_q.append((wnr,wnc))
                    grid[wnr][wnc] = "*"
        # for _ in grid:
        #     print(*_)
        if kosum_q:
            kq_size = len(kosum_q)
            for s in range(kq_size):
                kr, kc, cnt = kosum_q.popleft()
                if(kr==er and kc == ec):
                    ans = cnt
                    return
                for k in range(4):
                    knr = kr + row[k]
                    knc = kc + col[k]
                    if (not (0 <= knr < n and 0 <= knc < m) or kosum_visited[knr][knc]):
                        continue

                    # 소굴도 갈 수있음..
                    elif (grid[knr][knc] != "*" and grid[knr][knc] !="X"):
                        kosum_q.append((knr, knc,cnt+1))
                        kosum_visited[knr][knc] = True
                    # print(knr,knc)

bfs()
print(ans)


