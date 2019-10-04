import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;

public class PlayerInfoView extends ViewController {
    protected static JFrame view = new JFrame();
    protected static PlayerInfoView current;
    private static Game next;
    private static String[] config_args;

    public static void main(String[] args) {
        config_args = args;
        view.setSize(500,600);
        Container cp = view.getContentPane();
        cp.setLayout(new BorderLayout());

        JLabel b = new JLabel("Player view");
        int bLocX = view.getWidth() / 2 - 50;
        int bLocY = view.getHeight() / 2 - 40;

        b.setBounds(0, 0, 10, 20);
        cp.add(b, BorderLayout.NORTH);

        JLabel name = new JLabel("Character name: " + args[0]);
        name.setBounds(0, 50, 150, 150);
        cp.add(name, BorderLayout.CENTER);

        JLabel diff = new JLabel("Difficulty: " + args[1]);
        diff.setBounds(0, 100, 120, 100);
        cp.add(diff, BorderLayout.CENTER);

        JLabel pilot = new JLabel("Pilot: " + args[2]);
        pilot.setBounds(0, 150, 200, 40);
        cp.add(pilot, BorderLayout.CENTER);

        JLabel fighter = new JLabel("Fighter: " + args[3]);
        fighter.setBounds(0, 170, 200, 40);
        cp.add(fighter, BorderLayout.CENTER);

        JLabel merchant = new JLabel("Merchant: " + args[4]);
        merchant.setBounds(0, 190, 200, 40);
        cp.add(merchant, BorderLayout.CENTER);

        JLabel engineer = new JLabel("Engineer: " + args[5]);
        engineer.setBounds(0, 210, 200, 40);
        cp.add(engineer, BorderLayout.CENTER);


        JLabel credits = new JLabel("Starting Credits: ");
        if (args[1].equals("Easy")) {
            credits.setText("Starting Credits: 1000");
        } else if (args[1].equals("Medium")) {
            credits.setText("Starting Credits: 500");
        } else {
            credits.setText("Starting Credits: 100");
        }

        credits.setBounds(0, 250, 200, 40);
        cp.add(credits, BorderLayout.CENTER);

        JButton play = new JButton("Game on");
        play.setSize(200, 40);
        play.setBounds(0, 300, 4, 40);
        cp.add(play, BorderLayout.SOUTH);
        play.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                play.setEnabled(false);
                next.main(args);

            }
        });

        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }

    public static class SegueListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            current.view.setVisible(false);
            current.view.dispose();
            next = new Game();
            next.main(config_args);
        }
    }
}