package org.example.swtlabormimobaleph;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalTime;

public class Employee {
    /* KLASSE EMployee:
     -Repräsentiert die einzelnen Mitarbeiter des Unternehmens
    */

    private String firstname;
    private String lastname;
    private int id;
    private String password;
    private float dailyWorkload;
    private Supervisor supervisor;
    private ArrayList<DailyEntry> calender;

    protected boolean deleteVacation(LocalDate deletedVacation) {
        for(int i = 0; i < calender.size();i++){
            if(deletedVacation == calender.get(i).getDate()) {
                calender.remove(i);
                return true;
            }
        }
        return false;
    }

    protected boolean applyVacation() {


        return false;
    }

    protected boolean changeVacation() {
        return false;
    }

    protected ArrayList getEmployeeCalendar() {
        return null;
    }

    protected ArrayList setEmployeeCalendar() {
        return null;
    }

    Employee() {
        initializeCalender();
    }

    public void updateCalender(DailyEntry calenderEntry){
      for(int i = 0; i < this.calender.size(); i++){
          if(calender.get(i) == calenderEntry) calender.set(i, calenderEntry);
      }
    }


    public void initializeCalender(){
            LocalDate date = LocalDate.now();
            LocalTime begin = LocalTime.of(8, 30);
            LocalTime end = LocalTime.of(17, 15);
            LocalTime pause = LocalTime.of(0, 45);
            String absence = "Vacation";
            String comment = "";
            float diff = -2f;
            float hoursTarget = 8.0f;
            float hoursAsIs = 6f;// Tatsächliche Stunden, z.B. 6 Stunden


            calender = new ArrayList<>();
            for (int i = 0; i < 30; i++) {
                if (date.getDayOfWeek().toString().equals("SATURDAY") || date.getDayOfWeek().toString().equals("SUNDAY")) {
                    hoursTarget = 0.0f;
                    comment = "Frei";
                } else {
                    comment = "Top Tag";
                    hoursTarget = 8.0f;
                }
                String weekday = date.getDayOfWeek().toString();
                DailyEntry a = new DailyEntry(date, begin, end, pause, absence, comment, diff, weekday, hoursTarget, hoursAsIs);
                date = date.plusDays(1);

                calender.add(a);
        }
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setDailyWorkload(float dailyWorkload) {
        this.dailyWorkload = dailyWorkload;
    }

    public float getDailyWorkload() {
        return dailyWorkload;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public ArrayList<DailyEntry> getCalendar() {
        return calender;
    }


    //Overload der Methode setCalender, weil beim Auslesen der Datei es sinnvoller ist die Einträge einzeln zu übergeben, statt neue ArrayList als zwischenspeicher zu nutzen
    public void setCalender(ArrayList<DailyEntry> EmployeeCalender) {
        this.calender = EmployeeCalender;
    }

    public void setCalender(DailyEntry e) {
        this.calender.add(e);
    }

    public ArrayList<DailyEntry> getCalender() {
        return this.calender;
    }

}
