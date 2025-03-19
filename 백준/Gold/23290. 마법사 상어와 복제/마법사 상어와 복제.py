'''
문제 설명
    1. 초기 물고기 상태 남겨놓음
    2. 물고기 이동
    3. 상어 이동
    4. 2턴 전 물고기 냄새 사라짐
    5. 물고기 복제

구상
    1. origin 배열을 만든다
    2.
    3. 먼저 상어가 이동 가능한 방향들을 list에 담는다.
        그 모든 조합 중 가능한 조합 + 담겨있는 물고기 갯수(얘가 더 우선순위)를 담고
        젤 앞에있는 애를 고른다.
        그리고 죽은 애들은 냄새 표시
    4. 냄새 관리

입력
    물고기 수, 턴 수
    물고기 정보
출력
    남아있는 물고기 수
'''
import heapq

n = 4
fish_num, turn_num = map(int, input().split())
grid = [[[] for i in range(n)] for i in range(n)]
for f in range(fish_num):
    r, c, d = map(lambda x: int(x) - 1, input().split())
    grid[r][c].append(d)
sr, sc = map(lambda x: int(x) - 1, input().split())  # 상어 위치
smell_grid = [[0] * n for i in range(n)]  # 어차피 물고기는 냄새있는 곳으로 못가서 냄새가 겹칠 일은 없다. 그래서 2차원 배열
row = [0, -1, -1, -1, 0, 1, 1, 1]
col = [-1, -1, 0, 1, 1, 1, 0, -1]
s_row = [-1, 0, 1, 0]
s_col = [0, -1, 0, 1]


shark_dir_list = []
sel = [0] * 3


def duple_perm(idx):
    if idx == 3:
        shark_dir_list.append(sel[:])
        return

    for i in range(4):
        sel[idx] = i
        duple_perm(idx + 1)


duple_perm(0)


def out_of_range(sr, sc, s_dir):
    r, c = sr, sc
    for d in s_dir:
        r = r + s_row[d]
        c = c + s_col[d]
        if not (0 <= r < n and 0 <= c < n):
            return True
    return False


for turn in range(turn_num):
    # 1. 초기 물고기 상태 남겨놓음
    # 2. 물고기 이동
    new_grid = [[[] for i in range(n)] for i in range(n)]
    for i in range(n):
        for j in range(n):
            if grid[i][j]:
                for fish in grid[i][j]:
                    d = fish
                    move = False
                    for k in range(8):
                        nr = i + row[d]
                        nc = j + col[d]
                        if (nr, nc) == (sr, sc) or not (0 <= nr < n and 0 <= nc < n) or smell_grid[nr][nc]:
                            d = (d - 1 + 8) % 8
                        else:
                            new_grid[nr][nc].append(d)
                            move = True
                            break
                    if not move:
                        new_grid[i][j].append(fish)
    # print("-------물고기이동--------", (sr, sc))
    # for _ in new_grid:
    #     print(_)
    # 3. 상어 이동
    possible = []
    for s_dir in shark_dir_list:
        visited = [[False] * n for i in range(n)]
        if out_of_range(sr, sc, s_dir):  # 범위 벗어나면 아웃
            continue
        d1, d2, d3 = s_dir
        r1, c1 = sr + s_row[d1], sc + s_col[d1]
        r2, c2 = r1 + s_row[d2], c1 + s_col[d2]
        r3, c3 = r2 + s_row[d3], c2 + s_col[d3]
        eat = len(new_grid[r1][c1])
        visited[r1][c1] = True
        if not visited[r2][c2]:
            eat += len(new_grid[r2][c2])
            visited[r2][c2] = True
        if not visited[r3][c3]:
            eat += len(new_grid[r3][c3])
            visited[r3][c3] = True

        heapq.heappush(possible, (-eat, s_dir, (r1, c1, r2, c2, r3, c3)))  # 마지막 위치까쥐

    eat, real_dir, (r1, c1, r2, c2, r3, c3) = heapq.heappop(possible)
    # print(eat, real_dir, (r1, c1, r2, c2, r3, c3))
    sr, sc = r3, c3
    die_list = [(r1, c1), (r2, c2), (r3, c3)]
    for r, c in die_list:
        if new_grid[r][c]:
            smell_grid[r][c] = 3
            new_grid[r][c] = []
    # print("-------잡아머굼--------", (sr, sc))
    # for _ in new_grid:
    #     print(_)
        # 4. 2턴 전 물고기 냄새 사라짐
    for i in range(n):
        for j in range(n):
            if smell_grid[i][j] > 0:
                smell_grid[i][j] -= 1

    #     5. 물고기 복제
    for i in range(n):
        for j in range(n):
            if grid[i][j]:
                new_grid[i][j].extend(grid[i][j])
    grid = new_grid
    # print("---------------", (sr, sc))
    # for _ in grid:
    #     print(_)
    # for _ in smell_grid:
    #     print(_)

ans = 0
for i in range(n):
    for j in range(n):
        ans += len(grid[i][j])
print(ans)
