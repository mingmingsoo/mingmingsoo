'''
리프노드: 자식이 없는 말단애들.
특정 노드 하나를 지울 떄 모든 자손이 트리에서 제거됨.
리프노드의 갯수는?
'''

V = int(input())
tmp = list(map(int, input().split()))
parent_adj = [[] for i in range(V)]
child_adj = [[] for i in range(V)]

for i in range(V):
    parent = tmp[i]
    if parent != -1:
        child_adj[parent].append(i)
        parent_adj[i].append(parent)

delete = int(input())

deleteList = [False] * V

deleteList[delete]= True
# print(child_adj)
def dfs(delete):
    for node in child_adj[delete]:
        deleteList[node] = True
        dfs(node)
# print(deleteList)
dfs(delete)
for node in parent_adj[delete]:
    for child in child_adj[node]:
        if( child == delete):
            child_adj[node].remove(child)

ans = 0


# 잘린애를 제외하고 말단인 애들 갯수셈
for i in range(V):
    if(not deleteList[i] and not child_adj[i]):
        ans+=1
print(ans)