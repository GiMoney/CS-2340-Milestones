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
    public static JFrame view = new JFrame("Click on a region on the map to travel there");
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
        view.setSize(1000, 600);
        Container cp = view.getContentPane();
        cp.setLayout(null);

        int sgb_x = view.getWidth() / 2 - 50;
        int sgb_y = view.getHeight() / 2 - 40;

        JLabel welcome = new JLabel("Current Difficulty:" + difficulty);
        welcome.setBounds(sgb_x - 25, sgb_y - 100, 200, 40);
        JLabel location = new JLabel("Current Location:" + player.getRegion());
        location.setBounds(500, 300, 200, 40);
        JLabel listR = new JLabel("List of Regions:");
        listR.setBounds(500, 300, 200, 40);
        JLabel Money = new JLabel("Current Money:" + player.getMoney());
        Money.setBounds(sgb_x - 25, sgb_y - 400, 200, 40);


        //ArrayList<Double> coordinates = new ArrayList<>()
        ArrayList<JButton> buttons = new ArrayList<JButton>();
        Button buts = new Button();
        String name = null;
        for (int i = 0; i < 10; i++) {
            JButton btn = new JButton();
            int x = (player.getX() - region.get(i).getX());
            int y = (player.getY() - region.get(i).getY());
            int distance = (int) Math.sqrt(((x * x) + (y * y)));
            btn.setText(region.get(i).toString() + "/ " + "distance:" + distance);
            location.setText("Current Location:" + player.getRegion());
            int newx = region.get(i).getX();
            int newy = region.get(i).getY();
            name = region.get(i).getName();
            buttons.add(btn);
            cp.add(buttons.get(i));
            buts.addbuttons(buttons,name,location,region,i);
        }






        /*
        int q = 0;
        while(q <10) {
            double x= ( player.getX() - region.get(q).getX());
            double y = (player.getY() - region.get(q).getY());
            System.out.println(x);
            System.out.println(y);
            double distance = Math.sqrt(((x*x) + (y*y)));
            String name = region.get(q).getName();
            String wholeRegion = region.get(q).toString();
            int newx = region.get(q).getX();
            int newy = region.get(q).getY();
            JButton rec = new JButton(region.get(q).toString() + "/ " + "distance:" + distance );
            rec.setLayout(null);
            rec.setBounds(region.get(q).getX(),region.get(q).getY(),200,200);
                cp.add(rec, BorderLayout.CENTER);
            rec.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int num = 0;
                        player.setRegion(name);
                        player.setX(newx);
                        player.setY(newy);
                        //change(cp,location,rec);
                        location.setText("Current Location:" + player.getRegion());
                        main(args);
                    LinearLayout col2 = (LinearLayout)findViewById(R.id.col2);
                }
            });
            q++;
        }
*/
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

    public static void change(Container cp, JLabel location,JButton rec){
        view.getContentPane().remove(rec);
        view.repaint();
        view.setSize(1000,600);
        int q = 0;
        while(q <10) {
            double x= ( player.getX() - region.get(q).getX());
            double y = (player.getY() - region.get(q).getY());
            System.out.println(x);
            System.out.println(y);
            double distance = Math.sqrt(((x*x) + (y*y)));
            String name = region.get(q).getName();
            String wholeRegion = region.get(q).toString();
            int newx = region.get(q).getX();
            int newy = region.get(q).getY();
            JButton rec2 = new JButton(region.get(q).toString() + "/ " + "distance:" + distance );
            rec.setLayout(null);
            rec.setBounds(region.get(q).getX(),region.get(q).getY(),200,200);
            cp.add(rec, BorderLayout.CENTER);
            rec2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    player.setRegion(name);
                    player.setX(newx);
                    player.setY(newy);
                    location.setText("Current Location:" + player.getRegion());
                }
            });
            q++;
        }
    }

}