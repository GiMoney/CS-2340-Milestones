import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;



public class Police extends Game {
    protected static JDialog view = new JDialog();
    //protected static int creditsDemanded;
    protected static boolean fleePressed = false;
    protected static TravelUI next;
    //protected static Bandit current;
    protected static int policeMoney = 1000;
    protected static double fleeNum = (Math.random() * 99);
    protected static double fightNum = (Math.random() * 99);

    public void policeMain(Region regionPrev, ArrayList<JButton> buttons, JFrame mainView,
                                  Region region1, Button buts,
                                  JLabel[] shiplabels, Ship ship) throws IOException {
        view.setSize(1200, 600);
        Container cp = view.getContentPane();
        cp.removeAll();
        cp.setLayout(new BorderLayout());

        BufferedImage image;
        image = ImageIO.read(getClass().getResource("/resource/police.jpg"));
        JLabel label = new JLabel(new ImageIcon(image));
        cp.add(label);
        label.setBounds(400, 60, 256, 197);

        /*
        if (player.getDifficulty().equals("Hard")) {
            creditsDemanded = 50;
        } else {
            creditsDemanded = 100;
        }
        */

        JButton forfeit = new JButton("Forfeit the item");
        forfeit.setBounds(0, 290, 200, 40);
        cp.add(forfeit, BorderLayout.CENTER);

        JButton flee = new JButton("Attempt to flee");
        flee.setBounds(400, 290, 200, 40);
        cp.add(flee, BorderLayout.CENTER);

        JButton fight = new JButton("Engage in Combat");
        fight.setBounds(800, 290, 200, 40);
        cp.add(fight, BorderLayout.CENTER);

        JLabel welcome = new JLabel("Current Difficulty: " + player.getDifficulty());
        welcome.setBounds(20, 0, 200, 40);
        cp.add(welcome, BorderLayout.CENTER);
        JLabel locationPrev = new JLabel("Currently traveling from: " + player.getRegionPrev());
        System.out.println("Currently traveling from:" + player.getRegionPrev());
        locationPrev.setBounds(750, 0, 400, 40);
        cp.add(locationPrev, BorderLayout.CENTER);
        JLabel locationRegion1 = new JLabel("Currently traveling to: " + region1);
        locationRegion1.setBounds(750, 50, 400, 40);
        cp.add(locationRegion1, BorderLayout.CENTER);
        JLabel moneyPrev = new JLabel("Current money: " + player.getMoney());
        moneyPrev.setBounds(400, 0, 200, 40);
        cp.add(moneyPrev, BorderLayout.CENTER);
        JLabel shipInfo = new JLabel("Player Ship information:");
        shipInfo.setBounds(0, 100, 500, 40);
        cp.add(shipInfo, BorderLayout.CENTER);
        JLabel shipType = new JLabel(" Ship type: " + ship.getShipType());
        shipType.setBounds(0, 130, 200, 40);
        cp.add(shipType, BorderLayout.CENTER);
        JLabel shipCargo = new JLabel(" Ship cargo space: " + ship.getCargoSpace());
        shipCargo.setBounds(0, 160, 200, 40);
        cp.add(shipCargo, BorderLayout.CENTER);
        JLabel shipFuel = new JLabel(" Ship fuel capacity: " + ship.getFuelCapacity());
        shipFuel.setBounds(0, 190, 200, 40);
        cp.add(shipFuel, BorderLayout.CENTER);
        JLabel shipHealthPrev = new JLabel(" Ship health: " + ship.getHealth());
        shipHealthPrev.setBounds(0, 220, 200, 40);
        cp.add(shipHealthPrev, BorderLayout.CENTER);

        JLabel demand = new JLabel("The Police have suspected you of stealing " + inventory.get(0)
                + ". What do you decide to do?");
        demand.setBounds(0, 260, 100, 40);
        cp.add(demand, BorderLayout.CENTER);

        forfeit.addActionListener(e -> {
            player.setSuccessfulTravel(true);
            player.setDialogOpen(true);
            inventory.removeElementAt(0);
            ship.setCargoSpace(ship.getCargoSpace() + 1);

            shiplabels[0].setText("Player Ship information: ");
            shiplabels[1].setText(" Ship type: " + ship.getShipType());
            shiplabels[2].setText(" Ship cargo space: " + ship.getCargoSpace());
            shiplabels[3].setText(" Ship fuel capacity: " + ship.getFuelCapacity());
            shiplabels[4].setText(" Ship health: " + (ship.getHealth() - 20));
            shiplabels[5].setText("Current money: " + player.getMoney());
            ship.setHealth(ship.getHealth() - 20);
            JOptionPane.showMessageDialog(view,
                    "Payment Succeeded (Lost 'stolen' item) but Traveled to new Region");
            view.dispose();
            mainView.setVisible(true);
            mainView.revalidate();
            mainView.repaint();
            player.setSuccessfulTravel(true);
            player.setRegionPrev(player.getRegion1()); // now they match
            player.setRegion1(region1);
            //next = new TravelUI();
            //try{
            //  next.display(player.getRegion1());
            //} catch(IOException j){

            //}
            shiplabels[5].setText("Current money: " + player.getMoney());
            System.out.println(player.getMoney());
        });

        flee.addActionListener(e -> {
            if (fleeNum < player.getFleeChance()) {
                System.out.println("is this still the same" + player.getRegionPrev());
                player.setRegion1(player.getRegionPrev());
                System.out.println("Should match above" + player.getRegionPrev());
                //JLabel[] labels = new JLabel[] {
                //      shipInfo, shipType, shipCargo,
                //    shipFuel, shipHealth, money};

                player.setSuccessfulTravel(false);
                player.setDialogOpen(true);
                System.out.println(player.getX());

                //player.setRegion1(player.getRegionPrev());
                System.out.println("AFter" + player.getRegionPrev().getX());
                for (int id = 0; id < region.size(); id++) {

                    int x = (player.getRegionPrev().getX() - region.get(id).getX());
                    int y = (player.getRegionPrev().getY() - region.get(id).getY());
                    int distance = (int) Math.sqrt(((x * x) + (y * y)));

                    double pilotFactor = (player.getPilot() > 0) ? 1.0 / player.getPilot() : 1;
                    int fuelCost = (int) Math.ceil(distance / 5.0 * pilotFactor);
                    //System.out.println(buttons.get(id).getText());
                    buttons.get(id).setText(region.get(id).toString() + " / "
                            + "distance: " + distance
                            + " / " + "Fuel Cost: -" + fuelCost);
                    // System.out.println("After" + buttons.get(id).getText());
                    buttons.get(id).setBounds(view.getWidth() / 2 - 100, (id * 40) + 100, 500, 40);
                    currRegion = region.get(id);
                    //buts.update2(buttons, location, region, id, ship, labels);
                    cp.revalidate();
                    cp.repaint();
                    view.dispose();
                }
                //for (int i = 0;i < region.size();i++) {
                //  System.out.println(buttons.get(i).getText());
                // }
                fleePressed = true;
                //System.out.println("HELLOW WORD");
                mainView.revalidate();
                mainView.repaint();
                // player.setRegionPrev(currRegion);
                //player.setRegionPrev(player.getRegion1());
                player.setRegion1(player.getRegion1());
                mainView.setVisible(true);
                JOptionPane.showMessageDialog(mainView, "Flee Succeeded (Did not travel)");
                /*next = new TravelUI();
                try {
                    next.display(player.getRegion1());
                    mainView.setVisible(true);
                } catch(IOException j){

                }
                 */
                System.out.println(player.getRegion1());
                System.out.println(player.getRegionPrev());
                shiplabels[6].setText("Current Location: " + player.getRegion1().getName());
                System.out.println(player.getMoney());
                shiplabels[5].setText("Current money: " + player.getMoney());
            } else {
                policeMoney = player.getMoney();
                player.setMoney(0);
                inventory.removeElementAt(0);
                ship.setCargoSpace(ship.getCargoSpace() + 1);
                shiplabels[4].setText(" Ship health: " + (ship.getHealth() - 20));
                shiplabels[5].setText("Current money: " + player.getMoney());
                ship.setHealth(ship.getHealth() - 20);
                System.out.println(policeMoney);
                view.dispose();
                mainView.setVisible(true);
                JOptionPane.showMessageDialog(mainView,
                        "Flee Failed (Did not travel and lost health, money, and the stolen item)");

            }
        });

        fight.addActionListener(e -> {
            if (fightNum < player.getFightChance()) {
                player.setSuccessfulTravel(true);
                player.setRegionPrev(player.getRegion1()); // now they match
                player.setRegion1(region1);
                player.setMoney(player.getMoney() + policeMoney);
                mainView.revalidate();
                mainView.repaint();
                mainView.setVisible(true);
                JOptionPane.showMessageDialog(mainView,
                        "Fight Succeeded (Travel to new Region and won money)");
            } else {
                policeMoney = player.getMoney();
                player.setMoney(0);
                shiplabels[4].setText(" Ship health: " + (ship.getHealth() - 20));
                shiplabels[5].setText("Current money: " + player.getMoney());
                ship.setHealth(ship.getHealth() - 20);
                System.out.println(policeMoney);
                System.out.println("is this still the same" + player.getRegionPrev());
                player.setRegion1(player.getRegionPrev());
                System.out.println("Should match above" + player.getRegionPrev());
                //JLabel[] labels = new JLabel[] {
                //      shipInfo, shipType, shipCargo,
                //    shipFuel, shipHealth, money};

                player.setSuccessfulTravel(false);
                player.setDialogOpen(true);
                System.out.println(player.getX());

                //player.setRegion1(player.getRegionPrev());
                System.out.println("AFter" + player.getRegionPrev().getX());
                for (int id = 0; id < region.size(); id++) {

                    int x = (player.getRegionPrev().getX() - region.get(id).getX());
                    int y = (player.getRegionPrev().getY() - region.get(id).getY());
                    int distance = (int) Math.sqrt(((x * x) + (y * y)));

                    double pilotFactor = (player.getPilot() > 0) ? 1.0 / player.getPilot() : 1;
                    int fuelCost = (int) Math.ceil(distance / 5.0 * pilotFactor);
                    //System.out.println(buttons.get(id).getText());
                    buttons.get(id).setText(region.get(id).toString() + " / "
                            + "distance: " + distance
                            + " / " + "Fuel Cost: -" + fuelCost);
                    // System.out.println("After" + buttons.get(id).getText());
                    buttons.get(id).setBounds(view.getWidth() / 2 - 100, (id * 40) + 100, 500, 40);
                    currRegion = region.get(id);
                    //buts.update2(buttons, location, region, id, ship, labels);
                    cp.revalidate();
                    cp.repaint();
                }
                shiplabels[6].setText("Current Location: " + player.getRegion1().getName());
                view.dispose();
                mainView.revalidate();
                mainView.repaint();
                player.setRegionPrev(player.getRegion1());
                mainView.setVisible(true);
                JOptionPane.showMessageDialog(mainView,
                        "Fight Failed (Did not travel, lost money and ship health)");

            }
        });


        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }
}