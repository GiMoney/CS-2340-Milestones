import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.*;

public class StartGameView extends ViewController {
    protected static JFrame view = new JFrame();
    protected static StartGameView current;
    protected static PlayerConfigurationView next;

    public static void main(String[] args) {
        view.setSize(500, 600);

        JButton startGameButton = new JButton("Start Game!");
        int sgbX = view.getWidth() / 2 - 50;
        int sgbY = view.getHeight() / 2 - 40;

        JLabel welcome = new JLabel("Welcome to Space Trader!");

        startGameButton.setBounds(sgbX, sgbY, 100, 40);
        welcome.setBounds(sgbX - 25, sgbY - 100, 200, 40);

        startGameButton.addActionListener(new SegueListener());

        view.add(welcome);
        view.add(startGameButton);

        view.setLocationRelativeTo(null);
        view.setLayout(new FlowLayout());
        view.setVisible(true);
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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