import sys
input = sys.stdin.readline

N, S = map(int, input().split())
li = list(map(int, input().split()))

ret = 100000000
total = 0

start = 0
end = -1
flag = False
while end < N:
    
    if total >= S:
        ret = min(ret, end - (start -1))
        total -= li[start]
        start += 1
        
        flag = True
        continue
        

    end += 1
    if end > N-1: break
    total += li[end]

print(ret if flag else 0)