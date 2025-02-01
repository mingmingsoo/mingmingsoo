

n = int(input())
arr = list(int(input()) for i in range(n))
arr.append(1000000001)
'''
우큰수랑 비슷하네
뒤에서부터 나보다 가장 가까이 큰애의 인덱스를 찾아서
stk에 넣어줌..

그 대신 마지막에 엄청 큰 빌딩이 있다고 생각해야
뒤에서 두번째 애의 처리를 해줄 수 있음..

'''

n+=1
stk = [n-1]
ans = [0]*(n)

for i in range(n-2,-1,-1):
    while stk and arr[i]> arr[stk[-1]]:
        stk.pop()
    if(stk):
        ans[i] = stk[-1]
    stk.append(i)
total = 0
for i in range(n-1):
    if(ans[i]>0):
        total +=(ans[i]-i-1)
print(total)


