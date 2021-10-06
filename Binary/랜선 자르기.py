import sys
input = sys.stdin.readline

K, N = map(int, input().split())
lines = sorted([int(input()) for _ in range(K)])


start = 1
end = lines[-1]

while start <= end:
    mid = (start + end) // 2

    line_num = sum([line // mid for line in lines])

    if line_num >= N: start = mid + 1
    else: end = mid - 1

print(start - 1)
