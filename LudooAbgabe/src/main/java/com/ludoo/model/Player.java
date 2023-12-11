package com.ludoo.model;

import lombok.Data;

@Data
public class Player {

    private String login;

    private String colour;
    //private int amountOfRolls;

    private Pawn pawn1;
    private Pawn pawn2;
    private Pawn pawn3;
    private Pawn pawn4;

    private String [] playerMoves = {};


}
