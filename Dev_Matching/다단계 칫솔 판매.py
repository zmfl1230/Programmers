CENTER = '-'

order = {}
parents = {}


def dfs(target, rest):
    toss = rest // 10
    if toss < 1:
        answer[order[target]] += rest
        return
    
    answer[order[target]] += rest - toss 
    if parents[target] == CENTER: return
    return dfs(parents[target], toss)
    
def solution(enroll, referral, sellers, amount):
    global answer
    answer = [0] * len(enroll)

    # 부모 저장, 등록자 순서 정의
    for idx, elm in enumerate(enroll):
        parents[elm] = referral[idx]
        order[elm] = idx
    """
        이 문제는 만약 10%의 값이 1보다 작아지는 경우엔 분배를 안하고 본인이 전액을 다 가질 수 있게 되기 때문에
        중복된 seller가 있다고 하더라도 이를 합쳐서 한번에 계산하도록 하면 안된다. 왜냐면 나머지 값들이 달라질 수 있기 때문이다.
        즉, 1200, 400, 200, 500, 1000 을 따로 분리해서 분배하는 것과 이를 합쳐서 3300원으로 한번에 분배하는 것은 그 나머지 값들이 각
        값들마다 다르기 떄문에 특정 seller의 값이 동일하지 않을 수 있다. 고로 이런 경우 합쳐서 분배하지 않고 각각의 수익을 그떄 그때 배분해야 한다.
    """
    # seller의 수익 분배
    for s, a in zip(sellers, amount):
        dfs(s, a * 100)

    
    return answer

solution(["john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"],	["-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"],	["young", "young", "young", "young", "young"],	[12, 4, 2, 5, 10])