from collections import deque

def solution(bridge_length, weight, truck_weights):
    truck_queue = deque(truck_weights)
    
    total = 0
    time = 1
    on_bridge = deque()
    
    while truck_queue or on_bridge:
        
        # 현재 다리에 맨 앞에 있는 값이 현재 시간과 같을 때, 해당 값 제거
        if on_bridge and on_bridge[0][0] == time: 
            _, cost = on_bridge.popleft()
            total -= cost
            
        
        # 현재 총 무게 값 + 다음 트럽 무게가 허용가능한 범위 이내일때 다음 트럭 추가 
        if truck_queue and total + truck_queue[0] <= weight:
            next = truck_queue.popleft()
            on_bridge.append([time+bridge_length, next])
            total += next
            
            if not truck_queue: return time+bridge_length
        
        # 매 반복문마다 시간 카운트
        time += 1

solution(2,	10,	[7,4,5,6])
# 745638, 12, 2   