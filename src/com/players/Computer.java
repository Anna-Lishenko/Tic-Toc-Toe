package com.players;

import com.Field;

public class Computer extends Player {

    public Computer(){
        super('0');
    }

    public void nextStep(Field field) {
        startingText();
        if (quickFinish(field)) return;
        if (bestCell(field)) return;
        if (allCell(field)) return;
    }

    private void startingText(){
        System.out.println("Ход компьютера...");
    }

    private boolean quickFinish(Field field){
        if(analysisField(field, getMarking())){
            System.out.println("Конец игры - Компьютер победил !!!");
            field.showField();
            System.exit(0);
        }
        if(analysisField(field, 'X'))  return true;
        return false;
    }

    private boolean analysisField (Field field, char marking) {
        if (inLine(field, marking)) return true;
        if (inColumn(field, marking)) return true;
        if (inDiagonalDown(field, marking)) return true;
        if (inDiagonalUp(field, marking)) return true;
        return false;
    }

    private boolean inLine( Field field, char marking ){
        int point = 0;
        for (int i = 0; i < field.getFieldSize(); i++) {
            point = checkLineAndColumn(field, i, true, marking);
            if (point != -1) {
                field.updateCell(i, point, getMarking());
                return true;
            }
        }
        return false;
    }

    private boolean inColumn( Field field, char marking ){
        int point = 0;
        for (int i = 0; i < field.getFieldSize(); i++) {
            point = checkLineAndColumn(field, i, false, marking);
            if (point != -1) {
                field.updateCell(point, i, getMarking());
                return true;
            }
        }
        return false;
    }

    private boolean inDiagonalDown ( Field field, char marking ) {
        int point = checkDiagonalDown( field, marking );
        if (point != -1) {
            field.updateCell(point, point, getMarking());
            return true;
        }
        return false;
    }

    private boolean inDiagonalUp ( Field field, char marking ) {
        int point = checkDiagonalUp( field, marking );
        if (point != -1) {
            field.updateCell(point, field.getFieldSize() - 1 - point, getMarking());
            return true;
        }
        return false;
    }

    private int checkLineAndColumn(Field field, int objectNumber, boolean isLine, char baseMarking){
        int countBaseMarking = 0, countEmptyMarking = 0, point = -1;
        for (int i = 0; i < field.getFieldSize(); i++) {
            if(lineOrColumn ( field, objectNumber, i, isLine, baseMarking )) {
                ++countBaseMarking;
            } else if ( lineOrColumn ( field, objectNumber, i, isLine, Field.getDefaultValue() )){
                ++countEmptyMarking;
                point = i;
            }
        }
        return ( checkFinishCount(field, countBaseMarking, countEmptyMarking) ? point : -1);
    }

    private int checkDiagonalDown ( Field field, char baseMarking ){
        int countBaseMarking = 0, countEmptyMarking = 0, point = -1;
        for (int i = 0; i < field.getFieldSize(); i++) {
            if(lineOrColumn ( field, i, i, true, baseMarking )) {
                ++countBaseMarking;
            } else if ( lineOrColumn ( field, i, i, true, Field.getDefaultValue() )){
                ++countEmptyMarking;
                point = i;
            }
        }
        return ( checkFinishCount( field, countBaseMarking, countEmptyMarking) ? point : -1 );
    }

    private int checkDiagonalUp ( Field field, char baseMarking ){
        int countBaseMarking = 0, countEmptyMarking = 0, point = -1;
        for (int i = 0, j = field.getFieldSize() - 1 ; i < field.getFieldSize(); i++, j--) {
            if(lineOrColumn ( field, i, j, true, baseMarking )) {
                ++countBaseMarking;
            } else if ( lineOrColumn ( field, i, j, true, Field.getDefaultValue() )){
                ++countEmptyMarking;
                point = i;
            }
        }
        return ( checkFinishCount( field, countBaseMarking, countEmptyMarking) ? point : -1 );
    }

    private boolean lineOrColumn ( Field field, int lineNumber, int columnNumber, boolean isLine, char marking ) {
        if (isLine) {
            return ( field.getField()[lineNumber][columnNumber] == marking );
        } else {
            return ( field.getField()[columnNumber][lineNumber] == marking );
        }
    }

    private boolean checkFinishCount ( Field field, int countBaseMarking, int countEmptyMarking ) {
        return ( (countBaseMarking == (field.getFieldSize() - 1) && countEmptyMarking == 1)  ? true : false);
    }

    private boolean bestCell ( Field field ) {
        for (int i = 0; i < field.getFieldSize() - 1; i+=2) {
            for (int j = 0; j < field.getFieldSize() - 1; j+=2) {
                if(field.getField()[i][j] == Field.getDefaultValue()) {
                    field.updateCell(i, j, getMarking());
                    return true;
                }
            }
        }
        return false;
    }

    private boolean allCell (Field field) {
        for (int i = 0; i < field.getFieldSize() - 1; i++) {
            for (int j = 0; j < field.getFieldSize() - 1; j++) {
                if(field.getField()[i][j] == Field.getDefaultValue()) {
                    field.updateCell(i, j, getMarking());
                    return true;
                }
            }
        }
        return  false;
    }

}