import heapq
from collections import deque


class Node:
    def __init__(self, r, c, time, cur):
        self.r = r
        self.c = c
        self.time = time
        self.cur = cur

    def __lt__(self, other):
        return self.time > other.time


T = int(input())
for tc in range(T):
    '''
    우선순위큐 대신 리스트로 정렬해서 푼 풀이.
    시간복잡도 괜찮으면 이렇게 하는게 나을듯.

    '''

    size = 700
    n, m, k = map(int, input().split())
    grid = [[0] * size for i in range(size)]
    startX = size // 2 - n // 2
    startY = size // 2 - m // 2
    endX = size // 2 + n // 2
    endY = size // 2 + m // 2
    if (n % 2 == 1):
        endX += 1
    if (m % 2 == 1):
        endY += 1
    q = []
    for i in range(startX, endX):
        tmp = list(map(int, input().split()))
        for j in range(startY, endY):
            grid[i][j] = tmp[j - startY]
            if (grid[i][j] > 0):
                q.append(Node(i, j, grid[i][j], 0))
                # 위치, 생명력, 활성화되기전 시간, 활성화 되고나서 얼마나 지났는가
    row = [-1, 1, 0, 0]
    col = [0, 0, 1, -1]
    limit = 0
    dieQ = deque()
    while q:
        if (limit >= k):
            break
        qSize = len(q)  # 5.5 -1 되는거 확인
        q.sort()
        for s in range(qSize):
            node = q.pop(0)
            r = node.r
            c = node.c
            time = node.time
            curTime = node.cur
            if (curTime < time):
                q.append(Node(r, c, time, curTime + 1))
                # 시간이 안지났으면 대기
            elif (curTime == time):  # 시간이 지났으면 확장 가능
                dieQ.append(Node(r, c, time, 1))
                # 확장하는 로직
                for d in range(4):
                    nr = r + row[d]
                    nc = c + col[d]
                    if (nr < 0 or nr >= size or nc < 0 or nc >= size):
                        continue
                    elif (grid[nr][nc] == 0):
                        q.append(Node(nr, nc, time, 0))
                        grid[nr][nc] = time
                    elif (grid[nr][nc] == -1):
                        continue
        if dieQ:
            dieQsize = len(dieQ)
            for dieS in range(dieQsize):
                node2 = dieQ.popleft()
                r, c, time, cur = node2.r, node2.c, node2.time, node2.cur
                if (time == cur):
                    grid[r][c] = -1
                else:
                    dieQ.append(Node(r, c, time, cur + 1))

        limit += 1
        # print("----------")
        # for ele in grid:
        #     print(" ".join(f"{num:2}" for num in ele))
    sum = 0
    for i in range(size):
        for j in range(size):
            if (grid[i][j] > 0):
                sum += 1
    print(f"#{tc + 1} {sum}")
