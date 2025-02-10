from collections import deque

V,E,K,start = map(int,input().split())
adj = [[] for i in range(V+1)]

for i in range(E):
    s, e= map(int,input().split())
    adj[s].append(e)
size = float("inf")
dist = [size] *(V+1)
dist[start] = 0

def dijk(start):
    q = deque([start])
    while q:
        cur = q.popleft()
        for node in adj[cur]:
            if(dist[node]> dist[cur]+1):
                dist[node] = dist[cur]+1
                q.append(node)

dijk(start)
ans = []
for i in range(1,V+1):
    if(dist[i] == K):
        ans.append(i)
if(not ans):
    print(-1)
else:
    for x in ans:
        print(x)
