package org.example.swtlabormimobaleph;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DailyEntry implements Serializable {
  private Date date;
  private LocalTime begin;
  private LocalTime end;
  private LocalTime pause;
  private String absence;
  private String comment;

  DailyEntry() {
  }

  public Date getDate() {
    return null;
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


// Berechnung der Zeit durch Abziehen der DateObjekte


  public LocalTime getHoursAsIs()

  }

  public Float getHoursTarget(Employee e) {
    // Aufruf des Mitarbeiter und Auslesen seiner DailyWorkload
    return e.getDailyWorkload();
  }
}


