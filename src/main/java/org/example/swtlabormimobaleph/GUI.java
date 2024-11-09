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
import java.util.ArrayList;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Date;

import javafx.util.StringConverter;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;

public class GUI extends Application {
    private boolean tableEditable = true;

    private Stage ourPrimaryStage = new Stage();
    @FXML
    private TextField userField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private TableColumn<DailyEntry, String> CommentColumn;
    @FXML
    private TableColumn<DailyEntry, Absence> absenceColumn;
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
    private Label workTimeLabel;
    @FXML
    private Label flexTimeLabel;
    @FXML
    private TableView<DailyEntry> tableView;
    @FXML
    private TableColumn<DailyEntry, String> weekdayColumn;

    @FXML
    private TableView<Message> messageTableView;
    @FXML
    private TableColumn<String,String>employeeColumn;
    @FXML
    private TableColumn<String,String>topicColumn;

    @FXML
    private Button messagesAcceptButton;
    @FXML
    private Button showEmployeeCalenderButton;
    @FXML
    private Button messagesDenyButton;

    @FXML
    private Button saveButton;
    @FXML
    private Button submitButton;
    @FXML
    private TextField showEmployeeCalenderIDField;

    public GUI() throws IOException {
    }


    @Override
    public void start(Stage primaryStage) throws IOException {
        ourPrimaryStage = primaryStage;
        Parent loginRoot = FXMLLoader.load(getClass().getResource("LoginGUI.fxml"));
        ourPrimaryStage.setTitle("Login");
        ourPrimaryStage.setScene(new Scene(loginRoot));
        ourPrimaryStage.show();
    }

    @FXML
    public void handleSave(ActionEvent event) {
        try {
            theSystem.writeToFile();
        } catch (Exception e) {
        }
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
    private void handleSubmit(ActionEvent event) {
        tableView.setEditable(false);
        theSystem.writeCommunication("Submit");
    }

    @FXML
    private void handleshowEmployeeCalender(ActionEvent event) throws IOException {
        if (theSystem.currentUser instanceof Supervisor) {
            for (int i = 0; i < theSystem.employeeList.size(); i++) {
                if (theSystem.employeeList.get(i).getId() == Integer.parseInt(showEmployeeCalenderIDField.getText())) {
                    theSystem.currentUser = theSystem.employeeList.get(i);
                }
            }
            this.tableEditable = false;
            openMainWindow(event);
        } else {
            System.out.println("Sorry, not permitted to show employee calender");
        }
    }

    @FXML
    private void handleShowMessages(ActionEvent event) {
        try {
            String[] chat = theSystem.readCommunication();
            ArrayList<Message> test1 = new ArrayList<>();

            for(int i = 0; i < chat.length; i++) {
                Message w = new Message(chat[0], chat[2], chat[3]);
                test1.add(w);
            }
            System.out.println(chat);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MessagesGUI.fxml"));
            Parent homeRoot = loader.load();

            GUI controller = loader.getController();
            controller.employeeColumn.setCellValueFactory(new PropertyValueFactory<>("Sender"));
            controller.topicColumn.setCellValueFactory(new PropertyValueFactory<>("Topic"));

            ObservableList<Message> chats = FXCollections.observableArrayList(test1);
            controller.messageTableView.setItems(chats);


            Stage stage = new Stage();
            stage.setTitle("Messages");
            stage.setScene(new Scene(homeRoot));
            stage.show();

            // Close the login window
            Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            loginStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openMainWindow(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GUI.fxml"));
            Parent homeRoot = loader.load();
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
            };
            controller.beginColumn.setCellFactory(TextFieldTableCell.forTableColumn(timeConverter));
            controller.beginColumn.setOnEditCommit(ev -> {
                DailyEntry entry = ev.getRowValue();
                LocalTime newTime = ev.getNewValue();

                entry.setBegin(String.valueOf(newTime));

                theSystem.currentUser.updateCalender(entry);
                controller.tableView.refresh();
            });

            controller.endColumn.setCellFactory(TextFieldTableCell.forTableColumn(timeConverter));
            controller.endColumn.setOnEditCommit(ev -> {
                DailyEntry entry = ev.getRowValue();
                LocalTime newTime = ev.getNewValue();

                entry.setEnd(String.valueOf(newTime));

                theSystem.currentUser.updateCalender(entry);
                controller.tableView.refresh();
            });

            controller.pauseColumn.setCellFactory(TextFieldTableCell.forTableColumn(timeConverter));
            controller.pauseColumn.setOnEditCommit(ev -> {
                DailyEntry entry = ev.getRowValue();
                LocalTime newTime = ev.getNewValue();

                entry.setPause(String.valueOf(newTime));

                theSystem.currentUser.updateCalender(entry);
                controller.tableView.refresh();
            });


            controller.CommentColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            controller.CommentColumn.setOnEditCommit(ev -> {
                DailyEntry entry = ev.getRowValue();
                entry.setComment(ev.getNewValue());

                theSystem.currentUser.updateCalender(entry);
                controller.tableView.refresh();
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
            controller.workTimeLabel.setText(theSystem.workTime(theSystem.currentUser.getCalendar()));
            controller.flexTimeLabel.setText(theSystem.flexTime((theSystem.currentUser.getCalendar())));

            if(tableEditable == false){
                controller.tableView.setEditable(false);
                controller.CommentColumn.setEditable(false);
                controller.absenceColumn.setEditable(false);
                controller.beginColumn.setEditable(false);
                controller.pauseColumn.setEditable(false);
                controller.dayColumn.setEditable(false);
                controller.diffColumn.setEditable(false);
                controller.endColumn.setEditable(false);
            } else {
                controller.tableView.setEditable(true);
                controller.CommentColumn.setEditable(true);
                controller.absenceColumn.setEditable(true);
                controller.beginColumn.setEditable(true);
                controller.pauseColumn.setEditable(true);
                controller.dayColumn.setEditable(true);
                controller.diffColumn.setEditable(true);
                controller.endColumn.setEditable(true);
            }
            //Labels hinzufügen und Berechnen

            Stage stage = new Stage();
            stage.setTitle("Hauptfenster");
            stage.setScene(new Scene(homeRoot));
            stage.show();

            // Close the login window
            Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            loginStage.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
