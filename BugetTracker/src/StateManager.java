import java.io.*;

public class StateManager {

    File file = new File(".//src//purchases.txt");

    public void createFile(){
        boolean create;
        try {
            create=file.createNewFile();
        } catch (IOException e) {
            create=false;
            e.printStackTrace();
        }
        if(create){
            System.out.println("File created!");
        }else {
            System.out.println("File not created!");
        }
    }
}
