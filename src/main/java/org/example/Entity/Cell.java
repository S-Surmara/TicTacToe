package org.example.Entity;

import org.example.Enums.Symbol;

public class Cell {
    Symbol symbol;

    public Cell(){
        this.symbol = Symbol.EMPTY;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }
}
