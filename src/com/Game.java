package com;

import com.players.Computer;
import com.players.Human;
import com.players.Player;

public class Game {

    private Field field;
    private Player human;
    private Player computer;

    public Game() {
        this.field = new Field();
        this.human = new Human();
        this.computer = new Computer();
    }

    public void startingGame(){
        System.out.println("START GAME!!!");
        field.showField();

        while(true) {
            // Ход человека
            human.nextStep(field);
            field.showField();

            // Ход компьютера
            computer.nextStep(field);
            field.showField();
        }
    }


}
