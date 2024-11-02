package org.example.swtlabormimobaleph;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

public class DailyEntry implements Serializable {
 private Date date;
  private LocalTime begin;
  private LocalTime end;
  private LocalTime pause;
  private String absence;
  private String comment;
  private String weekday;
  private float diff;
  private float hoursTarget;
  private float hoursAsIs;



  public DailyEntry(Date date, LocalTime begin, LocalTime end, LocalTime pause, String absence, String comment, float diff, String weekday, float hoursTarget, float hoursAsIs) {
    this.date = date;
    this.begin = begin;
    this.end = end;
    this.pause = pause;
    this.absence = absence;
    this.comment = comment;
    this.diff = diff;
    this.weekday = weekday;
    this.hoursAsIs = hoursAsIs;
    this.hoursTarget = hoursTarget;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public LocalTime getBegin() {
    return begin;
  }

  public void setBegin(String beginEntry) {
    this.begin = LocalTime.parse(beginEntry);
  }

  public LocalTime getEnd() {
    return end;
  }

  public void setEnd(String endEntry) {
    this.end = LocalTime.parse(endEntry);
  }

  public LocalTime getPause() {
    return pause;
  }

  public void setPause(String pauseEntry) {
    this.pause = LocalTime.parse(pauseEntry);
  }

  public String getAbsence() {
    return absence;
  }

  public void setAbsence(String absence) {
    this.absence = absence;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }
  public float getDiff() {
    return diff;
  }
  public void setDiff(float diff) {
    this.diff = diff;
  }
  public String getWeekday() {
    return weekday;
  }
  public void setWeekday(String weekday) {
    this.weekday = weekday;
  }
  public float getHoursTarget() {
    return hoursTarget;
  }
  public void setHoursTarget(String hoursTarget) {
    this.hoursTarget = Float.parseFloat(hoursTarget);
  }
  public float getHoursAsIS(){
    return hoursAsIs;
  }

  public void setHoursAsIs(float hoursAsIs) {
    this.hoursAsIs = hoursAsIs;
  }

  // Berechnung der Zeit durch Abziehen der DateObjekte


  public LocalTime getHoursAsIs(){
 return begin;
}

  public Float getHoursTarget(Employee e) {
    // Aufruf des Mitarbeiter und Auslesen seiner DailyWorkload
    return e.getDailyWorkload();
  }

}


