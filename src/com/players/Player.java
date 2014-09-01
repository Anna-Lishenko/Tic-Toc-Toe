package com.players;

import com.Field;

public abstract class Player {

    private final char Marking;

    public Player(char marking) {
        this.Marking = marking;
    }

    public char getMarking() {
        return Marking;
    }

    public abstract void nextStep(Field field);
}
