'''
  문제 설명
      동일한 특성 셀이 k개 연속으로 있는지 (모든 j에)
      -> 약품 투입을 A or B로 하는데 최소로 하고싶음 (최대는 k가 될 듯)
      -> 0도 가능하기에 valid를 btk 위에서 확인
  필요한 함수
      valid : 검사
      btk : btk

6 8 3
0 0 1 0 1 0 0 1
1 1 0 1 0 1 1 1
0 0 0 0 0 0 0 0
1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1
1 1 1 1 1 1 1 1

6 8 2
0 0 0 0 0 0 0 0
1 1 1 1 1 1 1 1
0 0 0 0 0 0 0 0
1 1 1 1 1 1 1 1
0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 1


6 8 2
0 0 0 0 0 0 0 0
1 1 1 1 1 1 1 1
0 0 0 0 0 0 0 0
1 1 1 1 1 1 1 1
0 0 0 0 0 0 0 0
1 0 0 0 0 0 0 1

가지치기가 잘못됐나?
진짜로 외=ㅐ 49 맞췄는지 모르겟ㅅ성요ㅠ

valid 다시 살펴보기
찾앗다1!!!!!!!!!!!!
1
2 2 1
1 0
0 1
'''
T = int(input())


def valid():
    # 모든 j마다 A or B가 연속된 l개가 있는지 검사
    for j in range(m):
        same = -1
        cnt = 1
        ok = False
        for i in range(n):
            if cnt >= l:
                ok = True
                break
            if grid[i][j] == same:
                cnt += 1
                if cnt >= l:
                    ok = True
                    break
            else:
                same = grid[i][j]
                cnt = 1
        if not ok:
            return False

    return True

def btk(cnt, idx):
    global ans

    if valid():
        ans = min(ans, cnt)
        return
    if idx == n:
        return
    if cnt >= l:
        return

    origin = grid[idx][:]

    grid[idx] = [0] * m  # 0으로 바꾸고 보내기
    btk(cnt + 1, idx + 1)
    grid[idx] = origin[:]  # 원상 복구

    grid[idx] = [1] * m  # 1로 바꾸고 보내기
    btk(cnt + 1, idx + 1)
    grid[idx] = origin[:]  # 원상 복구

    btk(cnt, idx + 1)  # 일단 오리지날로 보내! - 여기 행 안바꿀거에요


for tc in range(T):
    n, m, l = map(int, input().split())
    grid = [list(map(int, input().split())) for i in range(n)]
    ans = l  # 최댓값

    btk(0, 0)  # 횟수, 몇번째 행 바꿀건지
    print(f"#{tc + 1} {ans}")
