import heapq
import sys
input = sys.stdin.readline
n = int(input())
q = []
for i in range(n):
   num = int(input())
   if num != 0:
       heapq.heappush(q,num)
   else:
       if not q:
           print(0)
       else:
           print(heapq.heappop(q))
