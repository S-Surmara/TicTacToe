package org.example.Command;

import org.example.Entity.Board;
import org.example.Entity.Game;
import org.example.Enums.Symbol;
import org.example.Observer.Player;

public class PlaceMoveCommand implements Command {
    private Game game;
    private int row;
    private int col;
    private Player player;
    private Symbol previousSymbol;

    public PlaceMoveCommand(Game game, int row, int col, Player player) {
        this.game = game;
        this.row = row;
        this.col = col;
        this.player = player;
    }

    @Override
    public void execute() {
        Board board = game.getBoard();
        previousSymbol = board.getCell(row, col).getSymbol();
        board.setCell(row, col, player.getSymbol());
    }

    @Override
    public void undo() {
        Board board = game.getBoard();
        board.setCell(row, col, previousSymbol);
        game.decrementMoves();
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Player getPlayer() {
        return player;
    }
}

