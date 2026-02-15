package org.example.Entity;

import org.example.Command.MoveInvoker;
import org.example.Enums.Symbol;
import org.example.Observer.GameObserver;
import org.example.Observer.Player;
import org.example.State.GameState;
import org.example.Stratergy.GameWinningStratergy;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private GameState gameState;
    private List<GameWinningStratergy> gameWinningStratergy;
    private List<GameObserver> gameObservers;
    private Player winner;
    private Player currentPlayer;
    private List<Player> playerList;
    private int moves;
    private MoveInvoker moveInvoker; // NEW

    public Game(Board board, List<GameWinningStratergy> gameWinningStratergy) {
        this.winner = null;
        this.board = board;
        this.moves = 0;
        this.gameWinningStratergy = gameWinningStratergy;
        this.gameObservers = new ArrayList<>();
        this.playerList = new ArrayList<>();
        this.moveInvoker = new MoveInvoker(); // NEW
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

    // NEW - for undo functionality
    public void decrementMoves() {
        this.moves -= 1;
    }

    // NEW - get move invoker
    public MoveInvoker getMoveInvoker() {
        return moveInvoker;
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

    public boolean determineWin() {
        boolean isWinner = gameWinningStratergy.stream()
                .anyMatch(strategy -> strategy.checkWinner(board, currentPlayer));
        if (isWinner) {
            this.winner = currentPlayer;
        }
        return isWinner;
    }

    public void addGameObservers(GameObserver gameObserver) {
        gameObservers.add(gameObserver);
    }

    public void notifyObservers(String name) {
        gameObservers.forEach((observer) -> observer.notifyObservers(name));
    }

    public void registerUser(String name, Symbol symbol) {
        gameState.registerUser(this, name, symbol);
    }

    public void placeSymbol(int row, int col) {
        gameState.placeSymbol(this, row, col, currentPlayer);
    }

    public void notifyObserver() {
        gameState.notifyObserver(this);
    }

    // NEW - undo move
    public void undoMove() {
        gameState.undoMove(this);
    }
}
