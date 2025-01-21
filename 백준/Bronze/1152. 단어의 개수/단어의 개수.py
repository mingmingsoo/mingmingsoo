string = list(input().split(" "))
# print(string)
while(string.__contains__("")):
    string.remove("")
print(len(string))