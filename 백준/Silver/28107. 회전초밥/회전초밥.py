import heapq
import sys
input = sys.stdin.readline
N, M = map(int, input().split())
size = 200_001
# size = 10
chobab = {}
for i in range(size):
    chobab[i]  = []
for i in range(N):
    tmp = list(map(int,input().split()))
    for j in range(1,len(tmp)):
        heapq.heappush(chobab[tmp[j]], i)

order= list(map(int, input().split()))
count = [0]*N
for num in order:
    if(chobab[num]):
        count[heapq.heappop(chobab[num])]+=1
print(*count)