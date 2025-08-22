package com.tictactoe.models;

/**
 * Represents the symbol (e.g., 'X' or 'O') used by a player.
 */
public class Symbol {
    private char character;

    /**
     * Constructs a new Symbol.
     * @param character The character representing the symbol.
     */
    public Symbol(char character) {
        this.character = character;
    }

    /**
     * Gets the character of the symbol.
     * @return The character.
     */
    public char getCharacter() {
        return character;
    }

    /**
     * Sets the character of the symbol.
     * @param character The new character.
     */
    public void setCharacter(char character) {
        this.character = character;
    }
}
