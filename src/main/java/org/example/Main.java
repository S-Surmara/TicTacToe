package org.example;

import org.example.Entity.Board;
import org.example.Entity.Game;
import org.example.Enums.Symbol;
import org.example.State.ReadyState;
import org.example.Stratergy.ColumnWinningStratergy;
import org.example.Stratergy.DiagonalWinningStratergy;
import org.example.Stratergy.GameWinningStratergy;
import org.example.Stratergy.RowWinningStratergy;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("╔════════════════════════════════╗");
        System.out.println("║   Welcome to Tic-Tac-Toe!     ║");
        System.out.println("╚════════════════════════════════╝\n");

        // Initialize board
        Board board = new Board(3, 3);

        // Initialize winning strategies
        GameWinningStratergy rowStrategy = new RowWinningStratergy();
        GameWinningStratergy colStrategy = new ColumnWinningStratergy();
        GameWinningStratergy diagStrategy = new DiagonalWinningStratergy();

        // Initialize game
        Game game = new Game(board, Arrays.asList(rowStrategy, colStrategy, diagStrategy));
        game.setGameState(new ReadyState());

        // Register players
        System.out.print("Enter Player 1 name (Symbol X): ");
        String player1Name = scanner.nextLine();
        game.registerUser(player1Name, Symbol.X);

        System.out.print("Enter Player 2 name (Symbol O): ");
        String player2Name = scanner.nextLine();
        game.registerUser(player2Name, Symbol.O);

        board.printBoard();

        System.out.println("Commands: Enter 'row col' to place, or 'u' to undo\n");

        // Game loop
        while (!(game.getGameState() instanceof org.example.State.GameOverState)) {
            try {
                System.out.print("Enter move (row col) or 'u' for undo: ");
                String input = scanner.nextLine().trim();

                // Check for undo command
                if (input.equalsIgnoreCase("u") || input.equalsIgnoreCase("undo")) {
                    game.undoMove();
                    continue;
                }

                // Parse row and col
                String[] parts = input.split("\\s+");
                if (parts.length != 2) {
                    System.out.println("❌ Invalid format! Use: row col (e.g., '0 1') or 'u' for undo\n");
                    continue;
                }

                int row = Integer.parseInt(parts[0]);
                int col = Integer.parseInt(parts[1]);

                if (row < 0 || row >= 3 || col < 0 || col >= 3) {
                    System.out.println("❌ Invalid position! Please enter values between 0-2\n");
                    continue;
                }

                game.placeSymbol(row, col);

            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input! Please enter numbers or 'u' for undo.\n");
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage() + "\n");
            }
        }

        System.out.println("\n╔════════════════════════════════╗");
        System.out.println("║        Game Over!              ║");
        System.out.println("╚════════════════════════════════╝");

        scanner.close();
    }
}