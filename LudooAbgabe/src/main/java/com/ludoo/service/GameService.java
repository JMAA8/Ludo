package com.ludoo.service;

import com.ludoo.exceptions.InvalidGameException;
import com.ludoo.exceptions.InvalidParamException;
import com.ludoo.model.*;
import com.ludoo.storage.GameStorage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.ludoo.model.GameStatus.*;

@Service
@AllArgsConstructor
@Slf4j
public class GameService {

    private final double step = 49.5;

    public Game createGame(Player player) {

        Game game = new Game();
        Dice dice  = new Dice();

        game.setGameId(UUID.randomUUID().toString());
        log.info("Start game with gameId:{}", game.getGameId());
        game.setDice(dice);


        //Initialisieren des Players
        game.setPlayer1(player);
        game.getPlayer1().setColour("red");
        game.getPlayer1().setPlayerMoves(new String[]{"StepDown", "StepDown", "StepDown", "StepDown",
                "StepRight", "StepRight", "StepRight", "StepRight",
                "StepDown", "StepDown",
                "StepLeft", "StepLeft", "StepLeft", "StepLeft",
                "StepDown", "StepDown", "StepDown", "StepDown",
                "StepLeft", "StepLeft",
                "StepUp", "StepUp", "StepUp", "StepUp",
                "StepLeft", "StepLeft", "StepLeft", "StepLeft",
                "StepUp", "StepUp",
                "StepRight", "StepRight", "StepRight", "StepRight",
                "StepUp", "StepUp", "StepUp", "StepUp",
                "StepRight",
                "StepDown", "StepDown", "StepDown", "StepDown",
                "StepDown"});


        //Initialisieren der Pawns

        Pawn redpawn1 = new Pawn();
        Pawn redpawn2 = new Pawn();
        Pawn redpawn3 = new Pawn();
        Pawn redpawn4 = new Pawn();

        game.getPlayer1().setPawn1(redpawn1);
        game.getPlayer1().setPawn2(redpawn2);
        game.getPlayer1().setPawn3(redpawn3);
        game.getPlayer1().setPawn4(redpawn4);

        game.getPlayer1().getPawn1().setOnBoard(false);
        game.getPlayer1().getPawn2().setOnBoard(false);
        game.getPlayer1().getPawn3().setOnBoard(false);
        game.getPlayer1().getPawn4().setOnBoard(false);

        //Game initialisieren

        game.setRickRoll(true);
        game.setTheDiceRolled(false);
        game.setStatus(NEW);
        game.setWinner(false);
        GameStorage.getInstance().setGame(game);

        return game;
    }

    public Connect connectToGame(Player player234, String gameId) throws InvalidParamException, InvalidGameException {

        Connect connect = new Connect();
        if (!GameStorage.getInstance().getGames().containsKey(gameId)) {
            throw new InvalidParamException("Game with provided Id doesn't exist");
        }

        Game game = GameStorage.getInstance().getGames().get(gameId);

        //Player in Game befüllen und initialisieren

        if (game.getPlayer2() == null) {
            game.setPlayer2(player234);
            game.getPlayer2().setColour("blue");
            game.getPlayer2().setPlayerMoves(new String[]{"StepLeft", "StepLeft", "StepLeft", "StepLeft",
                    "StepDown", "StepDown", "StepDown", "StepDown",
                    "StepLeft", "StepLeft",
                    "StepUp", "StepUp", "StepUp", "StepUp",
                    "StepLeft", "StepLeft", "StepLeft", "StepLeft",
                    "StepUp", "StepUp",
                    "StepRight", "StepRight", "StepRight", "StepRight",
                    "StepUp", "StepUp", "StepUp", "StepUp",
                    "StepRight", "StepRight",
                    "StepDown", "StepDown", "StepDown", "StepDown",
                    "StepRight", "StepRight", "StepRight", "StepRight",
                    "StepDown",
                    "StepLeft", "StepLeft", "StepLeft", "StepLeft",
                    "StepLeft"});

            Pawn bluepawn1 = new Pawn();
            Pawn bluepawn2 = new Pawn();
            Pawn bluepawn3 = new Pawn();
            Pawn bluepawn4 = new Pawn();

            game.getPlayer2().setPawn1(bluepawn1);
            game.getPlayer2().setPawn2(bluepawn2);
            game.getPlayer2().setPawn3(bluepawn3);
            game.getPlayer2().setPawn4(bluepawn4);

            game.getPlayer2().getPawn1().setOnBoard(false);
            game.getPlayer2().getPawn2().setOnBoard(false);
            game.getPlayer2().getPawn3().setOnBoard(false);
            game.getPlayer2().getPawn4().setOnBoard(false);

            connect.setGameId(game.getGameId());
            connect.setColour(game.getPlayer2().getColour());


        } else if (game.getPlayer3() == null) {

            game.setPlayer3(player234);
            game.getPlayer3().setColour("yellow");
            game.getPlayer3().setPlayerMoves(new String[]{"StepUp", "StepUp", "StepUp", "StepUp",
                    "StepLeft", "StepLeft", "StepLeft", "StepLeft",
                    "StepUp", "StepUp",
                    "StepRight", "StepRight", "StepRight", "StepRight",
                    "StepUp", "StepUp", "StepUp", "StepUp",
                    "StepRight", "StepRight",
                    "StepDown", "StepDown", "StepDown", "StepDown",
                    "StepRight", "StepRight", "StepRight", "StepRight",
                    "StepDown", "StepDown",
                    "StepLeft", "StepLeft", "StepLeft", "StepLeft",
                    "StepDown", "StepDown", "StepDown", "StepDown",
                    "StepLeft",
                    "StepUp", "StepUp", "StepUp", "StepUp",
                    "StepUp"});

            Pawn yellowpawn1 = new Pawn();
            Pawn yellowpawn2 = new Pawn();
            Pawn yellowpawn3 = new Pawn();
            Pawn yellowpawn4 = new Pawn();

            game.getPlayer3().setPawn1(yellowpawn1);
            game.getPlayer3().setPawn2(yellowpawn2);
            game.getPlayer3().setPawn3(yellowpawn3);
            game.getPlayer3().setPawn4(yellowpawn4);

            game.getPlayer3().getPawn1().setOnBoard(false);
            game.getPlayer3().getPawn2().setOnBoard(false);
            game.getPlayer3().getPawn3().setOnBoard(false);
            game.getPlayer3().getPawn4().setOnBoard(false);

            connect.setGameId(game.getGameId());
            connect.setColour(game.getPlayer3().getColour());


        } else if (game.getPlayer4() == null) {
            game.setPlayer4(player234);
            game.getPlayer4().setColour("green");
            game.getPlayer4().setPlayerMoves(new String[]{"StepRight", "StepRight", "StepRight", "StepRight",
                    "StepUp", "StepUp", "StepUp", "StepUp",
                    "StepRight", "StepRight",
                    "StepDown", "StepDown", "StepDown", "StepDown",
                    "StepRight", "StepRight", "StepRight", "StepRight",
                    "StepDown", "StepDown",
                    "StepLeft", "StepLeft", "StepLeft", "StepLeft",
                    "StepDown", "StepDown", "StepDown", "StepDown",
                    "StepLeft", "StepLeft",
                    "StepUp", "StepUp", "StepUp", "StepUp",
                    "StepLeft", "StepLeft", "StepLeft", "StepLeft",
                    "StepUp",
                    "StepRight", "StepRight", "StepRight", "StepRight",
                    "StepRight"});

            Pawn greenpawn1 = new Pawn();
            Pawn greenpawn2 = new Pawn();
            Pawn greenpawn3 = new Pawn();
            Pawn greenpawn4 = new Pawn();

            game.getPlayer4().setPawn1(greenpawn1);
            game.getPlayer4().setPawn2(greenpawn2);
            game.getPlayer4().setPawn3(greenpawn3);
            game.getPlayer4().setPawn4(greenpawn4);

            game.getPlayer4().getPawn1().setOnBoard(false);
            game.getPlayer4().getPawn2().setOnBoard(false);
            game.getPlayer4().getPawn3().setOnBoard(false);
            game.getPlayer4().getPawn4().setOnBoard(false);

            connect.setGameId(game.getGameId());
            connect.setColour(game.getPlayer4().getColour());


            //Hauspositionen den Pawns zuweisen
            initialize(game);
            //Aktuellen Spieler setzen
            game.setCurrentPlayer(game.getPlayer1());
            game.setStatus(IN_PROGRESS);
        } else {
            throw new InvalidGameException("Game is not valid anymore");
        }



        GameStorage.getInstance().setGame(game);
        return connect;
    }

    public RollDice rollDice(Player player, String gameId) throws InvalidParamException {

        RollDice rollDice = new RollDice();


        if (!GameStorage.getInstance().getGames().containsKey(gameId)) {
            throw new InvalidParamException("Game with provided Id doesn't exist");
        }

        Game game = GameStorage.getInstance().getGames().get(gameId);
        Player currentPlayer = game.getCurrentPlayer();
        Player player1 = game.getPlayer1();
        Player player2 = game.getPlayer2();
        Player player3 = game.getPlayer3();
        Player player4 = game.getPlayer4();


        //Abfrage, ob der Spieler gerade dran ist und würfeln darf
        if(game.getStatus().equals(IN_PROGRESS)) {
            if (!game.getCurrentPlayer().getLogin().equals(player.getLogin())) {
                rollDice.setTheDicer(false);
            } else {
                 if(game.isRickRoll()){

                //Würfeln

                int number = game.getDice().generatedNumber();

                log.info("rollDice Würfelzahl: {}", number);


                game.getDice().setNumber(number);

                //Prüfen, ob der Spieler alle Pawns im Haus hat und keine6 gewürfelt --> stuck

                boolean stuck = generalStuck(game.getCurrentPlayer(), number);

                //Pawn1

                //Setzen eines Pawns
                Pawn pawnVorMove = currentPlayer.getPawn1();
                //Werte des Pawns variabeln zuweisen
                double positionTopMove = pawnVorMove.getPositionTop();
                double positionLeftMove = pawnVorMove.getPositionLeft();
                boolean boardMove = pawnVorMove.isOnBoard();
                int sequenezMove = pawnVorMove.getMoveStatus();


                //Pawn auf die Position setzen, wo er hinhziehen würde
                Pawn pawnMove = move(number, currentPlayer, currentPlayer.getPawn1());

                //Prüfen, ob der Pawn dort hinziehen kann --> sonst steht dort ein anderer Pawn der gleichen Farbe
                boolean stuckPawn1 = specificStuck(pawnMove, currentPlayer, number);

                //Pawn wieder zurückstellen
                currentPlayer.getPawn1().setMoveStatus(sequenezMove);
                currentPlayer.getPawn1().setOnBoard(boardMove);
                currentPlayer.getPawn1().setPositionTop(positionTopMove);
                currentPlayer.getPawn1().setPositionLeft(positionLeftMove);


                //Pawn2
                pawnVorMove = currentPlayer.getPawn2();
                positionTopMove = pawnVorMove.getPositionTop();
                positionLeftMove = pawnVorMove.getPositionLeft();
                boardMove = pawnVorMove.isOnBoard();
                sequenezMove = pawnVorMove.getMoveStatus();
                pawnMove = move(number, currentPlayer, currentPlayer.getPawn2());

                log.info("positionTopMove bevor move: {}", positionTopMove);

                boolean stuckPawn2 = specificStuck(pawnMove, currentPlayer, number);
                currentPlayer.getPawn2().setMoveStatus(sequenezMove);
                currentPlayer.getPawn2().setOnBoard(boardMove);
                currentPlayer.getPawn2().setPositionTop(positionTopMove);
                currentPlayer.getPawn2().setPositionLeft(positionLeftMove);

                log.info("currentPlayer Top nach move und nach gleichsetzung: {}", currentPlayer.getPawn2().getPositionTop());


                //Pawn3
                pawnVorMove = currentPlayer.getPawn3();
                positionTopMove = pawnVorMove.getPositionTop();
                positionLeftMove = pawnVorMove.getPositionLeft();
                boardMove = pawnVorMove.isOnBoard();
                sequenezMove = pawnVorMove.getMoveStatus();
                pawnMove = move(number, currentPlayer, currentPlayer.getPawn3());
                boolean stuckPawn3 = specificStuck(pawnMove, currentPlayer, number);
                currentPlayer.getPawn3().setMoveStatus(sequenezMove);
                currentPlayer.getPawn3().setOnBoard(boardMove);
                currentPlayer.getPawn3().setPositionTop(positionTopMove);
                currentPlayer.getPawn3().setPositionLeft(positionLeftMove);

                //Pawn4
                pawnVorMove = currentPlayer.getPawn4();
                positionTopMove = pawnVorMove.getPositionTop();
                positionLeftMove = pawnVorMove.getPositionLeft();
                boardMove = pawnVorMove.isOnBoard();
                sequenezMove = pawnVorMove.getMoveStatus();
                pawnMove = move(number, currentPlayer, currentPlayer.getPawn4());
                boolean stuckPawn4 = specificStuck(pawnMove, currentPlayer, number);
                currentPlayer.getPawn4().setMoveStatus(sequenezMove);
                currentPlayer.getPawn4().setOnBoard(boardMove);
                currentPlayer.getPawn4().setPositionTop(positionTopMove);
                currentPlayer.getPawn4().setPositionLeft(positionLeftMove);


                //Prüfen, ob alle Pawns stuck sind
                if (!stuckPawn1) {
                    if (!stuckPawn2) {
                        if (!stuckPawn3) {
                            if (!stuckPawn4) {
                                stuck = false;
                            }
                        }
                    }
                } else if (stuckPawn1 && stuckPawn2 && stuckPawn3 && stuckPawn4) {
                    stuck = true;
                }


                log.info("Wert von Stuck in RollDice nach move und überprüfung aller pawns: {}", stuck);


                if (stuck) {

                    log.info("rollDice: stuck-Schleife wird durchlaufen");

                    if (number == 6) {

                        rollDice.setStuck(true);
                        rollDice.setTheDicer(false);

                    } else {


                        //Player wird neu gesetzt, da der aktuelle Player nicht ziehen kann

                        if (currentPlayer.equals(player1)) {
                            game.setCurrentPlayer(game.getPlayer2());
                        } else if (currentPlayer.equals(player2)) {
                            game.setCurrentPlayer(game.getPlayer3());
                        } else if (currentPlayer.equals(player3)) {
                            game.setCurrentPlayer(game.getPlayer4());
                        } else if (currentPlayer.equals(player4)) {
                            game.setCurrentPlayer(game.getPlayer1());
                        } else {
                            log.info("CurrentPlayer could not be set");
                        }
                        rollDice.setStuck(true);
                        rollDice.setTheDicer(false);
                    }

                } else {


                    rollDice.setStuck(false);
                    rollDice.setTheDicer(true);
                    game.setTheDiceRolled(true);
                    game.setRickRoll(false);

                }


                rollDice.setDiceNumber(number);
                rollDice.setGameId(game.getGameId());

            }

            }

        }else{
            log.info("Es sind noch nicht genügend Spieler vorhanden");
        }
        rollDice.setColour(currentPlayer.getColour());
        GameStorage.getInstance().setGame(game);

        return rollDice;

    }

    public GamePlay gamePlay(Player player, String gameId, double positionTop, double positionLeft, String pawnName){

        Game game = GameStorage.getInstance().getGames().get(gameId);
        GamePlay gamePlay = new GamePlay();
        int diceNumber = game.getDice().getNumber();
        gamePlay.setDiceNumber(diceNumber);
        Player currentPlayer = game.getCurrentPlayer();
        Pawn currentPawn = new Pawn();
        TossReturn tossReturn = new TossReturn();
        Player player1 = game.getPlayer1();
        Player player2 = game.getPlayer2();
        Player player3 = game.getPlayer3();
        Player player4 = game.getPlayer4();
        boolean stuck = false;



        //kann nur ausgeführt werden, wenn gewürfelt worden ist und ein Pawn ziehen kann
        if(game.isTheDiceRolled()) {

            //Prüfen, ob der Player gerade dran ist
            if (!currentPlayer.getLogin().equals(player.getLogin())) {
                gamePlay.setTheMover(false);
            } else {

                //Abgleichen welcher Pawn von dem Spieler gedrückt wird anhand der Top und Left Koordinate
                if ((currentPlayer.getPawn1().getPositionLeft() == positionLeft) && (currentPlayer.getPawn1().getPositionTop() == positionTop)) {


                    currentPawn = currentPlayer.getPawn1();


                    double positionTopMove = currentPawn.getPositionTop();
                    double positionLeftMove = currentPawn.getPositionLeft();
                    boolean boardMove = currentPawn.isOnBoard();
                    int sequenezMove = currentPawn.getMoveStatus();

                    //Move --> die neue Position des Pawns wird bestimmt
                    currentPlayer.getPawn1().setPositionLeft(move(diceNumber, currentPlayer, currentPawn).getPositionLeft());
                    currentPawn.setPositionLeft(currentPlayer.getPawn1().getPositionLeft());
                    currentPawn.setPositionTop(currentPlayer.getPawn1().getPositionTop());


                    //specificStuck --> Zug prüfen
                    stuck = specificStuck(currentPawn, currentPlayer, diceNumber);
                    if(stuck) {
                        gamePlay.setStuck(stuck);
                        log.info("gamePlay Wert von Stuck nach specificStuck: {}", stuck);
                        currentPlayer.getPawn1().setMoveStatus(sequenezMove);
                        currentPlayer.getPawn1().setOnBoard(boardMove);
                        currentPlayer.getPawn1().setPositionTop(positionTopMove);
                        currentPlayer.getPawn1().setPositionLeft(positionLeftMove);
                        //gamePlay.setTheMover(false);
                    }else{



                        //Toss --> Prüft ob ein Pawn geschmissen wird
                        tossReturn = toss(currentPawn, game);
                        gamePlay.setTossedName(tossReturn.getPawnName());
                        gamePlay.setTossedLeft(tossReturn.getPawn().getPositionLeft());
                        gamePlay.setTossedTop(tossReturn.getPawn().getPositionTop());

                        //check for Winner
                        gamePlay.setWinner(checkWin(currentPlayer));
                        game.setWinner(checkWin(currentPlayer));
                        if(gamePlay.isWinner()){
                            game.setStatus(FINISHED);
                            gamePlay.setWinnerName(currentPlayer.getLogin());
                        }


                        gamePlay.setPositionLeft(currentPawn.getPositionLeft());
                        gamePlay.setPositionTop(currentPawn.getPositionTop());
                        gamePlay.setGameId(game.getGameId());
                        gamePlay.setPawnName(pawnName);
                        gamePlay.setTheMover(true);

                        game.setTheDiceRolled(false);
                        game.setRickRoll(true);

                        if (currentPlayer.equals(player1)) {
                            game.getPlayer1().setPawn1(currentPawn);
                        } else if (currentPlayer.equals(player2)) {
                            game.getPlayer2().setPawn1(currentPawn);
                        } else if (currentPlayer.equals(player3)) {
                            game.getPlayer3().setPawn1(currentPawn);
                        } else if (currentPlayer.equals(player4)) {
                            game.getPlayer4().setPawn1(currentPawn);
                        } else {
                            log.info("CurrentPawn could not be set");
                        }

                        if (diceNumber != 6) {

                            if (currentPlayer.equals(player1)) {
                                game.setCurrentPlayer(game.getPlayer2());
                            } else if (currentPlayer.equals(player2)) {
                                game.setCurrentPlayer(game.getPlayer3());
                            } else if (currentPlayer.equals(player3)) {
                                game.setCurrentPlayer(game.getPlayer4());
                            } else if (currentPlayer.equals(player4)) {
                                game.setCurrentPlayer(game.getPlayer1());
                            } else {
                                log.info("CurrentPlayer could not be set");
                            }
                        }
                    }

                } else if ((currentPlayer.getPawn2().getPositionLeft() == positionLeft) && (currentPlayer.getPawn2().getPositionTop() == positionTop)) {


                    currentPawn = currentPlayer.getPawn2();

                    double positionTopMove = currentPawn.getPositionTop();
                    double positionLeftMove = currentPawn.getPositionLeft();
                    boolean boardMove = currentPawn.isOnBoard();
                    int sequenezMove = currentPawn.getMoveStatus();



                    //Move --> die neue Position des Pawns wird bestimmt
                    currentPlayer.getPawn2().setPositionLeft(move(diceNumber, currentPlayer, currentPawn).getPositionLeft());
                    currentPawn.setPositionLeft(currentPlayer.getPawn2().getPositionLeft());
                    currentPawn.setPositionTop(currentPlayer.getPawn2().getPositionTop());

                    //specificStuck --> Zug prüfen
                    stuck = specificStuck(currentPawn, currentPlayer, diceNumber);

                    log.info("gamePlay stuck von Pawn2 after Move: {}", stuck);

                    if(stuck) {
                        gamePlay.setStuck(stuck);
                        currentPlayer.getPawn2().setMoveStatus(sequenezMove);
                        currentPlayer.getPawn2().setOnBoard(boardMove);
                        currentPlayer.getPawn2().setPositionTop(positionTopMove);
                        currentPlayer.getPawn2().setPositionLeft(positionLeftMove);
                        //gamePlay.setTheMover(false);
                    }else {


                        //Toss --> Prüft ob ein Pawn geschmissen wird
                        tossReturn = toss(currentPawn, game);
                        gamePlay.setTossedName(tossReturn.getPawnName());
                        gamePlay.setTossedLeft(tossReturn.getPawn().getPositionLeft());
                        gamePlay.setTossedTop(tossReturn.getPawn().getPositionTop());

                        //check for Winner
                        gamePlay.setWinner(checkWin(currentPlayer));
                        game.setWinner(checkWin(currentPlayer));
                        if(gamePlay.isWinner()){
                            game.setStatus(FINISHED);
                            gamePlay.setWinnerName(currentPlayer.getLogin());
                        }

                        gamePlay.setPositionLeft(currentPawn.getPositionLeft());
                        gamePlay.setPositionTop(currentPawn.getPositionTop());
                        gamePlay.setGameId(game.getGameId());
                        gamePlay.setPawnName(pawnName);
                        gamePlay.setTheMover(true);

                        game.setTheDiceRolled(false);
                        game.setRickRoll(true);

                        if (currentPlayer.equals(player1)) {
                            game.getPlayer1().setPawn2(currentPawn);
                        } else if (currentPlayer.equals(player2)) {
                            game.getPlayer2().setPawn2(currentPawn);
                        } else if (currentPlayer.equals(player3)) {
                            game.getPlayer3().setPawn2(currentPawn);
                        } else if (currentPlayer.equals(player4)) {
                            game.getPlayer4().setPawn2(currentPawn);
                        } else {
                            log.info("CurrentPawn could not be set");
                        }

                        if (diceNumber != 6) {

                            if (currentPlayer.equals(player1)) {
                                game.setCurrentPlayer(game.getPlayer2());
                            } else if (currentPlayer.equals(player2)) {
                                game.setCurrentPlayer(game.getPlayer3());
                            } else if (currentPlayer.equals(player3)) {
                                game.setCurrentPlayer(game.getPlayer4());
                            } else if (currentPlayer.equals(player4)) {
                                game.setCurrentPlayer(game.getPlayer1());
                            } else {
                                log.info("CurrentPlayer could not be set");
                            }
                        }
                    }

                } else if ((currentPlayer.getPawn3().getPositionLeft() == positionLeft) && (currentPlayer.getPawn3().getPositionTop() == positionTop)) {


                    currentPawn = currentPlayer.getPawn3();

                    double positionTopMove = currentPawn.getPositionTop();
                    double positionLeftMove = currentPawn.getPositionLeft();
                    boolean boardMove = currentPawn.isOnBoard();
                    int sequenezMove = currentPawn.getMoveStatus();



                    //Move --> die neue Position des Pawns wird bestimmt
                    currentPlayer.getPawn3().setPositionLeft(move(diceNumber, currentPlayer, currentPawn).getPositionLeft());
                    currentPawn.setPositionLeft(currentPlayer.getPawn3().getPositionLeft());
                    currentPawn.setPositionTop(currentPlayer.getPawn3().getPositionTop());

                    //specificStuck --> Zug prüfen
                    stuck = specificStuck(currentPawn, currentPlayer, diceNumber);

                    if(stuck) {
                        gamePlay.setStuck(stuck);
                        currentPlayer.getPawn3().setMoveStatus(sequenezMove);
                        currentPlayer.getPawn3().setOnBoard(boardMove);
                        currentPlayer.getPawn3().setPositionTop(positionTopMove);
                        currentPlayer.getPawn3().setPositionLeft(positionLeftMove);
                        //gamePlay.setTheMover(false);
                    }else {


                        //Toss --> Prüft ob ein Pawn geschmissen wird
                        tossReturn = toss(currentPawn, game);
                        gamePlay.setTossedName(tossReturn.getPawnName());
                        gamePlay.setTossedLeft(tossReturn.getPawn().getPositionLeft());
                        gamePlay.setTossedTop(tossReturn.getPawn().getPositionTop());

                        //check for Winner
                        gamePlay.setWinner(checkWin(currentPlayer));
                        game.setWinner(checkWin(currentPlayer));
                        if(gamePlay.isWinner()){
                            game.setStatus(FINISHED);
                            gamePlay.setWinnerName(currentPlayer.getLogin());
                        }

                        gamePlay.setPositionLeft(currentPawn.getPositionLeft());
                        gamePlay.setPositionTop(currentPawn.getPositionTop());
                        gamePlay.setGameId(game.getGameId());
                        gamePlay.setPawnName(pawnName);
                        gamePlay.setTheMover(true);

                        game.setTheDiceRolled(false);
                        game.setRickRoll(true);

                        if (currentPlayer.equals(player1)) {
                            game.getPlayer1().setPawn3(currentPawn);
                        } else if (currentPlayer.equals(player2)) {
                            game.getPlayer2().setPawn3(currentPawn);
                        } else if (currentPlayer.equals(player3)) {
                            game.getPlayer3().setPawn3(currentPawn);
                        } else if (currentPlayer.equals(player4)) {
                            game.getPlayer4().setPawn3(currentPawn);
                        } else {
                            log.info("CurrentPawn could not be set");
                        }

                        if (diceNumber != 6) {


                            if (currentPlayer.equals(player1)) {
                                game.setCurrentPlayer(game.getPlayer2());
                            } else if (currentPlayer.equals(player2)) {
                                game.setCurrentPlayer(game.getPlayer3());
                            } else if (currentPlayer.equals(player3)) {
                                game.setCurrentPlayer(game.getPlayer4());
                            } else if (currentPlayer.equals(player4)) {
                                game.setCurrentPlayer(game.getPlayer1());
                            } else {
                                log.info("CurrentPlayer could not be set");
                            }

                        }
                    }

                }else if ((currentPlayer.getPawn4().getPositionLeft() == positionLeft) && (currentPlayer.getPawn4().getPositionTop() == positionTop)) {


                    currentPawn = currentPlayer.getPawn4();

                    double positionTopMove = currentPawn.getPositionTop();
                    double positionLeftMove = currentPawn.getPositionLeft();
                    boolean boardMove = currentPawn.isOnBoard();
                    int sequenezMove = currentPawn.getMoveStatus();



                    //Move --> die neue Position des Pawns wird bestimmt
                    currentPlayer.getPawn4().setPositionLeft(move(diceNumber, currentPlayer, currentPawn).getPositionLeft());
                    currentPawn.setPositionLeft(currentPlayer.getPawn4().getPositionLeft());
                    currentPawn.setPositionTop(currentPlayer.getPawn4().getPositionTop());

                    //specificStuck --> Zug prüfen
                    stuck = specificStuck(currentPawn, currentPlayer, diceNumber);

                    if(stuck) {
                        gamePlay.setStuck(stuck);
                        currentPlayer.getPawn4().setMoveStatus(sequenezMove);
                        currentPlayer.getPawn4().setOnBoard(boardMove);
                        currentPlayer.getPawn4().setPositionTop(positionTopMove);
                        currentPlayer.getPawn4().setPositionLeft(positionLeftMove);
                        //gamePlay.setTheMover(false);
                    }else {


                        //Toss --> Prüft ob ein Pawn geschmissen wird
                        tossReturn = toss(currentPawn, game);
                        gamePlay.setTossedName(tossReturn.getPawnName());
                        gamePlay.setTossedLeft(tossReturn.getPawn().getPositionLeft());
                        gamePlay.setTossedTop(tossReturn.getPawn().getPositionTop());

                        //check for Winner
                        gamePlay.setWinner(checkWin(currentPlayer));
                        game.setWinner(checkWin(currentPlayer));
                        if(gamePlay.isWinner()){
                            game.setStatus(FINISHED);
                            gamePlay.setWinnerName(currentPlayer.getLogin());
                        }

                        gamePlay.setPositionLeft(currentPawn.getPositionLeft());
                        gamePlay.setPositionTop(currentPawn.getPositionTop());
                        gamePlay.setGameId(game.getGameId());
                        gamePlay.setPawnName(pawnName);
                        gamePlay.setTheMover(true);

                        game.setTheDiceRolled(false);
                        game.setRickRoll(true);

                        if (currentPlayer.equals(player1)) {
                            game.getPlayer1().setPawn4(currentPawn);
                        } else if (currentPlayer.equals(player2)) {
                            game.getPlayer2().setPawn4(currentPawn);
                        } else if (currentPlayer.equals(player3)) {
                            game.getPlayer3().setPawn4(currentPawn);
                        } else if (currentPlayer.equals(player4)) {
                            game.getPlayer4().setPawn4(currentPawn);
                        } else {
                            log.info("CurrentPawn could not be set");
                        }

                        if (diceNumber != 6) {


                            if (currentPlayer.equals(player1)) {
                                game.setCurrentPlayer(game.getPlayer2());
                            } else if (currentPlayer.equals(player2)) {
                                game.setCurrentPlayer(game.getPlayer3());
                            } else if (currentPlayer.equals(player3)) {
                                game.setCurrentPlayer(game.getPlayer4());
                            } else if (currentPlayer.equals(player4)) {
                                game.setCurrentPlayer(game.getPlayer1());
                            } else {
                                log.info("CurrentPlayer could not be set");
                            }

                        }

                    }


                } else {
                    log.info("CurrentPawn could not be verified");
                    gamePlay.setTheMover(false);
                }




            }
        }else{
            log.info("Es muss erst gewürfelt gewerden");
        }
        GameStorage.getInstance().setGame(game);

        gamePlay.setColour(currentPlayer.getColour());
        return gamePlay;


    }

    public Pawn move(int diceNumber, Player player, Pawn currentPawn){

        Home home = new Home();


        int moveStatus = currentPawn.getMoveStatus();
        boolean onBoard = currentPawn.isOnBoard();


        String colour = player.getColour();
        String [] playerMoves = player.getPlayerMoves();
        String currentMoveStatusText;

        double positionTop = currentPawn.getPositionTop();
        double positionLeft = currentPawn.getPositionLeft();


        //Prüfen, ob der Pawn auf den Spielfeld steht
        if(!onBoard) {

            //Prüfen, ob eine 6 gewürfelt worden ist und der Pawn somit auf die Startposition gestellt werden kann
            if(diceNumber==6) {

                //Je nach Farbe wird der Pawn auf die richtige Startposition gestellt, die aus der Klasse Home abgefragt wird

                switch (colour) {

                    case "red":
                        positionTop = home.getRedStartTop();
                        positionLeft = home.getRedStartLeft();
                        currentPawn.setPositionTop(positionTop);
                        currentPawn.setPositionLeft(positionLeft);
                        currentPawn.setMoveStatus(0);
                        currentPawn.setOnBoard(true);
                        break;

                    case "blue":
                        positionTop = home.getBlueStartTop();
                        positionLeft = home.getBlueStartLeft();
                        currentPawn.setPositionTop(positionTop);
                        currentPawn.setPositionLeft(positionLeft);
                        currentPawn.setMoveStatus(0);
                        currentPawn.setOnBoard(true);
                        break;

                    case "yellow":
                        positionTop = home.getYellowStartTop();
                        positionLeft = home.getYellowStartLeft();
                        currentPawn.setPositionTop(positionTop);
                        currentPawn.setPositionLeft(positionLeft);
                        currentPawn.setMoveStatus(0);
                        currentPawn.setOnBoard(true);
                        break;

                    case "green":
                        positionTop = home.getGreenStartTop();
                        positionLeft = home.getGreenStartLeft();
                        currentPawn.setPositionTop(positionTop);
                        currentPawn.setPositionLeft(positionLeft);
                        currentPawn.setMoveStatus(0);
                        currentPawn.setOnBoard(true);
                        break;

                    default:
                        log.info("Colour not recognized");

                }
            }


            }else{

                //For schleife wird solange durchlaufen, bis die Augenzahl des Würfels errreicht wurde
                for (int i = 0; i < diceNumber; i++) {




                    //Wenn der moveStatus 43 erreicht wurde, ist der Pawn am Ende des Arrays seiner Route angelangt und steht somit
                    //an der letztmöglichen Position
                    if (moveStatus < 43) {

                        log.info("MoveStatus in if-Move: {}", moveStatus);

                        //Die aktuelle Laufbeschreibung aus dem Array wird einer Variabel zugewiesen
                        currentMoveStatusText = playerMoves[moveStatus];


                        //Je nachdem welche Laufbeschreibung gerade dran ist, wird die Top oder die Left Koordinate um 49.5 verändert
                        switch (currentMoveStatusText) {
                            case "StepDown":
                                positionTop += step;
                                currentPawn.setPositionTop(positionTop);
                                break;

                            case "StepUp":
                                positionTop -= step;
                                currentPawn.setPositionTop(positionTop);
                                break;

                            case "StepRight":
                                positionLeft += step;
                                currentPawn.setPositionLeft(positionLeft);
                                break;

                            case "StepLeft":
                                positionLeft -= step;
                                currentPawn.setPositionLeft(positionLeft);
                                break;

                            default:
                                log.info("Move could not happened");

                        }
                        //Der moveStatus wird eins nach oben gesetzt
                        moveStatus++;
                        currentPawn.setMoveStatus(moveStatus);
                    }else {
                        moveStatus++;
                        currentPawn.setMoveStatus(moveStatus);
                    }
                }
            }
        log.info("Move wurde durchlaufen");

        return currentPawn;

    }

    public Game initialize(Game game){


        //Festlegen de Haus und Startpositionen aller Pawns und der Farben
        Home home = new Home();

        //Player1 - Red
        game.getPlayer1().getPawn1().setPositionLeft(home.getRed1Left());
        game.getPlayer1().getPawn1().setPositionTop(home.getRed1Top());

        game.getPlayer1().getPawn1().setMoveStatus(0);



        game.getPlayer1().getPawn2().setPositionLeft(home.getRed2Left());
        game.getPlayer1().getPawn2().setPositionTop(home.getRed2Top());

        game.getPlayer1().getPawn2().setMoveStatus(0);



        game.getPlayer1().getPawn3().setPositionLeft(home.getRed3Left());
        game.getPlayer1().getPawn3().setPositionTop(home.getRed3Top());



        game.getPlayer1().getPawn4().setPositionLeft(home.getRed4Left());
        game.getPlayer1().getPawn4().setPositionTop(home.getRed4Top());



        //Player2 - Blue
        game.getPlayer2().getPawn1().setPositionLeft(home.getBlue1Left());
        game.getPlayer2().getPawn1().setPositionTop(home.getBlue1Top());

        game.getPlayer2().getPawn2().setPositionLeft(home.getBlue2Left());
        game.getPlayer2().getPawn2().setPositionTop(home.getBlue2Top());

        game.getPlayer2().getPawn3().setPositionLeft(home.getBlue3Left());
        game.getPlayer2().getPawn3().setPositionTop(home.getBlue3Top());

        game.getPlayer2().getPawn4().setPositionLeft(home.getBlue4Left());
        game.getPlayer2().getPawn4().setPositionTop(home.getBlue4Top());


        //Player3 - Yellow
        game.getPlayer3().getPawn1().setPositionLeft(home.getYellow1Left());
        game.getPlayer3().getPawn1().setPositionTop(home.getYellow1Top());

        game.getPlayer3().getPawn2().setPositionLeft(home.getYellow2Left());
        game.getPlayer3().getPawn2().setPositionTop(home.getYellow2Top());

        game.getPlayer3().getPawn3().setPositionLeft(home.getYellow3Left());
        game.getPlayer3().getPawn3().setPositionTop(home.getYellow3Top());

        game.getPlayer3().getPawn4().setPositionLeft(home.getYellow4Left());
        game.getPlayer3().getPawn4().setPositionTop(home.getYellow4Top());


        //Player4 - Green
        game.getPlayer4().getPawn1().setPositionLeft(home.getGreen1Left());
        game.getPlayer4().getPawn1().setPositionTop(home.getGreen1Top());

        game.getPlayer4().getPawn2().setPositionLeft(home.getGreen2Left());
        game.getPlayer4().getPawn2().setPositionTop(home.getGreen2Top());

        game.getPlayer4().getPawn3().setPositionLeft(home.getGreen3Left());
        game.getPlayer4().getPawn3().setPositionTop(home.getGreen3Top());

        game.getPlayer4().getPawn4().setPositionLeft(home.getGreen4Left());
        game.getPlayer4().getPawn4().setPositionTop(home.getGreen4Top());



        return game;
    }

    public TossReturn toss(Pawn currentPawn, Game game){

        Home home = new Home();
        Pawn returnPawn = new Pawn();
        TossReturn tossReturn = new TossReturn();
        String namePawn = null;

        //Currentplayer
        Player currentPlayer = game.getCurrentPlayer();


        //Player1 + Pawns
        Player player1 = game.getPlayer1();

        Pawn player1Pawn1 = player1.getPawn1();
        Pawn player1Pawn2 = player1.getPawn2();
        Pawn player1Pawn3 = player1.getPawn3();
        Pawn player1Pawn4 = player1.getPawn4();

        //Player2 + Pawns
        Player player2 = game.getPlayer2();

        Pawn player2Pawn1 = player2.getPawn1();
        Pawn player2Pawn2 = player2.getPawn2();
        Pawn player2Pawn3 = player2.getPawn3();
        Pawn player2Pawn4 = player2.getPawn4();

        //Player3 + Pawns
        Player player3 = game.getPlayer3();

        Pawn player3Pawn1 = player3.getPawn1();
        Pawn player3Pawn2 = player3.getPawn2();
        Pawn player3Pawn3 = player3.getPawn3();
        Pawn player3Pawn4 = player3.getPawn4();

        //Player4 + Pawns
        Player player4 = game.getPlayer4();

        Pawn player4Pawn1 = player4.getPawn1();
        Pawn player4Pawn2 = player4.getPawn2();
        Pawn player4Pawn3 = player4.getPawn3();
        Pawn player4Pawn4 = player4.getPawn4();




        //Je nach dem welcher Player dran ist, wird der aktuelle Pawn mit allen anderen Pawns anhand der Koordinaten verglichen um zu prüfen, ob er einen schmeißt
        if (currentPlayer.equals(player1)) {

            //Player2 und currentPlayer Pawns
            if(currentPawn.getPositionTop() == player2Pawn1.getPositionTop() && currentPawn.getPositionLeft() == player2Pawn1.getPositionLeft()){
                game.getPlayer2().getPawn1().setPositionLeft(home.getBlue1Left());
                game.getPlayer2().getPawn1().setPositionTop(home.getBlue1Top());
                game.getPlayer2().getPawn1().setOnBoard(false);
                game.getPlayer2().getPawn1().setMoveStatus(0);
                returnPawn = game.getPlayer2().getPawn1();
                namePawn = "bluepawn1";


            }else if(currentPawn.getPositionTop() == player2Pawn2.getPositionTop() && currentPawn.getPositionLeft() == player2Pawn2.getPositionLeft()){
                game.getPlayer2().getPawn2().setPositionLeft(home.getBlue2Left());
                game.getPlayer2().getPawn2().setPositionTop(home.getBlue2Top());
                game.getPlayer2().getPawn2().setOnBoard(false);
                game.getPlayer2().getPawn2().setMoveStatus(0);
                returnPawn = game.getPlayer2().getPawn2();
                namePawn="bluepawn2";

            }else if(currentPawn.getPositionTop() == player2Pawn3.getPositionTop() && currentPawn.getPositionLeft() == player2Pawn3.getPositionLeft()){
                game.getPlayer2().getPawn3().setPositionLeft(home.getBlue3Left());
                game.getPlayer2().getPawn3().setPositionTop(home.getBlue3Top());
                game.getPlayer2().getPawn3().setOnBoard(false);
                game.getPlayer2().getPawn3().setMoveStatus(0);
                returnPawn = game.getPlayer2().getPawn3();
                namePawn = "bluepawn3";

            }else if(currentPawn.getPositionTop() == player2Pawn4.getPositionTop() && currentPawn.getPositionLeft() == player2Pawn4.getPositionLeft()){
                game.getPlayer2().getPawn4().setPositionLeft(home.getBlue4Left());
                game.getPlayer2().getPawn4().setPositionTop(home.getBlue4Top());
                game.getPlayer2().getPawn4().setOnBoard(false);
                game.getPlayer2().getPawn4().setMoveStatus(0);
                returnPawn = game.getPlayer2().getPawn4();
                namePawn = "bluepawn4";
            }else{
               // log.info("Die Pawns von Player1 schlagen keinen Pawn von Player2");
            }


            //Player3 und currentPlayer Pawns
            if(currentPawn.getPositionTop() == player3Pawn1.getPositionTop() && currentPawn.getPositionLeft() == player3Pawn1.getPositionLeft()){
                game.getPlayer3().getPawn1().setPositionLeft(home.getYellow1Left());
                game.getPlayer3().getPawn1().setPositionTop(home.getYellow1Top());
                game.getPlayer3().getPawn1().setOnBoard(false);
                game.getPlayer3().getPawn1().setMoveStatus(0);
                returnPawn = game.getPlayer3().getPawn1();
                namePawn = "yellowpawn1";

            }else if(currentPawn.getPositionTop() == player3Pawn2.getPositionTop() && currentPawn.getPositionLeft() == player3Pawn2.getPositionLeft()){
                game.getPlayer3().getPawn2().setPositionLeft(home.getYellow2Left());
                game.getPlayer3().getPawn2().setPositionTop(home.getYellow2Top());
                game.getPlayer3().getPawn2().setOnBoard(false);
                game.getPlayer3().getPawn2().setMoveStatus(0);
                returnPawn = game.getPlayer3().getPawn2();
                namePawn = "yellowpawn2";

            }else if(currentPawn.getPositionTop() == player3Pawn3.getPositionTop() && currentPawn.getPositionLeft() == player3Pawn3.getPositionLeft()){
                game.getPlayer3().getPawn3().setPositionLeft(home.getYellow3Left());
                game.getPlayer3().getPawn3().setPositionTop(home.getYellow3Top());
                game.getPlayer3().getPawn3().setOnBoard(false);
                game.getPlayer3().getPawn3().setMoveStatus(0);
                returnPawn = game.getPlayer3().getPawn3();
                namePawn = "yellowpawn3";

            }else if(currentPawn.getPositionTop() == player3Pawn4.getPositionTop() && currentPawn.getPositionLeft() == player3Pawn4.getPositionLeft()){
                game.getPlayer3().getPawn4().setPositionLeft(home.getYellow4Left());
                game.getPlayer3().getPawn4().setPositionTop(home.getYellow4Top());
                game.getPlayer3().getPawn4().setOnBoard(false);
                game.getPlayer3().getPawn4().setMoveStatus(0);
                returnPawn = game.getPlayer3().getPawn4();
                namePawn = "yellowpawn4";
            }else{
              //  log.info("Die Pawns von Player1 schlagen keinen Pawn von Player3");
            }

            //Player4 und currentPlayer Pawns
            if(currentPawn.getPositionTop() == player4Pawn1.getPositionTop() && currentPawn.getPositionLeft() == player4Pawn1.getPositionLeft()){
                game.getPlayer4().getPawn1().setPositionLeft(home.getGreen1Left());
                game.getPlayer4().getPawn1().setPositionTop(home.getGreen1Top());
                game.getPlayer4().getPawn1().setOnBoard(false);
                game.getPlayer4().getPawn1().setMoveStatus(0);
                returnPawn = game.getPlayer4().getPawn1();
                namePawn = "greenpawn1";

            }else if(currentPawn.getPositionTop() == player4Pawn2.getPositionTop() && currentPawn.getPositionLeft() == player4Pawn2.getPositionLeft()){
                game.getPlayer4().getPawn2().setPositionLeft(home.getGreen2Left());
                game.getPlayer4().getPawn2().setPositionTop(home.getGreen2Top());
                game.getPlayer4().getPawn2().setOnBoard(false);
                game.getPlayer4().getPawn2().setMoveStatus(0);
                returnPawn = game.getPlayer4().getPawn2();
                namePawn = "greenpawn2";

            }else if(currentPawn.getPositionTop() == player4Pawn3.getPositionTop() && currentPawn.getPositionLeft() == player4Pawn3.getPositionLeft()){
                game.getPlayer4().getPawn3().setPositionLeft(home.getGreen3Left());
                game.getPlayer4().getPawn3().setPositionTop(home.getGreen3Top());
                game.getPlayer4().getPawn3().setOnBoard(false);
                game.getPlayer4().getPawn3().setMoveStatus(0);
                returnPawn = game.getPlayer4().getPawn3();
                namePawn = "greenpawn3";

            }else if(currentPawn.getPositionTop() == player4Pawn4.getPositionTop() && currentPawn.getPositionLeft() == player4Pawn4.getPositionLeft()){
                game.getPlayer4().getPawn4().setPositionLeft(home.getGreen4Left());
                game.getPlayer4().getPawn4().setPositionTop(home.getGreen4Top());
                game.getPlayer4().getPawn4().setOnBoard(false);
                game.getPlayer4().getPawn4().setMoveStatus(0);
                returnPawn = game.getPlayer4().getPawn4();
                namePawn = "greenpawn4";
            }else{
              //  log.info("Die Pawns von Player1 schlagen keinen Pawn von Player4");
            }





        } else if (currentPlayer.equals(player2)) {

            //Player1 und currentPlayer Pawns
            if(currentPawn.getPositionTop() == player1Pawn1.getPositionTop() && currentPawn.getPositionLeft() == player1Pawn1.getPositionLeft()){
                game.getPlayer1().getPawn1().setPositionLeft(home.getRed1Left());
                game.getPlayer1().getPawn1().setPositionTop(home.getRed1Top());
                game.getPlayer1().getPawn1().setOnBoard(false);
                game.getPlayer1().getPawn1().setMoveStatus(0);
                returnPawn = game.getPlayer1().getPawn1();
                namePawn = "redpawn1";

            }else if(currentPawn.getPositionTop() == player1Pawn2.getPositionTop() && currentPawn.getPositionLeft() == player1Pawn2.getPositionLeft()){
                game.getPlayer1().getPawn2().setPositionLeft(home.getRed2Left());
                game.getPlayer1().getPawn2().setPositionTop(home.getRed2Top());
                game.getPlayer1().getPawn2().setOnBoard(false);
                game.getPlayer1().getPawn2().setMoveStatus(0);
                returnPawn = game.getPlayer1().getPawn2();
                namePawn = "redpawn2";

            }else if(currentPawn.getPositionTop() == player1Pawn3.getPositionTop() && currentPawn.getPositionLeft() == player1Pawn3.getPositionLeft()){
                game.getPlayer1().getPawn3().setPositionLeft(home.getRed3Left());
                game.getPlayer1().getPawn3().setPositionTop(home.getRed3Top());
                game.getPlayer1().getPawn3().setOnBoard(false);
                game.getPlayer1().getPawn3().setMoveStatus(0);
                returnPawn = game.getPlayer1().getPawn3();
                namePawn = "redpawn3";

            }else if(currentPawn.getPositionTop() == player1Pawn4.getPositionTop() && currentPawn.getPositionLeft() == player1Pawn4.getPositionLeft()){
                game.getPlayer1().getPawn4().setPositionLeft(home.getRed4Left());
                game.getPlayer1().getPawn4().setPositionTop(home.getRed4Top());
                game.getPlayer1().getPawn4().setOnBoard(false);
                game.getPlayer1().getPawn4().setMoveStatus(0);
                returnPawn = game.getPlayer1().getPawn4();
                namePawn ="redpawn4";
            }else{
               // log.info("Die Pawns von Player2 schlagen keinen Pawn von Player1");
            }


            //Player3 und currentPlayer Pawns
            if(currentPawn.getPositionTop() == player3Pawn1.getPositionTop() && currentPawn.getPositionLeft() == player3Pawn1.getPositionLeft()){
                game.getPlayer3().getPawn1().setPositionLeft(home.getYellow1Left());
                game.getPlayer3().getPawn1().setPositionTop(home.getYellow1Top());
                game.getPlayer3().getPawn1().setOnBoard(false);
                game.getPlayer3().getPawn1().setMoveStatus(0);
                returnPawn = game.getPlayer3().getPawn1();
                namePawn = "yellowpawn1";

            }else if(currentPawn.getPositionTop() == player3Pawn2.getPositionTop() && currentPawn.getPositionLeft() == player3Pawn2.getPositionLeft()){
                game.getPlayer3().getPawn2().setPositionLeft(home.getYellow2Left());
                game.getPlayer3().getPawn2().setPositionTop(home.getYellow2Top());
                game.getPlayer3().getPawn2().setOnBoard(false);
                game.getPlayer3().getPawn2().setMoveStatus(0);
                returnPawn = game.getPlayer3().getPawn2();
                namePawn = "yellowpawn2";

            }else if(currentPawn.getPositionTop() == player3Pawn3.getPositionTop() && currentPawn.getPositionLeft() == player3Pawn3.getPositionLeft()){
                game.getPlayer3().getPawn3().setPositionLeft(home.getYellow3Left());
                game.getPlayer3().getPawn3().setPositionTop(home.getYellow3Top());
                game.getPlayer3().getPawn3().setOnBoard(false);
                game.getPlayer3().getPawn3().setMoveStatus(0);
                returnPawn = game.getPlayer3().getPawn3();
                namePawn = "yellowpawn3";

            }else if(currentPawn.getPositionTop() == player3Pawn4.getPositionTop() && currentPawn.getPositionLeft() == player3Pawn4.getPositionLeft()){
                game.getPlayer3().getPawn4().setPositionLeft(home.getYellow4Left());
                game.getPlayer3().getPawn4().setPositionTop(home.getYellow4Top());
                game.getPlayer3().getPawn4().setOnBoard(false);
                game.getPlayer3().getPawn4().setMoveStatus(0);
                returnPawn = game.getPlayer3().getPawn4();
                namePawn = "yellowpawn4";
            }else{
               // log.info("Die Pawns von Player2 schlagen keinen Pawn von Player3");
            }

            //Player4 und currentPlayer Pawns
            if(currentPawn.getPositionTop() == player4Pawn1.getPositionTop() && currentPawn.getPositionLeft() == player4Pawn1.getPositionLeft()){
                game.getPlayer4().getPawn1().setPositionLeft(home.getGreen1Left());
                game.getPlayer4().getPawn1().setPositionTop(home.getGreen1Top());
                game.getPlayer4().getPawn1().setOnBoard(false);
                game.getPlayer4().getPawn1().setMoveStatus(0);
                returnPawn = game.getPlayer4().getPawn1();
                namePawn = "greenpawn1";

            }else if(currentPawn.getPositionTop() == player4Pawn2.getPositionTop() && currentPawn.getPositionLeft() == player4Pawn2.getPositionLeft()){
                game.getPlayer4().getPawn2().setPositionLeft(home.getGreen2Left());
                game.getPlayer4().getPawn2().setPositionTop(home.getGreen2Top());
                game.getPlayer4().getPawn2().setOnBoard(false);
                game.getPlayer4().getPawn2().setMoveStatus(0);
                returnPawn = game.getPlayer4().getPawn2();
                namePawn = "greenpawn2";

            }else if(currentPawn.getPositionTop() == player4Pawn3.getPositionTop() && currentPawn.getPositionLeft() == player4Pawn3.getPositionLeft()){
                game.getPlayer4().getPawn3().setPositionLeft(home.getGreen3Left());
                game.getPlayer4().getPawn3().setPositionTop(home.getGreen3Top());
                game.getPlayer4().getPawn3().setOnBoard(false);
                game.getPlayer4().getPawn3().setMoveStatus(0);
                returnPawn = game.getPlayer4().getPawn3();
                namePawn = "greenpawn3";

            }else if(currentPawn.getPositionTop() == player4Pawn4.getPositionTop() && currentPawn.getPositionLeft() == player4Pawn4.getPositionLeft()){
                game.getPlayer4().getPawn4().setPositionLeft(home.getGreen4Left());
                game.getPlayer4().getPawn4().setPositionTop(home.getGreen4Top());
                game.getPlayer4().getPawn4().setOnBoard(false);
                game.getPlayer4().getPawn4().setMoveStatus(0);
                returnPawn = game.getPlayer4().getPawn4();
                namePawn = "greenpawn4";
            }else{
               // log.info("Die Pawns von Player2 schlagen keinen Pawn von Player4");
            }

        } else if (currentPlayer.equals(player3)) {

            //Player1 und currentPlayer Pawns
            if(currentPawn.getPositionTop() == player1Pawn1.getPositionTop() && currentPawn.getPositionLeft() == player1Pawn1.getPositionLeft()){
                game.getPlayer1().getPawn1().setPositionLeft(home.getRed1Left());
                game.getPlayer1().getPawn1().setPositionTop(home.getRed1Top());
                game.getPlayer1().getPawn1().setOnBoard(false);
                game.getPlayer1().getPawn1().setMoveStatus(0);
                returnPawn = game.getPlayer1().getPawn1();
                namePawn = "redpawn1";

            }else if(currentPawn.getPositionTop() == player1Pawn2.getPositionTop() && currentPawn.getPositionLeft() == player1Pawn2.getPositionLeft()){
                game.getPlayer1().getPawn2().setPositionLeft(home.getRed2Left());
                game.getPlayer1().getPawn2().setPositionTop(home.getRed2Top());
                game.getPlayer1().getPawn2().setOnBoard(false);
                game.getPlayer1().getPawn2().setMoveStatus(0);
                returnPawn = game.getPlayer1().getPawn2();
                namePawn = "redpawn2";

            }else if(currentPawn.getPositionTop() == player1Pawn3.getPositionTop() && currentPawn.getPositionLeft() == player1Pawn3.getPositionLeft()){
                game.getPlayer1().getPawn3().setPositionLeft(home.getRed3Left());
                game.getPlayer1().getPawn3().setPositionTop(home.getRed3Top());
                game.getPlayer1().getPawn3().setOnBoard(false);
                game.getPlayer1().getPawn3().setMoveStatus(0);
                returnPawn = game.getPlayer1().getPawn3();
                namePawn = "redpawn3";

            }else if(currentPawn.getPositionTop() == player1Pawn4.getPositionTop() && currentPawn.getPositionLeft() == player1Pawn4.getPositionLeft()){
                game.getPlayer1().getPawn4().setPositionLeft(home.getRed4Left());
                game.getPlayer1().getPawn4().setPositionTop(home.getRed4Top());
                game.getPlayer1().getPawn4().setOnBoard(false);
                game.getPlayer1().getPawn4().setMoveStatus(0);
                returnPawn = game.getPlayer1().getPawn4();
                namePawn ="redpawn4";
            }else{
              //  log.info("Die Pawns von Player3 schlagen keinen Pawn von Player1");
            }


            //Player2 und currentPlayer Pawns
            if(currentPawn.getPositionTop() == player2Pawn1.getPositionTop() && currentPawn.getPositionLeft() == player2Pawn1.getPositionLeft()){
                game.getPlayer2().getPawn1().setPositionLeft(home.getBlue1Left());
                game.getPlayer2().getPawn1().setPositionTop(home.getBlue1Top());
                game.getPlayer2().getPawn1().setOnBoard(false);
                game.getPlayer2().getPawn1().setMoveStatus(0);
                returnPawn = game.getPlayer2().getPawn1();
                namePawn = "bluepawn1";


            }else if(currentPawn.getPositionTop() == player2Pawn2.getPositionTop() && currentPawn.getPositionLeft() == player2Pawn2.getPositionLeft()){
                game.getPlayer2().getPawn2().setPositionLeft(home.getBlue2Left());
                game.getPlayer2().getPawn2().setPositionTop(home.getBlue2Top());
                game.getPlayer2().getPawn2().setOnBoard(false);
                game.getPlayer2().getPawn2().setMoveStatus(0);
                returnPawn = game.getPlayer2().getPawn2();
                namePawn="bluepawn2";

            }else if(currentPawn.getPositionTop() == player2Pawn3.getPositionTop() && currentPawn.getPositionLeft() == player2Pawn3.getPositionLeft()){
                game.getPlayer2().getPawn3().setPositionLeft(home.getBlue3Left());
                game.getPlayer2().getPawn3().setPositionTop(home.getBlue3Top());
                game.getPlayer2().getPawn3().setOnBoard(false);
                game.getPlayer2().getPawn3().setMoveStatus(0);
                returnPawn = game.getPlayer2().getPawn3();
                namePawn = "bluepawn3";

            }else if(currentPawn.getPositionTop() == player2Pawn4.getPositionTop() && currentPawn.getPositionLeft() == player2Pawn4.getPositionLeft()){
                game.getPlayer2().getPawn4().setPositionLeft(home.getBlue4Left());
                game.getPlayer2().getPawn4().setPositionTop(home.getBlue4Top());
                game.getPlayer2().getPawn4().setOnBoard(false);
                game.getPlayer2().getPawn4().setMoveStatus(0);
                returnPawn = game.getPlayer2().getPawn4();
                namePawn = "bluepawn4";
            }else{
              //  log.info("Die Pawns von Player3 schlagen keinen Pawn von Player2");
            }

            //Player4 und currentPlayer Pawns
            if(currentPawn.getPositionTop() == player4Pawn1.getPositionTop() && currentPawn.getPositionLeft() == player4Pawn1.getPositionLeft()){
                game.getPlayer4().getPawn1().setPositionLeft(home.getGreen1Left());
                game.getPlayer4().getPawn1().setPositionTop(home.getGreen1Top());
                game.getPlayer4().getPawn1().setOnBoard(false);
                game.getPlayer4().getPawn1().setMoveStatus(0);
                returnPawn = game.getPlayer4().getPawn1();
                namePawn = "greenpawn1";

            }else if(currentPawn.getPositionTop() == player4Pawn2.getPositionTop() && currentPawn.getPositionLeft() == player4Pawn2.getPositionLeft()){
                game.getPlayer4().getPawn2().setPositionLeft(home.getGreen2Left());
                game.getPlayer4().getPawn2().setPositionTop(home.getGreen2Top());
                game.getPlayer4().getPawn2().setOnBoard(false);
                game.getPlayer4().getPawn2().setMoveStatus(0);
                returnPawn = game.getPlayer4().getPawn2();
                namePawn = "greenpawn2";

            }else if(currentPawn.getPositionTop() == player4Pawn3.getPositionTop() && currentPawn.getPositionLeft() == player4Pawn3.getPositionLeft()){
                game.getPlayer4().getPawn3().setPositionLeft(home.getGreen3Left());
                game.getPlayer4().getPawn3().setPositionTop(home.getGreen3Top());
                game.getPlayer4().getPawn3().setOnBoard(false);
                game.getPlayer4().getPawn3().setMoveStatus(0);
                returnPawn = game.getPlayer4().getPawn3();
                namePawn = "greenpawn3";

            }else if(currentPawn.getPositionTop() == player4Pawn4.getPositionTop() && currentPawn.getPositionLeft() == player4Pawn4.getPositionLeft()){
                game.getPlayer4().getPawn4().setPositionLeft(home.getGreen4Left());
                game.getPlayer4().getPawn4().setPositionTop(home.getGreen4Top());
                game.getPlayer4().getPawn4().setOnBoard(false);
                game.getPlayer4().getPawn4().setMoveStatus(0);
                returnPawn = game.getPlayer4().getPawn4();
                namePawn = "greenpawn4";
            }else{
               // log.info("Die Pawns von Player3 schlagen keinen Pawn von Player4");
            }

        } else if (currentPlayer.equals(player4)) {

            //Player1 und currentPlayer Pawns
            if(currentPawn.getPositionTop() == player1Pawn1.getPositionTop() && currentPawn.getPositionLeft() == player1Pawn1.getPositionLeft()){
                game.getPlayer1().getPawn1().setPositionLeft(home.getRed1Left());
                game.getPlayer1().getPawn1().setPositionTop(home.getRed1Top());
                game.getPlayer1().getPawn1().setOnBoard(false);
                game.getPlayer1().getPawn1().setMoveStatus(0);
                returnPawn = game.getPlayer1().getPawn1();
                namePawn = "redpawn1";

            }else if(currentPawn.getPositionTop() == player1Pawn2.getPositionTop() && currentPawn.getPositionLeft() == player1Pawn2.getPositionLeft()){
                game.getPlayer1().getPawn2().setPositionLeft(home.getRed2Left());
                game.getPlayer1().getPawn2().setPositionTop(home.getRed2Top());
                game.getPlayer1().getPawn2().setOnBoard(false);
                game.getPlayer1().getPawn2().setMoveStatus(0);
                returnPawn = game.getPlayer1().getPawn2();
                namePawn = "redpawn2";

            }else if(currentPawn.getPositionTop() == player1Pawn3.getPositionTop() && currentPawn.getPositionLeft() == player1Pawn3.getPositionLeft()){
                game.getPlayer1().getPawn3().setPositionLeft(home.getRed3Left());
                game.getPlayer1().getPawn3().setPositionTop(home.getRed3Top());
                game.getPlayer1().getPawn3().setOnBoard(false);
                game.getPlayer1().getPawn3().setMoveStatus(0);
                returnPawn = game.getPlayer1().getPawn3();
                namePawn = "redpawn3";

            }else if(currentPawn.getPositionTop() == player1Pawn4.getPositionTop() && currentPawn.getPositionLeft() == player1Pawn4.getPositionLeft()){
                game.getPlayer1().getPawn4().setPositionLeft(home.getRed4Left());
                game.getPlayer1().getPawn4().setPositionTop(home.getRed4Top());
                game.getPlayer1().getPawn4().setOnBoard(false);
                game.getPlayer1().getPawn4().setMoveStatus(0);
                returnPawn = game.getPlayer1().getPawn4();
                namePawn ="redpawn4";
            }else{
               // log.info("Die Pawns von Player4 schlagen keinen Pawn von Player1");
            }


            //Player2 und currentPlayer Pawns
            if(currentPawn.getPositionTop() == player2Pawn1.getPositionTop() && currentPawn.getPositionLeft() == player2Pawn1.getPositionLeft()){
                game.getPlayer2().getPawn1().setPositionLeft(home.getBlue1Left());
                game.getPlayer2().getPawn1().setPositionTop(home.getBlue1Top());
                game.getPlayer2().getPawn1().setOnBoard(false);
                game.getPlayer2().getPawn1().setMoveStatus(0);
                returnPawn = game.getPlayer2().getPawn1();
                namePawn = "bluepawn1";


            }else if(currentPawn.getPositionTop() == player2Pawn2.getPositionTop() && currentPawn.getPositionLeft() == player2Pawn2.getPositionLeft()){
                game.getPlayer2().getPawn2().setPositionLeft(home.getBlue2Left());
                game.getPlayer2().getPawn2().setPositionTop(home.getBlue2Top());
                game.getPlayer2().getPawn2().setOnBoard(false);
                game.getPlayer2().getPawn2().setMoveStatus(0);
                returnPawn = game.getPlayer2().getPawn2();
                namePawn="bluepawn2";

            }else if(currentPawn.getPositionTop() == player2Pawn3.getPositionTop() && currentPawn.getPositionLeft() == player2Pawn3.getPositionLeft()){
                game.getPlayer2().getPawn3().setPositionLeft(home.getBlue3Left());
                game.getPlayer2().getPawn3().setPositionTop(home.getBlue3Top());
                game.getPlayer2().getPawn3().setOnBoard(false);
                game.getPlayer2().getPawn3().setMoveStatus(0);
                returnPawn = game.getPlayer2().getPawn3();
                namePawn = "bluepawn3";

            }else if(currentPawn.getPositionTop() == player2Pawn4.getPositionTop() && currentPawn.getPositionLeft() == player2Pawn4.getPositionLeft()){
                game.getPlayer2().getPawn4().setPositionLeft(home.getBlue4Left());
                game.getPlayer2().getPawn4().setPositionTop(home.getBlue4Top());
                game.getPlayer2().getPawn4().setOnBoard(false);
                game.getPlayer2().getPawn4().setMoveStatus(0);
                returnPawn = game.getPlayer2().getPawn4();
                namePawn = "bluepawn4";
            }else{
               // log.info("Die Pawns von Player4 schlagen keinen Pawn von Player2");
            }

            //Player3 und currentPlayer Pawns
            if(currentPawn.getPositionTop() == player3Pawn1.getPositionTop() && currentPawn.getPositionLeft() == player3Pawn1.getPositionLeft()){
                game.getPlayer3().getPawn1().setPositionLeft(home.getYellow1Left());
                game.getPlayer3().getPawn1().setPositionTop(home.getYellow1Top());
                game.getPlayer3().getPawn1().setOnBoard(false);
                game.getPlayer3().getPawn1().setMoveStatus(0);
                returnPawn = game.getPlayer3().getPawn1();
                namePawn = "yellowpawn1";

            }else if(currentPawn.getPositionTop() == player3Pawn2.getPositionTop() && currentPawn.getPositionLeft() == player3Pawn2.getPositionLeft()){
                game.getPlayer3().getPawn2().setPositionLeft(home.getYellow2Left());
                game.getPlayer3().getPawn2().setPositionTop(home.getYellow2Top());
                game.getPlayer3().getPawn2().setOnBoard(false);
                game.getPlayer3().getPawn2().setMoveStatus(0);
                returnPawn = game.getPlayer3().getPawn2();
                namePawn = "yellowpawn2";

            }else if(currentPawn.getPositionTop() == player3Pawn3.getPositionTop() && currentPawn.getPositionLeft() == player3Pawn3.getPositionLeft()){
                game.getPlayer3().getPawn3().setPositionLeft(home.getYellow3Left());
                game.getPlayer3().getPawn3().setPositionTop(home.getYellow3Top());
                game.getPlayer3().getPawn3().setOnBoard(false);
                game.getPlayer3().getPawn3().setMoveStatus(0);
                returnPawn = game.getPlayer3().getPawn3();
                namePawn = "yellowpawn3";

            }else if(currentPawn.getPositionTop() == player3Pawn4.getPositionTop() && currentPawn.getPositionLeft() == player3Pawn4.getPositionLeft()){
                game.getPlayer3().getPawn4().setPositionLeft(home.getYellow4Left());
                game.getPlayer3().getPawn4().setPositionTop(home.getYellow4Top());
                game.getPlayer3().getPawn4().setOnBoard(false);
                game.getPlayer3().getPawn4().setMoveStatus(0);
                returnPawn = game.getPlayer3().getPawn4();
                namePawn = "yellowpawn4";
            }else{
                //log.info("Die Pawns von Player4 schlagen keinen Pawn von Player3");
            }

        } else {
            log.info("CurrentPlayer could not be verified in toss");
        }

        tossReturn.setPawn(returnPawn);
        tossReturn.setPawnName(namePawn);




        return tossReturn;

    }public boolean checkWin(Player currentPlayer){

        boolean returnWin = false;

        //Es werden die letzten vier Positionen des aktuellen Players abgefragt, falls jede mit einem der Pawns besetzt ist, gewinnt er das Spiel
        //Es gewinnt der erste der alle vier auf den letzten vier Postionen hat und das Spiel wird beendet

        int lastOne = 43;
        int lastTwo = 42;
        int lastThree = 41;
        int lastFour = 40;

        int moveStatusPawn1 = currentPlayer.getPawn1().getMoveStatus();
        int moveStatusPawn2 = currentPlayer.getPawn2().getMoveStatus();
        int moveStatusPawn3 = currentPlayer.getPawn3().getMoveStatus();
        int moveStatusPawn4 = currentPlayer.getPawn4().getMoveStatus();

        if(lastOne == moveStatusPawn1 || lastOne == moveStatusPawn4 || lastOne == moveStatusPawn2 || lastOne == moveStatusPawn3){

            if(lastTwo == moveStatusPawn1 || lastTwo == moveStatusPawn4 || lastTwo == moveStatusPawn2 || lastTwo == moveStatusPawn3){

                if(lastThree == moveStatusPawn1 || lastThree == moveStatusPawn4 || lastThree == moveStatusPawn2 || lastThree == moveStatusPawn3){

                    if(lastFour == moveStatusPawn1 || lastFour == moveStatusPawn4 || lastFour == moveStatusPawn2 || lastFour == moveStatusPawn3){
                        returnWin = true;
                    }else{
                        log.info("lastFour nicht besetzt");
                    }

                }else{
                  //  log.info("lastThree nicht besetzt");
                }

            }else{
               // log.info("lastTwo nicht besetzt");
            }

        }else{
           // log.info("lastOne nicht besetzt");
        }

        return returnWin;

    }

    public boolean generalStuck(Player currentPlayer, int diceNumber){

        boolean stuck = false;
        Pawn pawn1 = currentPlayer.getPawn1();
        Pawn pawn2 = currentPlayer.getPawn2();
        Pawn pawn3 = currentPlayer.getPawn3();
        Pawn pawn4 = currentPlayer.getPawn4();

        //Es wird geprüft, ob keine 6 gewürfelt wurde und ob alle Spieler noch nicht am Feld sind

        if(diceNumber != 6) {
            if(!pawn1.isOnBoard()){
                if(!pawn2.isOnBoard()){
                    if(!pawn3.isOnBoard()){
                        if(!pawn4.isOnBoard()){
                            stuck = true;
                        }
                    }
                }
            }
        }

        log.info("generalStuck Ist: {}", stuck);



        return stuck;

    }

    public boolean specificStuck(Pawn currentPawn, Player currentPlayer, int diceNumber) {

        boolean stuck = false;

        Pawn pawn1 = currentPlayer.getPawn1();
        Pawn pawn2 = currentPlayer.getPawn2();
        Pawn pawn3 = currentPlayer.getPawn3();
        Pawn pawn4 = currentPlayer.getPawn4();

        //Es wird geprüft, ob die aktuelle Position des Pawns mit einem anderen eigenen Pawn übereinstimmt


        if (pawn1.getPositionTop() == currentPawn.getPositionTop() && pawn1.getPositionLeft() == currentPawn.getPositionLeft()) {




                if (currentPawn.getPositionTop() == pawn2.getPositionTop() && currentPawn.getPositionLeft() == pawn2.getPositionLeft()) {
                    stuck = true;

                } else if (currentPawn.getPositionTop() == pawn3.getPositionTop() && currentPawn.getPositionLeft() == pawn3.getPositionLeft()) {
                    stuck = true;

                } else if (currentPawn.getPositionTop() == pawn4.getPositionTop() && currentPawn.getPositionLeft() == pawn4.getPositionLeft()) {
                    stuck = true;
                } else {
                   // log.info("CurrentPawn ist mit keinem Pawn1 gleich");
                }


        } else if (pawn2.getPositionTop() == currentPawn.getPositionTop() && pawn2.getPositionLeft() == currentPawn.getPositionLeft()) {



                if (currentPawn.getPositionTop() == pawn1.getPositionTop() && currentPawn.getPositionLeft() == pawn1.getPositionLeft()) {
                    stuck = true;
                    log.info("Pawn2 = Pawn1: {}", stuck);
                } else if (currentPawn.getPositionTop() == pawn3.getPositionTop() && currentPawn.getPositionLeft() == pawn3.getPositionLeft()) {
                    stuck = true;
                    log.info("Pawn2 = Pawn3: {}", stuck);
                } else if (currentPawn.getPositionTop() == pawn4.getPositionTop() && currentPawn.getPositionLeft() == pawn4.getPositionLeft()) {
                    stuck = true;
                    log.info("Pawn2 = Pawn4: {}", stuck);
                } else {
                    log.info("CurrentPawn ist mit keinem Pawn von Player2(Blue) gleich");
                }


        } else if (pawn3.getPositionTop() == currentPawn.getPositionTop() && pawn3.getPositionLeft() == currentPawn.getPositionLeft()) {


                if (currentPawn.getPositionTop() == pawn1.getPositionTop() && currentPawn.getPositionLeft() == pawn1.getPositionLeft()) {
                    stuck = true;
                    log.info("Pawn3 = Pawn1: {}", stuck);
                } else if (currentPawn.getPositionTop() == pawn2.getPositionTop() && currentPawn.getPositionLeft() == pawn2.getPositionLeft()) {
                    stuck = true;
                    log.info("Pawn3 = Pawn2: {}", stuck);
                } else if (currentPawn.getPositionTop() == pawn4.getPositionTop() && currentPawn.getPositionLeft() == pawn4.getPositionLeft()) {
                    stuck = true;
                    log.info("Pawn3= Pawn4: {}", stuck);
                } else {
                   // log.info("CurrentPawn ist mit keinem Pawn von Player2(Blue) gleich");
                }




        } else if (pawn4.getPositionTop() == currentPawn.getPositionTop() && pawn4.getPositionLeft() == currentPawn.getPositionLeft()) {



                if (currentPawn.getPositionTop() == pawn1.getPositionTop() && currentPawn.getPositionLeft() == pawn1.getPositionLeft()) {
                    stuck = true;
                } else if (currentPawn.getPositionTop() == pawn2.getPositionTop() && currentPawn.getPositionLeft() == pawn2.getPositionLeft()) {
                    stuck = true;
                } else if (currentPawn.getPositionTop() == pawn3.getPositionTop() && currentPawn.getPositionLeft() == pawn3.getPositionLeft()) {
                    stuck = true;
                } else {
                   // log.info("CurrentPawn ist mit keinem Pawn von Player2(Blue) gleich");
                }




        }else{
            log.info("specificStuck: Pawn wird nicht blockiert");
        }





        log.info("specificStuck moveStatus: {}", currentPawn.getMoveStatus());


        //Es wird geprüft, ob der Pawn bereits am Ende seiner Route angelangt ist
        if (currentPawn.getMoveStatus() > 43) {
            stuck = true;
            log.info("Route blockiert");
            log.info("Wert von Stuck in Route: {}", stuck);

            }else{
            log.info("route: {}", currentPawn.getMoveStatus());
        }

        //Es wird geprüft, ob der Pawn nicht am Feld ist und die Würfelzahl ungleich 6 ist
        if(!currentPawn.isOnBoard() && diceNumber != 6){
            stuck = true;
            log.info("CurrentPawn ist nicht am Board und diceNumber ungleich 6");
        }

        log.info("specificStuck ist: {}", stuck);


        return stuck;


    }



}



