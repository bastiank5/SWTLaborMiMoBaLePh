package org.example.swtlabormimobaleph;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Duration;

public class DailyEntry implements Serializable {
 private LocalDate date;
  private LocalTime begin;
  private LocalTime end;
  private LocalTime pause;
  private String absence;
  private String comment;
  private String weekday;
  private float diff;
  private float hoursTarget;
  private float hoursAsIs;

  public DailyEntry(LocalDate date, LocalTime begin, LocalTime end, LocalTime pause, String absence, String comment, float diff, String weekday, float hoursTarget, float hoursAsIs) {
    this.date = date;
    this.begin = begin;
    this.end = end;
    this.pause = pause;
    this.absence = absence;
    this.comment = comment;
    this.hoursTarget = hoursTarget;

    this.weekday = weekday;
            Float flce = convertLocalTimeToFloat(getHoursAsIs());
            this.hoursAsIs = flce;
      this.diff = this.hoursAsIs-this.hoursTarget;
  }
    public static float convertLocalTimeToFloat(LocalTime time) {
        int hours = time.getHour();
        // Gesamtzeit in Sekunden
        return hours;
    }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public LocalTime getBegin() {
    return begin;
  }

  public void setBegin(String beginEntry) {
    this.begin = LocalTime.parse(beginEntry);
   Float flce = convertLocalTimeToFloat(getHoursAsIs());
    this.hoursAsIs = flce;
      this.diff = this.hoursAsIs-hoursTarget;
  }

  public LocalTime getEnd() {
    return end;
  }

  public void setEnd(String endEntry) {
      this.end = LocalTime.parse(endEntry);
       Float flce = convertLocalTimeToFloat(getHoursAsIs());
      this.hoursAsIs = flce;
      this.diff = this.hoursAsIs-hoursTarget;
  }

  public LocalTime getPause() {
    return pause;
  }

  public void setPause(String pauseEntry) {
    this.pause = LocalTime.parse(pauseEntry);
    Float flce = convertLocalTimeToFloat(getHoursAsIs());
      this.hoursAsIs = flce;
      this.diff = this.hoursAsIs - hoursTarget;
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
  /*public float getHoursAsIS(){
    return hoursAsIs;
  }*/
  public void setHoursAsIs(float hoursAsIs) {
    this.hoursAsIs = hoursAsIs;
  }

  // Berechnung der Zeit durch Abziehen der DateObjekte

  public LocalTime getHoursAsIs(){

      Duration duration = Duration.between(begin, end);
      Duration pauseDuration = Duration.between(LocalTime.MIN, pause);
      Duration totalDuration = duration.minus(pauseDuration);
      return LocalTime.MIN.plus(totalDuration);

}
/*
    public float getDiff1(float hoursTarget, float hoursAsIs) {
        // Konvertiere LocalTime in Stunden


        return hoursTarget - hoursAsIs;
    }
*/





  public Float getHoursTarget(Employee e) {    // Aufruf des Mitarbeiters und Auslesen seiner DailyWorkload
    return e.getDailyWorkload();
  }
}


