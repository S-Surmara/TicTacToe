package org.example.Stratergy;

import org.example.Entity.Board;
import org.example.Observer.Player;

public interface GameWinningStratergy {
    boolean checkWinner(Board board, Player player);
}
