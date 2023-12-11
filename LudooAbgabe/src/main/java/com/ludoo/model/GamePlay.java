package com.ludoo.model;

import lombok.Data;

@Data
public class GamePlay {

    private String gameId;
    private double positionTop;
    private double positionLeft;
    private boolean theMover;
    private String pawnName;
    private String tossedName;
    private double tossedTop;
    private double tossedLeft;
    private boolean winner;
    private boolean stuck;
    private String winnerName;
    private String colour;
    private int diceNumber;
}
