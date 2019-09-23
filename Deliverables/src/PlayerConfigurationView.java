import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.*;

public class PlayerConfigurationView extends ViewController {
    public static JFrame view = new JFrame();
    static PlayerConfigurationView current;
    static PlayerInfoView next;

    public static void main(String[] args) {
        view.setSize(500,600);
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

        int totalPts;
        if (diffList.getSelectedItem().equals("Easy")) {
            totalPts = 16;
        } else if (diffList.getSelectedItem().equals("Medium")) {
            totalPts = 12;
        } else {
            totalPts = 8;
        }

        JLabel skills = new JLabel("Allocate Skill Points: (" + totalPts + " available)");
        skills.setBounds(20, 130, 400, 40);
        cp.add(skills, BorderLayout.CENTER);

        JLabel pilot = new JLabel("Pilot");
        pilot.setBounds(20, 150, 200, 40);
        cp.add(pilot, BorderLayout.CENTER);

        JSpinner pSkill = new JSpinner(
                new SpinnerNumberModel(0, 0, totalPts, 1));
        pSkill.setBounds(250, 160, 40, 20);
        cp.add(pSkill, BorderLayout.CENTER);
        Integer allocatedPts = 0;
        /*pSkill.addChangeListener(e -> {
                allocatedPts = (Integer)((JSpinner)e.getSource()).getValue();
        });
        */
        JLabel fighter = new JLabel("Fighter");
        fighter.setBounds(20, 170, 200, 40);
        cp.add(fighter, BorderLayout.CENTER);

        JSpinner fSkill = new JSpinner(new SpinnerNumberModel(0, 0, totalPts, 1));
        fSkill.setBounds(250, 180, 40, 20);
        cp.add(fSkill, BorderLayout.CENTER);
        /*JLabel tempF = new JLabel();
        fSkill.addChangeListener(e -> {
            tempF.setText((String)((JSpinner)e.getSource()).getValue());
        });
        */
        JLabel merchant = new JLabel("Merchant");
        merchant.setBounds(20, 190, 200, 40);
        cp.add(merchant, BorderLayout.CENTER);

        JSpinner mSkill = new JSpinner(new SpinnerNumberModel(0, 0, totalPts, 1));
        mSkill.setBounds(250, 200, 40, 20);
        cp.add(mSkill, BorderLayout.CENTER);
        /*JLabel tempM = new JLabel();
        mSkill.addChangeListener(e -> {
            tempM.setText((String)((JSpinner)e.getSource()).getValue());
        });
        */

        JLabel engineer = new JLabel("Engineer");
        engineer.setBounds(20, 210, 200, 40);
        cp.add(engineer, BorderLayout.CENTER);

        JSpinner eSkill = new JSpinner(new SpinnerNumberModel(0, 0, totalPts, 1));
        eSkill.setBounds(250, 220, 40, 20);
        cp.add(eSkill, BorderLayout.CENTER);
        /*JLabel tempE = new JLabel();
        eSkill.addChangeListener(e -> {
            tempE.setText((String)((JSpinner)e.getSource()).getValue());
        });
        
         */

        JLabel totalPtsLeft = new JLabel("Total Points Left:" + (totalPts - allocatedPts));
        totalPtsLeft.setBounds(20, 230, 200, 40);
        cp.add(totalPtsLeft, BorderLayout.CENTER);

        JButton b = new JButton("Configure Player");

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