import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class PlayerConfigurationView extends ViewController {
    protected static JFrame view = new JFrame();
    private static PlayerConfigurationView current;
    private static PlayerInfoView next;
    private static String[] inputData = new String[7];
    private static int maxPoints = 0;
    private static JTextField nameField;
    private static JComboBox diffList;
    private static JButton b;
    private static JFormattedTextField pSkill;
    private static JFormattedTextField fSkill;
    private static JFormattedTextField mSkill;
    private static JFormattedTextField eSkill;

    public static void main(String[] args) {
        view.setSize(500, 600);
        Container cp = view.getContentPane();
        cp.setLayout(new BorderLayout());

        JLabel title = new JLabel("Player Configuration");
        title.setSize(200, 40);
        cp.add(title, BorderLayout.NORTH);

        JLabel name = new JLabel("Character Name:");
        name.setBounds(20, 50, 200, 40);
        cp.add(name, BorderLayout.CENTER);

        nameField = new JTextField();
        nameField.setBounds(180, 60, 200, 20);
        cp.add(nameField, BorderLayout.CENTER);

        JLabel difficulty = new JLabel("Select Game Difficulty:");
        difficulty.setBounds(20, 90, 200, 40);
        cp.add(difficulty, BorderLayout.CENTER);

        JLabel note = new JLabel("Must press enter when entering skill points");
        note.setBounds(20, 300, 300, 100);
        cp.add(note, BorderLayout.CENTER);

        HashMap<String, String> diffsToPoints = new HashMap<>();
        diffsToPoints.put("Easy", "16");
        diffsToPoints.put("Medium", "12");
        diffsToPoints.put("Hard", "8");

        Object[] diffs = diffsToPoints.keySet().toArray();
        diffList = new JComboBox(diffs);
        diffList.setBounds(180, 90, 200, 40);
        cp.add(diffList, BorderLayout.CENTER);

        JLabel skillsnum = new JLabel();
        skillsnum.setBounds(230, 130, 200, 40);

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

        b = new JButton("Configure Player");
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
                b.setEnabled(true);
            }
        });


        b.addActionListener(new SegueListener());
        b.setEnabled(true);
        cp.add(b, BorderLayout.SOUTH);

        pSkill.addActionListener(new SkillPointFieldUpdater());
        fSkill.addActionListener(new SkillPointFieldUpdater());
        mSkill.addActionListener(new SkillPointFieldUpdater());
        eSkill.addActionListener(new SkillPointFieldUpdater());
        b.setEnabled(true);
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static boolean canContinueConfiguation() {
        boolean didFillName = !nameField.getText().equals("");
        boolean didAllocatedAllPoints = getAllocatedPoints() == maxPoints;
        return didFillName && didAllocatedAllPoints;
    }

    private static int getAllocatedPoints() {
        return Integer.parseInt(pSkill.getValue().toString())
                + Integer.parseInt(fSkill.getValue().toString())
                + Integer.parseInt(mSkill.getValue().toString())
                + Integer.parseInt(eSkill.getValue().toString());
    }

    private static int getTextValue(JFormattedTextField field) {
        return Integer.parseInt(field.getValue().toString());
    }

    private static class SkillPointFieldUpdater implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFormattedTextField editedField =
                    (JFormattedTextField) e.getSource();
            int attemptedPoints = getTextValue(editedField);
            editedField.setValue(0);
            int distributedPoints = getAllocatedPoints();
            int remainingPoints = maxPoints - distributedPoints;

            if (attemptedPoints < remainingPoints) {
                editedField.setValue(attemptedPoints);
            } else {
                editedField.setValue(remainingPoints);
            }

            b.setEnabled(true);
        }
    }

    public static class SegueListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            current.view.setVisible(false);
            current.view.dispose();
            next = new PlayerInfoView();
            inputData = new String[] {
                nameField.getText(), diffList.getSelectedItem().toString(),
                pSkill.getValue().toString(),
                fSkill.getValue().toString(),
                mSkill.getValue().toString(),
                eSkill.getValue().toString(), null
            };

            if (getAllocatedPoints() == maxPoints && !nameField.getText().equals("")
                    && maxPoints != 0) {
                next.main(inputData);
            } else {
                view.setVisible(true);
                JOptionPane.showMessageDialog(view, "Double check if you configured properly"
                        +  " i.e included name, game mode, and correct skill points");
                view.setVisible(true);
            }

        }
    }
}