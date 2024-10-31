public class DailyEntry implements serializable{
  private Date date;
  private Float begin;
  private Float end;
  private Float break;
  private String absence;
  private String comment;
  
  DailyEntry(){}
  
}
public Float getBreak(){}
// Berechnung der Zeit durch ABziehen der DateObjekte

public Float getHoursAsIs()throws ClassCastException{
  return (float)TimeUnit.HOURS.convert((Math.abs(begin - end)), TimeUNIT.MilliSeconds);
}

public Float getHoursTarget(Employee e ){
  // Aufruf des Mitarbeiter und Auslesen seiner DailyWorkload
   return e.getDailyWorkload();
}
