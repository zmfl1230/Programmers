

def binary_search(target: int):
    start = 0
    end = len(cards) - 1 

    while start <= end:
        mid = (start + end) // 2

        if target == cards[mid]: return 1
        if target < cards[mid]:
            end = mid - 1
        if target > cards[mid]:
            start = mid + 1 
            

    return 0


N = int(input())
cards = sorted(list(map(int, input().split())))

M = int(input())
for elm in map(int, input().split()):
    print(binary_search(elm), end=" ")