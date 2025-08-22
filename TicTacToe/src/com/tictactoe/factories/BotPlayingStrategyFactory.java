package com.tictactoe.factories;

import com.tictactoe.enums.BotDifficultyLevel;
import com.tictactoe.strategies.bot.BotPlayingStrategy;
import com.tictactoe.strategies.bot.EasyBotPlayingStrategy;
import com.tictactoe.strategies.bot.HardBotPlayingStrategy;
import com.tictactoe.strategies.bot.MediumBotPlayingStrategy;

/**
 * Factory class for creating bot playing strategies.
 */
public class BotPlayingStrategyFactory {
    /**
     * Creates a bot playing strategy based on the given difficulty level.
     * @param difficultyLevel The difficulty level of the bot.
     * @return A BotPlayingStrategy instance.
     */
    public static BotPlayingStrategy createBotPlayingStrategy(BotDifficultyLevel difficultyLevel) {
        switch (difficultyLevel) {
            case EASY:
                return new EasyBotPlayingStrategy();
            case MEDIUM:
                return new MediumBotPlayingStrategy();
            case HARD:
                return new HardBotPlayingStrategy();
            default:
                throw new IllegalArgumentException("Invalid bot difficulty level");
        }
    }
}
