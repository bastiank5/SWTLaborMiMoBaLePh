package org.example.swtlabormimobaleph;

import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class theSystem {
     /* KLASSE System:
       - Durchführung der Speicherung in Dateien

       SYNTAX Dateien:
       "File + "ID des Mitarbeiters"+ ".txt"
     */


     public static Employee currentUser = new Employee();
     static File currentFile = new File("File"+currentUser.getId()+".txt");
    
    private static ArrayList<Employee> employeeList = new ArrayList<>();
    private static Map<String,String> employeePasswords = new HashMap<>();

    private static ObjectOutputStream oos;

    public static void writeToFile() throws IOException {

//Schreibt in File, prüft vorher, ob bereits etwas vorhanden ist, damit nichts überschrieben wird, sonst wird neue Datei erstellt
    try{
    if(currentFile.createNewFile()){ ObjectOutputStream oos= new ObjectOutputStream(new FileOutputStream(currentFile, true));}
    else{
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(currentFile));
    }
        for(int i = 0; i< currentUser.getCalender().size();i++){
            oos.writeObject(currentUser.getCalender());
    }
        System.out.println(currentUser.getCalender().toString());
        oos.flush();
        oos.close();    
    }
    catch(Exception e){}
    }
        
    public static void readFromFile() throws IOException{
//Liest die Objecte einzelnd aus der Datei aus und schreib sie in den Calender des Employees
        try{
            ObjectInputStream ois = new ObjectInputStream( new FileInputStream(currentFile));
            while(true){
               DailyEntry MyObject = (DailyEntry)  ois.readObject();
             currentUser.setCalender(MyObject);
            }
        }
        catch(Exception e){}
    }
    public static boolean getUserInformation(String id, String password){
        theSystem.employeePasswords.put("1","1");
        Employee e = new Employee();
        e.setFirstname("Sabine");
        e.setLastname("Müller");
        e.setId(Integer.parseInt("1"));
        theSystem.employeeList.add(e);


        if(employeePasswords.containsKey(id) && employeePasswords.get(id).equals(password)){

            for(int i = 0; i<employeeList.size();i++){
                Employee c = employeeList.get(i);
                if(c.getId() == Integer.parseInt(id)) {
                    currentUser =c;
                    System.out.println("Current User is"+ currentUser.toString());
                    try{
                        readFromFile();
                    }
                    catch(Exception u){}
                }
            }
            return true;
        }
        else return false;
    }


}

