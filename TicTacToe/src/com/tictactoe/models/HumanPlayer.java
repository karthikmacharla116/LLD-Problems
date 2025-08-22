package com.tictactoe.models;

import com.tictactoe.enums.PlayerType;
import com.tictactoe.exceptions.UndoCommandException;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Represents a human player who makes moves via console input.
 */
public class HumanPlayer extends Player {
    private Scanner scanner;

    /**
     * Constructs a new HumanPlayer.
     * @param name The name of the player.
     * @param symbol The symbol assigned to the player.
     */
    public HumanPlayer(String name, Symbol symbol) {
        super(name, symbol, PlayerType.HUMAN);
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prompts the user for input to make a move.
     * Reads row and column from the console. Also handles the "undo" command.
     * @param board The current game board.
     * @return The Move made by the player.
     * @throws UndoCommandException if the player types "undo".
     */
    @Override
    public Move makeMove(Board board) {
        System.out.println(this.getName() + ", it's your turn. Enter row and column, or type 'undo' to reverse the last move:");
        try {
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            Cell cell = new Cell(row, col);
            cell.setPlayer(this);
            return new Move(this, cell);
        } catch (InputMismatchException e) {
            String input = scanner.next();
            if (input.equalsIgnoreCase("undo")) {
                throw new UndoCommandException("Undo command issued by player");
            } else {
                System.out.println("Invalid input. Please try again.");
                // Recursive call to try again
                return makeMove(board);
            }
        }
    }
}
