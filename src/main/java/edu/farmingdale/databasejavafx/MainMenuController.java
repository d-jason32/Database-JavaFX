package edu.farmingdale.databasejavafx;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainMenuController {
    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private BorderPane menuBorder;

    boolean isDarkMode = false;

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

    @FXML
    void changeToDark(ActionEvent event) {

        ObservableList<String> stylesheets = menuBorder.getStylesheets();
        stylesheets.clear(); // remove current theme

        if (isDarkMode) {
            stylesheets.add(getClass().getResource("styles.css").toExternalForm());
        } else {
            stylesheets.add(getClass().getResource("darkstyles.css").toExternalForm());
        }

        isDarkMode = !isDarkMode;
    }
}
