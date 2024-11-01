package org.example.swtlabormimobaleph;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class GUI extends Application {

    @FXML
    private TextField userField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LoginGUI.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        if(theSystem.getUserInformation(userField.getText(),passwordField.getText())) {
            openMainWindow(event);
        } else {
            System.out.println("Ungültige Anmeldeinformationen");
        }
    }

    private void openMainWindow(ActionEvent event) {
        Stage stage = new Stage();
        Parent root1 = null;
        try {
            root1 = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setTitle("Hauptfenster");
        stage.setScene(new Scene(root1));
        stage.show();

        Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        loginStage.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
