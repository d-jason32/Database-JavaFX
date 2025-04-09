package edu.farmingdale.databasejavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class LoginController {
    @FXML
    private BorderPane borderPane;

    @FXML
    private StackPane loginBox;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    @FXML
    void loginButtonAction(ActionEvent event) throws IOException {
        MainApplication.setRoot("main_menu");
    }

    @FXML
    void registerButtonAction(ActionEvent event) throws IOException {
        MainApplication.setRoot("registration");
    }
}
