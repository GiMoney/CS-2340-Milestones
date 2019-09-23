import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.*;

public class PlayerConfigurationView extends ViewController {
    public static JFrame view = new JFrame();
    static PlayerConfigurationView current;
    static PlayerInfoView next;

    public static void main(String[] args) {
        view.setSize(800,600);
        Container cp = view.getContentPane();
        cp.setLayout(new BorderLayout());

        JLabel title = new JLabel("Player Configuration");
        title.setSize(200, 40);
        cp.add(title, BorderLayout.NORTH);

        JLabel name = new JLabel("Character Name:");
        name.setBounds(20, 50, 200, 40);
        cp.add(name, BorderLayout.CENTER);

        JTextField nameField = new JTextField();
        nameField.setBounds(250, 50, 200, 20);
        cp.add(nameField, BorderLayout.CENTER);

        JLabel difficulty = new JLabel("Select Game Difficulty:");
        difficulty.setBounds(20, 90, 200, 40);
        cp.add(difficulty, BorderLayout.CENTER);

        String[] diffs = {"Easy", "Medium", "Hard"};
        JComboBox diffList = new JComboBox(diffs);
        diffList.setBounds(250, 90, 200, 40);
        cp.add(diffList, BorderLayout.CENTER);

        JLabel skillsnum = new JLabel("hi");
        JButton select = new JButton("Select");
        select.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String diff = (String) diffList.getSelectedItem();
                System.out.print(diff);
                if (diff.equals("Easy")) {
                    skillsnum.setText("16");
                } else if (diff.equals("Medium")) {
                    skillsnum.setText("12");
                } else if (diff.equals("Hard")) {
                    skillsnum.setText("8");
                }
            }
        });
        select.setBounds(460, 90, 200, 40);
        cp.add(select, BorderLayout.CENTER);


        JLabel skills = new JLabel("Total Skill Points for that class:");
        skills.setBounds(20, 130, 200, 40);
        cp.add(skills, BorderLayout.CENTER);
        skillsnum.setBounds(230, 130, 200, 40);
        cp.add(skillsnum, BorderLayout.CENTER);

        JLabel pilot = new JLabel("Pilot");
        pilot.setBounds(20, 150, 200, 40);
        cp.add(pilot, BorderLayout.CENTER);
        JButton addpilot = new JButton("Add");
        int pilotpointscounter = 0;
        JLabel pilotpoints =  new JLabel(pilotpointscounter + "");
        addpilot.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pilotpoints.setText(pilotpointscounter + 1 + "");
            }
        });
        addpilot.setBounds(150, 150, 200, 40);
        cp.add(addpilot, BorderLayout.CENTER);
        pilotpoints.setBounds(120, 150, 200, 40);
        cp.add(pilotpoints, BorderLayout.CENTER);

        JLabel fighter = new JLabel("Fighter");
        fighter.setBounds(20, 170, 200, 40);
        cp.add(fighter, BorderLayout.CENTER);
        JButton addFighter = new JButton("Add");

        JLabel merchant = new JLabel("Merchant");
        merchant.setBounds(20, 190, 200, 40);
        cp.add(merchant, BorderLayout.CENTER);
        JButton addMerchant = new JButton("Add");

        JLabel engineer = new JLabel("Engineer");
        engineer.setBounds(20, 210, 200, 40);
        cp.add(engineer, BorderLayout.CENTER);
        JButton addEngineer = new JButton("Add");

        JLabel totalPtsLeft = new JLabel("Total Points Left:");
        totalPtsLeft.setBounds(20, 230, 200, 40);
        cp.add(totalPtsLeft, BorderLayout.CENTER);

        JButton b = new JButton("Configure Player");
        int bLocX = view.getWidth() / 2 - 100;
        int bLocY = view.getHeight() - 50;

        b.setSize(200, 40);
        b.addActionListener(new SegueListener());
        cp.add(b, BorderLayout.SOUTH);


        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }

    public static class SegueListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            current.view.setVisible(false);
            current.view.dispose();
            next = new PlayerInfoView();
            next.main(null);
        }
    }
}