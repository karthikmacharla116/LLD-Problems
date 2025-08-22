package com.tictactoe.models;

/**
 * Represents a single move made by a player.
 */
public class Move {
    private Player player;
    private Cell cell;

    /**
     * Constructs a new Move.
     * @param player The player who made the move.
     * @param cell The cell where the move was made.
     */
    public Move(Player player, Cell cell) {
        this.player = player;
        this.cell = cell;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }
}
