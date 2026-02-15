package org.example.Stratergy;

import org.example.Entity.Board;
import org.example.Observer.Player;

public class DiagonalWinningStratergy implements GameWinningStratergy {

    @Override
    public boolean checkWinner(Board board, Player player) {
        // Main diagonal
        boolean mainDiagWin = true;
        for (int i = 0; i < board.getRows(); i++) {
            if (board.getCell(i, i).getSymbol() != player.getSymbol()) {
                mainDiagWin = false;
                break;
            }
        }
        if (mainDiagWin) return true;

        // Anti-diagonal
        boolean antiDiagWin = true;
        for (int i = 0; i < board.getRows(); i++) {
            if (board.getCell(i, board.getCols() - 1 - i).getSymbol() != player.getSymbol()) {
                antiDiagWin = false;
                break;
            }
        }
        return antiDiagWin;
    }
}
