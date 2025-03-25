n, time = map(int, input().split())
tmp = [list(map(int, input().split())) for i in range(time)]
time_info = []
grid = [[1] * n for i in range(n)]
grid_origin = [[1] * n for i in range(n)]
order_set = set()
for i in range(n-1,-1,-1):
    for j in range(n):
        if i == 0 or j == 0:
            order_set.add((i,j))
for i in range(time):
    time_info.append([0]*tmp[i][0]+[1]*tmp[i][1]+[2]*tmp[i][2])
# print(time_info)
total_sum = []
for j in range(len(time_info[0])):
    sumi = 0
    for i in range(len(time_info)):
        sumi += time_info[i][j]
    total_sum.append(sumi)
# print(total_sum)


for idx, location in enumerate(order_set):
    r, c = location
    grid[r][c] += total_sum[idx]


for i in range(1,n):
    for j in range(1,n):
        maxi = max(grid[i][j-1]- grid_origin[i][j-1], grid[i-1][j]- grid_origin[i-1][j], grid[i-1][j-1]- grid_origin[i-1][j-1])
        grid[i][j] += maxi



for _ in grid:
    print(*_)
