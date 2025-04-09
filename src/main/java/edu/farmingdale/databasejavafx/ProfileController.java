package edu.farmingdale.databasejavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class ProfileController {

    @FXML
    void backButtonAction(ActionEvent event) throws IOException {
        MainApplication.setRoot("main_menu");
    }
}
