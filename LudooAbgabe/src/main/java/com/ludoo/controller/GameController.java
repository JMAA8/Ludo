package com.ludoo.controller;


import com.ludoo.controller.dto.MoveRequest;
import com.ludoo.controller.dto.connectRequest;
import com.ludoo.exceptions.InvalidGameException;
import com.ludoo.exceptions.InvalidParamException;
import com.ludoo.model.*;
import com.ludoo.service.GameService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping("/start")
    public ResponseEntity<Game> start(@RequestBody Player player){
        log.info("start game request:{}", player);
        return ResponseEntity.ok(gameService.createGame(player));
    }


    @PostMapping("/connect")
    public ResponseEntity<Connect> connect (@RequestBody connectRequest request) throws InvalidParamException, InvalidGameException {
        log.info("connect request {}", request);
        return ResponseEntity.ok(gameService.connectToGame(request.getPlayer(), request.getGameId()));
    }

    @PostMapping("/rollDice")
    public ResponseEntity<RollDice> rollDice (@RequestBody connectRequest request) throws InvalidParamException {
        log.info("connect request {}", request);
        RollDice rollDice = gameService.rollDice(request.getPlayer(), request.getGameId());
        simpMessagingTemplate.convertAndSend("/topic/game-progress/" + rollDice.getGameId() , rollDice);
        return ResponseEntity.ok(rollDice);
    }

    @PostMapping("/movePawn")
    public ResponseEntity<GamePlay> movePawn (@RequestBody MoveRequest request){
        log.info("Move Request {}", request);
        GamePlay gamePlay = gameService.gamePlay(request.getPlayer(), request.getGameId(), request.getPositionTop(), request.getPositionLeft(), request.getPawnName());
        simpMessagingTemplate.convertAndSend("/topic/game-progress/" + gamePlay.getGameId() , gamePlay);
        return ResponseEntity.ok(gamePlay);
    }





}
