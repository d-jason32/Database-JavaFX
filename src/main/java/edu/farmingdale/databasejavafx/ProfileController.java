package edu.farmingdale.databasejavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;

public class ProfileController {

    @FXML
    void backButtonAction(ActionEvent event) throws IOException {
        MainApplication.setRoot("main_menu");
    }

    @FXML
    void uploadProfileButton(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a Profile Picture");

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Open File explorer
        fileChooser.showOpenDialog(stage);
    }
}
