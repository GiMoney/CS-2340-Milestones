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

public class Button extends Game{

public static void addbuttons(ArrayList<JButton> buttons, String name,JLabel location,ArrayList<Region> region, int j) {
        for (int g = 0; g < 10; g++) {

        JButton but =  buttons.get(j);
        but.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {

        int x = (region.get(j).getX() - region.get(i).getX());
        int y = (region.get(j).getY() - region.get(i).getY());
        int distance = (int) Math.sqrt(((x * x) + (y * y)));
        buttons.get(i).setText(region.get(i).toString() + "/ " + "distance:" + distance);
        }
        int newx = region.get(j).getX();
        int newy = region.get(j).getY();
        String name = region.get(j).getName();
        player.setRegion(name);
        location.setText("Current Location:" + player.getRegion());
        player.setX(newx);
        player.setY(newy);
        }
        });
        }
        }
        }
