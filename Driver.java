import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
public class Driver {
    private static String level = "1";
    public static void main(String[] args) {
       String path = "/Users/alexandriahooks/Downloads/QuoteSheet.csv";
       String line;
       ArrayList<String> quotes = new ArrayList<>();
       try {
           BufferedReader br = new BufferedReader(new FileReader(path));
           while ((line = br.readLine()) != null){
           quotes.add(line);
           }


       }
           catch (FileNotFoundException e){
               e.printStackTrace();
           }
          catch (IOException e){
           e.printStackTrace();
       }

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
}


