import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.*;

public class StartGameView {
    private static JFrame view;
    public static void main(String[] args) {
        view = new JFrame();
        view.setSize(500, 600);

        JButton startGameButton = new JButton("Start Game!");
        int sgbX = view.getWidth() / 2 - 50;
        int sgbY = view.getHeight() / 2 - 40;

        JLabel welcome = new JLabel("Welcome to Space Trader!");

        startGameButton.setBounds(sgbX, sgbY, 1000, 40);
        welcome.setBounds(sgbX - 25, sgbY - 100, 2000, 40);

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
            view.setVisible(false);
            view.dispose();
            PlayerConfigurationView.main(null);
        }
    }
}