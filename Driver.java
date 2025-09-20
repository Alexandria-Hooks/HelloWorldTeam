import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Driver {
    private static String level = "1";
    public static void main(String[] args) {
        ArrayList<String> quotes = lists("YOUR PATH TO QuoteSheet.csv HERE");          // initialize quote list
        ArrayList<String> keywords = lists("YOUR PATH TO Keywords_Sheet1.csv HERE");   // initialize keyword list
        int key = keyGenerator();
        // Create UI and obtain user chosen level
        new UI(level1 -> {
            level = level1;

            // check input
            if (!(level.equals("1") || level.equals("2") || level.equals("3") || level.equals("4") || level.equals("5"))) {
                System.out.println("How are you going to do cryptography if you can't even read?");
            } else { // Game Loop
                new Game(Integer.parseInt(level)) {
                    // TODO: maybe add stuff later
                };
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
        catch (IOException e){
            e.printStackTrace();
        }
        return listed;
    }

    //Random Generator
    public static int keyGenerator() {
        Random rand = new Random();
        return rand.nextInt(10);
    }
}
