import java.awt.event.*;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.util.ArrayList;

public class Button extends Game {

    public static void addbuttons(ArrayList<JButton> buttons, String name,
                                  JLabel location, ArrayList<Region> region, int j,Ship ship, JLabel shipInfo) {
        for (int g = 0; g < region.size(); g++) {
            final int k = g;
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
                        int newDistance = (int) Math.sqrt(((newX * newX) + (newY * newY)));
                        if (newDistance < 0) {
                            newDistance = newDistance * -1;
                        }
                        String name = region.get(j).getName();
                        player.setRegion(name);
                        location.setText("Current Location:" + player.getRegion());
                    if(k == 9) {
                        int lostFuel = (ship.getFuelCapacity() - Math.abs(player.getX()) / 10);
                        if (lostFuel < 0) {
                            System.out.println("CANT TRAVEL NOT ENOUGH FUEL");
                        } else {
                            ship.setFuelCapacity(lostFuel);
                        }
                        player.setX(newX);
                        player.setY(newY);
                        shipInfo.setText("Player Ship information:"
                                + " Ship type: " + ship.getShipType()
                                + " Ship cargo space: " + ship.getCargoSpace()
                                + " Ship fuel capacity: " + ship.getFuelCapacity()
                                + " Ship health: " + ship.getHealth());
                    }
                }
            });
        }
    }
}
