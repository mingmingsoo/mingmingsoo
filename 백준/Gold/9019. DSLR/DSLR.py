from collections import deque
size = 10000

def bfs(start, end):
    q = deque([(start,[])])
    visited = [0]*size
    visited[start] = 0

    while q:
        now, order = q.popleft()
        now = now%10000
        if(now == end):
            return order

        stringNow = str(f"{now:04}")
        LNow = int(stringNow[1:]+stringNow[0])
        RNow =int(stringNow[-1]+stringNow[0:len(stringNow)-1])

        if(visited[(2*now)%10000]==0):
            q.append(((2*now)%10000,order+ ["D"]))
            visited[(2*now)%10000]=1
        if(visited[(now-1)%10000]==0):
            q.append(((now-1)%10000,order+["S"]))
            visited[(now-1)%10000] = 1
        if(LNow<10000 and visited[LNow]==0):
            q.append(((LNow,order +["L"])))
            visited[LNow] = 1
        if(RNow<10000 and visited[RNow]==0):
            q.append(((RNow,order+ ["R"])))
            visited[RNow] = 1

T = int(input())
for tc in range(T):
    start, end = map(int, input().split())
    ans = bfs(start ,end)
    for s in ans:
        print(s, end = "")
    print()

