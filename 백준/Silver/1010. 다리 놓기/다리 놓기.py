T = int(input())
for t in range(T):
    n, m = map(int, input().split())
    # mCn
    mul = 1
    for num in range(m-n+1, m+1):
        mul *= num
    for num in range(1, n+1):
        mul //= num
    print(int(mul))