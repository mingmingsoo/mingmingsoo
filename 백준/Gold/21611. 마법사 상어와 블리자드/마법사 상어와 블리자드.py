'''
문제 설명
    1. 공격 - 얘는 쉬움
    2. 댕기기 while 필요
    3. 구슬 폭발
            while
                발견(4개이상) - 폭발
                (발견 못했으면 break)
                댕겨
    4. 순서대로 배열 만들어주고
        새로운 순서대로 배열 생성
        그걸 또 순서대로 grid 새로 생성

출력
     1×(폭발한 1번 구슬의 개수) + 2×(폭발한 2번 구슬의 개수) + 3×(폭발한 3번 구슬의 개수)
     -> bomb에서 처리
'''


def make_location():  # 한번만 실행하는 함수, 달팽이 순서대로 위치 담아주기
    # 달팽이용 방향
    s_row = [0, 1, 0, -1]
    s_col = [-1, 0, 1, 0]
    r, c, d = n // 2, n // 2, 0
    num, two, cnt = 1, 0, 0
    while not (r == 0 and c == 0):

        location_idx.append((r, c))

        r = r + s_row[d]
        c = c + s_col[d]

        cnt += 1
        if cnt == num:
            d = (d + 1) % 4
            cnt = 0
            two += 1
        if two == 2:
            two = 0
            num += 1
    location_idx.pop(0)  # 가운는 빼주고
    location_idx.append((0, 0))  # 맨 끝에는 넣어주기


def attack():  # 공격!
    for dist in range(1, distance + 1):
        nr = r + row[d] * dist
        nc = c + col[d] * dist
        grid[nr][nc] = 0


def pull():  # 당겨! : 중력처럼 swap 해줌
    while True:
        p = 0 # pull 했는지 여부
        for i in range(len(location_idx) - 1):
            r, c = location_idx[i]
            nr, nc = location_idx[i + 1]
            if grid[r][c] == 0 and grid[nr][nc] != 0:
                grid[r][c], grid[nr][nc] = grid[nr][nc], grid[r][c]
                p = 1
        if not p:
            break


def bomb():
    while True:
        is_bomb = False
        idx = 0
        same = 0
        block = []

        while idx < len(location_idx):
            r, c = location_idx[idx]
            if grid[r][c] == same:
                block.append((r, c))
            else:
                same = grid[r][c]
                if len(block) >= 4:
                    num = grid[block[0][0]][block[0][1]]
                    ans[num - 1] += (len(block))
                    is_bomb = True
                    for br, bc in block:
                        grid[br][bc] = 0
                    block = []
                else:
                    block = [(r, c)]

            idx += 1
        if not is_bomb:
            break

        pull()  # 다시 당기기


def fill():
    global grid
    arr = []
    for r, c in location_idx:
        if grid[r][c]:
            arr.append(grid[r][c]) # 기존 배열을 1차원 배열로 변환
    arr.append(0) # 마지막 숫자 비교를 위해 0 넣어줌
    new_arr = []
    same = arr[0] # 일단 첫번째 숫자를 스타트로
    cnt = 1
    idx = 1
    while idx < len(arr):
        num = arr[idx]
        if num == same: # 같으면 갯수 증가
            cnt += 1
        else:
            new_arr.append(cnt) # 아니면 여태 몇개였는지 추가
            new_arr.append(same)
            same = num
            cnt = 1 # cnt 초기화
        idx += 1

    grid = [[0] * n for i in range(n)] # 배열 새로!
    for r, c in location_idx: # 새로 만들어진 숫자들 넣어주기
        # 아무리 많아도 map을 채울 수 있는 만큼만
        if new_arr:
            grid[r][c] = new_arr.pop(0)


row = [-1, 1, 0, 0]  # 공격용 방향
col = [0, 0, -1, 1]
n, attack_num = map(int, input().split())
grid = [list(map(int, input().split())) for i in range(n)]
location_idx = []
make_location()
r, c = n // 2, n // 2
ans = [0, 0, 0]  # 정답 출력용

for a in range(attack_num):
    d, distance = map(int, input().split())  # 공격 방향과 거리
    d -= 1

    attack()  # 공격
    pull()  # 당겨줌
    bomb()  # 폭발
    fill()  # 채워줌

print(ans[0] * 1 + ans[1] * 2 + ans[2] * 3)
