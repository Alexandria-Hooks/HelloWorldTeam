import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/* Game User Interface Class
*
*  used in Driver to complete
*  game loop
*/
public class Game extends JFrame {
    private JPanel panel;
    private JLabel title;
    private String encryption;      // phrase to be solved
    private JLabel currentPoints;
    private double points;          // user's total points
    private int max_points;         // total possible points from a question
    private JLabel currentAttempts;
    private int attempts = 0;
    private int correctAttempts = 0; // number of correct attempts
    private JLabel question;
    private JTextField guess;       // user guess
    ArrayList<String> quotes;       // phrases
    ArrayList<String> keywords;
    String phrase;

    public Game(int level, double inPoints, int inCorrect) { // Constructor
        points = inPoints;              // obtain current player points
        correctAttempts = inCorrect;    // obtain current player correct attempts
        // Initialize quotes and keywords
        quotes = lists("YOUR PATH TO QuoteSheet.csv HERE");          // initialize quote list
        keywords = lists("YOUR PATH TO Keywords_Sheet1.csv HERE");   // initialize keyword list

        setTitle("Anagrams - Level " +  level);
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel(new FlowLayout());
        // customize
        panel.setBackground(new Color(177, 156, 215)); // light purple

        // initialize parameters
        String type = "";
        max_points = 100; // max possible points
        encryption = "";
        String keyw;
        if (level == 4) { // for Xenocrypt Cipher
            phrase = quotes.get(randomGenerator(25,21)).toUpperCase();  // spanish quotes
            keyw = keywords.get(randomGenerator(25, 21)).toUpperCase(); // random spanish word
        } else {
            phrase = quotes.get(randomGenerator(20, 0)).toUpperCase();
            keyw = keywords.get(randomGenerator(20, 0)).toUpperCase();  // obtain random key word
        }
        //System.out.println("Encrypted Phrase: " + phrase); // for debugging
        int key = randomGenerator(19, 1);
        String encryptionKey = ""; // stuff to use to solve encryption
        switch (level) {  // figure out which cipher and obtain encryption
            case 1: {
                type = "Caesar";
                encryption = Cipher.CaesarCipher(phrase, key); // obtain Caesar encryption
                encryptionKey = Integer.toString(key);
                break;
            }
            case 2: {
                type = "Vigenère";
                encryption = Cipher.VigenereCipher(phrase, keyw);
                encryptionKey = " word '" + keyw + "'";
                break;
            }
            case 3: {
                type = "Aristocrat";
                max_points = 500;
                encryption = Cipher.Aristocrat(phrase, keyw, key);
                encryptionKey = "word '" + keyw + "' and key " + key;
                break;
            }
            default: {
                type = "Xenocrypt";
                max_points = 1000;
                encryption = Cipher.XenocryptCipher(phrase, keyw, key);
                encryptionKey = "word '" + keyw + "' and key " + key;
            }
        }
        title = new JLabel(type + " Cipher");
        currentPoints = new JLabel("Points: " + points);
        currentAttempts = new JLabel("Attempts: " + attempts);
        question = new JLabel("<html>Encrypted message: " + encryption + "<br>It is encoded using key " + encryptionKey + ".</html>");
        guess = new JTextField(40);
        // customize
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        currentPoints.setFont(new Font("SansSerif", Font.BOLD, 20));
        currentAttempts.setFont(new Font("SansSerif", Font.BOLD, 20));
        question.setFont(new Font("SansSerif", Font.BOLD, 15));
        question.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        question.setHorizontalAlignment(JLabel.CENTER);

        // enter button
        JButton enter = new JButton("Enter");
        // customize
        enter.setFont(new Font("SansSerif", Font.BOLD, 20));
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                attempts++; // add 1 attempt
                String guessed = guess.getText().toUpperCase(); // take in user input

                // update panel part that both if and else do
                currentAttempts.setText("Attempts: " + attempts);
                // compare to correct answer
                if (guessed.equals(phrase)) {
                    // obtain user points
                    points += getScore(max_points, attempts);
                    correctAttempts++; // update correct attempts by 1

                    // pop up msg
                    JOptionPane.showMessageDialog(null, "You guessed correctly!");

                    // panel update
                    currentPoints.setText("Points: " + points);
                    question.setText("Decrypted message: " + phrase);
                    panel.remove(enter);
                    panel.remove(guess);
                    if (level == 4) {
                        // pop up msg
                        JOptionPane.showMessageDialog(null, "You've completed the game with " + points + " points!");
                        System.exit(0); // close UI
                    }
                    if (correctAttempts == 5) {
                        // Ask to raise level
                        int choice = JOptionPane.showConfirmDialog(null, "Would you like to move to the next level?");
                        if (choice == JOptionPane.YES_OPTION) {
                            dispose();
                            new Game(level + 1, points, correctAttempts);
                        }
                        dispose(); // close UI
                    } else {
                        // Add next button
                        JButton next = new JButton("Next");
                        next.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                new Game(level, points, correctAttempts);
                            }
                        });
                        panel.add(next);
                    }
                } else {
                    // pop up msg
                    JOptionPane.showMessageDialog(null, "Wrong! you're low-key chopped...");

                    // panel update
                    panel.add(currentPoints);
                    panel.add(currentAttempts);
                    panel.add(question);
                    panel.add(guess);
                    panel.add(enter);
                }
                // panel update
                panel.revalidate();
                panel.repaint();
            }
        });

        // initial UI
        panel.add(title);
        panel.add(currentPoints);
        panel.add(currentAttempts);
        panel.add(question);
        panel.add(guess);
        panel.add(enter);

        add(panel);
        setVisible(true);
    }

    // Random Number Generator for min to max
    public static int randomGenerator(int max, int min) {
        return (int) (Math.random() * (max - min) + min);
    }

    // Calculate points gained based on number of attempts
    public static double getScore(int inPoint, int inAttempts) {
        if (inAttempts == 1)
            return inPoint;
        return inPoint * Math.pow(0.9, (inAttempts - 1));
    }

    // String lists - takes csv path & creates list
    public static ArrayList<String> lists(String path){
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
/*
    public static void main(String[] args) { // for debugging Game UI
        new Game(4, 0, 4);
        //System.out.println(getScore(100, 2));
    }*/
}
