import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.*;

public class StartGameView extends ViewController {
    public static JFrame view = new JFrame();
    static StartGameView current;
    static PlayerConfigurationView next;

    public static void main(String[] args) {
        view.setSize(500,600);

        JButton startGameButton = new JButton("Start Game!");
        int sgb_x = view.getWidth() / 2 - 50;
        int sgb_y = view.getHeight() / 2 - 40;

        JLabel welcome = new JLabel("Welcome to Space Trader!");

        startGameButton.setBounds(sgb_x, sgb_y, 100, 40);
        welcome.setBounds(sgb_x - 25, sgb_y - 100, 200, 40);

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