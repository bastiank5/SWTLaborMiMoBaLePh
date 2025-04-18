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
import javafx.scene.control.cell.ChoiceBoxTableCell;
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

import static org.example.swtlabormimobaleph.Absence.VACATION;
import static org.example.swtlabormimobaleph.Absence.values;

        public class GUI extends Application {
            private boolean tableEditable = true;
            private Stage ourPrimaryStage;
            private Stage ourLoginStage;
            private static ArrayList<Message> test1 = new ArrayList<>();
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
            private Label openVacationLabel;
            @FXML
            private Label plannedVacationLabel;
            @FXML
            private Label takenVacationLabel;
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
            private TableColumn<String,String>counterColumn;
            @FXML
            private TextField messageCounterField;
            @FXML
            private Button acceptButton;
            @FXML
            private Button denyButton;
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

            private static org.example.swtlabormimobaleph.GUI mainController;
            private static org.example.swtlabormimobaleph.GUI messagesController;
            private Parent loginRoot;

            public GUI() throws IOException {
            }

            @Override
            public void start(Stage Stage) throws IOException {
                ourLoginStage = Stage;
                loginRoot = FXMLLoader.load(getClass().getResource("LoginGUI.fxml"));
                ourLoginStage.setTitle("Login");
        ourLoginStage.setScene(new Scene(loginRoot));
        ourLoginStage.show();
    }
    @FXML
    public void handleAcceptVacationButton(ActionEvent event){
        Employee e = new Employee();
        for(int i = 0; i< theSystem.employeeList.size(); i++){
            if(Integer.parseInt(idLabel.getText()) ==(theSystem.employeeList.get(i).getId())) e=theSystem.employeeList.get(i);
        }
        if(theSystem.currentUser.equals(e))return;
         theSystem.writeCommunication("Supervisor accepted vacation",""+e.getId());
    }
    @FXML
    public void handleDenyVacationButton(ActionEvent event){
                Employee e = new Employee();
                for(int i = 0; i< theSystem.employeeList.size(); i++){
                    if(Integer.parseInt(idLabel.getText()) ==(theSystem.employeeList.get(i).getId())) e=theSystem.employeeList.get(i);
                }
                if(theSystem.currentUser.equals(e))return;
        else{
            theSystem.currentUser= e;
            DailyEntry entry;
            theSystem.writeCommunication("Supervisor denied vacation",""+e.getId());
            for (int i = 0; i < e.getCalender().size() ; i++) {
                if (e.getCalender().get(i).getAbsence() == VACATION) {
                    entry =  e.getCalendar().get(i);
                    entry.setAbsence(Absence.NO);
                    e.updateCalender(entry,i);
                }
            }
           // mainController.tableView.refresh();
        }
    }

    @FXML
    public void handleMessageAcceptButton(ActionEvent e){
        if(checkIfPossible()){
             theSystem.writeCommunication("Supervisor accepted worktime" ,test1.get(Integer.parseInt(messageCounterField.getText())).getSender());
             Message z = new Message(""+theSystem.currentUser.getId(),""+test1.get(Integer.parseInt(messageCounterField.getText())).getSender(),"Supervisor accepted worktime");
             test1.add(z);
            test1.remove(Integer.parseInt(messageCounterField.getText()));
            theSystem.deleteCommunication(test1);

            messagesController.messageTableView.refresh();
            }
        }
    @FXML
    public void handleMessageDenyButton(ActionEvent e){
        if(checkIfPossible()){
            theSystem.writeCommunication("Supervisor denied worktime" ,test1.get(Integer.parseInt(messageCounterField.getText())).getSender());
            Message z = new Message(""+theSystem.currentUser.getId(),""+test1.get(Integer.parseInt(messageCounterField.getText())).getSender(),"Supervisor denied worktime");
            test1.add(z);
            test1.remove(Integer.parseInt(messageCounterField.getText()));
            theSystem.deleteCommunication(test1);
            messagesController.messageTableView.refresh();
        }
    }
//Prüft bei der Eingabe der Nachrichtennummer, ob diese existiert
    private boolean checkIfPossible(){
        for(int i = 0; i < test1.size();i++){
            if(test1.get(i).getCounter().equals(messageCounterField.getText()))return true;
        }
        return false;
    }

    @FXML
    public void handleSave(ActionEvent event) {
        try {
            theSystem.writeToFile();
        } catch (Exception e) {
        }
         for(int i = 0; i < theSystem.currentUser.getCalender().size();i++){
             if(theSystem.currentUser.getCalender().get(i).getAbsence()== VACATION){
                 theSystem.writeCommunication("Vacation requested","");
             }
         }
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        if (theSystem.getUserInformation(userField.getText(), passwordField.getText())) {
            Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            openMainWindow(loginStage);
        } else {
            System.out.println("Ungültige Anmeldeinformationen");
        }
    }

    @FXML
    private void handleSubmit(ActionEvent event) {
        tableView.setEditable(false);
        theSystem.writeCommunication("Submit","");
    }

    @FXML
    private void handleshowEmployeeCalender() throws IOException {
        if (theSystem.currentUser instanceof Supervisor || theSystem.currentUser instanceof HR) {
            for (int i = 0; i < theSystem.employeeList.size(); i++) {
                if (theSystem.employeeList.get(i).getId() == Integer.parseInt(showEmployeeCalenderIDField.getText())) {
                    theSystem.currentUser2 = theSystem.currentUser;
                    theSystem.currentUser = theSystem.employeeList.get(i);
                }
            }
            this.tableEditable = false;
            theSystem.readFromFile();
            openMainWindow(null);
            theSystem.currentUser = theSystem.currentUser2;
        } else {
            System.out.println("Sorry, not permitted to show employee calender");
        }
    }

    @FXML
    private void handleShowMessages(ActionEvent event) {
        try {
            ArrayList<String> chats = theSystem.readCommunication();
            HashSet<String> set = new HashSet<>(chats);
            chats = new ArrayList<>(set);

            String[] chat = new String[4];

            for(int i = 0; i < chats.size(); i++) {
                chat = chats.get(i).split("_");
                Message w = new Message(chat[0], chat[2], chat[3]);
                this.test1.add(w);
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MessagesGUI.fxml"));
            Parent homeRoot = loader.load();

            messagesController = loader.getController();
            messagesController.employeeColumn.setCellValueFactory(new PropertyValueFactory<>("Sender"));
            messagesController.topicColumn.setCellValueFactory(new PropertyValueFactory<>("Topic"));
            messagesController.counterColumn.setCellValueFactory(new PropertyValueFactory<>("Counter"));

            ObservableList<Message> chats2 = FXCollections.observableArrayList(test1);
            messagesController.messageTableView.setItems(chats2);

            Stage stage = new Stage();
            stage.setTitle("Messages");
            stage.setScene(new Scene(homeRoot));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void openMainWindow(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GUI.fxml"));
            Parent homeRoot = loader.load();
            mainController = loader.getController();

            mainController.dayColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
            mainController.weekdayColumn.setCellValueFactory(new PropertyValueFactory<>("weekday"));
            mainController.beginColumn.setCellValueFactory(new PropertyValueFactory<>("begin"));
            mainController.endColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
            mainController.pauseColumn.setCellValueFactory(new PropertyValueFactory<>("pause"));
            mainController.hoursTargetColumn.setCellValueFactory(new PropertyValueFactory<>("hoursTarget"));
            mainController.hoursAsIsColumn.setCellValueFactory(new PropertyValueFactory<>("hoursAsIs"));
            mainController.diffColumn.setCellValueFactory(new PropertyValueFactory<>("diff"));
            mainController.absenceColumn.setCellValueFactory(new PropertyValueFactory<>("absence"));
            mainController.CommentColumn.setCellValueFactory(new PropertyValueFactory<>("comment"));

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
            mainController.beginColumn.setCellFactory(TextFieldTableCell.forTableColumn(timeConverter));
            mainController.beginColumn.setOnEditCommit(ev -> {
                DailyEntry entry = ev.getRowValue();
                LocalTime newTime = ev.getNewValue();

                entry.setBegin(String.valueOf(newTime));

                theSystem.currentUser.updateCalender(entry);
                mainController.tableView.refresh();
                mainController.workTimeLabel.setText(theSystem.workTime(theSystem.currentUser.getCalendar()));
                mainController.flexTimeLabel.setText(theSystem.flexTime((theSystem.currentUser.getCalendar())));
            });

            mainController.endColumn.setCellFactory(TextFieldTableCell.forTableColumn(timeConverter));
            mainController.endColumn.setOnEditCommit(ev -> {
                DailyEntry entry = ev.getRowValue();
                LocalTime newTime = ev.getNewValue();

                entry.setEnd(String.valueOf(newTime));

                theSystem.currentUser.updateCalender(entry);
                mainController.tableView.refresh();
                mainController.workTimeLabel.setText(theSystem.workTime(theSystem.currentUser.getCalendar()));
                mainController.flexTimeLabel.setText(theSystem.flexTime((theSystem.currentUser.getCalendar())));
            });

            mainController.pauseColumn.setCellFactory(TextFieldTableCell.forTableColumn(timeConverter));
            mainController.pauseColumn.setOnEditCommit(ev -> {
                DailyEntry entry = ev.getRowValue();
                LocalTime newTime = ev.getNewValue();

                entry.setPause(String.valueOf(newTime));

                theSystem.currentUser.updateCalender(entry);
                mainController.tableView.refresh();
                mainController.flexTimeLabel.setText(theSystem.flexTime((theSystem.currentUser.getCalendar())));
                mainController.workTimeLabel.setText(theSystem.workTime(theSystem.currentUser.getCalendar()));
            });
            mainController.absenceColumn.setCellValueFactory(new PropertyValueFactory<>("absence"));

                mainController.absenceColumn.setCellFactory(ChoiceBoxTableCell.forTableColumn(
                        FXCollections.observableArrayList(values())));

                mainController.absenceColumn.setOnEditCommit(ev -> {
                    DailyEntry entry = ev.getRowValue();
                    entry.setAbsence (ev.getNewValue());

                    theSystem.currentUser.updateCalender(entry);
                    mainController.tableView.refresh();
                    mainController.plannedVacationLabel.setText(theSystem.plannedVacation());
                    mainController.openVacationLabel.setText(theSystem.openVacation());
                    mainController.takenVacationLabel.setText("00");
                });


            mainController.CommentColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            mainController.CommentColumn.setOnEditCommit(ev -> {
                DailyEntry entry = ev.getRowValue();
                entry.setComment(ev.getNewValue());

                theSystem.currentUser.updateCalender(entry);
                mainController.tableView.refresh();
            });

            //Erstellung eines Test DailyEntry
            ObservableList<DailyEntry> dailyEntries = FXCollections.observableArrayList(
                    theSystem.currentUser.getCalendar()
            );
            mainController.tableView.setItems(dailyEntries);

            // Set label values through the controller
            mainController.firstnameLabel.setText(theSystem.currentUser.getFirstname());
            mainController.lastnameLabel.setText(theSystem.currentUser.getLastname());
            mainController.idLabel.setText(String.valueOf(theSystem.currentUser.getId()));
            mainController.workTimeLabel.setText(theSystem.workTime(theSystem.currentUser.getCalendar()));
            mainController.flexTimeLabel.setText(theSystem.flexTime((theSystem.currentUser.getCalendar())));
            mainController.plannedVacationLabel.setText(theSystem.plannedVacation());
            mainController.openVacationLabel.setText(theSystem.openVacation());
            mainController.takenVacationLabel.setText("00");

            if(tableEditable == false){
                mainController.tableView.setEditable(true);
                mainController.CommentColumn.setEditable(false);
                //mainController.absenceColumn.setEditable(false);
                mainController.beginColumn.setEditable(false);
                mainController.pauseColumn.setEditable(false);
                mainController.dayColumn.setEditable(false);
                mainController.diffColumn.setEditable(false);
                mainController.endColumn.setEditable(false);
            } else {
                mainController.tableView.setEditable(true);
                mainController.CommentColumn.setEditable(true);
                mainController.absenceColumn.setEditable(true);
                mainController.beginColumn.setEditable(true);
                mainController.pauseColumn.setEditable(true);
                mainController.dayColumn.setEditable(true);
                mainController.diffColumn.setEditable(true);
                mainController.endColumn.setEditable(true);
            }


            ourPrimaryStage = new Stage();
            ourPrimaryStage.setTitle("Hauptfenster");
            ourPrimaryStage.setScene(new Scene(homeRoot));
            ourPrimaryStage.show();

            // Close the login window
            if(stage != null) {
                stage.close();
            }
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
