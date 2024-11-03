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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Date;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class GUI extends Application {
    @FXML
    private TextField userField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;


    @FXML
    private TableColumn<DailyEntry, String> CommentColumn;
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
//    @FXML
//    private Button acceptButton;
//    @FXML
//    private Button denyButton;
      @FXML
      private Button saveButton;
//    @FXML
//    private Button submitButton;



    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent loginRoot = FXMLLoader.load(getClass().getResource("LoginGUI.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(loginRoot));
        primaryStage.show();
    }

    @FXML
    public void handleSave(ActionEvent event){
        try{
            theSystem.writeToFile();
        }catch(Exception e){}
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
            Parent homeRoot = loader.load();
            GUI controller = loader.getController();

            controller.tableView.setEditable(true);

            controller.dayColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
            controller.weekdayColumn.setCellValueFactory(new PropertyValueFactory<>("weekday"));
            controller.beginColumn.setCellValueFactory(new PropertyValueFactory<>("begin"));
            controller.endColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
            controller.pauseColumn.setCellValueFactory(new PropertyValueFactory<>("pause"));
            controller.hoursTargetColumn.setCellValueFactory(new PropertyValueFactory<>("hoursTarget"));
            controller.hoursAsIsColumn.setCellValueFactory(new PropertyValueFactory<>("hoursAsIs"));
            controller.diffColumn.setCellValueFactory(new PropertyValueFactory<>("diff"));
            controller.absenceColumn.setCellValueFactory(new PropertyValueFactory<>("absence"));
            controller.CommentColumn.setCellValueFactory(new PropertyValueFactory<>("comment"));

            StringConverter<LocalTime> timeConverter = new StringConverter<LocalTime>() {
                private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

                @Override
                public String toString(LocalTime time) {
                    return time != null ? time.format(formatter) : "";
                }

                public LocalTime fromString(String string) {
                    try {
                        return string != null && !string.isEmpty() ? LocalTime.parse(string, formatter) : null;
                    } catch (DateTimeParseException e) {
                        // Falls das Format nicht stimmt, gib null zurück oder handle den Fehler
                        return null;
                    }
                }

                ;
            };


            controller.beginColumn.setEditable(true);
            controller.beginColumn.setCellFactory(TextFieldTableCell.forTableColumn(timeConverter));
            controller.beginColumn.setOnEditCommit(ev -> {
                DailyEntry entry = ev.getRowValue();
                LocalTime newTime = ev.getNewValue();


                entry.setBegin(String.valueOf(newTime));

                theSystem.currentUser.updateCalender(entry);
            });

            controller.endColumn.setEditable(true);
            controller.endColumn.setCellFactory(TextFieldTableCell.forTableColumn(timeConverter));
            controller.endColumn.setOnEditCommit(ev -> {
                DailyEntry entry = ev.getRowValue();
                LocalTime newTime = ev.getNewValue();


                entry.setEnd(String.valueOf(newTime));

                theSystem.currentUser.updateCalender(entry);
            });

            controller.pauseColumn.setEditable(true);
            controller.pauseColumn.setCellFactory(TextFieldTableCell.forTableColumn(timeConverter));
            controller.pauseColumn.setOnEditCommit(ev -> {
                DailyEntry entry = ev.getRowValue();
                LocalTime newTime = ev.getNewValue();


                entry.setPause(String.valueOf(newTime));

                theSystem.currentUser.updateCalender(entry);
            });

            controller.CommentColumn.setEditable(true);
            controller.CommentColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            controller.CommentColumn.setOnEditCommit(ev -> {
                DailyEntry entry = ev.getRowValue();
                entry.setComment(ev.getNewValue());

                theSystem.currentUser.updateCalender(entry);
            });

            //Erstellung eines Test DailyEntry
            ObservableList<DailyEntry> dailyEntries = FXCollections.observableArrayList(
                    theSystem.currentUser.getCalendar()
            );
            controller.tableView.setItems(dailyEntries);

            // Set label values through the controller
            controller.firstnameLabel.setText(theSystem.currentUser.getFirstname());
            controller.lastnameLabel.setText(theSystem.currentUser.getLastname());
            controller.idLabel.setText(String.valueOf(theSystem.currentUser.getId()));

            Stage stage = new Stage();
            stage.setTitle("Hauptfenster");
            stage.setScene(new Scene(homeRoot));
            stage.show();

            // Close the login window
            Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            loginStage.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
