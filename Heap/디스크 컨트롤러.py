import heapq

def solution(jobs):
    answer = 0
    fcfs_jobs = []
    sjf_jobs = []
    for start, takes in jobs:
        heapq.heappush(fcfs_jobs, (start, takes))

    # FCFS  
    last = 0
    fcfs_times = 0

    def check_available():
        while fcfs_jobs:
            if fcfs_jobs[0][0] > last: break
            n_st, n_tk = heapq.heappop(fcfs_jobs)
            heapq.heappush(sjf_jobs, (n_tk, n_st))

    while fcfs_jobs:
        st, tk = heapq.heappop(fcfs_jobs)
        fcfs_times += last - st + tk if st <= last else tk
        last += tk if st <= last else st - last + tk

        check_available()
        
        # 현재 작업 끝나기 이전에 들어온 작업들 처리
        # 하나의 작업이 끝나더라도 그 시점에 해결할 수 있는 다른 작업들을 살펴봐야 한다.
        while sjf_jobs:
            n_tk, n_st = heapq.heappop(sjf_jobs)
            fcfs_times += last - n_st + n_tk
            last += n_tk

            check_available()

    
    answer = fcfs_times // len(jobs)

    return answer
solution([[0, 10], [4, 10], [5, 11], [15, 2]])
# solution([[24, 10], [28, 39], [43, 20], [37, 5], [47, 22], [20, 47], [15, 34], [15, 2], [35, 43], [26, 1]])