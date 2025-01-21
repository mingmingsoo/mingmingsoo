dict = {}

str = list((input().upper()))

for s in str:
    dict[s] = 0;
for s in str:
    dict[s] = dict[s]+1;

max = max(dict.values())
cnt = 0;
ans = ""

for _ in dict.keys():
    if(dict[_]==max):
        cnt += 1
        ans = _
    if(cnt>=2):
        ans = "?"
        break;

print(ans)