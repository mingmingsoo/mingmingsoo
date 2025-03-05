'''
문제 설명
    처음 주사위 이동 방향 동쪽
    1. 이동방향으로 한 칸, 이동방향에 칸이 없다면 반대로 굴러감
    2. 주사위가 도착한 칸 점수 획득
    3. 이동방향은
        주사위 아랫면 A, 주사위 있는 칸 B
        A>B : 이동방향  시계 90도 회전
        A<B : 이동방향  반시계 90도 회전
        A=B : 이동방향 유지

    점수는 x,y 에 있는 칸 B
    x,y 에서 동서남북 이동할 수 있는 칸의 수 C를 구함
    B*C?
입력
    N,M,K
구상
    성실히 구현.
'''
from collections import deque

def bfs(r, c, num):
    global ele_score

    visited = [[False] * m for i in range(n)]
    q = deque([(r, c, num)])
    visited[r][c] = True

    while q:
        r, c, num = q.popleft()
        ele_score += num

        for k in range(4):
            nr = r + row[k]
            nc = c + col[k]
            if not (0 <= nr < n and 0 <= nc < m):
                continue
            if not visited[nr][nc] and grid[nr][nc] == num:
                visited[nr][nc] = True
                q.append((nr, nc, num))


n, m, move_num = map(int, input().split())
grid = [list(map(int, input().split())) for i in range(n)]

dice = [1, 6, 5, 2, 4, 3]  # 위 아래  앞 뒤 왼 오
row = [0, 1, 0, -1]  # 동 남 서 북
col = [1, 0, -1, 0]
r, c, d = 0, 0, 0  # 처음엔 동쪽
score = 0


for move in range(move_num):
    # 주사위가 이동 방향으로 한 칸 굴러간다.
    # 만약, 이동 방향에 칸이 없다면,
    # 이동 방향을 반대로 한 다음 한 칸 굴러간다.

    if not (0 <= r + row[d] < n and 0 <= c + col[d] < m):
        d = (d + 2) % 4
    r = r + row[d]
    c = c + col[d]

    if d == 0:  # 동
        dice = [dice[4]] + [dice[5]] + [dice[2]] + [dice[3]] + [dice[1]] + [dice[0]]
    elif d == 1:  # 남
        dice = [dice[3]] + [dice[2]] + [dice[0]] + [dice[1]] + [dice[4]] + [dice[5]]
    elif d == 2:  # 서
        dice = [dice[5]] + [dice[4]] + [dice[2]] + [dice[3]] + [dice[0]] + [dice[1]]
    elif d == 3:  # 북
        dice = [dice[2]] + [dice[3]] + [dice[1]] + [dice[0]] + [dice[4]] + [dice[5]]
    # 주사위가 도착한 칸 (x, y)에 대한 점수를 획득한다.
    ele_score = 0
    bfs(r, c, grid[r][c])
    # print(ele_score)
    score += ele_score

    # 주사위의 아랫면에 있는 정수 A와 주사위가 있는 칸 (x, y)에 있는 정수 B를 비교해 이동 방향을 결정한다.
    # A > B인 경우 이동 방향을 90도 시계 방향으로 회전시킨다.
    # A < B인 경우 이동 방향을 90도 반시계 방향으로 회전시킨다.
    # A = B인 경우 이동 방향에 변화는 없다.
    dice_bottom = dice[1]
    if dice_bottom > grid[r][c]:
        d = (d + 1) % 4
    elif dice_bottom < grid[r][c]:
        d = (d + 3) % 4
print(score)
# print(dice)