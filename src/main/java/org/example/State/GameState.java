package org.example.State;

import org.example.Entity.Game;
import org.example.Enums.Symbol;
import org.example.Observer.Player;

public abstract class GameState {
    abstract public void registerUser(Game game, String name, Symbol symbol);
    abstract public void placeSymbol(Game game, int row, int col, Player player);
    abstract public void notifyObserver(Game game);
    abstract public void undoMove(Game game); // NEW
}
