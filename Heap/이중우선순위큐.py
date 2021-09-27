import heapq
INSERT = 'I'
DELETE = 'D'
MAX = '1'
MIN = '-1'

def solution(operations):
    max_heap = []
    min_heap = []
    min_removed = 0
    max_removed = 0

    for op in operations:
        op_char, val = op.split()

        if op_char == INSERT:
            heapq.heappush(max_heap, (-1 * int(val), int(val)))
            heapq.heappush(min_heap, int(val))

            continue
        
        if val == MAX and len(max_heap) > min_removed:
            heapq.heappop(max_heap)
            max_removed += 1 
            continue
            
        if val == MIN and len(min_heap) > max_removed:
            heapq.heappop(min_heap)
            min_removed += 1 
            continue
    
    if len(max_heap) > min_removed or len(min_heap) > max_removed:
        answer = [0, 0]
    else:
        """
            이미 최대힙에서 삭제된 값이 min heap에는 아직 존재함으로 해당 elm까지 제거해줘야하는 문제가 있었음.
        """
        answer = [heapq.heappop(max_heap)[1], heapq.heappop(min_heap)]
        
    return answer
ㄴ
solution(["I 4", "I 3", "I 2", "I 1", "D 1", "D 1", "D -1", "D -1", "I 5", "I 6"])