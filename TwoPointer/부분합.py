import sys
input = sys.stdin.readline

N, S = map(int, input().split())
li = list(map(int, input().split()))

"""
주의 요인:
- 중간에 end값이 인덱스 범위를 벗어난 경우 반드시 break을 걸어준다. 안 걸어준다면 클리게 된다.
- flag를 걸어 반드시 total값이 S값을 넘지 못하는 경우도 대비해야 한다.
- min을 구하는 것이기 떄문에 반드시 반환값을 최대로 자정해 두고 구현한다.
"""

ret = 100000000
total = 0

start = 0
end = -1

flag = False
while end < N:

    if total < S:
        if end+1 > N-1: break
        end += 1
        total += li[end]
    
    if total >= S:
        ret = min(ret, end - (start -1))
        flag = True
        total -= li[start]
        start += 1


print(ret if flag else 0)