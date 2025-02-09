import sys
input = sys.stdin.readline
while True:
    n, m = map(int, input().split())
    if(n ==0 and m ==0):
        break
    arr = list(map(int, input().split()))
    size = max(arr)
    # size = 40
    adj = [[] for i in range(size + 1)]
    myparent = 0
    i = 0
    visited = [False] * (size + 1)
    while (i < n):
        parent = arr[i]
        visited[parent] = True
        start, end = i + 1, i + 1
        for j in range(i + 1, n - 1):
            if (visited[arr[j]]):
                start += 1
                end += 1
                continue
            if (arr[j] + 1 == arr[j + 1]):
                start = min(start, j)
                end = max(end, j + 1)
            else:
                break
        # print(parent, start,end)
        for j in range(start, end + 1):
            if (j >= n):
                continue
            if (visited[arr[j]]):
                continue
            adj[parent].append(arr[j])
            if (arr[j] == m):
                myparent = parent
            visited[arr[j]] = True
        i += 1
        # print(i)
    # print(adj)
    cousin = 0
    # 사촌 찾기
    for node in adj:
        if myparent in node:
            for brother in node:
                if (brother == myparent):
                    continue
                cousin += len(adj[brother])
    print(cousin)
