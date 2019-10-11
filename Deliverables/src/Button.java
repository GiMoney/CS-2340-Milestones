import java.awt.event.*;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.util.ArrayList;

public class Button extends Game {

    public static void addbuttons(ArrayList<JButton> buttons, String name,
                                  JLabel location, ArrayList<Region> region, int j) {
        for (int g = 0; g < 10; g++) {
            JButton but =  buttons.get(j);
            but.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    for (int i = 0; i < 10; i++) {
                        int x = (region.get(j).getX() - region.get(i).getX());
                        int y = (region.get(j).getY() - region.get(i).getY());
                        int distance = (int) Math.sqrt(((x * x) + (y * y)));
                        buttons.get(i).setText(region.get(i).toString()
                                + "/ " + "distance:" + distance);
                    }
                    int newX = region.get(j).getX();
                    int newY = region.get(j).getY();
                    String name = region.get(j).getName();
                    player.setRegion(name);
                    location.setText("Current Location:" + player.getRegion());
                    player.setX(newX);
                    player.setY(newY);
                }
            });
        }
    }
}
