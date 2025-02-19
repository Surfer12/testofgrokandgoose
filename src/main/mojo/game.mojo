from java import TicTacToe

def handle_move(row: Int, col: Int) -> (List[List[Char]], Char):
    # Initialize the game with the current state or create a new one if not available
    game = TicTacToe()
    if game.makeMove(row, col):
        return game.getBoard(), game.getCurrentPlayer()
    else:
        return None, None

def check_winner(board):
    game = TicTacToe()
    game.board = board
    return game.checkWinner()