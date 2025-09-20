import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
public class Driver {
    private static String level = "1";
    public static void main(String[] args) {
     ArrayList<String> quotes = lists("/Users/alexandriahooks/Downloads/QuoteSheet.csv"); //initialize quote list
     ArrayList<String> keywords = lists("/Users/alexandriahooks/Downloads/Keywords-Sheet1.csv"); //inititalize keyword list
     int key = keyGenerator();
        // Create UI and obtain user chosen level
        UI ui = new UI(level1 -> {
            level = level1;

            // check input
            if (!(level.equals("1") || level.equals("2") || level.equals("3") || level.equals("4") || level.equals("5"))) {
                System.out.println("How are you going to do cryptography if you can't even read?");
            } else {
                // TODO: game loop
            }
        });
    }
    //String lists - takes csv path & creates list 
    public static ArrayList<String> lists (String path){
        String line;
        ArrayList<String> listed = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null){
                listed.add(line);
            }


        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return listed;
    }
    //Random Generator
    public static int keyGenerator (){
        Random rand = new Random();
        int radnum = rand.nextInt(22);
        return radnum;
    
}









