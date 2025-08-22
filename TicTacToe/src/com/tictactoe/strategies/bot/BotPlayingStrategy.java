package com.tictactoe.strategies.bot;

import com.tictactoe.models.Board;
import com.tictactoe.models.Cell;

/**
 * Interface for defining a bot's playing strategy.
 */
public interface BotPlayingStrategy {
    /**
     * Determines the next move for a bot.
     * @param board The current game board.
     * @return The Cell where the bot has decided to move.
     */
    Cell makeMove(Board board);
}
