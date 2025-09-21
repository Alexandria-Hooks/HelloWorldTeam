import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*  User Interface Class
 *
 *  to be used in Driver for welcome
 *  page, and collect user input
 */
public class UI extends JFrame {
    private JTextField level;
    private JLabel levelPrompt;
    private JPanel panel;

    // For Driver to be able to use input
    public interface LevelListener {
        void getLevel(String level);
    }

    public UI(LevelListener listener){
        setTitle("Anagrams - Menu");
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel.setBackground(new Color(177,156,215));
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20,40,20,40));

        panel = new JPanel(new FlowLayout());

        levelPrompt = new JLabel("Welcome to Anagrams!"); // temporarily welcome msg
        levelPrompt.setFont(new Font("SansSerif", Font.BOLD, 20));
        levelPrompt.setAlignmentX(Component.CENTER_ALIGNMENT);

        // enter button
        JButton enter = new JButton("Enter");
        enter.setFont(new Font("SansSerif", Font.BOLD, 20));
        enter.setAlignmentX(Component.CENTER_ALIGNMENT);
        enter.addActionListener(new ActionListener() {
            private boolean firstClick = true;

            @Override
            public void actionPerformed(ActionEvent e){
                panel.removeAll();
                if(firstClick){
                    // change temp welcome msg to level prompt
                    levelPrompt.setText("<html>Enter Level: (1, 2, 3, 4, or 5)<br>" +
                            "Easy:<br>1. Caesar<br>2. Vigenere<br>" +
                            "Mid:<br>3. Aristocrat<br>4. Rail Fence<br>" +
                            "Hell:<br>5. Baconian</html>");
                    levelPrompt.setFont(new Font("SansSerif", Font.BOLD, 15));
                    level = new JTextField(10); // to take user input

                    panel.add(levelPrompt);
                    panel.add(level);
                    panel.add(enter);
                    firstClick = false;
                } else {
                    // pass chosen level to Driver
                    String lvl = level.getText();   // user input
                    listener.getLevel(lvl);         // return input
                    dispose();                      // exit UI
                }
                // panel update
                panel.revalidate();
                panel.repaint();
            }
        });

        // initial welcome UI
        panel.add(levelPrompt);
        panel.add(enter);

        add(panel);
        setVisible(true);
    }
/*
    public static void main(String[] args) {    // for debugging UI
        new UI(level1 -> {
            System.out.println(level1);
        });
    } */
}
