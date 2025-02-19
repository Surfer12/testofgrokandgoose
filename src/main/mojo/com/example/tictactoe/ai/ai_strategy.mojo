from python import Python

fn find_available_moves(board: List[List[Char]]) -> List[List[Int]]:
    var available_moves = List[List[Int]]()
    for row in range(len(board)):
        for col in range(len(board[row])):
            if board[row][col] == Char(' '):
                available_moves.append([row, col])
    return available_moves

struct RandomMoveAI:
    fn __init__(inout self):
        pass
    
    fn select_move(inout self, board: List[List[Char]], ai_player: Char) -> List[Int]:
        var available_moves = find_available_moves(board)
        
        if len(available_moves) > 0:
            return available_moves[0]
        
        return List[Int](-1, -1)  # No move available 