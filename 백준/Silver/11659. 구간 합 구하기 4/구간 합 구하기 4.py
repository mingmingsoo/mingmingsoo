n, M = map(int, input().split())
arr = list(map(int, input().split()))
# print(arr)

sumArray = [0]*n
sumArray[0] = arr[0]
for i in range(1,n):
    sumArray[i] = sumArray[i-1]+arr[i]
# print(sumArray)
for m in range(M):
    start, end = map(int, input().split())
    start -= 1
    end -=1
    if(start ==0):
        print(sumArray[end])
    else:
        print(sumArray[end]-sumArray[start-1])


