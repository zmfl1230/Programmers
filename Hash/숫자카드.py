N = int(input())
cards = set(map(int, input().split()))

M = int(input())

for elm in map(int, input().split()):
    print(int(elm in cards), end=" ")
