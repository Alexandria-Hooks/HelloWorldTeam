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
    private double points;             // user's total points
    private int max_points;         // total possible points from a question
    private JLabel currentAttempts;
    private int attempts;
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
        title = new JLabel(type + " Cipher");
        currentPoints = new JLabel("Points: " + points);
        currentAttempts = new JLabel("Attempts: " + attempts);
        question = new JLabel(phrase);
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

                // update panel parts that both if and else do
                panel.removeAll();
                currentAttempts = new JLabel("Attempts: " + attempts);
                panel.add(title);
                panel.add(title);
                // compare to correct answer
                if (guessed.equals(phrase)) {
                    // obtain user points
                    points += getScore(max_points, attempts);

                    // pop up msg
                    JOptionPane.showMessageDialog(null, "You guessed correctly!");

                    // panel update
                    currentPoints = new JLabel("Points: " + points);
                    question = new JLabel("Encrypted message: " + phrase);
                    panel.add(currentPoints);
                    panel.add(currentAttempts);
                    panel.add(question);
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
        new Game(1, "DOVE IS BIRD");
        //System.out.println(keyGenerator());
        //System.out.println(getScore(100, 2));
    }*/
}

