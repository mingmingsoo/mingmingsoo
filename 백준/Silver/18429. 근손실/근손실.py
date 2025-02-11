'''
문제
    배열이 주어졌을 때 배열 순서에 따라 근육량이 500미만으로 떨어지 체크해야함
입력
    배열의 길이, 매일 떨어지는 근육량
    배열
출력
    순열을 구한 후 연산을 통해 500미만으로 떨어진적 없으면 경우의수 +=1
구상
    순열로 배열을 생성하고
    그 순서에 따라 근육량 감소를 확인한다.
'''

n,sad = map(int,input().split())
arr = list(map(int, input().split()))
sel = [0]*n
visited = [False]*n
cnt = 0
def btk(idx):
    global cnt
    if(idx == n):
        muscle = 500
        for i in range(n):
            muscle += sel[i]-sad
            if(muscle<500):
                return
        cnt+=1
        return
    for i in range(n):
        if not visited[i]:
            visited[i] = True
            sel[idx] = arr[i]
            btk(idx+1)
            visited[i] = False
btk(0)
print(cnt)