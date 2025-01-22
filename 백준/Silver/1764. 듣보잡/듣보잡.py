n, m = map(int, input().split())
set1 = set()
for i in range(n):
    set1.add(input())
set2 = set()
for i in range(m):
    set2.add(input())
arr = list(set1.intersection(set2))
arr.sort()
print(len(arr))
for _ in arr:
    print(_)