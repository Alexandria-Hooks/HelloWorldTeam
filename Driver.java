import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Driver {
    private static String level = "1";
    public static void main(String[] args) {          // initialize quote list
        // initialize keyword list
        // Create UI and obtain user chosen level
        new UI(level1 -> {
            level = level1;

            // check input
            if (!(level.equals("1") || level.equals("2") || level.equals("3") || level.equals("4") || level.equals("5"))) {
                System.out.println("How are you going to do cryptography if you can't even read?");
            } else { // Game Loop
                new Game(Integer.parseInt(level),questionGenerator()) {
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
    public static ArrayList<String> quotes = lists("/Users/alexandriahooks/Downloads/QuoteSheet.csv");
    public static int key = keyGenerator();
    public static ArrayList<String> keywords = lists("/Users/alexandriahooks/Downloads/Keywords-Sheet1.csv");
    public static String questionGenerator () {

        String encrypted = "";
        String type = "";
        switch (level){
            case "1":
                type = "caesar";
                encrypted = Cipher.CaesarCipher(quotes.get(key),key);
                System.out.println(quotes.get(key));
                break;
            case "2":
                type = "vigenere";
                encrypted = Cipher.VigenereCipher(quotes.get(key),keywords.get(key), true);
                break;
            case "3":
                type = "aristocrat";
                encrypted = Cipher.Aristocrat(quotes.get(key),keywords.get(key),key);
                break;
            default:
                type = "xenocrypt";
                encrypted = Cipher.XenocryptCipher(quotes.get(key),keywords.get(key), key);
                break;





        }
        String question = "The encrypted message is " + encrypted + ". It is encoded using a " + type + " cipher.";
        return question;
    }

    //Random Generator

    public static int keyGenerator() {
        Random rand = new Random();
        return rand.nextInt(19);
    }
}

