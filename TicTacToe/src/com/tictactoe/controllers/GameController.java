package com.tictactoe.controllers;

import com.tictactoe.enums.CellState;
import com.tictactoe.exceptions.UndoCommandException;
import com.tictactoe.models.Game;
import com.tictactoe.models.Player;
import com.tictactoe.models.Move;
import com.tictactoe.enums.GameState;
import com.tictactoe.strategies.winning.WinningStrategy;

import java.util.List;

/**
 * Manages the overall flow and state of a Tic-Tac-Toe game.
 */
public class GameController {
    private Game game;

    /**
     * Creates and initializes a new game.
     * @param dimension The dimension of the game board.
     * @param players The list of players.
     * @param winningStrategies The list of winning strategies to be used.
     * @return The newly created Game object.
     */
    public Game createGame(int dimension, List<Player> players, List<WinningStrategy> winningStrategies) {
        this.game = Game.builder()
                .setDimension(dimension)
                .setPlayers(players)
                .setWinningStrategies(winningStrategies)
                .build();
        return this.game;
    }

    /**
     * Prints the current state of the game board.
     */
    public void printBoard() {
        game.printBoard();
    }

    /**
     * Starts and manages the main game loop.
     * The loop continues until the game is won, drawn, or the user quits.
     */
    public void run() {
        while (game.getGameState() == GameState.IN_PROGRESS) {
            printBoard();

            int nextPlayerIndex = game.getNextPlayerIndex();
            Player currentPlayer = game.getPlayers().get(nextPlayerIndex);

            Move move;
            try {
                do {
                    System.out.println("It's " + currentPlayer.getName() + "'s turn.");
                    move = currentPlayer.makeMove(game.getBoard());
                } while (!validateMove(move));
            } catch (UndoCommandException e) {
                game.undo();
                continue; // Skip the rest of the loop and start the next turn
            }


            // Update board and game state
            updateBoard(move);
            game.getMoves().add(move);
            game.setNextPlayerIndex((nextPlayerIndex + 1) % game.getPlayers().size());

            if (checkWinner(move)) {
                game.setGameState(GameState.ENDED);
                game.setWinner(currentPlayer);
                System.out.println("Player " + currentPlayer.getName() + " wins!");
            } else if (checkDraw()) {
                game.setGameState(GameState.DRAW);
                System.out.println("The game is a draw!");
            }
        }
        printBoard(); // Print the final board state
    }

    private boolean validateMove(Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        int size = game.getBoard().getSize();

        if (row < 0 || row >= size || col < 0 || col >= size) {
            System.out.println("Invalid coordinates. Please enter a valid row and column between 0 and " + (size - 1));
            return false;
        }

        if (game.getBoard().getGrid().get(row).get(col).getCellState() != CellState.EMPTY) {
            System.out.println("Cell is already occupied. Please choose an empty cell.");
            return false;
        }

        return true;
    }

    private void updateBoard(Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        game.getBoard().getGrid().get(row).get(col).setPlayer(move.getPlayer());
        game.getBoard().getGrid().get(row).get(col).setCellState(CellState.FILLED);
    }

    private boolean checkWinner(Move move) {
        for (WinningStrategy strategy : game.getWinningStrategies()) {
            if (strategy.checkWinner(game.getBoard(), move)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDraw() {
        return game.getMoves().size() == game.getBoard().getSize() * game.getBoard().getSize();
    }

}
