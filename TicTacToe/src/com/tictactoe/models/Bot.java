package com.tictactoe.models;

import com.tictactoe.enums.BotDifficultyLevel;
import com.tictactoe.enums.PlayerType;
import com.tictactoe.factories.BotPlayingStrategyFactory;
import com.tictactoe.strategies.bot.BotPlayingStrategy;

/**
 * Represents a bot player that makes moves automatically based on a strategy.
 */
public class Bot extends Player {
    private BotDifficultyLevel difficultyLevel;
    private BotPlayingStrategy playingStrategy;

    /**
     * Constructs a new Bot player.
     * @param name The name of the bot.
     * @param symbol The symbol assigned to the bot.
     * @param difficultyLevel The difficulty level of the bot.
     */
    public Bot(String name, Symbol symbol, BotDifficultyLevel difficultyLevel) {
        super(name, symbol, PlayerType.BOT);
        this.difficultyLevel = difficultyLevel;
        this.playingStrategy = BotPlayingStrategyFactory.createBotPlayingStrategy(difficultyLevel);
    }

    public BotDifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(BotDifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    /**
     * Makes a move by delegating to its playing strategy.
     * @param board The current game board.
     * @return The Move made by the bot.
     */
    @Override
    public Move makeMove(Board board) {
        System.out.println("Bot is thinking...");
        Cell chosenCell = playingStrategy.makeMove(board);
        return new Move(this, chosenCell);
    }
}
