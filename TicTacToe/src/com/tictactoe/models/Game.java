package com.tictactoe.models;

import com.tictactoe.enums.GameState;
import com.tictactoe.enums.PlayerType;
import com.tictactoe.strategies.winning.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents a single game of Tic-Tac-Toe.
 * It holds the state of the game, including the board, players, and game status.
 */
public class Game {
    private Board board;
    private List<Player> players;
    private GameState gameState;
    private Player winner;
    private int nextPlayerIndex;
    private List<Move> moves;
    private List<WinningStrategy> winningStrategies;

    private Game(int dimension, List<Player> players, List<WinningStrategy> winningStrategies) {
        this.board = new Board(dimension);
        this.players = players;
        this.winningStrategies = winningStrategies;
        this.gameState = GameState.IN_PROGRESS;
        this.nextPlayerIndex = 0;
        this.moves = new ArrayList<>();
    }

    /**
     * Returns a new Builder instance to construct a Game object.
     * @return A new Game.Builder.
     */
    public static Builder builder() {
        return new Builder();
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    /**
     * Prints the current state of the game board to the console.
     */
    public void printBoard() {
        this.board.print();
    }

    /**
     * Undoes the last move made in the game.
     * It reverts the board, winning strategy states, player turn, and game state.
     */
    public void undo() {
        if (moves.isEmpty()) {
            System.out.println("No moves to undo.");
            return;
        }

        Move lastMove = moves.get(moves.size() - 1);
        moves.remove(lastMove);

        // Revert the cell
        Cell cell = lastMove.getCell();
        cell.setPlayer(null);
        cell.setCellState(com.tictactoe.enums.CellState.EMPTY);

        // Revert the winning strategies
        for (WinningStrategy strategy : winningStrategies) {
            strategy.handleUndo(board, lastMove);
        }

        // Revert the player turn
        nextPlayerIndex = (nextPlayerIndex - 1 + players.size()) % players.size();

        // Revert game state
        if (gameState != GameState.IN_PROGRESS) {
            gameState = GameState.IN_PROGRESS;
            winner = null;
        }

        System.out.println("Move undone.");
    }

    /**
     * Builder class for constructing a Game object.
     * It uses the Builder pattern to allow for flexible and validated game creation.
     */
    public static class Builder {
        private int dimension;
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        /**
         * Validates the parameters for the game before creation.
         * Ensures player count, symbol uniqueness, and bot count are valid.
         */
        private void validate() {
            if (players.size() != dimension - 1) {
                throw new IllegalArgumentException("Number of players must be dimension - 1");
            }

            Set<Character> symbols = new HashSet<>();
            for (Player player : players) {
                if (symbols.contains(player.getSymbol().getCharacter())) {
                    throw new IllegalArgumentException("Each player must have a unique symbol");
                }
                symbols.add(player.getSymbol().getCharacter());
            }

            int botCount = 0;
            for (Player player : players) {
                if (player.getType().equals(PlayerType.BOT)) {
                    botCount++;
                }
            }

            if (botCount > 1) {
                throw new IllegalArgumentException("There can be at most one bot");
            }
        }

        /**
         * Builds and returns a new Game instance after validating the parameters.
         * @return A new Game object.
         */
        public Game build() {
            validate();
            return new Game(dimension, players, winningStrategies);
        }
    }
}
