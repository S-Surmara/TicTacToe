package org.example.Stratergy;

import org.example.Entity.Board;
import org.example.Observer.Player;

public class ColumnWinningStratergy implements GameWinningStratergy {

    @Override
    public boolean checkWinner(Board board, Player player) {
        for (int col = 0; col < board.getCols(); col++) {
            boolean colWin = true;
            for (int row = 0; row < board.getRows(); row++) {
                if (board.getCell(row, col).getSymbol() != player.getSymbol()) {
                    colWin = false;
                    break;
                }
            }
            if (colWin) return true;
        }
        return false;
    }
}
