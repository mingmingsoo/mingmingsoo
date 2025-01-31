n = int(input())
stack = []
idx = 1
ans = ""
first = int(input())
while (idx <= first):
    stack.append(idx)
    idx += 1
    ans += "+\n"
stack.pop()
ans += "-\n"
for i in range(n-1):
    num = int(input())
    while (idx <= num):
        stack.append(idx)
        idx += 1
        ans+="+\n"
    while(stack and stack[-1]!=num):
        stack.pop()
        ans+="-\n"
    if(not stack):
        ans = "NO"
        break
    elif(stack[-1]==num):
        stack.pop()
        ans+="-\n"
print(ans)