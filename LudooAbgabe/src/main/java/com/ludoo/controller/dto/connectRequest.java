package com.ludoo.controller.dto;

import com.ludoo.model.Player;
import lombok.Data;

@Data
public class connectRequest {
    private Player player;
    private String gameId;
}
