public class System {
     public Employee currentUser;
     File currentFile = new File("File"+currenUser.getID()+".txt");
    
    private ArrayList<Employee> employeeList = new ArrayList<>();
    private Hashmap<Employee,String> employeePasswords = new Hashmap<>();
    
    public static void main(String[] args) {
        System.out.println("hallo");
        System.out.println("Hello World");
    }    
    public static void writeToFIle() throws IOException{
    try{
    if(createNewFile(currentFile)) ObjectOutputStream oos= new ObjectOutputStream(new FileOutputStream(currentFile, true));
    else{
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(currentFile);
    }
        for(int i = 0; i< Employee.getCalender().size();i++){
            oos.writeObject(Employee.getCalender()(i));
    }
        oos.flush();
        oos.close();    
    }
    catch(FIleNotFountException FileNotFoundException){}
    }
        
    public static void readFromFile() throws IOException{
        try{
            ObjectInputStream ois = new ObjectInputStream( new FIleInputStream(currentFIle);
            While(ois.ready()){
               DailyEntry MyObject = (DailyEntry)  ois.readObject;
             currenUser.setCalender(MyObject);
            }
        catch(ClassCastException e || FileNotFoundException e){}
    }
}
