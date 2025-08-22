package com.tictactoe.strategies.bot;

import com.tictactoe.models.Board;
import com.tictactoe.models.Cell;
import com.tictactoe.enums.CellState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HardBotPlayingStrategy implements BotPlayingStrategy {
    @Override
    public Cell makeMove(Board board) {
        // For now, Hard difficulty is the same as Easy.
        // A proper implementation would use an algorithm like Minimax
        // to determine the optimal move.
        List<Cell> emptyCells = new ArrayList<>();
        for (List<Cell> row : board.getGrid()) {
            for (Cell cell : row) {
                if (cell.getCellState() == CellState.EMPTY) {
                    emptyCells.add(cell);
                }
            }
        }

        if (emptyCells.isEmpty()) {
            return null;
        }

        Random random = new Random();
        int randomIndex = random.nextInt(emptyCells.size());
        return emptyCells.get(randomIndex);
    }
}
