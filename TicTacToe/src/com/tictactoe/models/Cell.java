package com.tictactoe.models;

import com.tictactoe.enums.CellState;

/**
 * Represents a single cell on the game board.
 */
public class Cell {
    private int row;
    private int col;
    private CellState cellState;
    private Player player;

    /**
     * Constructs a new Cell.
     * @param row The row index of the cell.
     * @param col The column index of the cell.
     */
    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.cellState = CellState.EMPTY;
        this.player = null;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public CellState getCellState() {
        return cellState;
    }

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Prints the content of the cell to the console.
     * Displays the player's symbol or "-" if the cell is empty.
     */
    public void print() {
        if (player == null) {
            System.out.print(" - ");
        } else {
            System.out.print(" " + player.getSymbol().getCharacter() + " ");
        }
    }
}
