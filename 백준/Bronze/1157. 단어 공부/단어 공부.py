from collections import Counter

def main():
    # 문자열 입력 받기
    str_input = input().strip().upper()
    
    # 각 알파벳의 등장 횟수 세기
    counter = Counter(str_input)
    
    # 등장 횟수가 가장 많은 알파벳 찾기
    max_count = max(counter.values())
    
    # 가장 큰 값이 여러 개인지 확인
    max_chars = [char for char, count in counter.items() if count == max_count]
    
    if len(max_chars) > 1:
        print("?")
    else:
        print(max_chars[0])

if __name__ == "__main__":
    main()