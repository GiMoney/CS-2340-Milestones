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
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Rectangle;
import java.io.IOException;


public class Game extends ViewController{
    static ArrayList<Region> region;
    public static JFrame view = new JFrame();
    static Game current;
    public static JComboBox RegionList;
    static Universe universe;
    public static Player player;
    public static String names[] = {"Alpha-20","Beta-43","Charlie-28", "Delta-8","EEEEE-E","Falcon-69","Gamma-Hamma","Helix-Felix","I-99","Ben-10"};
    public static void startGame(String[] args) {
        universe = new Universe(names);
        region = universe.region;
        if (args[1].equals("Easy")) {
           player.setMoney(1000);
        } else if (args[1].equals("Medium")) {
            player.setMoney(500);
        } else {
            player.setMoney(100);
        }
    }

    public static void main(String[] args) {
        player = new Player(args);
        startGame(args);
        String difficulty = args[1];
        view.setSize(1000,600);
        Container cp = view.getContentPane();
        cp.setLayout(null);

        int sgb_x = view.getWidth() / 2 - 50;
        int sgb_y = view.getHeight() / 2 - 40;

        JLabel welcome = new JLabel("Current Difficulty:" + difficulty);
        welcome.setBounds(sgb_x - 25, sgb_y - 100, 200, 40);
        JLabel location = new JLabel("Current Location:" + player.getRegion());
        location.setBounds(500, 300,200,40);
        JLabel listR = new JLabel("List of Regions:");
        listR.setBounds(500, 300,200,40);
        JLabel Money = new JLabel("Current Money:" + player.getMoney());
        Money.setBounds(sgb_x - 25, sgb_y - 400,200,40);



        int q = 0;
        while(q <10) {
            String name = region.get(q).getName();
            JButton rec = new JButton(name + "(" + region.get(q).getX() + "," + region.get(q).getY() + ")");
            rec.setLayout(null);
            rec.setBounds(region.get(q).getX(),region.get(q).getY(),200,200);
            cp.add(rec, BorderLayout.CENTER);
            rec.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println(name);
                        player.setRegion(name);
                        location.setText("Current Location:" + player.getRegion());
                }
            });
            q++;
        }

        System.out.println(player.getRegion());
        RegionList.setBounds(1000, 1000, 1000, 1000);
        cp.add(welcome,BorderLayout.CENTER);
        //view.add(map);
        cp.add(location,BorderLayout.CENTER);
        cp.add(Money,BorderLayout.CENTER);
        cp.add(listR);
        cp.add(RegionList,BorderLayout.CENTER);

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