n, m = map(int,input().split())
arr = list(map(int, input().split()))

arr.sort()
sel = [0]*m

def btk(idx):
    if idx == m:
        print(*sel)
        return

    for i in range(n):
        sel[idx] = arr[i]
        btk(idx+1)

btk(0)