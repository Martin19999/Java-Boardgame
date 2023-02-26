package com.example.demo1;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class GameController {


    @FXML
    Pane pane;
    @FXML
    HBox topPane;
    @FXML
    HBox bottomPane;

    private String username = "";
    private int num_of_lanes;
    private int length;
    private int num_of_players;
    private int num_of_obs;
    Game thisGame;

    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            // Initialize the Game
            thisGame = new Game(username,num_of_lanes,length,num_of_players,num_of_obs);
            System.out.println(thisGame);


            // ---- 1 center Pane - game course
            //board
            Board board = thisGame.getTest_board();
            int lanes = board.getLanes();
            int length = board.getLength();
            // square
            int height = 50;
            int width = 50;
            // alignment: center
            int totalLength = width * length;
            int blankLength = 1100 - totalLength;
            int totalHeight = height * lanes;
            int blankHeight = 500 - totalHeight;

            // drawing the tracks
            for (int i = 0; i < lanes; i++){
                for (int j = 0; j < length; j++) {
                    Rectangle r = new Rectangle(width, height);
                    r.setFill(Color.AZURE);
                    r.setStroke(Color.BLACK);
                    r.setTranslateX((int)(blankLength/2) + j*width);
                    r.setTranslateY((int)(blankHeight/2) + i*height);
                    pane.getChildren().add(r);
                }
            }

            // drawing obstacles
            Obstacle[][] obstacles = board.getObstacle_coordinate();
            ArrayList<ImageView> imageViews = new ArrayList<>();
            for (int i = 0; i < obstacles.length; i++) {
                for (int j = 0; j < obstacles[i].length; j++) {
                    if (obstacles[i][j] != null) {
                        if (obstacles[i][j] instanceof Roadblock) {
                            ImageView roadblockView = new ImageView(new Image(getClass().getResourceAsStream(obstacles[i][j].getImage())));
                            roadblockView.setTranslateX((int)(blankLength/2) + i*width);
                            roadblockView.setTranslateY((int)(blankHeight/2) + j*height);
                            imageViews.add(roadblockView);
                        } else if (obstacles[i][j] instanceof Pub) {
                            ImageView pubView = new ImageView(new Image(getClass().getResourceAsStream(obstacles[i][j].getImage())));
                            pubView.setTranslateX((int)(blankLength/2) + i*width);
                            pubView.setTranslateY((int)(blankHeight/2) + j*height);
                            imageViews.add(pubView);
                        } else if (obstacles[i][j] instanceof Teleport) {
                            ImageView teleportView = new ImageView(new Image(getClass().getResourceAsStream(obstacles[i][j].getImage())));
                            teleportView.setTranslateX((int)(blankLength/2) + i*width);
                            teleportView.setTranslateY((int)(blankHeight/2) + j*height);
                            imageViews.add(teleportView);
                        }
                    }
                }
            }
            for (ImageView i: imageViews) {
                i.setFitWidth(width);
                i.setFitHeight(height);
                pane.getChildren().add(i);
            }

            // drawing the players
            Player[][] players = board.getPlayer_coordinate();
            Color[] colorList = {Color.BLACK, Color.YELLOWGREEN, Color.BROWN, Color.TOMATO, Color.THISTLE,
                    Color.TEAL, Color.SIENNA, Color.ROYALBLUE, Color.PURPLE, Color.ORANGE};

            // appending the Players, order is important!!
            for (Player p: thisGame.getPlayers()) {
                for (int i = 0; i < players.length; i++) {
                    for (int j = 0; j < players[i].length; j++) {
                        if (players[i][j] != null) {
                            if (players[i][j].equals(p)) {
                                Color color = colorList[p.getNo_()];
                                Circle head = new Circle((int)(blankLength/2) + i*width + 25, (int)(blankHeight/2) + j*height + 15,5);
                                Rectangle body = new Rectangle((int)(blankLength/2) + i*width + 15, (int)(blankHeight/2) + j*height + 20, 20,25);
                                head.setFill(color);
                                body.setFill(color);
                                body.setArcWidth(10);
                                body.setArcHeight(10);
                                pane.getChildren().add(head);
                                pane.getChildren().add(body);
                            }
                        }
                    }
                }
            }


            // ---- 2 top Pane - User name, announce winner and score if it reaches an end
            topPane.setPadding(new Insets(15, 12, 15, 12));
            topPane.setAlignment(Pos.CENTER);
            Label usernameField = new Label("Username: " + username);
            Label endResults = new Label(" ");
            Button stat = new Button("Start");
            usernameField.setAlignment(Pos.CENTER);
            endResults.setAlignment(Pos.CENTER);
            stat.setAlignment(Pos.CENTER);
            topPane.getChildren().addAll(usernameField, stat, endResults);
            usernameField.setMaxWidth(366);
            stat.setMaxWidth(367);
            endResults.setMaxWidth(366);
            HBox.setHgrow(usernameField, Priority.ALWAYS);
            HBox.setHgrow(stat, Priority.ALWAYS);
            HBox.setHgrow(endResults, Priority.ALWAYS);


            // ---- 3 bottom Pane - Game status
            bottomPane.setPadding(new Insets(15, 12, 15, 12));
            bottomPane.setAlignment(Pos.CENTER);


            ArrayList<Player> playerList = thisGame.getPlayers();
            for (int i = 0; i < playerList.size(); i++) {
                Player p = playerList.get(i);
                Label userLabel = new Label(p.getName());
                userLabel.setTextFill(colorList[i]);
                bottomPane.getChildren().add(userLabel);
                userLabel.setMaxWidth(80);
                userLabel.setAlignment(Pos.CENTER);
                HBox.setHgrow(userLabel, Priority.ALWAYS);
            }

            Label whosTurn = new Label("||");
            Label dice1 = new Label("Dice 1: ");
            Label dice2 = new Label("Dice 2: ");
            Button rollDice = new Button("Roll");
            bottomPane.getChildren().addAll(whosTurn, dice1, dice2, rollDice);
            whosTurn.setAlignment(Pos.CENTER);
            dice1.setAlignment(Pos.CENTER);
            dice2.setAlignment(Pos.CENTER);
            rollDice.setAlignment(Pos.CENTER);
            whosTurn.setMaxWidth(10);
            HBox.setHgrow(whosTurn, Priority.ALWAYS);
            dice1.setMaxWidth(70);
            HBox.setHgrow(dice1, Priority.ALWAYS);
            dice2.setMaxWidth(130);
            HBox.setHgrow(dice2, Priority.ALWAYS);
            rollDice.setMaxWidth(70);
            HBox.setHgrow(rollDice, Priority.ALWAYS);
            rollDice.setDisable(true);

            // begin game loop
            stat.setOnAction(e -> {
                stat.setDisable(true);
                GameLoop gameLoop = new GameLoop(thisGame, topPane, pane, bottomPane);
                gameLoop.run();


            });

        });
    }

    /**
     * A method to pass user information to the gameController.
     *
     * @param username this username of the person that is playing this game
     */
    public void passInfo(String username, int num_of_lanes, int length,int num_of_players, int num_of_obs) {
        if (username.equals("Please enter a user name") || username.equals("")){
            this.username = "Who are u";
        } else {
            this.username = username;
        }
        this.num_of_lanes = num_of_lanes;
        this.length = length;
        this.num_of_players = num_of_players;
        this.num_of_obs = num_of_obs;
    }





}
