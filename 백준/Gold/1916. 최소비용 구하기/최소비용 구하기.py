'''
A->B의 최소 비용
단방향
'''
import heapq

V = int(input())
E = int(input())

adj = [[] for i in range(V+1)]
for i in range(E):
    s,e,cost = map(int,input().split())
    adj[s].append((cost,e))

s,e = map(int, input().split())
d = [100_000*100_000+1]*(V+1)
d[s] = 0

def dijk(start, end):
    q = []
    heapq.heappush(q, (0,start))
    while q:
        cost, cur = heapq.heappop(q)
        if(cost>d[cur]):
            continue

        for next_cost, next in adj[cur]:
            if(d[next] > next_cost+cost):
                d[next] = next_cost+cost
                heapq.heappush(q, (next_cost+cost, next))
dijk(s,e)
print(d[e])
