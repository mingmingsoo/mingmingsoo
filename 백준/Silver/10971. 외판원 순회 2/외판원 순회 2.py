'''
전자카트와 동일문제
이해하면서 코드짜기

시작점을 어디로 설정하든 돌아오게 하므로 모든 시작점을 고려하지 않아도 됨
모든 길은 총 N개의 depth임.
'''

n = int(input())
grid = [list(map(int,input().split())) for i in range(n)]

ans = 1000000*10+1
visited = [False]*n

def btk(idx,cur,sm):
    global ans
    if(sm>ans):
        return
    if(idx==n-1):
        # 01->12->23->...->50 이렇게 오게 되있음 그래서 grid[cur][0]을 더해준다
        if(grid[cur][0]==0):
            return
        ans = min(ans,sm+grid[cur][0])
        return
    for i in range(1,n):
        if not visited[i] and grid[cur][i]!=0:
            visited[i] = True
            btk(idx+1,i,sm+grid[cur][i])
            visited[i] = False

btk(0,0,0) # idx, cur, sm
print(ans)