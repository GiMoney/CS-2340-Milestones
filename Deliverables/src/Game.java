import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Rectangle;
import java.io.IOException;

public class Game extends ViewController {
    protected static ArrayList<Region> region;
    protected static JFrame view = new JFrame();
    protected static Game current;
    protected static JComboBox regionList;
    protected static Universe universe;
    protected static Player player;
    private static String[][] distTable;

    public static void startGame(String[] args) {
        universe = new Universe();
        region = universe.getRegion();
        distTable = new String[10][2];

        if (args[1].equals("Easy")) {
           player.setMoney(1000);
        } else if (args[1].equals("Medium")) {
            player.setMoney(500);
        } else {
            player.setMoney(100);
        }

        for (int i = 0; i < distTable.length; i++) {
            distTable[i][0] = region.get(i).getName();
            distTable[i][1] = String.format("%f", Region.distBetween(
                    player.getRegion(), region.get(i)));
        }
    }

    public static void main(String[] args) {
        player = new Player(args);
        startGame(args);
        String difficulty = args[1];
        view.setSize(1000,600);
        Container cp = view.getContentPane();
        cp.setLayout(new BorderLayout());

        int sgbX = cp.getWidth() / 2 - 50;
        int sgbY = cp.getHeight() / 2 - 40;

        JLabel welcome = new JLabel("Current Difficulty: " + difficulty);
        welcome.setBounds(sgbX - 25, sgbY - 100, 200, 40);
        JLabel location = new JLabel("Current Location: " + player.getRegion());
        location.setBounds(500, 300,200,40);
        JLabel listR = new JLabel("List of Regions: ");
        listR.setBounds(500, 300, 200, 40);
        JLabel money = new JLabel("Current Money: " + player.getMoney());
        money.setBounds(sgbX - 25, sgbY - 400, 200, 40);

        regionList.setBounds(1000, 1000, 1000, 1000);
        cp.add(welcome, BorderLayout.NORTH);
        //view.add(map);
        cp.add(location, BorderLayout.NORTH);
        cp.add(money, BorderLayout.NORTH);
        cp.add(listR);

        for (int i = 0; i < 10; i++) {
            JLabel regionName = new JLabel(distTable[i][0] + " (" + region.get(i).getX()
                    + ", " + region.get(i).getY() + ") ");
            regionName.setBounds(region.get(i).getX(), region.get(i).getY(), 200, 200);
            cp.add(regionName, BorderLayout.WEST);
        }

        JLabel distance1 = new JLabel(" Distance: " + distTable[0][1]);
        distance1.setBounds(region.get(0).getX(), region.get(0).getY(), 200, 200);
        cp.add(distance1, BorderLayout.EAST);

        JLabel distance2 = new JLabel(" Distance: " + distTable[1][1]);
        distance2.setBounds(region.get(1).getX(), region.get(1).getY(), 200, 200);
        cp.add(distance2, BorderLayout.EAST);

        JLabel distance3 = new JLabel(" Distance: " + distTable[2][1]);
        distance3.setBounds(region.get(2).getX(), region.get(2).getY(), 200, 200);
        cp.add(distance3, BorderLayout.EAST);

        JLabel distance4 = new JLabel(" Distance: " + distTable[3][1]);
        distance4.setBounds(region.get(3).getX(), region.get(3).getY(), 200, 200);
        cp.add(distance4, BorderLayout.EAST);

        JLabel distance5 = new JLabel(" Distance: " + distTable[4][1]);
        distance5.setBounds(region.get(4).getX(), region.get(4).getY(), 200, 200);
        cp.add(distance5, BorderLayout.EAST);

        JLabel distance6 = new JLabel(" Distance: " + distTable[5][1]);
        distance6.setBounds(region.get(5).getX(), region.get(5).getY(), 200, 200);
        cp.add(distance6, BorderLayout.EAST);

        JLabel distance7 = new JLabel(" Distance: " + distTable[6][1]);
        distance7.setBounds(region.get(6).getX(), region.get(6).getY(), 200, 200);
        cp.add(distance7, BorderLayout.EAST);

        JLabel distance8 = new JLabel(" Distance: " + distTable[7][1]);
        distance8.setBounds(region.get(7).getX(), region.get(7).getY(), 200, 200);
        cp.add(distance8, BorderLayout.EAST);

        JLabel distance9 = new JLabel(" Distance: " + distTable[8][1]);
        distance9.setBounds(region.get(8).getX(), region.get(8).getY(), 200, 200);
        cp.add(distance9, BorderLayout.EAST);

        JLabel distance10 = new JLabel(" Distance: " + distTable[9][1]);
        distance10.setBounds(region.get(9).getX(), region.get(9).getY(), 200, 200);
        cp.add(distance10, BorderLayout.EAST);

        JButton travel = new JButton("Travel!");
        travel.setBounds(390, 90, 100, 40);
        cp.add(regionList, BorderLayout.NORTH);
        cp.add(travel, BorderLayout.NORTH);

        travel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selection = regionList.getSelectedIndex();
                Region reg = region.get(selection);
                player.setRegion(reg);

                for (int i = 0; i < distTable.length; i++) {
                    distTable[i][1] = String.format("%f", Region.distBetween(
                            player.getRegion(), region.get(i)));
                }
                distance1.updateUI();
                distance2.updateUI();
                distance3.updateUI();
                distance4.updateUI();
                distance5.updateUI();
                distance6.updateUI();
                distance7.updateUI();
                distance8.updateUI();
                distance9.updateUI();
                distance10.updateUI();
            }
        });

        view.setLocationRelativeTo(null);
        view.setLayout(new FlowLayout());
        view.setVisible(true);
    }

    public static class SegueListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            current.view.setVisible(false);
            current.view.dispose();
            //next = new PlayerConfigurationView();
            //next.main(null);
        }
    }
}