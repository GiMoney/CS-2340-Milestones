import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.*;

public class PlayerInfoView extends ViewController {
    public static JFrame view = new JFrame();
    static PlayerInfoView current;

    public static void main(String[] args) {
        view.setSize(500,600);
        Container cp = view.getContentPane();
        cp.setLayout(new BorderLayout());


        JLabel b = new JLabel("Player view");
        int bLocX = view.getWidth() / 2 - 50;
        int bLocY = view.getHeight() / 2 - 40;

        b.setBounds(0, 0, 10, 20);
        cp.add(b, BorderLayout.NORTH);
        //b.addActionListener(new SegueListener());

        JLabel name = new JLabel("Character name: args0x");
        name.setBounds(0, 50, 150, 150);
        cp.add(name, BorderLayout.CENTER);

        JLabel diff = new JLabel("Difficulty: args1");
        diff.setBounds(0, 100, 120, 100);
        cp.add(diff, BorderLayout.CENTER);

        JLabel pilot = new JLabel("Pilot: args2");
        pilot.setBounds(0, 150, 200, 40);
        cp.add(pilot, BorderLayout.CENTER);

        JLabel fighter = new JLabel("Fighter: args3");
        fighter.setBounds(0, 170, 200, 40);
        cp.add(fighter, BorderLayout.CENTER);

        JLabel merchant = new JLabel("Merchant: args4");
        merchant.setBounds(0, 190, 200, 40);
        cp.add(merchant, BorderLayout.CENTER);

        JLabel engineer = new JLabel("Engineer: args5");
        engineer.setBounds(0, 210, 200, 40);
        cp.add(engineer, BorderLayout.CENTER);


        JLabel credits = new JLabel("Starting Credits: args6");
        /*if (args[6].equals("Easy")) {
            credits.setText("Starting Credits: 1000");
        } else if (args[6].equals("Medium")) {
            credits.setText("Starting Credits: 500");
        } else {
            credits.setText("Starting Credits: 100");
        }
        */
        credits.setBounds(0, 250, 200, 40);
        cp.add(credits, BorderLayout.CENTER);


        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }

    public static class SegueListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            current.view.setVisible(false);
            current.view.dispose();
        }
    }
}