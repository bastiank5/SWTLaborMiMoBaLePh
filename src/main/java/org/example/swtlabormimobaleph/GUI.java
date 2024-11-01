package org.example.swtlabormimobaleph;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class GUI extends Application {

    @FXML
    private TextField userField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Label firstnameLabel;
    @FXML
    private Label lastnameLabel;
    @FXML
    private Label idLabel;


    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LoginGUI.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        if (theSystem.getUserInformation(userField.getText(), passwordField.getText())) {

            openMainWindow(event);
        } else {
            System.out.println("Ungültige Anmeldeinformationen");
        }
    }

    private void openMainWindow(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GUI.fxml"));
            Parent root1 = loader.load();

            // Get the controller instance associated with GUI.fxml
            GUI controller = loader.getController();

            // Set label values through the controller
            controller.firstnameLabel.setText(theSystem.currentUser.getFirstname());
            controller.lastnameLabel.setText(theSystem.currentUser.getLastname());
            controller.idLabel.setText(String.valueOf(theSystem.currentUser.getId()));

            Stage stage = new Stage();
            stage.setTitle("Hauptfenster");
            stage.setScene(new Scene(root1));
            stage.show();

            // Close the login window
            Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            loginStage.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    private void openMainWindow(ActionEvent event) {
//        Stage stage = new Stage();
//        Parent root1 = null;
//        try {
//            root1 = FXMLLoader.load(getClass().getResource("GUI.fxml"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        firstnameLabel.setText(theSystem.currentUser.getFirstname());
//        lastnameLabel.setText(theSystem.currentUser.getLastname());
//        idLabel.setText("" + theSystem.currentUser.getId());
//
//        stage.setTitle("Hauptfenster");
//        stage.setScene(new Scene(root1));
//
//
//        stage.show();
//
//        Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        loginStage.close();
//    }

    public static void main(String[] args) {
        launch(args);
    }
}
