package com.example.tictactoe;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

public class ParameterizedBoardTest {

    @ParameterizedTest
    @CsvSource({
        "0, 0, X, true",
        "1, 1, O, true",
        "2, 2, X, true",
        "0, 1, O, true"
    })
    void testValidMoves(int row, int col, char player, boolean expectedResult) {
        TicTacToeBoard board = new TicTacToeBoard();
        assertEquals(expectedResult, board.makeMove(row, col, player), 
            "Move validation failed for row=" + row + ", col=" + col + ", player=" + player);
    }

    @ParameterizedTest
    @CsvSource({
        "0, 0, X, X",
        "1, 1, O, O",
        "2, 2, X, X"
    })
    void testCellOccupancy(int row, int col, char player, char expectedPlayer) {
        TicTacToeBoard board = new TicTacToeBoard();
        board.makeMove(row, col, player);
        assertEquals(expectedPlayer, board.getCell(row, col), 
            "Cell occupancy incorrect for row=" + row + ", col=" + col);
    }

    // Example of more complex parameterized test with method source
    @ParameterizedTest
    @MethodSource("winningBoardStates")
    void testWinningConditions(char[][] boardState, char player, boolean expectedWinResult) {
        TicTacToeBoard board = new TicTacToeBoard();
        // Populate board with predefined state
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (boardState[row][col] != ' ') {
                    board.makeMove(row, col, boardState[row][col]);
                }
            }
        }
        assertEquals(expectedWinResult, board.checkWin(player), 
            "Win condition incorrect for player " + player);
    }

    private static Stream<Arguments> winningBoardStates() {
        return Stream.of(
            // Horizontal win
            Arguments.of(new char[][]{
                {'X', 'X', 'X'},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
            }, 'X', true),
            
            // Vertical win
            Arguments.of(new char[][]{
                {'O', ' ', ' '},
                {'O', ' ', ' '},
                {'O', ' ', ' '}
            }, 'O', true),
            
            // Diagonal win
            Arguments.of(new char[][]{
                {'X', ' ', ' '},
                {' ', 'X', ' '},
                {' ', ' ', 'X'}
            }, 'X', true),
            
            // No win scenario
            Arguments.of(new char[][]{
                {'X', 'O', 'X'},
                {'O', 'X', 'O'},
                {' ', ' ', ' '}
            }, 'X', false)
        );
    }
} 