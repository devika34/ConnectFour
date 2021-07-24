package com.internshala.connectfour;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.util.Optional;

public class Main extends Application {
    private Controller controller;
    @Override
    public void start(Stage primaryStage) throws Exception{
       FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
        GridPane rootGridPane =  loader.load();
        controller= loader.getController();
        controller.createPlayground();
        MenuBar menuBar = createMenu();
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        Pane menuPane= (Pane) rootGridPane.getChildren().get(0);
        menuPane.getChildren().add(menuBar);

        Scene scene = new Scene(rootGridPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Connect Four");
        primaryStage.setResizable(false);
        primaryStage.show();

    }
    public MenuBar createMenu(){
        //file menu
        Menu fileMenu =  new Menu("File");
        MenuItem newGame = new MenuItem("New game");

        newGame.setOnAction(event -> newGame());

        MenuItem resetGame= new MenuItem("Reset game");

        resetGame.setOnAction(event -> reset());

        SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem();
        MenuItem exitGame = new MenuItem("Exit game");
        exitGame.setOnAction(event -> {
            exitGame();
        });

        fileMenu.getItems().addAll(newGame,resetGame,separatorMenuItem,exitGame);

        //help menu
        Menu helpMenu=new Menu("Help");
        MenuItem aboutGame = new MenuItem("About Connect4");
        aboutGame.setOnAction(event -> {
            aboutConnect4();
        });
        SeparatorMenuItem separator = new SeparatorMenuItem();
        MenuItem aboutMe = new MenuItem("About Me");
        aboutMe.setOnAction(event -> aboutMe());
        helpMenu.getItems().addAll(aboutGame,separator,aboutMe);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, helpMenu);
        return menuBar;

    }

    private void reset() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Reset Game");
        alert.setHeaderText("Are you sure you want to reset the game?");
        alert.setContentText("Resetting the game will delete the previous score.");
        ButtonType yesBtn = new ButtonType("Yes, Reset");
        ButtonType noBtn = new ButtonType("No, Go Back");
        alert.getButtonTypes().setAll(yesBtn , noBtn);
        Optional<ButtonType> clickedBtn = alert.showAndWait();
        if(clickedBtn.isPresent() && clickedBtn.get() == yesBtn){
            controller.resetGame();
        }
    }

    private void newGame() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("New Game");
        alert.setHeaderText("Are you sure you want to start afresh?");
        alert.setContentText("Starting a new game will delete the previous score.");
        ButtonType yesBtn = new ButtonType("Yes");
        ButtonType noBtn = new ButtonType("No");
        alert.getButtonTypes().setAll(yesBtn , noBtn);
        Optional<ButtonType> clickedBtn = alert.showAndWait();
        if(clickedBtn.isPresent() && clickedBtn.get() == yesBtn){
            controller.resetGame();
        }
    }

    private void aboutMe() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About The Developer");
        alert.setHeaderText("Devika Sapra");
        alert.setContentText("I love to play around with code and create games."+
                " Connect Four is one of them. In free time I like to spend time with near and dear ones.");
        alert.show();

    }

    private void aboutConnect4() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About Connect Four");
        alert.setHeaderText("How To Play?");
        alert.setContentText("Connect Four is a two-player connection game in which the players first choose a color and then " +
                "take turns dropping colored discs from the top into a seven-column, six-row vertically suspended grid. The pieces " +
                "fall straight down, occupying the next available space within the column. The objective of the game is to be the" +
                " first to form a horizontal, vertical, or diagonal line of four of one's own discs. Connect Four is a solved game." +
                " The first player can always win by playing the right moves.");
        alert.show();

    }

    private void exitGame() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Exit Game");
        alert.setHeaderText("Are you sure you want to quit?");
        alert.setContentText("You can play for some more time with your near and dear ones.");
        ButtonType yesBtn = new ButtonType("Yes, Quit");
        ButtonType noBtn = new ButtonType("No, Play More");
        alert.getButtonTypes().setAll(yesBtn , noBtn);
        Optional<ButtonType> clickedBtn = alert.showAndWait();
        if(clickedBtn.isPresent() && clickedBtn.get() == yesBtn){
            Platform.exit();
            System.exit(0);
        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}
