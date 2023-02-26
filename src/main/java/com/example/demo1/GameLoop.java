package com.example.demo1;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import java.util.ArrayList;

/**
 * Essentially a while loop implementing the game logic that is being transformed into a class with
 * recursive methods.
 */
public class GameLoop{
    // game object
    private Game game;
    // Different sections in the game scene
    private HBox top;
    private Pane middle;
    private HBox bottom;
    // If a user has won
    private boolean game_finished;
    // all the layouts needs to changed during the progress of a game in the game scene
    private Label endResults;
    private Button specialInfo;
    private Label dice1;
    private Label dice2;
    private Button rollDice;
    private Board b;
    private ArrayList<Circle> heads;
    private ArrayList<Rectangle> bodies;

    public GameLoop(Game game, HBox top, Pane middle, HBox bottom) {
        this.game = game;
        this.top = top;
        this.middle = middle;
        this.bottom = bottom;
        // for change in the view
        this.specialInfo = (Button) top.getChildren().get(top.getChildren().size() - 2);
        this.endResults = (Label) top.getChildren().get(top.getChildren().size() - 1);
        this.dice1 = (Label) bottom.getChildren().get(bottom.getChildren().size() - 3);
        this.dice2 = (Label) bottom.getChildren().get(bottom.getChildren().size() - 2);
        this.rollDice = (Button) bottom.getChildren().get(bottom.getChildren().size() - 1);

        this.b = game.getTest_board();
        this.heads = new ArrayList<>();
        this.bodies = new ArrayList<>();
        for (int i = 0; i < b.getNum_of_players(); i++){
            heads.add((Circle) middle.getChildren().get(b.getLanes() * b.getLength() + b.getNum_of_obstacles() + i*2));
            bodies.add((Rectangle) middle.getChildren().get(b.getLanes() * b.getLength() + b.getNum_of_obstacles() + i*2+1));
        }
        this.game_finished = false;
    }

    // Game begins
    public void run() {
        // set layouts
        rollDice.setDisable(false);
        for (int j = 0; j < game.getNum_of_players(); j++){
            Label playerLabel = (Label) bottom.getChildren().get(j);
            playerLabel.setFont(Font.font("Verdana", FontWeight.NORMAL, 12));
        }
        Label playerLabel = (Label) bottom.getChildren().get(0);
        playerLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 14));


        if (game_finished) {
            endResults.setText("Winner is: " + game.getPlayers().get(0).getName());
            stop();
            game_finished = true;
        }


        // game really begins
        // User roll the dice
        rollDice.setOnAction(e -> {


            Dice dice = new Dice();
            System.out.println("You: " + dice.getValue() + " " + dice.getDirection());
            System.out.println("Before: " + game.getPlayers().get(0).getPos());
            rollDice.setDisable(true);
            // User makes the move
            dice1.setText("Dice1: " + String.valueOf(dice.getValue()));
            dice2.setText("Dice2: " + dice.getDirection().toString());

            game_finished = game.move(dice, 0, heads, bodies);

            System.out.println("Now: " + game.getPlayers().get(0).getPos());
            System.out.println("-------------------------------");
            if (game_finished) {
                rollDice.setDisable(true);
                endResults.setText("Winner is: " + game.getPlayers().get(0).getName());
                stop();
            }

            // After a second the first Player made the run, continue to the next Player
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
                continueToRun(1);
            }));
            if (!game_finished) {
                timeline.play();
            }


        });
    }

    /**
     * A recursive function essentially imitating a for loop iterating through each Bot Player.
     *
     * @param no_ the number sequence of a Player in a Player array
     */
    public void continueToRun(int no_) {
        if (game_finished) {
            rollDice.setDisable(true);
            endResults.setText("Winner is: " + game.getPlayers().get(no_).getName());
            stop();
        }else {
            if (no_ == game.getNum_of_players() - 1) {
                System.out.println("=================================");
                run();
            }
        }

        // now bots make the move
        for (int j = 0; j < game.getNum_of_players(); j++){
            Label playerLabel = (Label) bottom.getChildren().get(j);
            playerLabel.setFont(Font.font("Verdana", FontWeight.NORMAL, 12));
        }
        Label playerLabel = (Label) bottom.getChildren().get(no_);
        playerLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 14));

        Dice dice = new Dice();
        dice1.setText("Dice1: " + String.valueOf(dice.getValue()));
        dice2.setText("Dice2: " + dice.getDirection().toString());
        System.out.println(game.getPlayers().get(no_).getName() + ": "
                + dice.getValue() + " " + dice.getDirection());
        System.out.println("Before: " + game.getPlayers().get(no_).getPos());

        game_finished = game.move(dice, no_, heads, bodies);

        System.out.println("Now: " + game.getPlayers().get(no_).getPos());
        System.out.println("-------------------------------");

        if (game_finished) {
            rollDice.setDisable(true);
            endResults.setText("Winner is: " + game.getPlayers().get(no_).getName());
            stop();
        }else {
            if (no_ == game.getNum_of_players() - 1) {
                System.out.println("=================================");
                run();
            }
        }

        if (no_ < game.getNum_of_players() - 1) {
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
                System.out.println("Printed after 1 second from FX thread");
                continueToRun(no_ + 1);
            }));
            if (!game_finished) {
                timeline.play();
            }
        }
    }

    // the game has finished
    public void stop() {
        System.out.println("ENdddddddd");
        rollDice.setDisable(true);
    }


}
