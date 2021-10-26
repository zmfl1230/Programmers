STATUS = {
    'Enter': '들어왔습니다.',
    'Leave': '나갔습니다.',
}
record = ["Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"]

def solution():
    answer = []
    user_info = {}
    
    for _, elm in enumerate(record):
        splited = elm.split()
        
        if splited[0] == 'Leave': continue
        user_info[splited[1]] = splited[2]
    
    for _, elm in enumerate(record):
        splited = elm.split()
        
        if splited[0] == 'Change': continue
        answer.append(f'{user_info[splited[1]]}'"님이" f'{STATUS[splited[0]]}')

    return answer

print(solution())