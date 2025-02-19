package com.example.tictactoe;

import lombok.Getter;
import lombok.NonNull;

/**
 * A custom exception class for handling Tic Tac Toe game-specific errors.
 * Extends RuntimeException to provide additional context for game-related issues.
 */
public class TicTacToeException extends RuntimeException {
    /**
     * The error message describing the exception.
     */
    @Getter
    private final String errorMessage;

    /**
     * Constructs a new TicTacToeException with a descriptive error message.
     * 
     * @param message A detailed description of the error that occurred during the game
     */
    public TicTacToeException(@NonNull String message) {
        super(message);
        this.errorMessage = message;
    }

    /**
     * Constructs a new TicTacToeException with a descriptive error message and the underlying cause.
     * 
     * @param message A detailed description of the error that occurred during the game
     * @param cause The original throwable that led to this exception
     */
    public TicTacToeException(@NonNull String message, Throwable cause) {
        super(message, cause);
        this.errorMessage = message;
    }
}