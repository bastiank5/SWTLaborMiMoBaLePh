package org.example.swtlabormimobaleph;

import javafx.application.Application;
import javafx.beans.Observable;
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
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Date;
import java.util.ResourceBundle;

public class GUI extends Application {
    @FXML
    private TextField userField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;

    @FXML
    private TableColumn<DailyEntry, String> CommendColumn;

    @FXML
    private TableColumn<DailyEntry, String> absenceColumn;

    @FXML
    private TableColumn<DailyEntry, LocalTime> beginColumn;

    @FXML
    private TableColumn<DailyEntry, LocalTime> pauseColumn;

    @FXML
    private TableColumn<DailyEntry, Date> dayColumn;

    @FXML
    private TableColumn<DailyEntry, Float> diffColumn;

    @FXML
    private TableColumn<DailyEntry, LocalTime> endColumn;

    @FXML
    private Label firstnameLabel;

    @FXML
    private TableColumn<DailyEntry, Float> hoursAsIsColumn;

    @FXML
    private TableColumn<DailyEntry, Float> hoursTargetColumn;

    @FXML
    private Label idLabel;

    @FXML
    private Label lastnameLabel;

    @FXML
    private TableView<DailyEntry> tableView;

    @FXML
    private TableColumn<DailyEntry, String> weekdayColumn;

    String weekday = "Tuesday"; // Tag der Woche, z.B. "Tuesday"
    Date date = new Date(); //  Datum als Beispiel
    LocalTime begin = LocalTime.of(8, 30); // Beginn  08:30 Uhr
    LocalTime end = LocalTime.of(17, 15); // Ende  17:15 Uhr
    LocalTime pause = LocalTime.of(0, 45); // 45 Minuten Pause
    String absence = "Vacation"; // Beispiel für Abwesenheit, z.B. "Vacation"
    String comment = "Top Tag"; // Kommentar, z.B. "Top Tag"
    float diff = -2f; //  Differenz, z.B. -2 Stunden

    float hoursTarget = 8.0f; // Zielstunden, z.B. 8 Stunden pro Tag
    float hoursAsIs = 6f; // Tatsächliche Stunden, z.B. 6 Stunden





    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LoginGUI.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));


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

    @FXML
    private void openMainWindow(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GUI.fxml"));
            Parent root1 = loader.load();

            // Get the controller instance associated with GUI.fxml
            GUI controller = loader.getController();
            
            controller.dayColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
            controller.weekdayColumn.setCellValueFactory(new PropertyValueFactory<>("weekday"));
            controller.beginColumn.setCellValueFactory(new PropertyValueFactory<>("begin"));
            controller.endColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
            controller.pauseColumn.setCellValueFactory(new PropertyValueFactory<>("pause"));
            controller.hoursTargetColumn.setCellValueFactory(new PropertyValueFactory<>("hoursTarget"));
            controller.hoursAsIsColumn.setCellValueFactory(new PropertyValueFactory<>("hoursAsIs"));
            controller.diffColumn.setCellValueFactory(new PropertyValueFactory<>("diff"));
            controller.absenceColumn.setCellValueFactory(new PropertyValueFactory<>("absence"));
            controller.CommendColumn.setCellValueFactory(new PropertyValueFactory<>("comment"));

            //Erstellung eiens Test DailyEntry
            ObservableList<DailyEntry> dailyEntries = FXCollections.observableArrayList(
                    new DailyEntry(date, begin, end, pause, absence, comment, diff, weekday, hoursTarget, hoursAsIs)
            );
            controller.tableView.setItems(dailyEntries);

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
