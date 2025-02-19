package com.example.tictactoe;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class LoggingTest {

    private ListAppender<ILoggingEvent> listAppender;
    private Logger logger;

    @BeforeEach
    void setup() {
        logger = (Logger) LoggerFactory.getLogger(TicTacToeBoard.class);
        listAppender = new ListAppender<>();
        listAppender.start();
        logger.addAppender(listAppender);
    }

    @Test
    void testInvalidMoveLogging() {
        TicTacToeBoard board = new TicTacToeBoard();
        
        // Make an initial valid move
        board.makeMove(0, 0, 'X');
        
        // Try to make a move in an already occupied cell
        board.makeMove(0, 0, 'O');

        // Check logged events
        assertThat(listAppender.list)
            .isNotEmpty()
            .anyMatch(event -> 
                event.getLevel() == Level.WARN && 
                event.getMessage().contains("Invalid move")
            );
    }

    @Test
    void testGameStateLogging() {
        TicTacToeBoard board = new TicTacToeBoard();
        
        // Make moves to trigger game state logging
        board.makeMove(0, 0, 'X');
        board.makeMove(1, 1, 'O');
        board.makeMove(0, 1, 'X');
        board.makeMove(1, 0, 'O');
        board.makeMove(0, 2, 'X');

        // Check for win logging
        assertThat(listAppender.list)
            .isNotEmpty()
            .anyMatch(event -> 
                event.getLevel() == Level.INFO && 
                event.getMessage().contains("Player X wins")
            );
    }
} 