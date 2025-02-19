package com.example.tictactoe;

public class TicTacToeException extends RuntimeException {
    public TicTacToeException(String message) {
        super(message);
    }

    public TicTacToeException(String message, Throwable cause) {
        super(message, cause);
    }
} 