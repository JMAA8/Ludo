const url = 'http://localhost:8080';
let stompClient;
let ultraGameId;
let ultraLogin;



function connectToSocket(gameId) {

    console.log("connecting to the game");
    let socket = new SockJS(url + "/abc");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log("connected to the frame: " + frame);
        stompClient.subscribe("/topic/game-progress/" + gameId, function (response) {
            let data = JSON.parse(response.body);
            console.log(data)


            if(data.theDicer) {

                displayDice(data);

            }else if(data.stuck) {

                displayGeneralStuck(data);
                displayCurrentPlayer(data);

            }if(data.theMover){

                displayMove(data);
                if(data.diceNumber != 6){
                    displayCurrentPlayer(data);
                }
                }


                if(data.tossedName != null){

                    displayToss(data);
                }
                if(data.winner){
                    displayWinner(data)
                }

        })
    })
}

function create_game() {
    let login = document.getElementById("login").value;
    if (login == null || login === '') {
        alert("Please enter login");
    } else {
        ultraLogin = login;
        $.ajax({
            url: url + "/game/start",
            type: 'POST',
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify({
                "login": login


            }),
            success: function (data) {
                ultraGameId = data.gameId;
               let colour = data.player1.colour;
                connectToSocket(ultraGameId);
                alert("Your created a game. Game id is: " + ultraGameId);
                alert("Your colour is: " + colour);
                let gameIdText = document.getElementById("gameIdText");
                gameIdText.innerText = "GameId: " + data.gameId;

                let yourColour = document.getElementById("yourColour");
                yourColour.innerText = "Your colour is: " + colour;
            },
            error: function (error) {
                console.log(error);
                alert("failed");
            }
        })
    }
}

function connect_game() {
    let login = document.getElementById("login").value;
    if (login == null || login === '') {
        alert("Please enter login");
    } else {
        ultraLogin = login;
        let gameId = document.getElementById("game_id").value;
        if (gameId == null || gameId === '') {
            alert("Please enter game id");
        }
        $.ajax({
            url: url + "/game/connect",
            type: 'POST',
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify({
                "player": {
                    "login": login
                },
                "gameId": gameId

            }),
            success: function (data) {
                gameId = data.gameId;
                ultraGameId = data.gameId;
               let colour = data.colour;
                connectToSocket(gameId);
                alert("Your joined a game. Game id is: " + gameId + "  " + "Your colour is : " + colour);
                let gameIdText = document.getElementById("gameIdText");
                gameIdText.innerText = "GameId: " + data.gameId;
                let yourColour = document.getElementById("yourColour");
                yourColour.innerText = "Your colour is: " + colour;
            },
            error: function (error) {
                console.log(error);
                alert("failed");
            }
        })
    }
}

function rollDice(){



        $.ajax({
            url: url + "/game/rollDice",
            type: 'post',
            datatype: "json",
            contentType: "application/json",
            data: JSON.stringify({
                "player": {
                    "login": ultraLogin
                },
                "gameId": ultraGameId

            }),
            success: function (data) {

                if(data.theDicer) {


                    displayDice(data);

                }else if(data.stuck){

                    displayGeneralStuck(data);

                }else {
                    alert("It is not your turn");
                }


            },
            error: function (error) {
                console.log(error);
            }

        })


}

function displayDice(data){
    let number = data.diceNumber;

    let dice = document.getElementById('dice');
    dice.style.backgroundImage = "url(Images/" + number + ".jpg)";
}

function displayGeneralStuck(data){
    let number = data.diceNumber;
    let dice = document.getElementById('dice');
    dice.style.backgroundImage = "url(Images/" + number + ".jpg)";

    setTimeout(resetDice,2000);

}

function resetDice(){
    dice.style.backgroundImage = "url(Images/dice.gif)";
}


function movePawn(color, pawn){



    let NumOfPaw = pawn;
    let CurrentColor = color;
    let CurrentPawn = CurrentColor + "pawn" + NumOfPaw;
    let doc = document.getElementById(CurrentPawn);
    let currLeft = Number(doc.style.left.replace(/[a-z]/g,''));
    let currTop = Number(doc.style.top.replace(/[a-z]/g,''));

    $.ajax({
        url: url + "/game/movePawn",
        type: 'post',
        datatype: "json",
        contentType: "application/json",
        data: JSON.stringify({
            "player": {
                "login": ultraLogin
            },
            "gameId": ultraGameId,
            "positionLeft": currLeft,
            "positionTop": currTop,
            "pawnName": CurrentPawn

        }),
        success: function (data) {

            //alert("Data has been sent");

            if(data.theMover){

           displayMove(data);
            }else{
                alert("You can not move big Man");
            }

            if(data.tossedName != null){

                displayToss(data);
            }
            if(data.winner){
                displayWinner()
            }


        },
        error: function (error) {
            console.log(error);
        }

    })
}

function displayMove(data){

    let CurrentPawn = data.pawnName;
    let doc = document.getElementById(CurrentPawn);
    doc.style.left = data.positionLeft + 'px';
     doc.style.top = data.positionTop + 'px';
    let dice = document.getElementById('dice');
    dice.style.backgroundImage = "url(Images/dice.gif)";


}
function displayToss(data){
    let tossedPawn = data.tossedName;
    let doc = document.getElementById(tossedPawn);
    doc.style.left = data.tossedLeft + 'px';
    doc.style.top = data.tossedTop + 'px';

}
function displayWinner(data){
    let winner = data.winnerName;
    alert(winner + " have won!!");
}

function displayCurrentPlayer(data){
    let text = document.getElementById('player');
    let colour = data.colour;
    switch (colour) {
        case "red": text.innerText = text.style.color = "blue"; break;
        case "blue": text.innerText = text.style.color = "yellow"; break;
        case "yellow": text.innerText = text.style.color = "green"; break;
        case "green": text.innerText = text.style.color = "red"; break;
    }
}