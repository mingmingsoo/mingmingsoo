'''
[문제 설명]
    어떤 칸을 클릭했을 때 지뢰가 있는 칸을 제외한 모든 칸들의 숫자가 표시되려면 최소 몇번 클릭해야하는가?
[구상]
    1. 먼저 지뢰가 아닌 곳에 근방에 지뢰가 몇개 있는지 담는 배열을 만든다.(지뢰면 -1)
    2. 근데 무조건 0먼저 눌러야 장땡아닌가?
    3. 그래서 무조건 0먼저 누르게 하겠다.
'''
def count_pang(r, c): # 주변에 몇개의 지뢰가 있는지 세주는 함수
    pang = 0
    for k in range(8):
        nr = r + row[k]
        nc = c + col[k]
        if (not (0 <= nr < n and 0 <= nc < n)):
            continue
        if (grid[nr][nc] == "*"):
            pang += 1
    return pang

def pangpang(r, c, pang, grid_copy):  # 팡팡.. 지뢰팡팡
    global visited_pang

    grid_copy[r][c] = pang
    visited_pang[r][c] = True
    if pang == 0: # 0이면 한번 더 타고들어가기
        for k in range(8):
            nr = r + row[k]
            nc = c + col[k]
            if (not (0 <= nr < n and 0 <= nc < n)):
                continue
            if (info[nr][nc] == 0 and not visited_pang[nr][nc]):
                visited_pang[nr][nc] = True
                pangpang(nr, nc, info[nr][nc], grid_copy)
            else:
                visited_pang[nr][nc] = True
                grid_copy[nr][nc] = info[nr][nc]

def game():
    global ans
    cnt = 0 # 몇번 지뢰를 눌렀는지
    for r, c, pang in pang_list:

        if (grid[r][c] == "."):
            pangpang(r, c, pang, grid)
            cnt += 1

    return cnt

T = int(input())
for tc in range(T):

    n = int(input())
    grid = [list(input()) for i in range(n)]
    info = [[0] * n for i in range(n)]
    row = [1, -1, 0, 0, 1, 1, -1, -1]
    col = [0, 0, 1, -1, 1, -1, 1, -1]

    pang_list = []
    for i in range(n):
        for j in range(n):
            if (grid[i][j] == "*"):
                info[i][j] = -1
            else:
                info[i][j] = count_pang(i, j)
                pang_list.append((i, j, info[i][j]))
    pang_list.sort(key=lambda x: x[2])
    ans = 300 * 300 + 1

    visited_pang = [[False] * n for i in range(n)]
    ans = game()
    print(f"#{tc+1} {ans}")