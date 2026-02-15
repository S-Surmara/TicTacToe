package org.example.State;

import org.example.Entity.Board;
import org.example.Entity.Cell;
import org.example.Entity.Game;
import org.example.Enums.Symbol;
import org.example.Observer.Player;

public class InProgressState extends GameState{
    public void registerUser(Game game,String name, Symbol symbol){
        System.out.println("Game in progress can't register anymore users");
    }
    public void placeSymbol(Game game,int row, int col, Player player){
        Board board = game.getBoard();
        if(board.getCell(row,col).getSymbol() != Symbol.EMPTY){
            System.out.println("Invalid cell , already occupied");
            return;
        }
        board.setCell(row,col,player.getSymbol());
        System.out.println("placed symbol successfully");
        boolean wonGame = game.determineWin();
        if(wonGame || game.getMoves() == game.getBoard().getSize()){
            game.setGameState(new GameOverState());
            game.notifyObserver(game);
        }
        game.setMoves();
    }
    public void notifyObserver(Game game){
        Player otherPlayer = game.getPlayerList().stream()
                .filter(p -> !p.equals(game.getCurrentPlayer()))
                .findFirst()
                .orElse(null);
        System.out.println("Provide your next move player: "+ otherPlayer);
        game.setCurrentPlayer(otherPlayer);
    }
}
