package com.example.SeoWooJang_comp228lab5;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Player> table;

    @FXML
    private TableColumn<Player, String> taddress;

    @FXML
    private TableColumn<Player, String> tdate;

    @FXML
    private TableColumn<Player, String> tfirst;

    @FXML
    private TableColumn<Player, String> tid;

    @FXML
    private TableColumn<Player, String> tlast;

    @FXML
    private TableColumn<Player, String> tphone;

    @FXML
    private TableColumn<Player, String> tpostal;

    @FXML
    private TableColumn<Player, String> tprovince;

    @FXML
    private TableColumn<Player, String> tscore;

    @FXML
    private TableColumn<Player, String> ttitle;


    @FXML
    void initialize() {
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'view.fxml'.";
        assert taddress != null : "fx:id=\"taddress\" was not injected: check your FXML file 'view.fxml'.";
        assert tdate != null : "fx:id=\"tdate\" was not injected: check your FXML file 'view.fxml'.";
        assert tfirst != null : "fx:id=\"tfirst\" was not injected: check your FXML file 'view.fxml'.";
        assert tid != null : "fx:id=\"tid\" was not injected: check your FXML file 'view.fxml'.";
        assert tlast != null : "fx:id=\"tlast\" was not injected: check your FXML file 'view.fxml'.";
        assert tphone != null : "fx:id=\"tphone\" was not injected: check your FXML file 'view.fxml'.";
        assert tpostal != null : "fx:id=\"tpostal\" was not injected: check your FXML file 'view.fxml'.";
        assert tprovince != null : "fx:id=\"tprovince\" was not injected: check your FXML file 'view.fxml'.";
        assert tscore != null : "fx:id=\"tscore\" was not injected: check your FXML file 'view.fxml'.";
        assert ttitle != null : "fx:id=\"ttitle\" was not injected: check your FXML file 'view.fxml'.";

        try {
            // Create connection and statement
            Connection conn = null;
            conn = DriverManager.getConnection("jdbc:oracle:thin:@199.212.26.208:1521:SQLD", "COMP228_F22_SY_54", "password");
            System.out.println("Connection to SQLite has been established.");

            Statement stmt = null;
            ResultSet rs = null;

            // Create query and execute it
            String query = "SELECT * FROM PlayerAndGame LEFT JOIN Player ON PlayerAndGame.player_id = Player.player_id LEFT JOIN game ON PlayerAndGame.player_id = Game.game_id";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            // Set factory for columns to player properties
            tid.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPlayer_id()));
            tfirst.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirst_name()));
            tlast.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLast_name()));
            taddress.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
            tpostal.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPostal_code()));
            tprovince.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProvince()));
            tphone.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhone()));
            tdate.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPlaying_date()));
            ttitle.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGameTitle()));
            tscore.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getScore()));

            table.getItems().clear();

            // Get the data from the database and display it in the table
            while(rs.next()) {

                // Get the data from the database
                String player_id = rs.getString("player_id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String pphone = rs.getString("phone_number");
                String paddress = rs.getString("address");
                String postal_code = rs.getString("postal_code");
                String pprovince = rs.getString("province");
                String game_title = rs.getString("game_title");
                String game_date = rs.getString("playing_date").replace("00:00:00.0", "");
                String game_score = rs.getString("score");

                // Add data to table
                table.getItems().add(new Player(player_id, first_name, last_name, pphone, paddress, postal_code, pprovince, game_title, game_date, game_score));
            }

            // Set the table's size
            table.setPrefSize(1000, 500);

            // Show the table
            table.setVisible(true);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Player class
    public static class Player {
        public String getPlayer_id() {
            return player_id;
        }

        public String getFirst_name() {
            return first_name;
        }

        public String getLast_name() {
            return last_name;
        }

        public String getPhone() {
            return phone_number;
        }

        public String getAddress() {
            return address;
        }

        public String getPostal_code() {
            return postal_code;
        }

        public String getProvince() {
            return province;
        }

        public String getGameTitle() {
            return game_title;
        }

        public String getPlaying_date() {
            return playing_date;
        }

        public String getScore() {
            return score;
        }

        // Declare variables
        private String player_id;
        private String first_name;
        private String last_name;
        private String phone_number;
        private String address;
        private String postal_code;
        private String province;
        private String game_title;
        private String playing_date;
        private String score;

        // Constructor
        public Player(String player_id, String first_name, String last_name, String phone_number, String address, String postal_code, String province, String game_title, String playing_date, String score) {
            this.player_id = player_id;
            this.first_name = first_name;
            this.last_name = last_name;
            this.phone_number = phone_number;
            this.address = address;
            this.postal_code = postal_code;
            this.province = province;
            this.game_title = game_title;
            this.playing_date = playing_date;
            this.score = score;
        }
    }
}
