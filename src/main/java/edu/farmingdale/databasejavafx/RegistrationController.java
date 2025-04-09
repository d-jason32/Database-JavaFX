package edu.farmingdale.databasejavafx;

import javafx.event.ActionEvent;

import java.io.IOException;

public class RegistrationController {
    public void RegisterButtonAction(ActionEvent actionEvent) {
    }

    public void backButtonAction(ActionEvent actionEvent) throws IOException {
        MainApplication.setRoot("login");
    }
}
