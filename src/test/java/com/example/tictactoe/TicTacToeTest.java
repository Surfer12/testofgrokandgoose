package com.example.tictactoe;

import static org.junit.jupiter.api.Assertions.*;
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

    @BeforeEach
    void setUp() {
        game = new TicTacToe();
        log.info("Game instance created for tests.");
    }

    @Test
    void testInitialState() {
        assertEquals('X', game.getCurrentPlayer(), "Game should start with player X");
        assertFalse(game.isGameOver(), "Game should not be over at start");
        
        // Check that all cells are empty
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(' ', game.getCell(i, j), "Cell should be empty at start");
            }
        }
    }

    @Test
    void testWinCondition() {
        // Make winning moves for X (horizontal first row)
        game.makeMove(0, 0);
        game.makeMove(1, 1); // O's move
        game.makeMove(0, 1);
        game.makeMove(1, 2); // O's move
        boolean won = game.makeMove(0, 2);

        assertTrue(won, "Game should be won");
        assertTrue(game.isGameOver(), "Game should be over");
        log.debug("testWinCondition passed with X winning.");
    }

    @Test
    void testInvalidMove() {
        game.makeMove(0, 0); // Valid first move
        
        TicTacToeException exception = assertThrows(
            TicTacToeException.class,
            () -> game.makeMove(0, 0), // Try to play in the same spot
            "Should throw exception for invalid move"
        );
        log.error("Expected exception caught: {}", exception.getMessage());
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

        assertFalse(result, "Game should end in a draw");
        assertTrue(game.isGameOver(), "Game should be over");
        log.info("testDrawCondition confirms a draw state correctly.");
    }

    @Test
    void testMockitoIntegration() {
        TicTacToeBoard boardMock = mock(TicTacToeBoard.class);
        when(boardMock.makeMove(anyInt(), anyInt(), anyChar())).thenReturn(true);

        // Verify that the mock works
        assertTrue(boardMock.makeMove(0, 0, 'X'));
        verify(boardMock, times(1)).makeMove(0, 0, 'X');
        log.info("Mockito integration test passed.");
    }
}
