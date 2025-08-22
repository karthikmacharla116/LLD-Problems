package com.tictactoe.strategies.winning;

import com.tictactoe.models.Board;
import com.tictactoe.models.Move;

/**
 * Interface for defining a strategy to check for a winner.
 */
public interface WinningStrategy {
    /**
     * Checks if the last move resulted in a win.
     * @param board The game board.
     * @param move The last move made.
     * @return True if the move resulted in a win, false otherwise.
     */
    boolean checkWinner(Board board, Move move);

    /**
     * Handles the reversal of a move for stateful strategies.
     * @param board The game board.
     * @param move The move to be undone.
     */
    void handleUndo(Board board, Move move);
}
