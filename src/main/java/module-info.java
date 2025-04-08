module edu.farmingdale.databasejavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.farmingdale.databasejavafx to javafx.fxml;
    exports edu.farmingdale.databasejavafx;
}