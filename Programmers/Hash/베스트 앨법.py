from collections import deque
"""
    어차피 인덱스 순으로 조사하기 떄문에 추에 들어오는 값이라면 인덱스측면에서 우선순위가 낮을 수 밖에 없다.
    이러한 이유로, 인덱스는 따로 비교하지 않았다.
"""
def solution(genres, plays):

    answer = []
    records = {}
    orders = {}
    total_score = []
    cnt = 0

    def manage_records(gen, play, i):
        if gen not in records:
            records[gen] = deque()
            records[gen].append([play, i])
            return
        
        if len(records[gen]) == 1:
            if records[gen][0][0] >= play:
                records[gen].append([play, i])
            else:
                records[gen].appendleft([play, i])
            
            return

        min_play, min_idx = records[gen].pop()

        if min_play > play: 
            records[gen].append([min_play, min_idx])
            return

        if min_play == play: 
            records[gen].append([play, i])
            return
        
        # play가 minimum보다 더 큰 경우,
        if records[gen][0][0] >= play:
            records[gen].append([play, i])
            return
        
        if records[gen][0][0] < play:
            records[gen].appendleft([play, i])
            return


    for idx, elm in  enumerate(zip(genres, plays)):

        if elm[0] not in orders:
            orders[elm[0]] = cnt
            total_score.append([elm[0], 0])
            cnt += 1
        
        total_score[orders[elm[0]]][1] += elm[1]

        manage_records(elm[0], elm[1], idx)

    for gen, _ in sorted(total_score, key=lambda x: x[1], reverse=True):
        for playlist in records[gen]:
            answer.append(playlist[1])

    return answer