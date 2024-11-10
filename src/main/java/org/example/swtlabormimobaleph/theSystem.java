package org.example.swtlabormimobaleph;

import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class theSystem {
     /* KLASSE System:
       - Durchführung der Speicherung in Dateien

       SYNTAX Dateien:
       "File + "ID des Mitarbeiters"+ ".txt"
     */


    public static Employee currentUser = new Employee();
    static File currentFile = new File("File" + currentUser.getId() + ".txt");
    static File communicationLog = new File("CommunicationLog.txt");

    public static ArrayList<Employee> employeeList = new ArrayList<>();
    private static Map<String, String> employeePasswords = new HashMap<>();


    public static void writeToFile() throws IOException {
        currentFile = new File("File" + currentUser.getId() + ".txt");

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
    public static void writeCommunication(String r  ,String id){
        try{
           DataOutputStream dos = new DataOutputStream(new FileOutputStream(communicationLog,true));
            BufferedWriter buffw = new BufferedWriter(new OutputStreamWriter(dos));
            switch(r){
                case "Submit": buffw.write(currentUser.getId()+" communicateswith "+currentUser.getSupervisor().getId()+" "+r+"\n");
                case "Supervisor accepted":
                    buffw.write(currentUser.getId()+" communicateswith"+ id +r+"\n");
            }

            buffw.flush();
            buffw.close();
        }
        catch(Exception e){}
    }

    public static ArrayList<String> readCommunication(){
        ArrayList<String> test = new ArrayList<>();
        String[] array = new String[4];
        try{
            DataInputStream dis = new DataInputStream(new FileInputStream(communicationLog));
            BufferedReader br = new BufferedReader(new InputStreamReader(dis));
            while(br.ready()){
                array = br.readLine().split(" ");
                if(Integer.parseInt(array[2]) ==(currentUser.getId())){
                    test.add(array[0]+" "+array[1]+ " " +array[2] +" "+ array[3]);
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return test;
    }
    public static void readFromFile() throws IOException {
        currentFile = new File("File" + currentUser.getId() + ".txt");

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

        theSystem.employeePasswords.put("2","2");
        Supervisor z = new Supervisor();
        z.setFirstname("Jürgen");
        z.setLastname("Tischbein");
        z.setId(Integer.parseInt("2"));
        theSystem.employeeList.add(z);
        e.setSupervisor(z);

        theSystem.employeePasswords.put("3","3");
        HR h = new HR();
        h.setFirstname("Andi");
        h.setLastname("Latte");
        h.setId(Integer.parseInt("3"));
        theSystem.employeeList.add(h);
        h.setSupervisor(z);

        if(employeePasswords.containsKey(id) && employeePasswords.get(id).equals(password)){

            for(int i = 0; i<employeeList.size();i++){
                Employee c = employeeList.get(i);
                if(c.getId() == Integer.parseInt(id)) {
                    currentUser =c;
                    System.out.println("Current User is "+ currentUser.getFirstname());
                    try{
                        readFromFile();
                    }
                    catch(Exception u){}
                }
            }
            try {
                currentFile = new File("File"+currentUser.getId()+".txt");
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(currentFile));
                Integer test =(Integer) in.readObject();
            } catch(Exception t){
                try{writeToFile();}catch(Exception t1){}
            }
            return true;
        }
        else return false;
    }

    public static String workTime(ArrayList<DailyEntry> calenderAL){
        float totalHours = 0;
        for (DailyEntry entry : calenderAL) {
            totalHours += entry.getHoursAsIs().toSecondOfDay() / 3600.0;
        }
        int hours = (int) totalHours;
        int minutes = (int) ((totalHours - hours) * 60);
        return String.format("%02d:%02d", hours, minutes);

    }

    public static String flexTime(ArrayList<DailyEntry> calenderAL) {
        float totalDiff = 0;
        for (DailyEntry entry : calenderAL) {
            totalDiff += entry.getDiff();
        }
        int hours = (int) totalDiff;
        int minutes = (int) Math.abs((totalDiff - hours) * 60);

        return String.format("%s%02d:%02d", totalDiff >= 0 ? "+" : "-", Math.abs(hours), minutes);

    }



}

