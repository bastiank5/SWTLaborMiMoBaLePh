package org.example.swtlabormimobaleph;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DailyEntry implements Serializable {
  private Date date;
  private Float begin;
  private Float end;
  private Float pause;
  private String absence;
  private String comment;

  DailyEntry() {
  }

  public Float getPause() {
      return pause;
  }
// Berechnung der Zeit durch ABziehen der DateObjekte

  public Float getHoursAsIs() throws ClassCastException {
    return (float) TimeUnit.HOURS.convert((long) Math.abs(begin - end), TimeUnit.MILLISECONDS);
  }

  public Float getHoursTarget(Employee e) {
    // Aufruf des Mitarbeiter und Auslesen seiner DailyWorkload
    return e.getDailyWorkload();
  }

  public Date getDate() {
      return null;
  }
}
