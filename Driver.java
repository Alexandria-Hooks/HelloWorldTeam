


import java.util.Scanner;
public class Driver {
    public static void main(String[] args) {
        // Initialize scanner and variables
        Scanner in = new Scanner(System.in);
        String level = "";                          // difficulty level user will attempt
        int points = 0;                             // user points, goes down with number of failed attempts

        // Welcome text
        System.out.println("Welcome to Anagrams!");
        do { 
            System.out.println("Please choose your level: (1, 2, or 3)");
            System.out.println("1. Easy\n2. Medium\n3. Hard");
            level = in.nextLine();
        } while (!(level.equals("1") || level.equals("2") || level.equals("3")));  // keep going until user inputs correct level
    }

}
