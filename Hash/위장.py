from functools import reduce
from operator import mul
from collections import Counter


"""
    A 유형의 옷이 3가지 있을 때, 1번 옷을 입은 경우, 2번 옷을 입은 경우, 3번 옷을 입은 경우, 아예입지 않는 경우를 모두 고려함
    이 경우의 수들을 모두 곱한 경우 모두 입지 않은 경우의 수 까지 포함한 결과가 나오는데 이 때문에 -1을 해주면 원하는 최종 결과값을 도출할 수 있다.
"""
def solution(clothes):
    class_list = Counter([cat for _, cat in clothes])
    tot_sum = reduce(mul ,[val+1 for val in class_list.values()],1)

    return tot_sum-1

solution([["yellowhat", "headgear"], ["bluesunglasses", "eyewear"], ["green_turban", "headgear"]])
solution([["crowmask", "face"], ["bluesunglasses", "face"], ["smoky_makeup", "face"]])