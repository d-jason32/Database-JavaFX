module edu.farmingdale.databasejavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens edu.farmingdale.databasejavafx to javafx.fxml;
    exports edu.farmingdale.databasejavafx;
}