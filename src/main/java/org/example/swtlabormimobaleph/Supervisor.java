package org.example.swtlabormimobaleph;

import java.util.ArrayList;

public class Supervisor extends Employee{
    
    private ArrayList <Employee> employeelist;

    
    Supervisor(){
        super();

    }
     private boolean approveWorktime(){
         return false;
     }
    private boolean approveVac(){
        return false;
    }
    private boolean denyVac(){
        return false;
    }
    private ArrayList getInferiorCalendar(){
        return null;
    }

    public void setEmployeelist(ArrayList <Employee>){
    this.employeelist = employeelist;
    }
    public ArrayList<Employee> getEmployeelist(){
        return employeelist;
    }

}
