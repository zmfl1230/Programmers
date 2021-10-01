import copy

"""
    이름순대로 이동하다가 그래프가 끊기는 경우도 고려해야한 문제
    그래서 해당 부분은 backtracking으로 해결했다. 

    만약 모든 티켓을 소진하지 않았는데도 더이상 움직일 경로가 없다면, 그래프가 끊긴것이라 할 수 있다.
    그런 경우엔 이전 stack으로 리턴해 그 다음 경로를 우선 탐색하도록 구현했고, 
    모든 가능한 경로를 answer 에 담고, 이를 이름순으로 정렬해 가장 첫번째 값을 리턴하도록 구현했다.
"""
def dfs(cnt, paths):

    if cnt == total:
        answer.append(copy.deepcopy(paths))
        return
    
    if paths[-1] not in path: return

    for arrive in path[paths[-1]]:
        if arrive[1] != 0: continue

        paths.append(arrive[0])
        arrive[1] = 1

        dfs(cnt + 1, paths)

        paths.pop()
        arrive[1] = 0
    
    return

        

def solution(tickets):

    global answer
    global path
    global total

    answer = []
    path = {}
    total = 0
    
    for depart, arrive in tickets:
        if depart not in path:
            path[depart] = []
        
        path[depart].append([arrive, 0])
        total += 1
    
    stack = ['ICN']
    dfs(0, stack)
           
    return sorted(answer)[0]
# solution([["ICN", "SFO"], ["ICN", "ATL"], ["SFO", "ATL"], ["ATL", "ICN"], ["ATL","SFO"]])
print(solution(
[["ICN", "SFO"], ["SFO", "ICN"], ["ICN", "SFO"], ["SFO", "QRE"]]
))
# ["ICN", "BBB", "CCC", "ICN", "CCC", "BBB"])