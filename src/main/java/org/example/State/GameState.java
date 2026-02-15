package org.example.State;

import org.example.Entity.Board;
import org.example.Entity.Game;
import org.example.Observer.Player;
import org.example.Enums.Symbol;
import org.example.Observer.GameObserver;
import org.example.Stratergy.GameWinningStratergy;

import java.util.List;

public abstract class GameState {

    abstract public void registerUser(Game game,String name, Symbol symbol);
    abstract public void placeSymbol(Game game,int row, int col, Player player);
    abstract public void notifyObserver(Game game);

}
