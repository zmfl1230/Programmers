# def solution(n, times):
#     answer = min(times) * n
#     start = 0
#     end = max(times) * n

#     while start <= end:
#         mid = (start + end) // 2

#         serviced = 0
#         for time in times:
#             serviced += mid // time
#             if serviced >= n: break
            
#         if serviced >= n: 
#             answer = min(answer, mid)
            
#         if serviced >= n:
#             end = mid - 1
#         else:
#             start = mid + 1


#     return answer
"""
    문제의 핵심. 반례가 3,	[1, 99, 99, 1] 에 있었다.

    단순히 answer를 업데이트하는 조건을 반드시 3과 그 경과값들이 일치해야 한다고 생각한 점이 큰 착각이였다.
    
    위 내용은 1분이 걸리는 심사대에서 2명이 받고 다른 1분이 걸리는 심사대에서 1명이 받으면, 총 2초가 걸린다.

    하지만 3과 일치하는 시간대가 나올 수 있느냐 하면 아니다.
    mid == 2일 때, 1번 심사대에서 2번 4번 심사대에서 2번으로 총 4명의 사람을 심사할 수 있다고 판단하기 때문에 
    가장 최소시간인 2에서 조차도 시간이 업데이트 되지 않고, 3이 반환되는 현상이 발생했다.

    아를 반드시 수용가능한 승객보다 그 이상의 승객을 받을 수 있을 경우, 그때마다 최소시간을 업데이트 해줘야 한다. 
    어차피 이 경우에는 end값을 계속 줄여나가기 떄문에 그 이후에 mid값은 현재 값보다 반드시 작음을 보장한다.

    이렇게 코드를 수정할 경우, 결국 4명의 승객을 수용할 수 있더라도 그 값이 반드시 수용해야 하는 3보다 크며, 전체 시간은 최소이기때문에
    해당 값이 도출되게 된다.
"""
def solution(n, times):
    times.sort()
    answer = times[0] * n
    start = 0
    end = times[-1] * n

    while start <= end:
        mid = (start + end) // 2

        serviced = 0
        for time in times:
            serviced += mid // time
            if serviced >= n: break

        if serviced >= n:
            answer = min(answer, mid)
            end = mid - 1
        else:
            start = mid + 1


    return answer

solution(3,	[1, 99, 99, 1])