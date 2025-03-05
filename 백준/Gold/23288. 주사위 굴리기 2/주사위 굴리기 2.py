'''

# 백준 23288 주사위 굴리기2 (코드트리 정육면체 한번 더 굴리기)
# 세반째 풀이
    그냥 int로 해보기
# 두번째 풀이
    배열 새로 생성하지말고 값바꿔치기.

'''
from collections import deque


def bfs(R, C, num):
    ele_score = 0
    visited = [[False] * m for i in range(n)]
    visited[R][C] = True
    q = deque([(R, C, num)])

    while q:
        R, C, num = q.popleft()
        ele_score += num

        for k in range(4):
            nr = R + row[k]
            nc = C + col[k]
            if not (0 <= nr < n and 0 <= nc < m):
                continue
            if not visited[nr][nc] and grid[nr][nc] == num:
                visited[nr][nc] = True
                q.append((nr, nc, num))
    return ele_score


n, m, move_num = map(int, input().split())
grid = [list(map(int, input().split())) for i in range(n)]

# 위 아래 앞 뒤 왼 오
u, d, f, b, l, r = 1, 6, 5, 2, 4, 3
row = [0, 1, 0, -1]  # 동 남 서 북
col = [1, 0, -1, 0]
R, C, dir = 0, 0, 0  # 처음엔 동쪽
score = 0

for move in range(move_num):

    # 주사위가 이동 방향으로 한 칸 굴러간다.
    # 만약, 이동 방향에 칸이 없다면,
    # 이동 방향을 반대로 한 다음 한 칸 굴러간다.

    if not (0 <= R + row[dir] < n and 0 <= C + col[dir] < m):  # 만약, 이동 방향에 칸이 없다면,
        dir = (dir + 2) % 4  # 이동 방향을 반대로 한 다음 한 칸 굴러간다.
    R = R + row[dir]
    C = C + col[dir]

    if dir == 0:  # 동
        u, d, f, b, l, r = l, r, f, b, d, u
    elif dir == 1:  # 남
        u, d, f, b, l, r = b, f, u, d, l, r
    elif dir == 2:  # 서
        u, d, f, b, l, r = r, l, f, b, u, d
    elif dir == 3:  # 북
        u, d, f, b, l, r = f, b, d, u, l, r

    score += bfs(R, C, grid[R][C])

    if d > grid[R][C]:
        dir = (dir + 1) % 4
    elif d < grid[R][C]:
        dir = (dir + 3) % 4

print(score)
