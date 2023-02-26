package com.example.demo1;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The main entry for Javafx UI. Has mainly 3 scenes.
 *      1. Welcome Page
 *      2. Enter information page
 *      3. Game scene
 *
 */
public class HelloApplication extends Application {
    Scene scene1, scene2, scene3;

    /**
     * UI display will start here
     *
     * @param stage a Stage class instance
     */
    @Override
    public void start(Stage stage) {
        try {
            // First scene: Welcome Page
            Button bt = new Button("Let's Begin");
            bt.setOnAction(e -> stage.setScene(scene2));
            VBox home = new VBox(10, bt);

//            home.setStyle("-fx-background-color: blue;");

            home.setAlignment(Pos.CENTER);
            scene1 = new Scene(home, 1100, 600);
            stage.setTitle("Simon's Race");
            stage.setScene(scene1);

            // Second scene: User fill out information
            TextField username = new TextField("Please enter a user name");
            TextField num_of_lanes = new TextField("number of lanes: ");
            TextField length = new TextField("track length: ");
            TextField num_of_players = new TextField("number of players: ");
            TextField num_of_obstacles = new TextField("number of obstacles: ");
            Button bt3 = new Button("Enter game");

            GridPane secondPage = new GridPane();
            GridPane.setConstraints(username, 0,0);
            GridPane.setConstraints(num_of_lanes, 0,2);
            GridPane.setConstraints(length, 0,3);
            GridPane.setConstraints(num_of_players, 0,4);
            GridPane.setConstraints(num_of_obstacles, 0,5);
            GridPane.setConstraints(bt3, 0,6);
            secondPage.getChildren().addAll(username, num_of_lanes,length, num_of_players, num_of_obstacles, bt3);
            secondPage.setAlignment(Pos.CENTER);
            scene2 = new Scene(secondPage,1100,600);

            stage.show();


            // Third scene: Game scene. Using Scene builder.
            bt3.setOnAction(e -> {
                FXMLLoader loader = new FXMLLoader();
                BorderPane gameScene;
                try {
                    gameScene = (BorderPane)loader.load(getClass().getResource("/Game.fxml").openStream());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                // pass user specified information to the controller.
                GameController gameController = loader.getController();
                try {
                    gameController.passInfo(username.getText(), Integer.valueOf(num_of_lanes.getText()), Integer.valueOf(length.getText()),
                            Integer.valueOf(num_of_players.getText()),Integer.valueOf(num_of_obstacles.getText()) );
                }catch (Exception ex) {
                    gameController.passInfo(username.getText(), 2, 10, 2,1 );
                }

                scene3 = new Scene(gameScene,1100,600);
                stage.setScene(scene3);
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }



}