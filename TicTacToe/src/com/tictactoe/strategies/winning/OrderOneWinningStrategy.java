package com.tictactoe.strategies.winning;

import com.tictactoe.models.Board;
import com.tictactoe.models.Move;
import com.tictactoe.models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class OrderOneWinningStrategy implements WinningStrategy {
    private final Map<Integer, Map<Character, Integer>> rowSymbolCounts = new HashMap<>();
    private final Map<Integer, Map<Character, Integer>> colSymbolCounts = new HashMap<>();
    private final Map<Character, Integer> mainDiagSymbolCounts = new HashMap<>();
    private final Map<Character, Integer> antiDiagSymbolCounts = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        char symbol = move.getPlayer().getSymbol().getCharacter();
        int size = board.getSize();

        // Update and check row
        rowSymbolCounts.putIfAbsent(row, new HashMap<>());
        Map<Character, Integer> rowMap = rowSymbolCounts.get(row);
        rowMap.put(symbol, rowMap.getOrDefault(symbol, 0) + 1);
        if (rowMap.get(symbol) == size) {
            return true;
        }

        // Update and check column
        colSymbolCounts.putIfAbsent(col, new HashMap<>());
        Map<Character, Integer> colMap = colSymbolCounts.get(col);
        colMap.put(symbol, colMap.getOrDefault(symbol, 0) + 1);
        if (colMap.get(symbol) == size) {
            return true;
        }

        // Update and check main diagonal
        if (row == col) {
            mainDiagSymbolCounts.put(symbol, mainDiagSymbolCounts.getOrDefault(symbol, 0) + 1);
            if (mainDiagSymbolCounts.get(symbol) == size) {
                return true;
            }
        }

        // Update and check anti-diagonal
        if (row + col == size - 1) {
            antiDiagSymbolCounts.put(symbol, antiDiagSymbolCounts.getOrDefault(symbol, 0) + 1);
            if (antiDiagSymbolCounts.get(symbol) == size) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void handleUndo(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        char symbol = move.getPlayer().getSymbol().getCharacter();
        int size = board.getSize();

        // Decrement row count
        Map<Character, Integer> rowMap = rowSymbolCounts.get(row);
        rowMap.put(symbol, rowMap.get(symbol) - 1);

        // Decrement column count
        Map<Character, Integer> colMap = colSymbolCounts.get(col);
        colMap.put(symbol, colMap.get(symbol) - 1);

        // Decrement main diagonal count
        if (row == col) {
            mainDiagSymbolCounts.put(symbol, mainDiagSymbolCounts.get(symbol) - 1);
        }

        // Decrement anti-diagonal count
        if (row + col == size - 1) {
            antiDiagSymbolCounts.put(symbol, antiDiagSymbolCounts.get(symbol) - 1);
        }
    }
}
