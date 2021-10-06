import sys
input = sys.stdin.readline

N = int(input())
budgets = sorted(list(map(int, input().split())))
L = int(input())


def binary():

    start = 1
    end = L 

    while start <= end:
        mid = (start + end) // 2

        middle = sum([ elm if elm <= mid else mid for elm in budgets])
        if middle > L: end = mid - 1
        else: start = mid + 1
    
    return start - 1

def solution():

    if sum(budgets) <= L: return budgets[-1]

    return binary()

print(solution())