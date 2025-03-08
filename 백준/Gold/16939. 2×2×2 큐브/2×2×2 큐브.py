'''
오른쪽을 오른쪽으로 한번 돌린거랑 왼쪽을 왼쪽으로 한번 돌린거랑 같다
오른쪽으로만 돌리겠다
'''


def same(cube):
    for face in cube:
        first = face[0][0]
        for i in range(2):
            for j in range(2):
                if face[i][j] != first:
                    return False
    return True


colors = list(map(int, input().split()))

cube = [[[0] * 2 for j in range(2)] for i in range(6)]  # 4개씩 6개면
idx = 0
while colors:
    cube[idx][0][0] = colors.pop(0)
    cube[idx][0][1] = colors.pop(0)
    cube[idx][1][0] = colors.pop(0)
    cube[idx][1][1] = colors.pop(0)
    idx += 1

# 위 0 아래 2
# 앞 1 뒤 5
# 왼 3 우 4

isOk = False
# 한번만 돌릴 수 있다.
origin_cube = [[_[:] for _ in __] for __ in cube]
# 오른쪽 시계
# 윗면에 앞면이옴
cube[0][0][1] = origin_cube[1][0][1]
cube[0][1][1] = origin_cube[1][1][1]
# 앞면에 아랫면이옴
cube[1][0][1] = origin_cube[2][0][1]
cube[1][1][1] = origin_cube[2][1][1]
# 아랫면에 뒷면이옴
cube[2][0][1] = origin_cube[5][1][0]
cube[2][1][1] = origin_cube[5][0][0]
# 뒷면에 윗면이옴
cube[5][1][0] = origin_cube[0][0][1]
cube[5][0][0] = origin_cube[0][1][1]

if same(cube):
    isOk = True
cube = [[_[:] for _ in __] for __ in origin_cube]
# 왼쪽 반시계
# 윗면에 앞면이옴

cube[0][0][0] = origin_cube[1][0][0]
cube[0][1][0] = origin_cube[1][1][0]
# 앞면에 아랫면이옴
cube[1][0][0] = origin_cube[2][0][0]
cube[1][1][0] = origin_cube[2][1][0]
# 아랫면에 뒷면이옴
cube[2][0][0] = origin_cube[5][1][1]
cube[2][1][0] = origin_cube[5][0][1]
# 뒷면에 윗면이옴
cube[5][1][1] = origin_cube[0][0][0]
cube[5][0][1] = origin_cube[0][1][0]

if same(cube):
    isOk = True

cube = [[_[:] for _ in __] for __ in origin_cube]

# 위 0 아래 2
# 앞 1 뒤 5
# 왼 3 우 4

# 위
# 앞면에 왼쪽
cube[1][0][0] = origin_cube[3][0][0]
cube[1][0][1] = origin_cube[3][0][1]
# 왼쪽에 뒷면
cube[3][0][0] = origin_cube[5][0][0]
cube[3][0][1] = origin_cube[5][0][1]
# 뒷면에 오른쪽면
cube[5][0][0] = origin_cube[4][0][0]
cube[5][0][1] = origin_cube[4][0][1]
# 오른쪽면에 앞면
cube[4][0][0] = origin_cube[1][0][0]
cube[4][0][1] = origin_cube[1][0][1]

if same(cube):
    isOk = True
cube = [[_[:] for _ in __] for __ in origin_cube]
# 아래 반시계
# 앞면에 왼쪽
cube[1][1][0] = origin_cube[3][1][0]
cube[1][1][1] = origin_cube[3][1][1]
# 왼쪽에 뒷면
cube[3][1][0] = origin_cube[5][1][0]
cube[3][1][1] = origin_cube[5][1][1]
# 뒷면에 오른쪽면
cube[5][1][0] = origin_cube[4][1][0]
cube[5][1][1] = origin_cube[4][1][1]
# 오른쪽면에 앞면
cube[4][1][0] = origin_cube[1][1][0]
cube[4][1][1] = origin_cube[1][1][1]

if same(cube):
    isOk = True
cube = [[_[:] for _ in __] for __ in origin_cube]

# 앞 시계
# 위 0 아래 2
# 앞 1 뒤 5
# 왼 3 우 4
# 윗면에 왼쪽
cube[0][1][0] = origin_cube[3][1][1]
cube[0][1][1] = origin_cube[3][0][1]
# 왼쪽에 아랫면
cube[3][1][1] = origin_cube[2][0][0]
cube[3][0][1] = origin_cube[2][0][1]
# 아랫면 오른쪽면
cube[2][0][0] = origin_cube[4][1][0]
cube[2][0][1] = origin_cube[4][0][0]
# 오른쪽면에 윗면
cube[4][1][0] = origin_cube[0][1][0]
cube[4][0][0] = origin_cube[0][1][1]
if same(cube):
    isOk = True
cube = [[_[:] for _ in __] for __ in origin_cube]
# 뒤 반시계
# 위 0 아래 2
# 앞 1 뒤 5
# 왼 3 우 4
# 윗면에 왼쪽
cube[0][0][0] = origin_cube[3][1][0]
cube[0][0][1] = origin_cube[3][0][0]
# 왼쪽에 아랫면
cube[3][1][0] = origin_cube[2][1][0]
cube[3][0][0] = origin_cube[2][1][1]
# 아랫면 오른쪽면
cube[2][1][0] = origin_cube[4][1][1]
cube[2][1][1] = origin_cube[4][0][1]
# 오른쪽면에 윗면
cube[4][1][1] = origin_cube[0][0][0]
cube[4][0][1] = origin_cube[0][0][1]
if same(cube):
    isOk = True
if isOk:
    print(1)
else:
    print(0)
