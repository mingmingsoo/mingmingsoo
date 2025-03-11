'''
문제설명
    원판을 회전시켜라.
    원판에 수가 남아 있으면, 인접하면서 수가 같은 것을 모두 찾는다.
    그러한 수가 있는 경우에는 원판에서 인접하면서 같은 수를 모두 지운다.
    없는 경우에는 원판에 적힌 수의 평균을 구하고, 평균보다 큰 수에서 1을 빼고, 작은 수에는 1을 더한다.
입력
    원판 갯수 N, 원판에 몇개들었는지 M, 명령 수
    원판 정보
    명령 정보
        x,d,k :x의 배수인 원판을 d방향으로 k번 회전 (0 시계, 1 반시계)
        k는 M보다 작음.
구상
    회전: 1 2 3 4 5 6
         1 2 3 0 1 2 ...
    회전은 m의 나머지
'''

n, m, order_num = map(int, input().split())
grid = [list(map(int, input().split())) for i in range(n)]
for o in range(order_num):
    x, d, k = map(int, input().split())
    k = k % m
    # 회전
    for i in range(x - 1, n, x):  # x 의 배수인 애들 회전
        arr = grid[i][:]
        if d == 0:
            for kk in range(k):
                arr.insert(0, arr.pop())
        elif d == 1:
            for kk in range(k):
                arr.append(arr.pop(0))
        grid[i] = arr
    # 회전 끝

    is_delete = False
    delete = [[0] * m for i in range(n)]
    # 1. 같은 원 확인
    for i in range(n):
        for j in range(-1, m - 1):  # 0하고 m-1은 연결된다.
            if grid[i][j] != 0:
                if grid[i][j] == grid[i][j + 1]:
                    delete[i][j], delete[i][j + 1] = 1, 1
                    is_delete = True

    # 2. 세로 확인
    for j in range(m):
        for i in range(n - 1):
            if grid[i][j] != 0:
                if grid[i][j] == grid[i + 1][j]:
                    delete[i][j], delete[i + 1][j] = 1, 1
                    is_delete = True

    # 삭제가능하면 삭제
    if is_delete:
        for i in range(n):
            for j in range(m):
                if delete[i][j] == 1:
                    grid[i][j] = 0
    # 아니라면 평균 구하기
    else:
        cnt = sm = 0
        for i in range(n):
            for j in range(m):
                if grid[i][j] > 0:
                    cnt += 1
                    sm += grid[i][j]

        for i in range(n):
            for j in range(m):
                if grid[i][j] <= 0:
                    continue
                if grid[i][j] < (sm / cnt): grid[i][j] += 1
                elif grid[i][j] > (sm / cnt): grid[i][j] -= 1

ans = sum(map(sum, grid))
print(ans)
