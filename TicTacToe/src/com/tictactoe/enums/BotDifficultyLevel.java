package com.tictactoe.enums;

/**
 * Represents the difficulty level of a bot player.
 */
public enum BotDifficultyLevel {
    /**
     * Bot makes random valid moves.
     */
    EASY,

    /**
     * Bot attempts to block opponent's winning moves.
     */
    MEDIUM,

    /**
     * Bot uses an optimal algorithm (e.g., Minimax) to play.
     */
    HARD
}
