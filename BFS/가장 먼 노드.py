from collections import deque

def solution(n, edges):
    answer = []
    path = {}
    
    visited = [False] * (n + 1)
    visited[1] = True

    for s, e in edges:
        if s not in path:
            path[s] = []
        
        if e not in path:
            path[e] = []
        
        path[s].append(e)
        path[e].append(s)
    
    max_val = 0
    queue = deque()
    queue.append([1, 0])
    
    while queue:
        pop, cnt = queue.popleft()
        
        for elm in path[pop]:
            if visited[elm]: continue
            
            queue.append([elm, cnt + 1])
            max_val = max(max_val, cnt + 1)
            answer.append([elm, cnt+1])
            
            visited[elm] = True
            
    return len(list(filter(lambda x: x[1] >= max_val, answer)))