package com.example.tictactoe;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyChar;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(MockitoExtension.class)
class TicTacToeTest {
    private TicTacToe game;
    private StandardTicTacToeBoard board;

    @BeforeEach
    void setUp() {
        board = new StandardTicTacToeBoard();
        game = new TicTacToe(board);
        log.info("Game instance created for tests.");
    }

    @Test
    void testInitialState() {
        assertThat(game.getCurrentPlayer())
            .as("Game should start with player X")
            .isEqualTo('X');
        
        assertThat(game.isGameOver())
            .as("Game should not be over at start")
            .isFalse();
        
        // Check that all cells are empty
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertThat(game.getCell(i, j))
                    .as("Cell should be empty at start")
                    .isEqualTo(' ');
            }
        }
    }

    @Test
    void testWinCondition() {
        // Make winning moves for X (horizontal first row)
        boolean won = game.makeMove(0, 0);
        game.makeMove(1, 1); // O's move
        game.makeMove(0, 1);
        game.makeMove(1, 2); // O's move
        won = game.makeMove(0, 2);

        assertThat(won)
            .as("Game should be won")
            .isTrue();
        
        assertThat(game.isGameOver())
            .as("Game should be over")
            .isTrue();
        
        log.debug("testWinCondition passed with X winning.");
    }

    @Test
    void testInvalidMove() {
        game.makeMove(0, 0); // Valid first move
        
        assertThatThrownBy(() -> game.makeMove(0, 0))
            .as("Should throw exception for invalid move")
            .isInstanceOf(TicTacToeException.class)
            .hasMessageContaining("Invalid move");
        
        log.error("Invalid move test passed successfully.");
    }

    @Test
    void testDrawCondition() {
        // Fill the board without winning
        game.makeMove(0, 0); // X
        game.makeMove(0, 1); // O
        game.makeMove(0, 2); // X
        game.makeMove(1, 1); // O
        game.makeMove(1, 0); // X
        game.makeMove(1, 2); // O
        game.makeMove(2, 1); // X
        game.makeMove(2, 0); // O
        boolean result = game.makeMove(2, 2); // X

        assertThat(result)
            .as("Game should end in a draw")
            .isFalse();
        
        assertThat(game.isGameOver())
            .as("Game should be over")
            .isTrue();
        
        log.info("testDrawCondition confirms a draw state correctly.");
    }

    @Test
    void testBoardStateCopy() {
        // Verify board state copy is independent
        game.makeMove(0, 0);
        char[][] boardState = game.getBoard();
        
        // Modify the copy without affecting the game board
        boardState[0][0] = 'Z';
        
        assertThat(game.getCell(0, 0))
            .as("Original board should not be modified")
            .isEqualTo('X');
    }

    @Test
    void testCustomBoardStrategy() {
        // Create a mock BoardStrategy
        BoardStrategy mockStrategy = mock(BoardStrategy.class);
        when(mockStrategy.makeMove(anyInt(), anyInt(), anyChar())).thenReturn(true);
        when(mockStrategy.checkWin(anyChar())).thenReturn(false);
        when(mockStrategy.isFull()).thenReturn(false);

        TicTacToe customGame = new TicTacToe(mockStrategy);
        
        boolean result = customGame.makeMove(0, 0);
        
        assertThat(result)
            .as("Move should be accepted by mock strategy")
            .isFalse();
        
        verify(mockStrategy, times(1))
            .makeMove(0, 0, 'X');
    }
}
