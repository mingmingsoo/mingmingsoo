n = int(input()) # 2*n+1 개의 IOI가 나와야함
m = int(input())
line = input()
cnt = 0
for i in range(m-(2*n+1)+1):
    if(line[i]=="I"):
        # print(i)
        isIO = True
        for j in range(i,i+(2*n+1)):
            # print(i,i+(2*n+1))
            if((j-i)%2==0):
                if(line[j]!='I'):
                    isIO = False
                    break
            else:
                if(line[j]!='O'):
                    isIO = False
                    break
        if(isIO):
            # print(i)
            cnt+=1
            continue
print(cnt)