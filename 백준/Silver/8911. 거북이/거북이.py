T = int(input())
for tc in range(T):
    '''
    문제설명
        명령이 주어졌을 때 거북이가 이동한 영역을 구해라.

    구상
        이동할 때마다 최소 최대 x,y를 갱신해서 넓이를 구한다.
        2차원 배열처럼 생겼지만 굳이 그렇게 안해도 된다.
    '''

    order_list = list(input())
    minR, maxR, minC, maxC = 0, 0, 0, 0
    # 북 왼 우 남
    row = [1, 0, 0, -1]
    col = [0, -1, 1, 0]
    r, c, d = 0, 0, 0  # 시작점과 방향
    for order in order_list:
        if (order == "F"):
            r = r + row[d]
            c = c + col[d]
        elif (order == "B"):
            r = r - row[d]
            c = c - col[d]
        elif (order == "L"):
            if(d==0):
                d = 1
            elif(d==1):
                d= 3
            elif(d==2):
                d=0
            elif(d==3):
                d = 2
        elif (order == "R"):
            if(d==0):
                d = 2
            elif(d==1):
                d= 0
            elif(d==2):
                d=3
            elif(d==3):
                d = 1
        minR = min(minR, r)
        minC = min(minC, c)
        maxR = max(maxR, r)
        maxC = max(maxC, c)
    print(abs(maxR - minR) * abs(maxC - minC))
