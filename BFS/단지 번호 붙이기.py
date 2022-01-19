from collections import deque


N = int(input())
EMPTY = 0
HOUSE = 1
maps = [[int(s) for s in input()] for _ in range(N)]
visited = [[False for _ in range(N)] for _ in range(N)]

dr = [0, 0, -1, 1]
dc = [-1, 1, 0, 0]

apt_number = 0;
apt_results = []


def find_same_apt(row, col):
  queue = deque()
  queue.append([row, col])
  visited[row][col] = True
  
  count = 1
  while queue:
    row, col = queue.popleft()
    
    for idx in range(4):
      newRow = row + dr[idx]
      newCol = col + dc[idx]
      
      if newRow < 0 or newRow > N-1 or newCol < 0 or newCol > N-1: continue
      if visited[newRow][newCol]: continue
      if maps[newRow][newCol] == EMPTY: continue
      
      queue.append([newRow, newCol])
      visited[newRow][newCol] = True
      count += 1
  
  return count


for row in range(N):
  for col in range(N):
    if maps[row][col] == HOUSE and not visited[row][col]: 
      apt_number += 1
      apt_results.append(find_same_apt(row, col))
      


if apt_number == 0: print(0)
else:
  print(apt_number)
  for result in sorted(apt_results):
    print(result) 
  