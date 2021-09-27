import heapq

def solution(scoville, K):
    answer = 0
    heapq.heapify(scoville)

    while scoville:
        smallest = heapq.heappop(scoville)

        if smallest >= K: return answer
        if not scoville: return -1

        second = heapq.heappop(scoville)

        heapq.heappush(scoville, smallest + second * 2)
        answer += 1

    return answer

solution([1, 2, 3, 9, 10, 12],	7)