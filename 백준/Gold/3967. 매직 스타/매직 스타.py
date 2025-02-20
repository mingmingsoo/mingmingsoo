'''
백트래킹 하겠습니다.
사전순이니까 .. 그냥 포문 돌면서
발견하면 바로 탈출하면 될듯?
'''
N = 5
M = 9
grid = [list(input()) for i in range(N)]
arr = [True]*13
sel = []
n = 0
# 숫자로 변환

for i in range(N):
    for j in range(M):
        if "A"<=grid[i][j]<="Z":
            grid[i][j] = ord(grid[i][j])-64
            arr[grid[i][j]] = False
        if(grid[i][j]=="x"):
            sel.append((i,j))
            n +=1


arr2 = []
for i in range(1,13):
    if arr[i]:
        arr2.append(i)

find = False
visited = [False]*n
sel_num = [0]*n

def isok(grid):
    if(grid[0][4]+grid[1][3]+grid[2][2]+grid[3][1]!=26):
        return False
    if(grid[3][1]+grid[3][3]+grid[3][5]+grid[3][7]!=26):
        return False
    if(grid[0][4]+grid[1][5]+grid[2][6]+grid[3][7]!=26):
        return False
    if(grid[1][1]+grid[2][2]+grid[3][3]+grid[4][4]!=26):
        return False
    if(grid[1][7]+grid[2][6]+grid[3][5]+grid[4][4]!=26):
        return False
    if(grid[1][1]+grid[1][3]+grid[1][5]+grid[1][7]!=26):
        return False
    return True

def btk(idx):
    global find
    if find:
        return
    if(idx == n):
        # print(sel_num)
        for i in range(n):
            r,c = sel[i]
            num = sel_num[i]
            grid[r][c] = num
        if(isok(grid)):
            find = True
            for i in range(N):
                for j in range(M):
                    if type(grid[i][j]) == int:
                        print(chr(grid[i][j] + 64), end="")
                    else:
                        print(grid[i][j], end="")
                print()
        return

    for i in range(n):
        if not visited[i]:
            visited[i]= True
            sel_num[idx] = arr2[i]
            btk(idx+1)
            visited[i]= False



btk(0)

