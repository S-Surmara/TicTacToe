package org.example.Command;

import java.util.Stack;

public class MoveInvoker {
    private Stack<Command> moveHistory;

    public MoveInvoker() {
        this.moveHistory = new Stack<>();
    }

    public void executeCommand(Command command) {
        command.execute();
        moveHistory.push(command);
    }

    public void undoLastMove() {
        if (!moveHistory.isEmpty()) {
            Command lastCommand = moveHistory.pop();
            lastCommand.undo();
        }
    }

    public boolean canUndo() {
        return !moveHistory.isEmpty();
    }

    public int getHistorySize() {
        return moveHistory.size();
    }

    public void clearHistory() {
        moveHistory.clear();
    }
}

