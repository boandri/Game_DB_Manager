package com.example.SeoWooJang_comp228lab5;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import java.sql.*;

public class GameController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField id;

    @FXML
    private TextField address;

    @FXML
    private TextField first;

    @FXML
    private TextField last;

    @FXML
    private TextField phone;

    @FXML
    private TextField postal;

    @FXML
    private TextField province;

    @FXML
    void goBack(ActionEvent event) {
        // Go back to the main menu
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GameApplication.class.getResource("main.fxml"));
            GameApplication.scene = new Scene(fxmlLoader.load(), 620, 240);
            GameApplication.stage.setTitle("Game Application");
            GameApplication.stage.setScene(GameApplication.scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void insertInfo(ActionEvent event) {
        // Get the text from the text fields
        String playerId = id.getText();

        String firstName = first.getText();
        String lastName = last.getText();

        String addressName = address.getText();
        String postalName = postal.getText();
        String provinceName = province.getText();
        String phoneName = phone.getText();


        // Create a connection to the database
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@199.212.26.208:1521:SQLD", "COMP228_F22_SY_54", "password");
            System.out.println("Connection to SQLite has been established.");
            // Create a query to insert the data into the database
            String query = "INSERT INTO player (player_id, first_name, last_name, address, postal_code, province, phone_number) VALUES ('" + playerId + "', '" + firstName + "', '" + lastName + "', '" + addressName + "', '" + postalName + "', '" + provinceName + "', '" + phoneName + "')";

            // Create a statement, execute the query, and close the connection
            Statement stmt = null;

            stmt = conn.createStatement();
            stmt.execute(query);

            Dialog dialog = new Dialog();
            dialog.getDialogPane().getButtonTypes().add(new ButtonType("Okay!", ButtonBar.ButtonData.CANCEL_CLOSE));
            dialog.setContentText("Data inserted successfully");
            dialog.show();

            conn.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void initialize() {
        assert address != null : "fx:id=\"address\" was not injected: check your FXML file 'player.fxml'.";
        assert first != null : "fx:id=\"first\" was not injected: check your FXML file 'player.fxml'.";
        assert last != null : "fx:id=\"last\" was not injected: check your FXML file 'player.fxml'.";
        assert phone != null : "fx:id=\"phone\" was not injected: check your FXML file 'player.fxml'.";
        assert postal != null : "fx:id=\"postal\" was not injected: check your FXML file 'player.fxml'.";
        assert province != null : "fx:id=\"province\" was not injected: check your FXML file 'player.fxml'.";
        assert id != null : "fx:id=\"playerId\" was not injected: check your FXML file 'player.fxml'.";
    }

}
