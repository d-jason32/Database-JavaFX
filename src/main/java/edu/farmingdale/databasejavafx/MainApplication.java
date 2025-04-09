package edu.farmingdale.databasejavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Moaath Alrajab
 * @author Jason Devaraj
 */
public class MainApplication extends Application {
    public static ConnDbOps cdbop;
    private static Scene scene;
    public Stage primaryStage;

    public static void main(String[] args) {
        //databaseCLI(args);
        cdbop = new ConnDbOps();
        launch();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setResizable(false);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("splash_screen.fxml"));
            scene = new Scene(root, 1200, 800);
            primaryStage.setScene(scene);
            primaryStage.show();
            changeScene();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeScene() {
        try {
            Parent newRoot = FXMLLoader.load(getClass().getResource("login.fxml"));

            Scene currentScene = primaryStage.getScene();
            Parent currentRoot = currentScene.getRoot();
            //currentScene.getStylesheets().add("com/example/module03_basicgui_db_interface/style.css");
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(4), currentRoot);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setOnFinished(e -> {
                scene.setRoot(newRoot);
            });
            fadeOut.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void databaseCLI(String[] args) {
        cdbop = new ConnDbOps();
        Scanner scan = new Scanner(System.in);
        char input;

        do {
            System.out.println(" ");
            System.out.println("============== Menu ==============");
            System.out.println("| To connect to DB,       press 'c' |");
            System.out.println("| To display all users,   press 'a' |");
            System.out.println("| To insert to the DB,    press 'i' |");
            System.out.println("| To query by ID,       press 'q' |");
            System.out.println("| To exit,                press 'e' |");
            System.out.println("===================================");
            System.out.print("Enter your choice: ");
            input = scan.next().charAt(0);

            switch (input) {
                case 'c':
                    cdbop.connectToDatabase();
                    break;

                case 'a':
                    cdbop.listAllUsers(); //all users in DB
                    break;

                case 'i':
                    System.out.print("Enter id: ");
                    String id = scan.next();
                    System.out.print("Enter first name: ");
                    String first_name = scan.next();
                    System.out.print("Enter last name: ");
                    String last_name = scan.next();
                    System.out.print("Enter department: ");
                    String department = scan.next();
                    System.out.print("Enter major: ");
                    String major = scan.next();
                    cdbop.insertUser(id, first_name, last_name, department, major);
                    break;

                case 'q':
                    System.out.print("Enter the name to query: ");
                    String queryName = scan.next();
                    cdbop.queryUserById(queryName); //Your queryUserByName method
                    break;

                case 'e':
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
            System.out.println(" ");
        } while (input != 'e');
        scan.close();
    }
}