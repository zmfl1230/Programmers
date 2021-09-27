import heapq
INSERT = 'I'
DELETE = 'D'
MAX = '1'
MIN = '-1'

def solution(operations):
    min_heap = []
    answer = [0, 0]
    for op in operations:
        op_char, val = op.split()

        if op_char == INSERT:
            heapq.heappush(min_heap, int(val))
            continue
        
        if val == MAX and min_heap:
            min_heap.remove(heapq.nlargest(1, min_heap)[0])
            continue
            
        if val == MIN and min_heap:
            heapq.heappop(min_heap)
            continue
    
    if min_heap:
        answer = [heapq.nlargest(1, min_heap)[0], heapq.nsmallest(1, min_heap)[0]]
        
        
    return answer