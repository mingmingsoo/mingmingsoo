# visited 4차원
# 청소,방향,위치

n,m = map(int, input().split())
r,c,d = map(int, input().split())
grid1 = [list(map(int, input())) for i in range(n)]
grid2 = [list(map(int, input())) for i in range(n)]

visited = [[[[False]*(n*m+1) for _ in range(4)] for _ in range(m)] for  _ in range(n)]
clean = [[False] * m for i in range(n)]
row = [-1,0,1,0]
col = [0,1,0,-1]
empty = 0
time = 0
eat = 0
while True:
    # print(r,c,d,eat)
    if not clean[r][c]:
        eat+=1
        empty = 0
        clean[r][c] = True
        d = (d+grid1[r][c])%4
        nr = r+row[d]
        nc = c+col[d]
        if not(0<=nr<n and 0<=nc<m):
            time +=1
            break
        r = nr
        c = nc
    else:
        if not visited[r][c][d][eat]:
            empty +=1
            visited[r][c][d][eat] = True
            d = (d + grid2[r][c]) % 4
            nr = r + row[d]
            nc = c + col[d]
            if not (0 <= nr < n and 0 <= nc < m):
                time+=1
                break
            r = nr
            c = nc
        else:
            break
    time +=1
print(time-empty)