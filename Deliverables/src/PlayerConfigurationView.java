import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class PlayerConfigurationView extends ViewController {
    public static JFrame view = new JFrame();
    private static PlayerConfigurationView current;
    private static PlayerInfoView next;
    private static String[] inputData = new String[6];
    private static int maxPoints = 0;
    private static JFormattedTextField pSkill;
    private static JFormattedTextField fSkill;
    private static JFormattedTextField mSkill;
    private static JFormattedTextField eSkill;

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

        HashMap<String, String> diffsToPoints = new HashMap<>();
        diffsToPoints.put("Easy", "16");
        diffsToPoints.put("Medium", "12");
        diffsToPoints.put("Hard", "8");

        Object[] diffs = diffsToPoints.keySet().toArray();
        JComboBox diffList = new JComboBox(diffs);
        diffList.setBounds(180, 90, 200, 40);
        cp.add(diffList, BorderLayout.CENTER);

        JLabel skillsnum = new JLabel();
        skillsnum.setBounds(230, 130, 200, 40);
        String currentSelection = (String) diffList.getSelectedItem();

        JButton select = new JButton("Select");
        select.setBounds(390, 90, 100, 40);
        cp.add(skillsnum, BorderLayout.CENTER);
        cp.add(select, BorderLayout.CENTER);

        JLabel skills = new JLabel("Total Skill Points for that class:");
        skills.setBounds(20, 130, 200, 40);
        cp.add(skills, BorderLayout.CENTER);

        JLabel pilot = new JLabel("Pilot");
        pilot.setBounds(50, 150, 200, 40);
        cp.add(pilot, BorderLayout.CENTER);

        pSkill = new JFormattedTextField("%d*");
        pSkill.setValue(0);
        pSkill.setBounds(200, 160, 40, 20);
        cp.add(pSkill, BorderLayout.CENTER);

        JLabel fighter = new JLabel("Fighter");
        fighter.setBounds(50, 170, 200, 40);
        cp.add(fighter, BorderLayout.CENTER);

        fSkill = new JFormattedTextField("%d*");
        fSkill.setValue(0);
        fSkill.setBounds(200, 180, 40, 20);
        cp.add(fSkill, BorderLayout.CENTER);

        JLabel merchant = new JLabel("Merchant");
        merchant.setBounds(50, 190, 200, 40);
        cp.add(merchant, BorderLayout.CENTER);

        mSkill = new JFormattedTextField("%d*");
        mSkill.setValue(0);
        mSkill.setBounds(200, 200, 40, 20);
        cp.add(mSkill, BorderLayout.CENTER);

        JLabel engineer = new JLabel("Engineer");
        engineer.setBounds(50, 210, 200, 40);
        cp.add(engineer, BorderLayout.CENTER);

        eSkill = new JFormattedTextField("%d*");
        eSkill.setValue(0);
        eSkill.setBounds(200, 220, 40, 20);
        cp.add(eSkill, BorderLayout.CENTER);

        JLabel space = new JLabel(" ");
        space.setBounds(50, 210, 200, 40);
        cp.add(space, BorderLayout.CENTER);

        JButton b = new JButton("Configure Player");

        b.setSize(200, 40);

        select.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selection = (String) diffList.getSelectedItem();
                String points = diffsToPoints.get(selection);
                maxPoints = Integer.parseInt(points);
                skillsnum.setText(points);
                skillsnum.updateUI();

                // Flush all entered values
                pSkill.setValue(0);
                fSkill.setValue(0);
                mSkill.setValue(0);
                eSkill.setValue(0);
            }
        });

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

        pSkill.addActionListener(new SkillPointFieldUpdater());
        fSkill.addActionListener(new SkillPointFieldUpdater());
        mSkill.addActionListener(new SkillPointFieldUpdater());
        eSkill.addActionListener(new SkillPointFieldUpdater());

        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }

    private static class SkillPointFieldUpdater implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Updated field");
            JFormattedTextField editedField =
                    (JFormattedTextField) e.getSource();
            int attemptedPoints = getTextValue(editedField);
            editedField.setValue(0);
            int distributedPoints = getAllocatedPoints();
            int remainingPoints = maxPoints - distributedPoints;

            System.out.printf("A: %d, D: %d, T: %d, R: %d%n", attemptedPoints,
                    distributedPoints, maxPoints, remainingPoints);

            if (attemptedPoints < remainingPoints) {
                editedField.setValue(attemptedPoints);
            } else {
                editedField.setValue(remainingPoints);
            }
        }
    }

    private static int getAllocatedPoints() {
        System.out.println("Got allocated points");
        return Integer.parseInt(pSkill.getValue().toString()) +
                Integer.parseInt(fSkill.getValue().toString()) +
                Integer.parseInt(mSkill.getValue().toString()) +
                Integer.parseInt(eSkill.getValue().toString());
    }

    private static int getTextValue(JFormattedTextField field) {
        return Integer.parseInt(field.getValue().toString());
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