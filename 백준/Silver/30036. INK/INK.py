'''
고민되는 것 잉크 색을 결정해주는 리스트가 부족하진 않은가??

'''

I, N, orderNum = map(int, input().split()) # 컬러리스트, 맵 크기, 주문횟수
color_list = list(input())
grid = [list(input()) for i in range(N)]
order_list = list(input())
r, c, d = -1,-1,-1


def first():
    global r,c
    for i in range(N):
        for j in range(N):
            if (grid[i][j] == "@"):
                r, c = i, j
                grid[i][j] = "."
                break
first()

power = 0
row = [-1,1,0,0]
col = [0,0,-1,1]
color_idx = 0


def shot(r, c, power, color):
    for i in range(N):
        for j in range(N):
            if(abs(i-r)+abs(j-c)<=power and grid[i][j]!="."):
                grid[i][j]= color




for order in order_list:
    if(order in "UDLR"):
        d = "UDLR".index(order)
        nr = r+row[d]
        nc = c+col[d]
        if(not(0<=nr<N and 0<=nc<N)):
            continue
        if(grid[nr][nc]=="."):
            r = nr
            c = nc
    elif(order == "j"):
        power+=1
    elif(order =="J"):
        if(power<=0):
            color_idx = (color_idx+1)%I
            continue # 못쏘면 넘어가
        color = color_list[color_idx]

        shot(r,c,power,color)
        power = 0
        color_idx = (color_idx + 1) % I
grid[r][c]="@"

for _ in grid:
    print("".join(_))



