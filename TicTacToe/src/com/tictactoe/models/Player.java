package com.tictactoe.models;

import com.tictactoe.enums.PlayerType;

/**
 * Represents a player in the game. This can be a human or a bot.
 */
public abstract class Player {
    private String name;
    private Symbol symbol;
    private PlayerType type;

    /**
     * Constructs a new Player.
     * @param name The name of the player.
     * @param symbol The symbol assigned to the player.
     * @param type The type of the player (HUMAN or BOT).
     */
    public Player(String name, Symbol symbol, PlayerType type) {
        this.name = name;
        this.symbol = symbol;
        this.type = type;
    }

    /**
     * Gets the name of the player.
     * @return The player's name.
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the symbol of the player.
     * @return The player's symbol.
     */
    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    /**
     * Gets the type of the player.
     * @return The player's type.
     */
    public PlayerType getType() {
        return type;
    }

    public void setType(PlayerType type) {
        this.type = type;
    }

    /**
     * Abstract method for a player to make a move.
     * @param board The current game board.
     * @return The Move made by the player.
     */
    public abstract Move makeMove(Board board);
}
