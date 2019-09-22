import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.*;

public class StartGameView extends ViewController {
    public static JFrame view = new JFrame();
    static StartGameView current;
    static PlayerConfigurationView next;

    public static void main(String[] args) {
        view.setSize(500,600);

        JButton b = new JButton("Start Game!");
        int bLocX = view.getWidth() / 2 - 50;
        int bLocY = view.getHeight() / 2 - 40;

        JLabel welcome = new JLabel("Welcome to Space Trader!");

        b.setBounds(bLocX, bLocY, 100, 40);
        welcome.setBounds(bLocX - 25, bLocY - 100, 200, 40);

        b.addActionListener(new SegueListener());

        view.add(welcome);
        view.add(b);

        view.setLocationRelativeTo(null);
        view.setLayout(new FlowLayout());
        view.setVisible(true);
    }

    public static class SegueListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            current.view.setVisible(false);
            current.view.dispose();
            next = new PlayerConfigurationView();
            next.main(null);
        }
    }
}