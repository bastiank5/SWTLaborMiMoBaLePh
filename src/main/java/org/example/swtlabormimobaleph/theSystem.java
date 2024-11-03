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
    static File currentFile = new File("File" + currentUser.getId() + ".txt");

    private static ArrayList<Employee> employeeList = new ArrayList<>();
    private static Map<String, String> employeePasswords = new HashMap<>();


    public static void writeToFile() throws IOException {

//Schreibt in File, prüft vorher, ob bereits etwas vorhanden ist, damit nichts überschrieben wird, sonst wird neue Datei erstellt
        try {
            //if(currentFile.createNewFile()){ ObjectOutputStream oos= new ObjectOutputStream(new FileOutputStream(currentFile, true));}
            //else{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(currentFile));
            oos.writeObject(1);
            //}
            for (int i = 0; i < currentUser.getCalender().size(); i++) {
//            currentFile.cle
                oos.writeObject(currentUser.getCalender().get(i));
            }
            oos.flush();
            oos.close();
        } catch (Exception t) {
        }
    }

    /* public static void readFromFile() throws IOException{
         ObjectInputStream ois = new ObjectInputStream(new FileInputStream(currentFile));
         //Liest die Objecte einzeln aus der Datei aus und schreib sie in den Calender des Employees
             for (int i = 0; i < currentUser.getCalender().size(); i++) {
                 try {
                     DailyEntry MyObject = (DailyEntry) ois.readObject();
                     currentUser.setCalender(MyObject);
                     System.out.println(currentUser.getCalender().get(i).getComment().toString());
                 }
         catch(Exception e){
                 e.printStackTrace();
             }
         }
     }*/
    public static void readFromFile() throws IOException {

        // Liest die Objekte einzelnd aus der Datei aus und schreibt sie in den Kalender des Mitarbeiters
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(currentFile))) {
            ois.readObject();
            DailyEntry MyObject;
            currentUser.getCalendar().clear();
            while ((MyObject = (DailyEntry) ois.readObject()) != null) {
                currentUser.setCalender(MyObject);
            }
        } catch (EOFException e) {
            // End of file reached
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean getUserInformation(String id, String password) {
        theSystem.employeePasswords.put("1", "1");
        Employee e = new Employee();
        e.setFirstname("Sabine");
        e.setLastname("Müller");
        e.setId(Integer.parseInt("1"));
        theSystem.employeeList.add(e);
        try {
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(currentFile));
                Integer test =(Integer) in.readObject();
        } catch(Exception t){
        try{writeToFile();}catch(Exception t1){}
    }


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

