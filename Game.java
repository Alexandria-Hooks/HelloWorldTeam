import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private double points = 0.0;    // user's total points
    private int max_points;         // total possible points from a question
    private JLabel currentAttempts;
    private int attempts = 0;
    private int correctAttempts = 0; // number of correct attempts
    private JLabel question;
    private JLabel prompt;
    private JTextField guess;       // user guess

    public Game(int level, String phrase) { // Constructor
        setTitle("Anagrams - Level " +  level);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel(new FlowLayout());

        String type = "";
        max_points = 100; // max possible points
        encryption = "";
        int key = keyGenerator();
        String keyw = "DOVE";  // TODO: use real keyw
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
                encryptionKey = "word '" + keyw + "' and key " + Integer.toString(key);
                break;
            }
            default: {
                type = "Xenocrypt";
                max_points = 1000;
                encryption = Cipher.XenocryptCipher(phrase, "DOVE", key);
                encryptionKey = "word '" + keyw + "' and key " + Integer.toString(key);
            }
        }
        title = new JLabel(type + " Cipher");
        currentPoints = new JLabel("Points: " + points);
        currentAttempts = new JLabel("Attempts: " + attempts);
        question = new JLabel("<html>Encrypted message: " + encryption + "<br>It is encoded using key " + encryptionKey + ".</html>");
        prompt = new JLabel("Guess: ");
        guess = new JTextField(15);

        // enter button
        JButton enter = new JButton("Enter");
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
                    if (correctAttempts == 10) {
                        if (level == 4) {
                            // pop up msg
                            JOptionPane.showMessageDialog(null, "You've completed the game with " + points + " points!");
                            dispose(); // close UI
                        }
                        // Ask to raise level
                        int choice = JOptionPane.showConfirmDialog(null, "Would you like to move to the next level?");
                        if (choice == JOptionPane.YES_OPTION) {
                            dispose();
                            new Game(level + 1, "NEW PHRASE HERE"); // TODO: get new phrase
                        }
                        dispose(); // close UI
                    } else {
                        // Add next button
                        JButton next = new JButton("Next");
                        next.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                new Game(level, "NEW PHRASE HERE"); // TODO: get a new phrase
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
        panel.add(prompt);
        panel.add(guess);
        panel.add(enter);

        add(panel);
        setVisible(true);
    }

    // Random Key Generator (1 to 19)
    public static int keyGenerator() {
        return (int) (Math.random() * (18) + 1);
    }

    // Calculate points gained based on number of attempts
    public static double getScore(int inPoint, int inAttempts) {
        if (inAttempts == 1)
            return inPoint;
        return inPoint * Math.pow(0.9, (inAttempts - 1));
    }
/*
    public static void main(String[] args) { // for debugging Game UI
        new Game(3, "DOVE IS BIRD");
        //System.out.println(keyGenerator());
        //System.out.println(getScore(100, 2));
    }*/
}
