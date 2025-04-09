package edu.farmingdale.databasejavafx;

import javafx.event.ActionEvent;
import java.io.IOException;

/**
 * Registration controller class.
 * @author Jason Devaraj
 */
public class RegistrationController {
    public void RegisterButtonAction(ActionEvent actionEvent) {
    }

    /**
     * If the back button is clicked, the application changes to the login screen.
     * @param actionEvent
     * @throws IOException
     */
    public void backButtonAction(ActionEvent actionEvent) throws IOException {
        MainApplication.setRoot("login");
    }
}
