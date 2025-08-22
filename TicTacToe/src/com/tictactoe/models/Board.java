package com.tictactoe.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the game board.
 */
public class Board {
    private int size;
    private List<List<Cell>> grid;

    /**
     * Constructs a new Board of a given size.
     * @param size The dimension of the square board.
     */
    public Board(int size) {
        this.size = size;
        this.grid = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            grid.add(new ArrayList<>());
            for (int j = 0; j < size; j++) {
                grid.get(i).add(new Cell(i, j));
            }
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<List<Cell>> getGrid() {
        return grid;
    }

    public void setGrid(List<List<Cell>> grid) {
        this.grid = grid;
    }

    /**
     * Prints the current state of the board to the console.
     */
    public void print() {
        for (List<Cell> row : grid) {
            for (Cell cell : row) {
                cell.print();
            }
            System.out.println();
        }
    }
}
