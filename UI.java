import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*  User Interface Class
 *
 *  to be used in Driver for welcome
 *  page, and game loop
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
        setTitle("Anagrams");
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new FlowLayout());

        levelPrompt = new JLabel("Welcome to Anagrams!"); // temporarily welcome msg

        // enter button
        JButton enter = new JButton("Enter");
        enter.addActionListener(new ActionListener() {
            private boolean firstClick = true;

            @Override
            public void actionPerformed(ActionEvent e){
                panel.removeAll();
                if(firstClick){
                    // change temp welcome msg to level prompt
                    levelPrompt.setText("<html>Enter Level: (1, 2, or 3).<br>1. Easy.<br>2. Medium.<br>3. Hard</html>");
                    level = new JTextField(10); // to take user input

                    panel.add(levelPrompt);
                    panel.add(level);
                    panel.add(enter);
                    firstClick = false;
                } else {
                    // pass chosen level to Driver
                    String lvl = level.getText();   // user input
                    listener.getLevel(lvl);
                    // TODO: exit UI?
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