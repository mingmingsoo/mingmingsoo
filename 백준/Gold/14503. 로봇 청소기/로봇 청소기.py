'''

두번째 풀이
    불필요한 visited True 주석처리, input

문제설명
    1. 현재 방향 기준으로 왼쪽 방향으로 간 적 없으면 좌회전해서 1칸 간다
    2. 1번에서 이미 방문했거나 도로가 아니면 1번으로 돌아감
    3. 4방향 모두 전진하지 못하면 후진(방향 유지)
    4. 후진 못하면 끝

입력
    맵 크기
    초기 위치, 방향
    맵 (도로 0, 인도 1)
출력
    총 면적 (visited가 True 인 애들의 갯수)

구상
    조건 잘 따져야...
'''
import sys
input = sys.stdin.readline
n, m = map(int, input().split())
r,c,d = map(int, input().split())
grid = [list(map(int, input().split())) for i in range(n)]
visited = [[False] * m for i in range(n)]
visited[r][c] = True
row = [-1, 0, 1, 0]
col = [0, 1, 0, -1]
cnt = 0 # 헛도는거 확인용

while True:
    # 1. 현재 방향 기준으로 왼쪽 방향으로 간 적 없으면 좌회전해서 1칸 간다 -> 이게 근데 현재방향이 유지 되는건가?ㅠㅠ 아니라고 가정하고 풀자
    # 방향이 바뀌는게 맞았음. yeah!
    nr = r + row[(d + 3) % 4]
    nc = c + col[(d + 3) % 4]
    if not visited[nr][nc] and grid[nr][nc] == 0 :
        # 이러면 갈 수 있다.
        cnt = 0 # 갔으니까 헛도는거 초기화
        visited[nr][nc] = True
        d = (d + 3) % 4
        r = nr
        c = nc
    else:
        # 2. 1번에서 이미 방문했거나 도로가 아니면 좌회전하고 1번으로 돌아감
        d = (d + 3) % 4  # 방향만 바꾸기
        cnt += 1  # 한바퀴 돌았다.
    if cnt > 3:  # 3. 4방향 모두 전진하지 못하면 == 나 한 자리에서 4번 돌았다.
        nr = r - row[d]
        nc = c - col[d]
        if grid[nr][nc] == 0: # 후진할 수 있으면
            # 여기서 중요한건 visited를 따지지 않는 것
            # visited[nr][nc] = True # 이건 없어도 됨... 어차피 후진이니까
            r = nr
            c = nc
            cnt = 0 # 갔으니까 헛도는거 초기화
        # 4. 후진 못하면 끝
        else:
            break

ans = 0
for _ in visited:
    ans += _.count(True)
print(ans)
