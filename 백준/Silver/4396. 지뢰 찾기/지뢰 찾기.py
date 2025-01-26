n = int(input())

bombGrid = [list(input()) for i in range(n)]

currGrid = [list(input()) for i in range(n)]

# print(bombGrid)
# print(currGrid)

row = [-1,1,0,0,1,1,-1,-1]
col = [0,0,1,-1,1,-1,1,-1]
ansGrid= [["."]* n for i in range(n)]
for i in range(n):
    for j in range(n):
        if(currGrid[i][j]=='x'):
            sum = 0
            for k in range(8):
                ni = i+row[k]
                nj = j+col[k]
                if(0<=ni<n and 0<=nj<n and bombGrid[ni][nj] == '*'):
                    sum+=1
            # print(sum)
            ansGrid[i][j] = sum
isbomb = False
for i in range(n):
    for j in range(n):
        if(bombGrid[i][j]=="*" and currGrid[i][j]=="x"):
            isbomb = True
            break
    if(isbomb):
        break
if(isbomb):
    for i in range(n):
        for j in range(n):
            if(bombGrid[i][j]=="*"):
                ansGrid[i][j] ="*"
for i in range(n):
    for j in range(n):
        print(ansGrid[i][j], end="")
    print()