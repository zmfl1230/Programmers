import heapq
from collections import deque
def solution(priorities, location):
    importance = []
    queue = deque(priorities)
    
    for elm in priorities:
        heapq.heappush(importance,(-elm, elm))
    
    rotate = 0
    pos = location
    cur_priority = heapq.heappop(importance)[1]
    while queue:
        cur = queue.popleft()
        
        if cur == cur_priority: 
            rotate += 1
            if pos == 0: break

            cur_priority = heapq.heappop(importance)[1]
        else:
            queue.append(cur)
        pos = len(queue) - 1 if pos == 0 else pos - 1

    return rotate
print(solution([1, 1, 9, 1, 1, 1], 0))