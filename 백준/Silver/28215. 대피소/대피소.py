'''
n개의 대피소중에 m개를 선택해서 거리를 계산
거리를 계산하려면 다 다다랐을 때 계산할 수 있음


'''
n, m = map(int,input().split()) # 점의 갯수와 대피소 갯수.
arr = []
for i in range(n):
    x,y = map(int,input().split())
    arr.append((x,y))
# print(arr)

sel = [0]*m
ans = 10000000000

def perm(sidx, idx,cnt):
    global ans
    if(sidx ==m and cnt>0):
        # print(sel)
        not_sel = []
        for i in range(n):
            if i not in sel:
                not_sel.append(i)
        # print(not_sel)
        ele_dist = 0
        for j in range(n-m):
            dist = 1000000000
            house_num = not_sel[j]
            for i in range(m):
            # 대피소 번호
                store_num = sel[i]
                dist = min(dist, abs(arr[store_num][0] - arr[house_num][0])+abs(arr[store_num][1] - arr[house_num][1]))
                # print(house_num, store_num, dist)
            ele_dist = max(ele_dist,dist)
        ans = min(ele_dist,ans)
        return

    if(idx ==n):
        return

    sel[sidx]=idx
    perm(sidx+1,idx+1,cnt+1)
    perm(sidx,idx+1,cnt)

perm(0,0,0)
print(ans)