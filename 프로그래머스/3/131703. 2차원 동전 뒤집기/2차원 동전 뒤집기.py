def solution(beginning, target):
    '''
    모든 경우의 수 탐색
    백트래킹
    행 and 열
    '''

    n = len(beginning)
    m = len(beginning[0])

    ans = 1000000

    def same(beginning):
        if beginning == target:
            return True
        return False

    def btk_col(jdx, cnt,beginning):
        if same(beginning):
            nonlocal ans
            ans = min(ans, cnt)
            return
        if jdx == m:
            return

        # 오리지널기록
        origin = [_[:] for _ in beginning]

        # 돌려!
        for i in range(n):
            beginning[i][jdx] = 1 - beginning[i][jdx]
        btk_col(jdx + 1, cnt + 1,beginning)

        # 원상복구!
        beginning = [_[:] for _ in origin]
        btk_col(jdx + 1, cnt,beginning)


    def btk_row(idx, cnt, beginning):
        nonlocal ans
        if same(beginning):
            nonlocal ans
            ans = min(ans, cnt)
            return
        if idx == n:
            btk_col(0,cnt,beginning)
            return

        # 오리지널기록
        origin = [_[:] for _ in beginning]

        # 돌려!
        for j in range(m):
            beginning[idx][j] = 1 - beginning[idx][j]

        btk_row(idx+1, cnt+1,beginning)

        # 원상복구!
        beginning = [_[:] for _ in origin]
        btk_row(idx + 1, cnt,beginning)


    btk_row(0,0,beginning)
    if ans == 1000000:
        return -1
    else:
        return ans
