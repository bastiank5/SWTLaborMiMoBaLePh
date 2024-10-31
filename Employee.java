public class Employee {
    private String firstname;
    private String lastname;
    private int id;
    private String password;
    private float workLoad;
    private Supervisor supervisor;
    private Arraylist calender <DailyEntry>;

    protected boolean deleteVacation(){}
    protected boolean applyVacation(){}
    protected boolean changeVacation(){}
    protected Arraylist getEmployeeCalendar(){}
    protected Arraylist setEmployeeCalendar(){}

    
    Employee() {
    } 
    
    public void setFirstname(String firstname){
        this.firstname = firstname;
    }
    public String getFristname(){
        return fristname;
    }

    public void setLastname(String lastname){
        this.lastname = lastname;
    }
    public String getLastname(){
        return lastname;
    }

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }

    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }

    public void setDailyWorkload(float dailyWorkload){
        this.dailyWorkload = dailyWorkload;
    }
    public float getDailyWorkload(){
        return dailyWorkload;
    }

    public void setSupervisor(Supervisor supervisor){
        this.supervisor = supervisor;
    }
    public Supervisor getSupervisor(){
        return supervisor;
    }

    public void setCalendar( Arraylist <DailyEntry>){
        this.calendar = calendar;
    }
    public Arraylist getCalendar(){
        return calendar;
    }
    

    
    

    public void setCalender(ArrayList EmployeeCalender){
        this.Calender = EmployeeCalender;
}
   public voud setCalender(DailyEntry e){
       this.Clander.add(e);
   }
    public ArrayList getCalender(){ return this.calender;}
