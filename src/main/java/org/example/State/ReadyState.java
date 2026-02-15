package org.example.State;

import org.example.Entity.Game;
import org.example.Enums.Symbol;
import org.example.Observer.Player;
import java.util.List;

public class ReadyState extends GameState {

    public void registerUser(Game game, String name, Symbol symbol) {
        List<Player> playerList = game.getPlayerList();
        if (playerList.size() >= 2) {
            System.out.println("No more players can register");
            return;
        }

        Player newPlayer = new Player(name, symbol);
        playerList.add(newPlayer);
        game.addGameObservers(newPlayer);
        System.out.println("Player " + name + " registered successfully");

        if (playerList.size() == 2) {
            game.setGameState(new InProgressState());
            game.setCurrentPlayer(playerList.get(0));
            System.out.println("\nGame Started! " + game.getCurrentPlayer().getName() + "'s turn (Symbol: " + game.getCurrentPlayer().getSymbol() + ")");
        }
    }

    public void placeSymbol(Game game, int row, int col, Player player) {
        System.out.println("Game can't be started without all players");
    }

    public void notifyObserver(Game game) {
        System.out.println("Game should be started first to win/lose/draw");
    }

    @Override
    public void undoMove(Game game) {
        System.out.println("❌ Game hasn't started yet. Nothing to undo!");
    }
}
