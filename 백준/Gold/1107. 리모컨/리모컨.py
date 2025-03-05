'''
문제설명
    0에서 -1 누르면 변화 없음
    채널은 무한대
    N으로 이동하기 위해 최소?
    bfs?
    어렵당
    숫자로도 이동이 가능함
    end에서 from 에 따라 연산.


재구상
    end에서
    0,+1,-1 그 숫자 조합이 되면
    해서 100이 되면 그만.

'''
import heapq
from collections import deque

possible = [True] * 10
end = int(input())
e = int(input())
if e > 0:
    tmp = list(map(int, input().split()))
    for i in range(e):
        possible[tmp[i]] = False
ans = abs(end - 100)


def bfs():
    global ans
    visited = [False] * (999_999 + 1)
    q = deque([(end, 0)])
    visited[end] = True
    while q:
        cur, cnt = q.popleft()
        num = str(cur)
        for ele in num:
            if not possible[int(ele)]:
                break
        else:
            ans = min(ans, cnt + len(str(num)))
            return
        if cur - 1 >= 0 and not visited[cur - 1]:
            q.append((cur - 1, cnt + 1))
            visited[cur - 1] = True
        if cur + 1 < 999_999 + 1 and not visited[cur + 1]:
            q.append((cur + 1, cnt + 1))
            visited[cur + 1] = True


bfs()
print(ans)
