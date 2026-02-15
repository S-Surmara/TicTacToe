package org.example.Entity;

import org.example.Enums.Symbol;
import org.example.Observer.GameObserver;
import org.example.Observer.Player;
import org.example.State.GameState;
import org.example.Stratergy.GameWinningStratergy;

import java.util.List;

public class Game {
    Board board;
    GameState gameState;
    List<GameWinningStratergy> gameWinningStratergy;
    List<GameObserver> gameObservers;

    Player winner;

    Player currentPlayer;

    List<Player> playerList;

    int moves;

    public Game(Board board,List<GameWinningStratergy> gameWinningStratergy){
        this.winner = null;
        this.board = board;
        this.moves = 0;
        this.gameWinningStratergy = gameWinningStratergy;
    }

    public Board getBoard() {
        return board;
    }

    public int getMoves() {
        return this.moves;
    }

    public void setMoves() {
        this.moves += 1;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Player getWinner() {
        return this.winner;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public boolean determineWin(){
        boolean isWinner = gameWinningStratergy.stream()
                .anyMatch(strategy -> strategy.checkWinner(board, currentPlayer));
        this.winner = currentPlayer;
        return isWinner;
    }

    public void addGameObservers(GameObserver gameObserver){
        gameObservers.add(gameObserver);
    }

    public void notifyObservers(String name){
        gameObservers.forEach((observer) -> observer.notifyObservers(name));
    }

    public void registerUser(Game game,String name, Symbol symbol) {
        gameState.registerUser(game,name,symbol);
    }

    public void placeSymbol(Game game,int row, int col, Player player){
        gameState.placeSymbol(game,row,col,currentPlayer);
    }

    public void notifyObserver(Game game){
        gameState.notifyObserver(game);
    }
}
