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
    private JLabel currentPoints;
    private int points;
    private JLabel currentAttempts;
    private int attempts;
    private JLabel question;
    private JLabel prompt;
    private JTextField guess;

    public Game(int level) { // Constructor
        setTitle("Anagrams - Level " +  level);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel(new FlowLayout());
        String type = "";
        switch (level) {  // figure out which cipher
            case 1: {
                type = "Caesar";
                break;
            }
            case 2: {
                type = "Vigenere";
                break;
            }
            case 3: {
                type = "Aristocrat";
                break;
            }
            case 4: {
                type = "Rail Fence";
                break;
            }
            default:
                type = "Baconian";
        }
        title = new JLabel(type + " Cipher");
        currentPoints = new JLabel("Points: " + points);
        currentAttempts = new JLabel("Attempts: " + attempts);
        question = new JLabel("Get from Cipher Class"); // TODO: obtain encryption from Cipher Class
        prompt = new JLabel("Guess: ");
        guess = new JTextField(15);

        // enter button
        JButton enter = new JButton("Enter");
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String guessed = guess.getText().toUpperCase(); // take in user input
                System.out.println("Guessed: " + guessed);
                // TODO: compare to correct answer
                // TODO: figure out how many points earned if correct
                attempts++; // add 1 attempt
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

    public static void main(String[] args) { // for debugging Game UI
        new Game(3);
    }
}
