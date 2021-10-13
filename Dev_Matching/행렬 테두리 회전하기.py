
from collections import deque


def rotate(x1, y1, x2, y2, m):
    # 오, 아래, 왼, 위
    dir = [[y1 + 1, y2 + 1, 1], [x1 + 1, x2 + 1, 1], [y2 - 1, y1 - 1, -1], [x2 - 1, x1 - 1, -1]]
    order = [x1, y2, x2, y1]
    ret = m[x1][y1]
    temp = deque()
    temp.append(m[x1][y1])

    for idx in range(4):
        if not idx % 2:
            for elm in range(dir[idx][0], dir[idx][1], dir[idx][2]):
                temp.append(m[order[idx]][elm])
                m[order[idx]][elm] = temp.popleft()
                ret = min(ret, m[order[idx]][elm])
        else:
            for elm in range(dir[idx][0], dir[idx][1], dir[idx][2]):
                temp.append(m[elm][order[idx]])
                m[elm][order[idx]] = temp.popleft()
                ret = min(ret, m[elm][order[idx]])

    return ret

def solution(rows, columns, queries):
    answer = []
    matrix = []
    for row in range(1, rows + 1):
        matrix.append([columns * (row - 1) + col for col in range(1, columns + 1)])

    for x1, y1, x2, y2 in queries:
        answer.append(rotate(x1-1, y1-1, x2-1, y2-1, matrix))

    return answer

