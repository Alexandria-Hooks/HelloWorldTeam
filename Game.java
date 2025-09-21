import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

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
    private int points;
    private JLabel currentAttempts;
    private int attempts;
    private JLabel question;
    private JLabel prompt;
    private JTextField guess;       // user guess

    public Game(int level) { // Constructor
        setTitle("Anagrams - Level " +  level);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel(new FlowLayout());

        String type = "";
        encryption = "";
        int key = keyGenerator();
        switch (level) {  // figure out which cipher and obtain encryption
            case 1: {
                type = "Caesar";
                encryption = Cipher.CaesarCipher("DOVE IS BIRD", key); // TODO: use real phrase
                break;
            }
            case 2: {
                type = "Vigenere";
                encryption = Cipher.VigenereCipher("Dove", "", true); // TODO: use real phrase
                break;
            }
            case 3: {
                type = "Aristocrat";
                encryption = Cipher.Aristocrat("Dove", "Dove", key); // TODO: use real phrase
                break;
            }
            case 4: {
                type = "Porta";
                // TODO: call Porta function from Cipher and assign to encryption
                break;
            }
            default:
                type = "Xenocrypt";
                encryption = Cipher.XenocryptCipher("Dove", "Dove", key); // TODO: use real phrase
        }
        title = new JLabel(type + " Cipher");
        currentPoints = new JLabel("Points: " + points);
        currentAttempts = new JLabel("Attempts: " + attempts);
        question = new JLabel("<html>Encrypted message: " + encryption + "<br>It is encoded using TODO</html>"); // TODO: obtain encryption from Cipher Class
        prompt = new JLabel("Guess: ");
        guess = new JTextField(15);

        // enter button
        JButton enter = new JButton("Enter");
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                attempts++; // add 1 attempt
                String guessed = guess.getText().toUpperCase(); // take in user input
                System.out.println("Guessed: " + guessed);
                // TODO: compare to correct answer
                if (guessed.equals(encryption)) {

                }
                // TODO: figure out how many points earned if correct
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

    // Random Key Generator
    public static int keyGenerator() {
        return new Random().nextInt(10);
    }

    public static void main(String[] args) { // for debugging Game UI
        new Game(1);
        //System.out.println(keyGenerator());
    }
}
