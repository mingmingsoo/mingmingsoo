'''
답이 되는 거리를 이분탐색
'''

end_origin, n, m = map(int, input().split())
tmp = [int(input()) for i in range(n)]
tmp.sort()
tmp.insert(0, 0)
tmp.append(end_origin)
arr = []
for i in range(len(tmp) - 1):
    arr.append(tmp[i + 1] - tmp[i])
# print(arr)

start = 0
end = end_origin
ans = 0
arr_origin = arr[:]


def isOk(middle):
    remove = 0
    idx = 0
    arr = arr_origin[:]

    while idx <= len(arr) - 1:
        if arr[idx] < middle:
            if remove < m:
                remove += 1
                arr[idx + 1] += arr[idx]
                idx += 1
            else:
                return False
        else:
            idx += 1
    return True


while start <= end:
    middle = (start + end) // 2
    if isOk(middle):
        ans = middle
        start = middle + 1
    else:
        end = middle - 1
print(ans)
