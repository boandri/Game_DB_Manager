package com.example.SeoWooJang_comp228lab5;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;

public class EditController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private TextField gdate;

    @FXML
    private TextField gscore;

    @FXML
    private TextField gtitle;

    @FXML private TextField gid;

    @FXML
    private URL location;

    @FXML
    private TextField address;

    @FXML
    private TextField first;

    @FXML
    private TextField id;

    @FXML
    private TextField last;

    @FXML
    private TextField phone;

    @FXML
    private TextField postal;

    @FXML
    private TextField province;

    @FXML
    void findPlayer(ActionEvent event) {
        // Get the text from the text fields
        String toFind = id.getText();

        // Create a connection to the database
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@199.212.26.208:1521:SQLD", "COMP228_F22_SY_54", "password");
            System.out.println("Connection to SQLite has been established.");
            // Get the player from the database
            String sql = "SELECT * FROM Player WHERE player_id = " + toFind;

            // Prepare the statement
            Statement stmt = null;

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                first.setText(rs.getString("first_name"));
                last.setText(rs.getString("last_name"));
                address.setText(rs.getString("address"));
                postal.setText(rs.getString("postal_code"));
                province.setText(rs.getString("province"));
                phone.setText(rs.getString("phone_number"));
            }

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }


    @FXML
    void updateInfo(ActionEvent event) {
        // Check if data is retrieved
        if (first.getText().isEmpty() || last.getText().isEmpty() || address.getText().isEmpty() || postal.getText().isEmpty() || province.getText().isEmpty() || phone.getText().isEmpty()) {
            // Create a dialog box
            showInfo( "Please enter a valid ID");
        } else {

            // Get the text from the text fields
            String firstName = first.getText();
            String lastName = last.getText();

            String addressName = address.getText();
            String postalName = postal.getText();
            String provinceName = province.getText();
            String phoneName = phone.getText();

            String pId = id.getText();

            // Create a connection to the database
            Connection conn = null;

            try {
                conn = DriverManager.getConnection("jdbc:oracle:thin:@199.212.26.208:1521:SQLD", "COMP228_F22_SY_54", "password");
                System.out.println("Connection to SQLite has been established.");

                // Update the player in the database
                Statement stmt = null;
                stmt = conn.createStatement();

                // Execute the update
                stmt.executeUpdate("UPDATE Player SET first_name = '" + firstName + "', last_name = '" + lastName + "', address = '" + addressName + "', postal_code = '" + postalName + "', province = '" + provinceName + "', phone_number = '" + phoneName + "' WHERE player_id = " + pId);

                // Close the connection
                if (conn != null) {
                    conn.close();
                    showInfo("Player Updated");
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    @FXML
    void insertGame(ActionEvent event) {
        // Check if the text fields are empty
        if (first.getText().isEmpty() || last.getText().isEmpty() || address.getText().isEmpty() || postal.getText().isEmpty() || province.getText().isEmpty() || phone.getText().isEmpty()) {
            showInfo("Please retrieve the player first.");
        }
        else {
            // Check if the text fields are empty
            if (gtitle.getText().isEmpty() || gdate.getText().isEmpty() || gscore.getText().isEmpty() || gid.getText().isEmpty()) {
                showInfo("Please enter a valid game.");
            }
            else {

                // Get Game info
                String gameTitle = gtitle.getText();
                String gameDate = gdate.getText();
                String gameScore = gscore.getText();
                String gameId = gid.getText();



                // Insert the game into the database
                Connection conn = null;
                try {
                    conn = DriverManager.getConnection("jdbc:oracle:thin:@199.212.26.208:1521:SQLD", "COMP228_F22_SY_54", "password");
                    System.out.println("Connection to SQLite has been established.");

                    // Update the game in the database
                    Statement stmt = null;
                    stmt = conn.createStatement();

                    // Check if game exists in the database
                    String sql = "SELECT * FROM Game WHERE game_title = '" + gameTitle + "'";
                    ResultSet rs = stmt.executeQuery(sql);
                    int gameID=-1;
                    if (!rs.next()) {
                        // Execute the update
                        stmt.executeUpdate("INSERT INTO Game (game_title) VALUES ('" + gameTitle+ "')");

                        // Get the game id
                        sql = "SELECT game_id FROM Game WHERE game_title = '" + gameTitle + "'";
                        rs = stmt.executeQuery(sql);
                        while(rs.next()) {
                            gameID = rs.getInt("game_id");
                        }
                    }
                    else {
                        // Get the game id
                        gameID = rs.getInt("game_id");
                    }

                    // Execute the update
                    stmt.executeUpdate("INSERT INTO PlayerAndGame (player_game_id, game_id, player_id, playing_date, score) VALUES (" + id.getText()+gameId+ ", '" + gameId + "', '" + id.getText() + "', '" + gameDate + "', " + gameScore + ")");

                    showInfo("Game Inserted");

                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    showInfo("Please check all the info.");
                }
            }
        }
    }

    void showInfo(String msg) {
        Dialog dialog = new Dialog();
        dialog.getDialogPane().getButtonTypes().add(new ButtonType("Okay!", ButtonBar.ButtonData.CANCEL_CLOSE));
        dialog.setContentText(msg);
        dialog.show();
    }

    @FXML
    void initialize() {
        assert address != null : "fx:id=\"address\" was not injected: check your FXML file 'edit.fxml'.";
        assert first != null : "fx:id=\"first\" was not injected: check your FXML file 'edit.fxml'.";
        assert id != null : "fx:id=\"id\" was not injected: check your FXML file 'edit.fxml'.";
        assert last != null : "fx:id=\"last\" was not injected: check your FXML file 'edit.fxml'.";
        assert phone != null : "fx:id=\"phone\" was not injected: check your FXML file 'edit.fxml'.";
        assert postal != null : "fx:id=\"postal\" was not injected: check your FXML file 'edit.fxml'.";
        assert province != null : "fx:id=\"province\" was not injected: check your FXML file 'edit.fxml'.";

    }

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

}
