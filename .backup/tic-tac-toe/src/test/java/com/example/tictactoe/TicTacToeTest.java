package com.example.tictactoe;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TicTacToeTest {

    private TicTacToe game;

    @BeforeAll
    public void setup() {
        // Initialize the game with any mocked services if needed
        game = new TicTacToe();
        log.info("Game instance created for tests.");
    }

    @BeforeEach
    public void beforeEachTest() {
        // Optionally reset the game state
        game.reset();
    }

    @Test
    public void testWinCondition() {
        // Simulate a scenario where a win should occur
        game.makeMove(0, 0, 'X');
        game.makeMove(1, 0, 'X');
        game.makeMove(2, 0, 'X');

        // Use assertAll to group multiple assertions
        assertAll("Verify winning conditions",
            () -> assertTrue(game.hasWinner(), "Should have a winner after a horizontal line"),
            () -> assertEquals('X', game.getWinner(), "Winner should be player 'X'")
        );
        log.debug("testWinCondition passed with X winning.");
    }

    @Test
    public void testInvalidMoveThrowsException() {
        // Define a custom exception scenario for invalid moves
        TicTacToeException exception = assertThrows(
            TicTacToeException.class,
            () -> game.makeMove(-1, 0, 'O'),
            "Expected makeMove to throw, but it didn't."
        );
        log.error("Expected exception caught: {}", exception.getMessage());
        assertTrue(exception.getMessage().contains("Invalid move"), "Exception message should mention 'Invalid move'");
    }

    @Test
    public void testDrawCondition() {
        // Arrange a draw condition by filling the board with no winner
        // (Assuming that your implementation supports a draw state)
        // Set up the board with proper edge cases manually or via helper methods
        game.simulateDraw();
        assertFalse(game.hasWinner(), "Game should have no winner in a draw condition");
        log.info("testDrawCondition confirms a draw state correctly.");
    }

    @Test
    public void testMockitoIntegration() {
        // Example of using Mockito to simulate a dependency
        TicTacToeBoard boardMock = Mockito.mock(TicTacToeBoard.class);
        when(boardMock.isCellEmpty(anyInt(), anyInt())).thenReturn(true);
        // Inject this mock into your game if your design supports DI
        game.setBoard(boardMock);
        game.makeMove(0, 0, 'O');
        verify(boardMock, times(1)).isCellEmpty(0, 0);
        log.info("Mockito integration test passed.");
    }
}
