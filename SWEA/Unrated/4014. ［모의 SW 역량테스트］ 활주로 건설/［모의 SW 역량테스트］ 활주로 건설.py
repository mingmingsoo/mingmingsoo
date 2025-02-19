'''
문제 설명
    모든 행, 열을 검사해서 활주로 건설이 가능한지 세라
입력
    맵의 크기 N, 활주로 가로 길이 X
출력
    경우의 수
구상
    활주로가 가능한 경우
    1. 내 높이와 연속된 길이를 기록
    2. 내 높이보다 낮으면 그 높이와 연속된 길이를 기록
        -> 연속된 길이가 X 보다 작거나 같으면 ok 넘어가
    3. 내 높이보다 높으면 내 연속된 길이를 비교
    혹은 모두 같은 수로 이루어져 있는경우
틀린이유
    겹치는 경우 처리가 잘 안됐음.
'''

T = int(input())
for tc in range(T):

    n, x = map(int, input().split())
    grid = [list(map(int, input().split())) for i in range(n)]


    def same_first(arr):

        first = arr[0]
        for i in range(1, n):
            if arr[i] != first:
                return False
        return True

    def valid(arr):

        # 1. 내 높이와 연속된 길이를 기록
        # 2. 내 높이보다 낮으면 그 높이와 연속된 길이를 기록
        #     -> 연속된 길이가 X 보다 작거나 같으면 ok 넘어가
        # 3. 내 높이보다 높으면 내 연속된 길이를 비교

        karo = 0
        h = 0
        i = 0
        while i < n:
            if h == 0:
                h = arr[i]
                karo = 1
                i += 1
            elif arr[i] == h:  # 1. 내 높이와 연속된 길이를 기록
                karo += 1
                i += 1
            elif arr[i] == h + 1:  # 3. 내 높이보다 높으면 내 연속된 길이를 비교
                if karo >= x:
                    karo = 1
                    h = arr[i]
                    i += 1
                else:
                    return False
            elif arr[i] == h - 1:  # 2. 내 높이보다 낮으면 그 높이와 연속된 길이를 기록
                # 여기가 잘 안됐음
                small_h = arr[i]
                for j in range(i, i + x):
                    if j >= n or arr[j] != small_h: # 활주로 건설 불가
                        return False
                i = i + x # 다음칸으로 넘어가고
                karo = 0 # 가로길이  초기화
                h = arr[i-1] # 높이는 전 값으로.
            else:
                return False
        return True


    ans = 0
    for row in grid:
        if (same_first(row)):
            ans += 1
        elif (valid(row)):
            ans += 1

    for j in range(n):
        col = []
        for i in range(n):
            col.append(grid[i][j])
        if (same_first(col)):
            ans += 1
        elif (valid(col)):
            ans += 1
    print(f"#{tc+1} {ans}")