package com.ludoo.model;

import lombok.Data;
import org.springframework.context.annotation.Bean;

@Data
public class Pawn {
    private double positionTop;
    private double positionLeft;
    private boolean onBoard;
    private int moveStatus;

}
