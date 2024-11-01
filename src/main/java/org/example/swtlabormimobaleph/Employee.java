package org.example.swtlabormimobaleph;

import java.util.ArrayList;
import java.util.Date;

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

    protected boolean deleteVacation(Date deletedVacation) {
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
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getFristname() {
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