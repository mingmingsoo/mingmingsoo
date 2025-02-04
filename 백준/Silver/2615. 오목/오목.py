'''
주의할 점
오목은 우 하 만 간다.

1과 2가 있으면 검사. 근데 검사하는 방향 반대편에 같은 색 있으면 안된다.
5개 있으면 ㅇㅋ 근데 6개 있으면 안된다.

'''
size = 19

grid = [list(map(int, input().split())) for i in range(19)]

endR, endC, endColor = -1,-1, -1

row = [1,0,1,-1]
col = [0,1,1,1]

def omok(r, c, color):

    isOmok = False

    for k in range(4):
        isO = True
        br = r-row[k] # 반대편 오목이
        bc = c-col[k] # 같은 색깔이면 넘어가
        if(0<=br<size and 0<=bc<size and grid[br][bc]==color):
            continue
        for l in range(1,5):
            nr = r+row[k]*l
            nc = c+col[k]*l
            if(not(0<=nr<size and 0<=nc<size and grid[nr][nc]==color)):
                isO = False
                break
        if(not isO):
            continue
        if(r+row[k]*5 <0 or r+row[k]*5>=size or c+col[k]*5<0 or c+col[k]*5>=size or
        grid[r+row[k]*5][c+col[k]*5]!=color):
            isOmok = True

    return isOmok


def isGame():
    global endR, endC, endColor
    for i in range(size):
        for j in range(size):
            if (grid[i][j] != 0):
                if( omok(i, j, grid[i][j])):
                    endR = i + 1
                    endC = j + 1
                    endColor = grid[i][j]
                    return
isGame()

if(endR == -1):
    print(0)
else:
    print(endColor)
    print(endR, endC)
