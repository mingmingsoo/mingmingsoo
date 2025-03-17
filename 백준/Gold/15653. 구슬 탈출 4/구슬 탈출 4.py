from collections import deque

n, m = map(int, input().split())
grid = [list(input()) for i in range(n)]

ar = ac = br = bc = er = ec = -1
for i in range(n):
    for j in range(m):
        if grid[i][j] == "R":
            ar, ac = i, j
            grid[i][j] = "."
        elif grid[i][j] == "B":
            br, bc = i, j
            grid[i][j] = "."
        elif grid[i][j] == "O":
            er, ec = i, j
            grid[i][j] = "."
visited = set()
visited.add((ar, ac, br, bc))
ans = -1
q = deque([(ar, ac, br, bc, 0)])
row = [-1, 1, 0, 0]
col = [0, 0, 1, -1]
while q:
    oar, oac, obr, obc, time = q.popleft()
    if obr == er and obc == ec:
        continue
    if oar == er and oac == ec:
        ans = time
        break
    for k in range(4):
        ar, ac, br, bc = oar, oac, obr, obc
        while True:
            nar = ar + row[k]
            nac = ac + col[k]
            if not (0 <= nar < n and 0 <= nac < m) or grid[nar][nac] == "#":
                break
            ar = nar
            ac = nac
            if ar == er and ac == ec:
                break
        while True:
            nbr = br + row[k]
            nbc = bc + col[k]
            if not (0 <= nbr < n and 0 <= nbc < m) or grid[nbr][nbc] == "#":
                break
            br = nbr
            bc = nbc
            if br == er and bc == ec:
                break
        if (ar, ac) == (oar, oac) and (br, bc) == (obr, obc):
            continue
        if (ar, ac) == (br, bc) and (ar, ac) != (er, ec):
            # 북남동서
            if k == 0:
                if obr < oar: ar+=1
                else: br+=1
            elif k == 1:
                if obr > oar: ar-=1
                else: br-=1
            elif k == 2:
                if obc > oac: ac-=1
                else: bc-=1
            elif k == 3:
                if obc < oac: ac+=1
                else: bc+=1
        if (ar, ac, br, bc) not in visited:
            visited.add((ar, ac, br, bc))
            q.append((ar, ac, br, bc, time + 1))
print(ans
      )