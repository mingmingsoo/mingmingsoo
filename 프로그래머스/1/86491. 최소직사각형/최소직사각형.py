'''
일단 제일 큰 값은 하나 뽑혀야하고 그 나머지중에 하나를 골라서
범위 안에 드는지 확인해야함
'''

def isFitted(small, maxi,sizes):
    # 모든 명함이 들어가면 되는거임
    for x, y in sizes:
        if(x>y):
            x, y = y,x
        if x > small: # 지갑이 못담아
            return False
    return True



def solution(sizes):
    answer = 0
    maxi = 0
    for row in sizes:
        maxi = max(maxi, max(row))
    # print(maxi) # 제일 큰놈은 필요
    
    small_list = []
    for row in sizes:
        for x in row:
            small_list.append(x)
    small_list = sorted(list(set(small_list)))
    # 오름차순해서 조건 만족하면 바로 출력하고 return 칠거임
    # print(small_list)

    for s in small_list:
        if(isFitted(s,maxi,sizes)):
            answer = s* maxi
            break # 오름차순 했기에 찾으면 바로 나가기
    
    return answer