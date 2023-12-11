package com.ludoo.model;


import lombok.Data;

@Data
public class RollDice {

    private String gameId;
    private int diceNumber;
    private boolean theDicer;
    private boolean stuck;
    private String colour;
}
