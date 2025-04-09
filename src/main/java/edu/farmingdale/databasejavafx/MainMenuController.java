package edu.farmingdale.databasejavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MainMenuController {
    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    void goToDatabase(ActionEvent event) throws IOException {
        MainApplication.setRoot("database_view");
    }

    @FXML
    void goToHelpPage(ActionEvent event) {

    }

    @FXML
    void goToProfile(ActionEvent event) throws IOException {
        MainApplication.setRoot("profile");
    }

    @FXML
    void logoutButton(ActionEvent event) throws IOException {
        MainApplication.setRoot("login");
    }
}
