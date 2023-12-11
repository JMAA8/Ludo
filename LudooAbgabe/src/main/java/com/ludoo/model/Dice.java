package com.ludoo.model;

import lombok.Data;

import java.util.Random;

@Data
public class Dice {

    private int number;

    public int generatedNumber(){

        Random random = new Random();
        int number = random.nextInt(6) + 1;


        return number;

    }
}
