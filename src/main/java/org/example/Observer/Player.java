package org.example.Observer;

import org.example.Enums.Symbol;

public class Player implements GameObserver{
    String name;
    Symbol symbol;

    public Player(String name,Symbol symbol){
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    @Override
    public void notifyObservers(String name) {
        System.out.println("Winner is " + name);
    }
}
