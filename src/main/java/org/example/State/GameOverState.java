package org.example.State;

import org.example.Entity.Game;
import org.example.Enums.Symbol;
import org.example.Observer.Player;

public class GameOverState extends GameState {

    public void registerUser(Game game, String name, Symbol symbol) {
        System.out.println("Game over, can't register anymore users");
    }

    public void placeSymbol(Game game, int row, int col, Player player) {
        System.out.println("Game over, can't place anymore symbols");
    }

    public void notifyObserver(Game game) {
        game.notifyObservers(game.getWinner().getName());
    }

    @Override
    public void undoMove(Game game) {
        System.out.println("❌ Game is over. Cannot undo!");
    }
}
