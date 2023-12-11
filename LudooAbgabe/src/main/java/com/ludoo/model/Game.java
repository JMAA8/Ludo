package com.ludoo.model;

import lombok.Data;

@Data
public class Game {

    private String gameId;

    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;
    private GameStatus status;
    private Player currentPlayer;
    private Dice dice;
    private boolean theDiceRolled;
    private boolean winner;
    private boolean rickRoll;
}
