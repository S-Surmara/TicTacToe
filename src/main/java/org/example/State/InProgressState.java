package org.example.State;

import org.example.Command.PlaceMoveCommand;
import org.example.Entity.Board;
import org.example.Entity.Game;
import org.example.Enums.Symbol;
import org.example.Observer.Player;

public class InProgressState extends GameState {

    public void registerUser(Game game, String name, Symbol symbol) {
        System.out.println("Game in progress, can't register anymore users");
    }

    public void placeSymbol(Game game, int row, int col, Player player) {
        Board board = game.getBoard();

        // Validate cell is empty
        if (board.getCell(row, col).getSymbol() != Symbol.EMPTY) {
            System.out.println("Invalid cell, already occupied. Try again!");
            return;
        }

        // Create and execute command
        PlaceMoveCommand command = new PlaceMoveCommand(game, row, col, game.getCurrentPlayer());
        game.getMoveInvoker().executeCommand(command);

        System.out.println(game.getCurrentPlayer().getName() + " placed " + game.getCurrentPlayer().getSymbol());
        board.printBoard();

        // Increment move count
        game.setMoves();

        // Check if current player won
        boolean wonGame = game.determineWin();

        if (wonGame) {
            // Game over - current player wins
            game.setGameState(new GameOverState());
            game.notifyObserver();
        } else if (game.getMoves() == board.getSize() * board.getSize()) {
            // Game over - draw
            System.out.println("Game is a Draw!");
            game.setGameState(new GameOverState());
        } else {
            // Switch to other player
            switchPlayer(game);
        }
    }

    private void switchPlayer(Game game) {
        Player currentPlayer = game.getCurrentPlayer();
        Player nextPlayer = game.getPlayerList().stream()
                .filter(p -> !p.equals(currentPlayer))
                .findFirst()
                .orElse(null);

        game.setCurrentPlayer(nextPlayer);
        System.out.println("\n" + nextPlayer.getName() + "'s turn (Symbol: " + nextPlayer.getSymbol() + ")");
    }

    public void notifyObserver(Game game) {
        // This is called when switching players
    }

    @Override
    public void undoMove(Game game) {
        if (!game.getMoveInvoker().canUndo()) {
            System.out.println("❌ No moves to undo!");
            return;
        }

        // Undo the command
        game.getMoveInvoker().undoLastMove();

        // Switch back to previous player
        switchPlayer(game);

        System.out.println("✓ Move undone successfully!");
        game.getBoard().printBoard();
    }
}
