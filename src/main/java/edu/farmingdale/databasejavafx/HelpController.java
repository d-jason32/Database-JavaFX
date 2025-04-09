package edu.farmingdale.databasejavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import java.io.IOException;

public class HelpController {
    @FXML
    private Button backButton;

    @FXML
    private BorderPane borderPane;

    @FXML
    private StackPane loginBox;

    @FXML
    private TextFlow words;

    @FXML
    void backButtonAction(ActionEvent event) throws IOException {
        MainApplication.setRoot("database_view");
    }

    @FXML
    void load(){
        Text paragraph = new Text("This is a nice block of text to read comfortably. This is a nice block of text to read comfortably.This is a nice block of text to read comfortably.This is a nice block of text to read comfortably.");
        paragraph.setFont(Font.font("Segoe UI", 14));
        words.getChildren().add(paragraph);
    }
}
