S = int(input())

start = 1
end = S

flag = True
while start <= end:
    mid = (start + end) // 2

    minimum_sum = (mid * (mid + 1)) // 2

    if minimum_sum == S:
        print(mid)
        flag = False
        break

    if minimum_sum <= S: start = mid + 1
    else: end = mid - 1

if flag:
    print(start-1)