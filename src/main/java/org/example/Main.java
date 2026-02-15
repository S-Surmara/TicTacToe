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

        // Game loop
        while (!(game.getGameState() instanceof org.example.State.GameOverState)) {
            try {
                System.out.print("Enter row (0-2): ");
                int row = scanner.nextInt();

                System.out.print("Enter col (0-2): ");
                int col = scanner.nextInt();

                if (row < 0 || row >= 3 || col < 0 || col >= 3) {
                    System.out.println("❌ Invalid position! Please enter values between 0-2\n");
                    continue;
                }

                game.placeSymbol(row, col);

            } catch (Exception e) {
                System.out.println("❌ Invalid input! Please enter numbers only.\n");
                scanner.nextLine(); // Clear buffer
            }
        }

        System.out.println("\n╔════════════════════════════════╗");
        System.out.println("║        Game Over!              ║");
        System.out.println("╚════════════════════════════════╝");

        scanner.close();
    }
}