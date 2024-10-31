public class Employee {
    private String firstname;
    private String lastname;
    private int id;
    private String password;
    private Float workLoad;
    private Supervisor supervisor;
    private Arraylist calender <DailyEntry>;

    protected boolean deleteVacation(){}
    protected boolean applyVacation(){}
    protected boolean changeVacation(){}
    protected Arraylist getEmployeeCalendar(){}
    protected Arraylist setEmployeeCalendar(){}

    
    Employee() {
    }

    public void setCalender(ArrayList EmployeeCalender){
        this.Calender = EmployeeCalender;
}
   public voud setCalender(DailyEntry e){
       this.Clander.add(e);
   }
    public ArrayList getCalender(){ return this.calender;}
