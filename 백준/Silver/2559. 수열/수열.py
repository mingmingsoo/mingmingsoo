n,m = map(int, input().split())
arr = list(map(int, input().split()))

sum_arr = [0]*(n)

first = 0
for i in range(m):
    first += arr[i]
# print(first)


sum_arr[m-1] = first
for i in range(m,n):
    sum_arr[i] = sum_arr[i-1]-arr[i-m]+arr[i]

ans = -float("inf")
# print(sum_arr)
for i in range(m-1,n):
    ans = max(ans, sum_arr[i])
print(ans)