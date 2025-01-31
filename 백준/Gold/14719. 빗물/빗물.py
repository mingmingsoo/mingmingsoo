h, n = map(int, input().split())
arr = list(map(int, input().split()))
ans = 0
# 내 위치에서 고일 수 있는가? 를 판별
for i in range(1,n-1):
    leftMax = max(arr[:i])
    rightMax = max(arr[i+1:])

    heignt = min(leftMax, rightMax,h)
    # 낮은 높이만큼 고여질 수 있음, 내리는 빗물까지의 최솟값 만큼 채워질 수 있음
    if(arr[i]<heignt): # 고여질 수 있다면 계산
       ans += heignt - arr[i]
print(ans)