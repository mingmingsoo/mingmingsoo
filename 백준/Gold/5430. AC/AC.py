from collections import deque

T = int(input())
for tc in range(T):
    order = input()
    n = int(input())
    stringQueue = deque()
    if(n==0):
        string = input()
        stringQueue = deque()
    else:

        string =input()
        string = string[1:len(string)-1]
        # print(string)
        stringQueue = deque(string.split(","))
    # print(stringQueue)
    orderQueue = deque()
    cnt = 0
    while (cnt<len(order)):
        # print(order[cnt])
        if(cnt<len(order)-1 and order[cnt] == order[cnt+1] == "R"):
            cnt+=2
            continue
        orderQueue.append(order[cnt])
        cnt+=1
    isError = False
    isReverse = -1

    while orderQueue:
        if(orderQueue.popleft()=="R"):
            isReverse *= -1
        else:
            if(len(stringQueue)==0):
                isError = True
                break
            else:
                if(isReverse==1):
                    stringQueue.pop()
                else :
                    stringQueue.popleft()
    if(isError):
        print("error")
    else:
        if(isReverse==1):
            stringQueue.reverse()
        if(len(stringQueue)==0):
            print("[]")
        else:
            print("[",end = "")
            for i in range(len(stringQueue)):
                if(i == len(stringQueue)-1):
                    print(f'{stringQueue[i]}]',end = "\n")
                else:
                    print(stringQueue[i], end=",")