'''
문제 설명
    1번말부터 K번말까지 순서대로 이동시킨다.
    한 말이 이동할 때 올려져있는 말도 함께 이동(?)
    말이 4개이상 쌓이면 게임 종료(k개 아님)
    1. A번말이 흰색이로 이동하려는 경우
        말이 있을 때 A는 위에 올라감
        A번 위에 다른 말이 있을 때는 A번위에 모든 말이 이동
        C           C
        B  E        B
        A  D   ->   A
                    E
                    D
    2. 빨간색으로 이동하는 경우, A번말 은 순서가 바뀜
        ABC -> CBA
        G           A
        F  E        D
        D  C   ->   F
        A  B        G
                    B
                    C
                    E
    3. 파란색인 경우 이동방향 반대
        만약 반대에도 파란색이 있으면 유지.

헷갈렸던 점
    213 이런식으로 있으면 1번말이면 1~부터 이동 2는 안간다.
    범위를 벗어나면 d가 반대로? 아아 ㅇㅇ 그렇대
구상
    append 할지 insert 할지
입력
    맵크기, 말갯수
    체스판 정보(0흰 1빨 2파)
    말 정보 1부터
출력
    게임 종료되는 턴
    1000보다 크면 -1 출력
    같은 칸에 말이 두 개 이상 있는 경우는 입력으로 주어지지 않는다. -> 0 일 수 없다.
'''


class Horse:
    def __init__(self, num, d):
        self.num = num
        self.d = d


n, horse_num = map(int, input().split())
color = [list(map(int, input().split())) for i in range(n)]
grid = [[[] for i in range(n)] for i in range(n)]
for horse in range(horse_num):
    r, c, d = map(int, input().split())
    grid[r - 1][c - 1].append(Horse(horse + 1, d - 1))  # 넘버링, 방향

ans = -1

row = [0, 0, -1, 1]
col = [1, -1, 0, 0]
move = {0: 1, 1: 0, 2: 3, 3: 2}
# print("--------------------------")
# for i in range(n):
#     for j in range(n):
#         tmp = ""
#         if grid[i][j]:
#             for horse in grid[i][j]:
#                 tmp += str(horse.num)
#                 if horse.d == 0:
#                     tmp += ">"
#                 if horse.d == 1:
#                     tmp += "<"
#                 if horse.d == 2:
#                     tmp += "^"
#                 if horse.d == 3:
#                     tmp += "v"
#             print(tmp, end=" ")
#         else:
#             if color[i][j] == 0:
#                 print("W", end=" ")
#             if color[i][j] == 1:
#                 print("R", end=" ")
#             if color[i][j] == 2:
#                 print("B", end=" ")
#     print()


def valid():
    for i in range(n):
        for j in range(n):
            if len(grid[i][j]) >= 4:
                return True
    return False


for turn in range(1, 1001):
    real_find = False
    # move
    for order in range(1, horse_num + 1):
        find = False
        for r in range(n):
            for c in range(n):
                if grid[r][c]:
                    for w in range(len(grid[r][c])):
                        horse = grid[r][c][w]
                        if horse.num == order:  # 움직임 수행
                            # print(horse.d, ":", r, "->", r + row[horse.d], ",", c, "->", c + col[horse.d])
                            if not (0 <= r + row[horse.d] < n and 0 <= c + col[horse.d] < n) or color[r + row[horse.d]][
                                c + col[horse.d]]==2:
                                # 방향 전환 필요
                                horse.d = move[horse.d]
                            # 이동할 위치
                            nr = r + row[horse.d]
                            nc = c + col[horse.d]
                            # 흰색 검사
                            if 0 <= nr < n and 0 <= nc < n and color[nr][nc] == 0:
                                # 내 위에 말들 모두 올린다
                                for k in range(w, len(grid[r][c])):
                                    grid[nr][nc].append(grid[r][c][k])
                                # 그리고 제거
                                for k in range(len(grid[r][c]) - 1, w - 1, -1):
                                    grid[r][c].pop(k)

                            # 빨간색 검사
                            elif 0 <= nr < n and 0 <= nc < n and color[nr][nc] == 1:
                                # 내 위에 말들 뒤에서부터 올린다.
                                for k in range(len(grid[r][c]) - 1, w - 1, -1):
                                    grid[nr][nc].append(grid[r][c][k])
                                # 그리고 제거
                                for k in range(len(grid[r][c]) - 1, w - 1, -1):
                                    grid[r][c].pop(k)
                            find = True
                            break
                if find:
                    break
            if find:
                break
        # print("--------------------------", turn, order)
        # for i in range(n):
        #     for j in range(n):
        #         tmp = ""
        #         if grid[i][j]:
        #             for horse in grid[i][j]:
        #                 tmp += str(horse.num)
        #                 if horse.d == 0:
        #                     tmp += ">"
        #                 if horse.d == 1:
        #                     tmp += "<"
        #                 if horse.d == 2:
        #                     tmp += "^"
        #                 if horse.d == 3:
        #                     tmp += "v"
        #             print(tmp, end=" ")
        #         else:
        #             if color[i][j] ==0:
        #                 print("W", end = " ")
        #             if color[i][j] ==1:
        #                 print("R", end = " ")
        #             if color[i][j] ==2:
        #                 print("B", end = " ")
        #     print()

        # valid
        if valid():
            ans = turn
            real_find = True
            break
    if real_find:
        break
print(ans)
