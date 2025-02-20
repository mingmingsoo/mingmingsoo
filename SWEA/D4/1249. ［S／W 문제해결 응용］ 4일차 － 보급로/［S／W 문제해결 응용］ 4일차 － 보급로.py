T = int(input())
for tc in range(T):
    '''
    다익스트라
    '''
    import heapq

    n = int(input())

    grid = [list(map(int, input())) for i in range(n)]

    ans = 0
    d = [[100001] * n for i in range(n)]


    def dijk():
        row = [-1, 1, 0, 0]
        col = [0, 0, 1, -1]
        q = [(grid[0][0], 0, 0)]
        d[0][0] = grid[0][0]

        while q:
            cost, r, c = heapq.heappop(q)

            if (cost > d[r][c]):
                continue

            for k in range(4):
                nr = r + row[k]
                nc = c + col[k]
                if not (0 <= nr < n and 0 <= nc < n):
                    continue
                next_cost = grid[nr][nc]

                if (d[nr][nc] > cost + next_cost):
                    d[nr][nc] = cost + next_cost
                    heapq.heappush(q, (d[nr][nc], nr, nc))


    dijk()
    print(f"#{tc+1} {d[n - 1][n - 1]}")
