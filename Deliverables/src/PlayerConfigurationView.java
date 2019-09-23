import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.*;

public class PlayerConfigurationView extends ViewController {
    public static JFrame view = new JFrame();
    static PlayerConfigurationView current;
    static PlayerInfoView next;
    static String[] inputData = new String[6];

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
        nameField.setBounds(180, 60, 200, 20);
        cp.add(nameField, BorderLayout.CENTER);

        JLabel difficulty = new JLabel("Select Game Difficulty:");
        difficulty.setBounds(20, 90, 200, 40);
        cp.add(difficulty, BorderLayout.CENTER);

        String[] diffs = {"Easy", "Medium", "Hard"};
        JComboBox diffList = new JComboBox(diffs);
        diffList.setBounds(180, 90, 200, 40);
        cp.add(diffList, BorderLayout.CENTER);

        JLabel skillsnum = new JLabel("16");
        JButton select = new JButton("Select");

        select.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String diff = (String) diffList.getSelectedItem();
                if (diff.equals("Easy")) {
                    skillsnum.setText("16");
                } else if (diff.equals("Medium")) {
                    skillsnum.setText("12");
                } else {
                    skillsnum.setText("8");
                }
            }
        });
        select.setBounds(390, 90, 100, 40);
        cp.add(select, BorderLayout.CENTER);

        JLabel skills = new JLabel("Total Skill Points for that class:");
        skills.setBounds(20, 130, 200, 40);
        cp.add(skills, BorderLayout.CENTER);
        skillsnum.setBounds(230, 130, 200, 40);
        cp.add(skillsnum, BorderLayout.CENTER);

        JLabel pilot = new JLabel("Pilot");
        pilot.setBounds(50, 150, 200, 40);
        cp.add(pilot, BorderLayout.CENTER);

        JSpinner pSkill = new JSpinner(
                new SpinnerNumberModel(0, 0, 16, 1));
        pSkill.setBounds(200, 160, 40, 20);
        cp.add(pSkill, BorderLayout.CENTER);

        JLabel fighter = new JLabel("Fighter");
        fighter.setBounds(50, 170, 200, 40);
        cp.add(fighter, BorderLayout.CENTER);

        JSpinner fSkill = new JSpinner(new SpinnerNumberModel(0, 0, 16, 1));
        fSkill.setBounds(200, 180, 40, 20);
        cp.add(fSkill, BorderLayout.CENTER);

        JLabel merchant = new JLabel("Merchant");
        merchant.setBounds(50, 190, 200, 40);
        cp.add(merchant, BorderLayout.CENTER);

        JSpinner mSkill = new JSpinner(new SpinnerNumberModel(0, 0, 16, 1));
        mSkill.setBounds(200, 200, 40, 20);
        cp.add(mSkill, BorderLayout.CENTER);

        JLabel engineer = new JLabel("Engineer");
        engineer.setBounds(50, 210, 200, 40);
        cp.add(engineer, BorderLayout.CENTER);

        JSpinner eSkill = new JSpinner(new SpinnerNumberModel(0, 0, 16, 1));
        eSkill.setBounds(200, 220, 40, 20);
        cp.add(eSkill, BorderLayout.CENTER);

        JLabel space = new JLabel(" ");
        space.setBounds(50, 210, 200, 40);
        cp.add(space, BorderLayout.CENTER);

        JButton b = new JButton("Configure Player");

        b.setSize(200, 40);
        b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                inputData[0] = nameField.getText();
                inputData[1] = (String) diffList.getSelectedItem();
                inputData[2] = ((Integer)pSkill.getValue()).toString();
                inputData[3] = ((Integer)fSkill.getValue()).toString();
                inputData[4] = ((Integer)mSkill.getValue()).toString();
                inputData[5] = ((Integer)eSkill.getValue()).toString();
            }
        });
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
            next.main(inputData);
        }
    }
}