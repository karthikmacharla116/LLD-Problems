package com.tictactoe.exceptions;

public class UndoCommandException extends RuntimeException {
    public UndoCommandException(String message) {
        super(message);
    }
}
