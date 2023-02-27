package com.example.SeoWooJang_comp228lab5;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void displayPlayer(ActionEvent event) throws IOException {
        // Display the edit.fxml
        FXMLLoader fxmlLoader = new FXMLLoader(GameApplication.class.getResource("edit.fxml"));
        GameApplication.scene = new Scene(fxmlLoader.load(), 620, 440);
        GameApplication.stage.setTitle("User Information");
        GameApplication.stage.setScene(GameApplication.scene);
    }

    @FXML
    void insertPlayer(ActionEvent event) throws IOException {
        // Display the player.fxml
        FXMLLoader fxmlLoader = new FXMLLoader(GameApplication.class.getResource("player.fxml"));
        GameApplication.scene = new Scene(fxmlLoader.load(), 420, 440);
        GameApplication.stage.setTitle("Insert a new player");
        GameApplication.stage.setScene(GameApplication.scene);
    }

    @FXML
    void viewPlayers() {
        // Load the new menu
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GameApplication.class.getResource("view.fxml"));
            GameApplication.scene = new Scene(fxmlLoader.load(), 920, 480);
            GameApplication.stage.setTitle("All Players");
            GameApplication.stage.setScene(GameApplication.scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {

    }

}
