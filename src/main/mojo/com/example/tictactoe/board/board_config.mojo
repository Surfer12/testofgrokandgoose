from python import Python

struct BoardConfiguration:
    fn get_board_size(self) -> Int:
        return 3

    fn get_winning_line_length(self) -> Int:
        return 3

struct LargeBoardConfig:
    fn get_board_size(self) -> Int:
        return 5

    fn get_winning_line_length(self) -> Int:
        return 4 