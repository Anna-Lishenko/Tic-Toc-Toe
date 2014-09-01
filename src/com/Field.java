package com;

public class Field {

    public static final int MIN_FIELD_SIZE = 0;
    private static final int MAX_FIELD_SIZE = 100;
    private static final int DEFAULT_FIELD_SIZE = 3;

    private static final char DEFAULT_VALUE = ' ';

    private int fieldSize;
    private char[][] field;

    public Field() {
        this(DEFAULT_FIELD_SIZE);
    }

    public Field(int fieldSize) {
        if(fieldSize > MIN_FIELD_SIZE && fieldSize < MAX_FIELD_SIZE) {
            this.fieldSize = fieldSize;
            field = new char[fieldSize][fieldSize];
            clearField();
        }
    }

    public static char getDefaultValue() {
        return DEFAULT_VALUE;
    }

    public int getFieldSize() {
        return fieldSize;
    }

    public void setField(char[][] field) {
        this.field = field;
    }
    public char[][] getField() {
        return field;
    }

    public void updateCell(int x, int y, char newValue) {
        field[x][y] = newValue;
    }

    private void clearCell ( int x, int y){
        field[x][y] = DEFAULT_VALUE;
    }
    private void clearLine(int lineNumber) {
        for (int i = 0; i < fieldSize; i++) {
            clearCell(lineNumber, i);
        }
    }
    public void clearField() {
        for (int i = 0; i < fieldSize; i++) {
            clearLine(i);
        }
    }


    private void showCell(int x, int y) {
        System.out.print("[" + field[x][y] + "]");
    }
    private void showLine(int lineNumber) {
        for (int i = 0; i < fieldSize; i++) {
            showCell(lineNumber, i);
        }
        System.out.println();
    }
    public void showField() {
        for (int i = 0; i < fieldSize; i++) {
            showLine(i);
        }
    }

    public boolean isEmptyCell (int indexI, int indexJ) {
        if ( this.getField()[indexI][indexJ] == this.DEFAULT_VALUE ) {
            return true;
        }
        return false;
    }
}
