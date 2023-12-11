package com.ludoo.controller.dto;

import com.ludoo.model.Player;
import lombok.Data;

@Data
public class MoveRequest {

    private Player player;
    private String gameId;
    private double positionLeft;
    private double positionTop;
    private String pawnName;
}
