def solution(prices):
    answer = [0] * len(prices)
    
    stack = []
    for idx, price in enumerate(prices):
        if not stack: 
            stack.append([idx, price])
            continue
        
        # 과거에 현재보다 작은 값이 있으면 pop해서 제거
        while stack[-1][1] > price:
            time, _ = stack.pop()
            answer[time] = idx - time
        
        stack.append([idx, price])
    
    # 마지막으로 아직 stack에 남아있는 값들은 한번도 작아진 적이 없는 값들이므로 전체 크기에서 뺀값을
    # 헤당 인덱스에 저장
    for time, _ in stack:
        answer[time] = len(prices) - time - 1
        

    return answer

print(solution([1,1,1,1,1,1,1,1,1]))