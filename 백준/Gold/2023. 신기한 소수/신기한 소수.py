'''
1,2,3,5,7 5개 숫자중에서 N개를 고르고 그게 소수인지 아닌지 확인한댱...
'''
import math

N = int(input())


def btk(idx, arr):
    if arr:
        if arr[0]== 1:
            return
        num = int("".join(map(str, arr)))
        for j in range(2, int(math.sqrt(num)) + 1):
            if num % j == 0:
                return
    if idx == N:
        print("".join(map(str, arr)))
        return

    for i in range(1, 10):
        arr.append(i)
        btk(idx + 1, arr)
        arr.pop()


btk(0, [])
