package org.example.State;

import org.example.Entity.Game;
import org.example.Enums.Symbol;
import org.example.Observer.Player;

import java.util.List;

public class ReadyState extends GameState{
    public void registerUser(Game game, String name, Symbol symbol){
        List<Player> playerList = game.getPlayerList();
        if(playerList.size() > 2){
            System.out.println("No more player can register");
            return;
        }
        playerList.add(new Player(name,symbol));
        System.out.println("Player registered successfully");
        if(playerList.size() == 2){
            game.setGameState(new InProgressState());
        }
    }
    public void placeSymbol(Game game,int row, int col, Player player){
        System.out.println("Game can't be started without all players");
    }
    public void notifyObserver(Game game){
        System.out.println("Start should be over first to win/lose/draw");
    }
}
