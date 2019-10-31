import javax.swing.JLabel;
import javax.swing.JButton;
import java.util.ArrayList;

public class Button extends Game {

    public void update(ArrayList<JButton> buttons, JLabel location,
                                  ArrayList<Region> region, int id,
                                  Ship ship, JLabel[] labels) {

        System.out.println("CHECK" + player.getRegionPrev());

        for (int g = 0; g < region.size(); g++) {
            final int k = g;
            JButton but = buttons.get(id);
            but.addActionListener(e -> {
                for (int i = 0; i < 10; i++) {
                    int x = (region.get(id).getX() - region.get(i).getX());
                    int y = (region.get(id).getY() - region.get(i).getY());
                    int distance = (int) Math.sqrt(((x * x) + (y * y)));
                    buttons.get(i).setText(region.get(i).toString()
                            + "/ " + "distance:" + distance);
                }
                int newX = region.get(id).getX();
                int newY = region.get(id).getY();

                Region current = player.getRegion1();
                int remFuel;

                player.setRegion1(region.get(id));


                location.setText("Current Location: " + current.getName());

                double pilotFactor = (player.getPilot() > 0) ? 1.0 / player.getPilot() : 1;

                if (k == 9) {
                    int fuelCost = (int) Math.ceil(
                            distance(current, region.get(id)) / 5.0 * pilotFactor);
                    remFuel = ship.getFuelCapacity() - fuelCost;
                    if (remFuel < 0) {
                        System.out.println("CANT TRAVEL NOT ENOUGH FUEL");
                    } else {
                        ship.setFuelCapacity(remFuel);
                    }
                }

                //player.setX(newX);
                //player.setY(newY);
                for (int l = 0; l < buttons.size(); l++) {

                    Region toRegion = region.get(l);
                    int fuelCost = (int) Math.ceil(
                            distance(current, toRegion) / 5.0 * pilotFactor);
                    int remFuel2 = ship.getFuelCapacity() - fuelCost;
                    buttons.get(l).setText(buttons.get(l).getText() + "/ " + "Fuel Cost: -"
                            + fuelCost);
                    if (remFuel2 < 0) {
                        buttons.get(l).setEnabled(false);
                    } else {
                        buttons.get(l).setEnabled(true);
                    }
                }

                labels[0].setText("Player Ship information: ");
                labels[1].setText(" Ship type: " + ship.getShipType());
                labels[2].setText(" Ship cargo space: " + ship.getCargoSpace());
                labels[3].setText(" Ship fuel capacity: " + ship.getFuelCapacity());
                labels[4].setText(" Ship health: " + ship.getHealth());
                labels[5].setText("Current money: " + player.getMoney());
            });
        }

    }

    public void update2(ArrayList<JButton> buttons, JLabel location,
                       ArrayList<Region> region, int id,
                       Ship ship, JLabel[] labels) {

        System.out.println("CHECK" + player.getRegionPrev());

        for (int g = 0; g < region.size(); g++) {
            final int k = g;
            JButton but = buttons.get(id);
            but.addActionListener(e -> {
                for (int i = 0; i < 10; i++) {
                    int x = (player.getRegionPrev().getX() - region.get(i).getX());
                    int y = (player.getRegionPrev().getY() - region.get(i).getY());
                    int distance = (int) Math.sqrt(((x * x) + (y * y)));
                    buttons.get(i).setText(region.get(i).toString()
                            + "/ " + "distance:asdfjsklfdladsjfl;jkdsjf" + distance);
                }
                int newX = player.getRegionPrev().getX();
                int newY = player.getRegionPrev().getY();

                Region current = player.getRegion1();
                int remFuel;

                player.setRegion1(player.getRegionPrev());


                location.setText("Current Location: " + current.getName());

                double pilotFactor = (player.getPilot() > 0) ? 1.0 / player.getPilot() : 1;

                if (k == 9) {
                    int fuelCost = (int) Math.ceil(
                            distance(current, player.getRegionPrev()) / 5.0 * pilotFactor);
                    remFuel = ship.getFuelCapacity() - fuelCost;
                    if (remFuel < 0) {
                        System.out.println("CANT TRAVEL NOT ENOUGH FUEL");
                    } else {
                        ship.setFuelCapacity(remFuel);
                    }
                }

                //player.setX(newX);
                //player.setY(newY);
                for (int l = 0; l < buttons.size(); l++) {

                    Region toRegion = region.get(l);
                    int fuelCost = (int) Math.ceil(
                            distance(current, toRegion) / 5.0 * pilotFactor);
                    int remFuel2 = ship.getFuelCapacity() - fuelCost;
                    buttons.get(l).setText(buttons.get(l).getText() + "/ " + "Fuel Cost: -"
                            + fuelCost);
                    if (remFuel2 < 0) {
                        buttons.get(l).setEnabled(false);
                    } else {
                        buttons.get(l).setEnabled(true);
                    }
                }

                labels[0].setText("Player Ship information: ");
                labels[1].setText(" Ship type: " + ship.getShipType());
                labels[2].setText(" Ship cargo space: " + ship.getCargoSpace());
                labels[3].setText(" Ship fuel capacity: " + ship.getFuelCapacity());
                labels[4].setText(" Ship health: " + ship.getHealth());
                labels[5].setText("Current money: " + player.getMoney());
            });
        }

    }

    private static int distance(Region r1, Region r2) {
        int x = (r1.getX() - r2.getX());
        int y = (r1.getY() - r2.getY());
        return (int) Math.sqrt(((x * x) + (y * y)));

    }

}
