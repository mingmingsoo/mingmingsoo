'''
깨ㅏ아아ㅏ 내가 좋아하는 유형의 문제엥에에~~~
20:14~20:35
구상
    에어컨에서 시작해서 방향대로 쭉쭉쭉 True 처리
    visited가 필요하겠네요.
'''


n, m = map(int,input().split())
grid = [list(map(int, input().split())) for i in range(n)]

air = [[0]*m for i in range(n)]

air_list = []
air_q = []

for i in range(n):
    for j in range(m):
        if grid[i][j]==9:
            for k in range(4):
                air_q.append((i, j, k))

row = [-1,0,1,0]
col = [0,1,0,-1]


while air_q:
    r,c,k = air_q.pop(0)
    air[r][c] = 1

    nr = r+row[k]
    nc = c+col[k]

    # 탈출조건
    if not(0<=nr<n and 0<=nc<m):
        continue

    if grid[nr][nc]==1:
        air[nr][nc] = 1
        if k ==1 or k == 3:
            continue
    elif grid[nr][nc]==2:
        air[nr][nc] =1
        if k ==0 or k == 2:
            continue
    elif grid[nr][nc]==3:
        if k==0:
            k =1
        elif k ==1:
            k = 0
        elif k ==2:
            k =3
        elif k ==3:
            k = 2
    elif grid[nr][nc]==4:
        if k==0:
            k =3
        elif k ==1:
            k = 2
        elif k ==2:
            k =1
        elif k ==3:
            k = 0
    elif grid[nr][nc]==9:
        continue
    air_q.append((nr,nc,k))

ans = 0
for _ in air:
    ans += _.count(1)
print(ans)