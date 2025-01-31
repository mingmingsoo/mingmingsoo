n = int(input())
stack = []
idx = 1
ans = []
for i in range(n):
    num = int(input())
    while (idx <= num):
        stack.append(idx)
        idx += 1
        ans.append("+")
    while(stack and stack[-1]!=num):
        stack.pop()
        ans.append("-")
    if(not stack):
        ans = "NO"
        break
    elif(stack[-1]==num):
        stack.pop()
        ans.append("-")
if(ans =="NO"):
    print(ans)
else:
    for x in ans:
        print(x)
