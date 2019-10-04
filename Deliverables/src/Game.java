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
import javax.swing.JOptionPane;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;


public class Game extends ViewController{

    public static JFrame view = new JFrame();
    static Game current;
    public static JComboBox RegionList;
    static Universe universe;
    public static Player player;
    public static String names[] = {"Alpha-20","Beta-43","Charlie-28", "Delta-8","EEEEE-E","Falcon-69","Gamma-Hamma","Helix-Felix","I-99","Ben-10"};
    public static void startGame(String[] args) {
        universe = new Universe(names);
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

        JButton map = new JButton("See Map");
        int sgb_x = view.getWidth() / 2 - 50;
        int sgb_y = view.getHeight() / 2 - 40;

        JLabel welcome = new JLabel("Current Difficulty:" + difficulty);
        welcome.setBounds(sgb_x - 25, sgb_y - 100, 200, 40);
        JLabel location = new JLabel("Current Location:");
        location.setBounds(sgb_x - 25, sgb_y - 300,200,40);
        JLabel Money = new JLabel("Current Money:" + player.getMoney());
        Money.setBounds(sgb_x - 1000, sgb_y - 400,200,40);
        map.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedImage img = ImageIO.read(new File("http://oi65.tinypic.com/30uwp6b.jpg"));
                    ImageIcon icon = new ImageIcon(img);
                    JLabel label = new JLabel(icon);
                    JOptionPane.showMessageDialog(null, label);
                } catch (IOException j) {
                    j.printStackTrace();
                }
            }
        });
        System.out.println(player.getRegion());
        RegionList.setBounds(1000, 1000, 1000, 1000);
        view.add(welcome);
        view.add(map);
        view.add(location);
        view.add(RegionList, BorderLayout.SOUTH);
        view.add(Money);

        JPanel pan = new JPanel();
        pan.setBackground(Color.BLUE);
        //setContentPane(pan);
        view.add(pan);

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