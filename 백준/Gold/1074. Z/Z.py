'''
분할...정복...
문제 설명
    size가 2가 될때까지 계속 분할한다.
    r행 c열을 몇번째로 방문하는지 출력-> 넘버링을 하면 됨
입력
    n,r,c
'''

N, er, ec = map(int, input().split())

size = 2 ** N

num = 0
ans = 0
find = False


def z(r, c, size):
    global num, ans, find
    if find:
        return
    if (size == 1):
        return
    # 필요한데만 간다.
    if (er < r + size // 2 and ec < c + size // 2):
        z(r, c, size // 2)
    elif er < r + size // 2 and ec >= c + size // 2:
        z(r, c + size // 2, size // 2)
        ans += (size*size)//4*1
    elif er >= r + size // 2 and ec < c + size // 2:
        z(r + size // 2, c, size // 2)
        ans += (size*size)//4*2
    else:
        z(r + size // 2, c + size // 2, size // 2)
        ans += (size*size)//4*3


z(0, 0, size)

print(ans)
