package org.example.Entity;

import org.example.Enums.Symbol;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<List<Cell>> board;
    private int rows;
    private int cols;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.board = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            List<Cell> row = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                row.add(new Cell());
            }
            board.add(row);
        }
    }

    public int getSize() {
        return rows;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public Cell getCell(int row, int col) {
        return board.get(row).get(col);
    }

    public void setCell(int row, int col, Symbol symbol) {
        board.get(row).get(col).setSymbol(symbol);
    }

    public void printBoard() {
        System.out.println("\nCurrent Board:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Symbol sym = getCell(i, j).getSymbol();
                if (sym == Symbol.EMPTY) {
                    System.out.print(" . ");
                } else {
                    System.out.print(" " + sym + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
