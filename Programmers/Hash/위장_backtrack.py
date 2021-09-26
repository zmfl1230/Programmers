from itertools import combinations
from collections import OrderedDict

def backtrack(cnt, last, args):
    ret = 0

    if cnt == len(info):
        return 0

    for key in info.keys():
        if key <= last: continue

        temp = 1
        args.append(key)
        print(*args)

        for a in args:
            temp *= info[a]
        ret += temp
        ret += backtrack(cnt+1, key, args)

        args.pop()
    return ret

def solution(clothes):
    ans = 0

    global info
    info = OrderedDict()

    for elm in sorted(clothes, key=lambda x: x[1]):
        if elm[1] not in info:
            info[elm[1]] = 0
        info[elm[1]] += 1

    ans = backtrack(0, 'a', [])
    return ans

# solution([["yellowhat", "headgear"], ["bluesunglasses", "eyewear"], ["green_turban", "headgear"]])
solution([["crowmask", "face"], ["bluesunglasses", "face"], ["smoky_makeup", "face"]])