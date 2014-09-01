package com.players;

import com.Field;
import com.exception.EnterIndexException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Human extends Player {

    public Human(){
        super('X');
    }

    public void nextStep(Field field) {
        Question();
        input2Indexes(field);
    }

    private static void Question() {
        System.out.println("Сделайте ход, введите индексы: ");
    }

    private int inputIndex(Integer index, Field field) throws EnterIndexException {
        if( index >= field.getFieldSize() ||  index < Field.MIN_FIELD_SIZE) {
            throw new EnterIndexException( index);
        }
        return  index;
    }

    private static void MessageError () {
        System.out.println("Ошибка при вводе индексов" + "\n" + "Введите индексы еще раз");
    }

    private void input2Indexes(Field field) {
        Integer inputIndexI = null, inputIndexJ = null;
        InputStreamReader inputStreamReader = new InputStreamReader( System.in );
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        while (true) {
            try {
                System.out.println("Введите I: ");
                inputIndexI = Integer.parseInt(bufferedReader.readLine());
                System.out.println("Введите J: ");
                inputIndexJ = Integer.parseInt(bufferedReader.readLine());
                if ( field.isEmptyCell(inputIndexI, inputIndexJ) ) {
                    field.updateCell(inputIndex(inputIndexI, field), inputIndex(inputIndexJ, field), getMarking());
                    break;
                } else {
                    MessageError();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (EnterIndexException e) {
                MessageError();
            }
        }
    }

}
