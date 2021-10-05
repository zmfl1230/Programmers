import sys
from collections import deque
""" 
    슬라이딩 윈도우, visited 배열을 이용한 중복 조회 
    visited 배열에 [elm]값이 0이 아닌 경우 이미 포함되어 있다는 뜻이므로 visited[elm] 값에 따라 전체 가짓수의 값을 조절한다.
    if visited[elm]: 이전에 같은 종류를 담은적 없음
    if not visited[elm]: 이전에 같은 종류를 담은적 있음

    슬라이딩 윈도우를 이용해서 고정 크기의 값을 사전에 구한뒤 start값을 0 - N-1 까지 변경하면서, start 값을 제거해주고, end 값을 추가해준다.
    제거하고, 추가한 start, end값을 이용해서 visited 배열 및 전체 가짓수 최대값을 업데이트 한다.


"""

input = sys.stdin.readline

N, D, K, C = map(int, input().split())


dishes = []
for idx in range(N):
    dishes.append(int(input()))


visited = [0] * (D+1)
visited[C] += 1

max_dish = 1
for idx in range(K):

    if not visited[dishes[idx]]: max_dish += 1
    visited[dishes[idx]] += 1

total = max_dish
for start in range(N):

    visited[dishes[start]] -= 1
    if not visited[dishes[start]]: total -= 1

    if not visited[dishes[(start + K) % N]]: total += 1
    visited[dishes[(start + K) % N]] += 1
    max_dish = max(max_dish, total)

print(max_dish)





