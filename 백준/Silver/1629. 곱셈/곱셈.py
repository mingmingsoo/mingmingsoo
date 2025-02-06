
'''
별의 별 큰 숫자를 넣어도 바로 출력되는데 시간초과 나는중 ㅠㅠ
1021321314 24913213 134342313
c가 b 더 클경우에는 시간초과가 남!! -> 몫이 0 이고 나머지가 그대로 되기 때문임

'''
import sys

input = sys.stdin.readline

a, b, c = map(int, input().split())


def my_pow(a, b):
    if (b <= 1):  # == 1이 아니라 1보다 작아야함! 0승이 들어갈 수도 있음
        return a
    if (b % 2 == 0):  # 짝수면
        tmp = my_pow(a, b // 2) %c
        return tmp * tmp
    else:
        tmp = my_pow(a, (b - 1) // 2) %c
        return tmp * tmp * a


ans = my_pow(a, b)
print(ans % c)