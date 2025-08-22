package com.tictactoe;

import com.tictactoe.controllers.GameController;
import com.tictactoe.enums.BotDifficultyLevel;
import com.tictactoe.models.Bot;
import com.tictactoe.models.HumanPlayer;
import com.tictactoe.models.Player;
import com.tictactoe.models.Symbol;
import com.tictactoe.strategies.winning.OrderOneWinningStrategy;
import com.tictactoe.strategies.winning.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameController gameController = new GameController();

        System.out.println("Welcome to Tic-Tac-Toe!");

        System.out.print("Enter the dimension of the board: ");
        int dimension = scanner.nextInt();

        List<Player> players = new ArrayList<>();
        Set<Character> usedSymbols = new HashSet<>();
        int numberOfPlayers = dimension - 1;

        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.println("Configuring Player " + (i + 1) + ":");
            System.out.print("Enter name: ");
            String name = scanner.next();

            char symbolChar;
            do {
                System.out.print("Enter a unique symbol (single character): ");
                symbolChar = scanner.next().charAt(0);
                if (usedSymbols.contains(symbolChar)) {
                    System.out.println("Symbol is already taken. Please choose another.");
                }
            } while (usedSymbols.contains(symbolChar));
            usedSymbols.add(symbolChar);
            Symbol symbol = new Symbol(symbolChar);

            System.out.print("Is this player a bot? (y/n): ");
            boolean isBot = scanner.next().equalsIgnoreCase("y");

            if (isBot) {
                System.out.print("Enter bot difficulty (EASY, MEDIUM, HARD): ");
                String difficultyStr = scanner.next().toUpperCase();
                BotDifficultyLevel difficulty = BotDifficultyLevel.valueOf(difficultyStr);
                players.add(new Bot(name, symbol, difficulty));
            } else {
                players.add(new HumanPlayer(name, symbol));
            }
        }

        List<WinningStrategy> winningStrategies = new ArrayList<>();
        winningStrategies.add(new OrderOneWinningStrategy());

        System.out.println("Starting the game...");
        gameController.createGame(dimension, players, winningStrategies);
        gameController.run();
    }
}
