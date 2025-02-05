'''
하나의 정점으로 여러 노드 방문하고 싶음
최댓값을 가지는 정점들을 오름차순으로 출력

단방향

A가 B를 신뢰하면 B를 해킹시 A도 해킹 가능

넣어본 엣지케이스
5 4
3 1
3 2
3 4
3 5

1 2 4 5 출력
'''
import sys
from collections import deque

V, E = map(int, input().split())
adj = [[] for i in range(V+1)]
for i in range(E):
    end, start = map(int, input().split())
    adj[start].append(end)

# print(adj)

def bfs(start):
    ele = 1
    q = deque([start])
    while q:
        cur= q.popleft()
        for node in adj[cur]:
            if(not visited[node]):
                visited[node] = True
                q.append((node))
                ele+=1
    return ele
ans = [0]*(V+1)
for v in range(1, V+1):
    visited = [False] * (V + 1)
    visited[v] = True
    ans[v] = bfs(v)
# print(ans)

maxNum = max(ans)
for i in range(1,V+1):
    if(maxNum == ans[i]):
        print(i,end= " ")
