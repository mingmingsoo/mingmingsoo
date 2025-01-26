n = int(input())
whatRC = int(input())

r= -1
c = -1


row= [-1,0,1,0]
col = [0,1,0,-1]
grid = [[0]*n for i in range(n)]

d = 0

curR = n//2
curC = n//2
num = 1
grid[curR][curC] = num
if (num == whatRC):
    r = curR + 1
    c = curC + 1

while(num<n*n):
    num += 1
    if(grid[curR+row[d]][curC+col[d]]==0):
        curR += row[d]
        curC += col[d]
        grid[curR][curC] = num
        d = (d + 1) % 4
    else:
        d = (d - 1 + 4) % 4
        curR += row[d]
        curC += col[d]
        grid[curR][curC] = num
        d = (d + 1) % 4

    if(num == whatRC):
        r = curR+1
        c = curC+1

for i in range(n):
    for j in range(n):
        print(grid[i][j], end = " ")
    print()
print(r,c)



