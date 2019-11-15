import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.*;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.util.ArrayList;
import java.io.IOException;
//import java.util.Random;



public class Game extends ViewController {
    protected static ArrayList<Region> region;
    protected static JFrame view;
    protected static Game current;
    protected static Region currRegion;
    protected static Region regionPrev;
    protected static Universe universe;
    protected static Player player;
    protected static EndGame end;
    private static String[] configArgs = new String[1000];
    protected static Ship ship;
    protected static TravelUI next;
    protected static DefaultListModel inventory = new DefaultListModel();
    protected static String[] names = new String[] {
        "Alpha-20", "Beta-43", "Charlie-28",
        "Delta-8", "EEEEE-E", "Falcon-69",
        "Gamma-Hamma", "Helix-Felix", "I-99", "Ben-10"
    };


    public static void startGame(String[] args) {
        view = new JFrame("Click on a region on the map to travel there");
        universe = new Universe(names);
        region = universe.region;
        if (args[1].equals("Easy")) {
            player.setMoney(1000000);
        } else if (args[1].equals("Medium")) {
            player.setMoney(500);
        } else {
            player.setMoney(100);
        }
    }

    public void start(String[] args) {
        Boolean banditWon = false;
        player = new Player(args);
        startGame(args);
        player.setKarma(0);
        String difficulty = player.getDifficulty();
        view.setSize(1000, 600);
        Container cp = view.getContentPane();
        cp.setLayout(null);
        ship = new Ship();
        player.setSuccessfulTravel(false);
        player.setDialogOpen(true);
        regionPrev = player.getRegion1();

        JLabel welcome = new JLabel("Current Difficulty: " + difficulty);
        welcome.setBounds(20, 0, 200, 40);
        JLabel location = new JLabel("Current Location: " + player.getRegion());
        location.setBounds(750, 0, 300, 40);
        JLabel money = new JLabel("Current money: " + player.getMoney());
        money.setBounds(400, 0, 200, 40);
        JLabel shipInfo = new JLabel("Player Ship information:");
        shipInfo.setBounds(50, 100, 500, 40);
        JLabel shipType = new JLabel(" Ship type: " + ship.getShipType());
        shipType.setBounds(80, 130, 200, 40);
        JLabel shipCargo = new JLabel(" Ship cargo space: " + ship.getCargoSpace());
        shipCargo.setBounds(80, 160, 200, 40);
        JLabel shipFuel = new JLabel(" Ship fuel capacity: " + ship.getFuelCapacity());
        shipFuel.setBounds(80, 190, 200, 40);
        JLabel shipHealth = new JLabel(" Ship health: " + ship.getHealth());
        shipHealth.setBounds(80, 220, 200, 40);
        JButton repair = new JButton(" Repair Ship ($10 for 10 health): ");
        repair.setBounds(60, 350, 300, 50);

        cp.add(shipInfo, BorderLayout.CENTER);
        cp.add(shipType, BorderLayout.CENTER);
        cp.add(shipCargo, BorderLayout.CENTER);
        cp.add(shipFuel, BorderLayout.CENTER);
        cp.add(shipHealth, BorderLayout.CENTER);
        cp.add(repair, BorderLayout.CENTER);

        repair.addActionListener(e -> {
            if (ship.getHealth() + 10 > 101 || player.getMoney() - 10 < 0) {
                JOptionPane.showMessageDialog(view,
                        "Not enough money/ship is already healthy");
            } else {
                player.setMoney(player.getMoney() - 10);
                money.setText("Current money: " + player.getMoney());
                ship.setHealth(ship.getHealth() + 10);
                shipHealth.setText(" Ship health: " + ship.getHealth());
            }
        });

        ArrayList<JButton> buttons = new ArrayList<>();
        Button buts = new Button();
        JLabel[] labels = new JLabel[] {shipInfo, shipType, shipCargo, shipFuel,
            shipHealth, money, location};
        for (int id = 0; id < region.size(); id++) {

            JButton btn = new JButton();
            int x = (player.getX() - region.get(id).getX());
            int y = (player.getY() - region.get(id).getY());
            int distance = (int) Math.sqrt(((x * x) + (y * y)));

            double pilotFactor = (player.getPilot() > 0) ? 1.0 / player.getPilot() : 1;
            int fuelCost = (int) Math.ceil(distance / 5.0 * pilotFactor);

            btn.setText(region.get(id).toString() + " / " + "distance: " + distance
                    + " / " + "Fuel Cost: -" + fuelCost);
            btn.setBounds(view.getWidth() / 2 - 100, (id * 40) + 100, 500, 40);
            location.setText("Current Location: " + player.getRegion());
            currRegion = region.get(id);
            buttons.add(btn);
            buts.update(buttons, location, region, id, ship, labels);
            cp.add(buttons.get(id), BorderLayout.CENTER);
            btn.addActionListener(new PageActionListener(currRegion, banditWon, regionPrev,
                    buttons, buts, labels, ship));
            cp.add(welcome, BorderLayout.CENTER);
            //view.add(map);
            cp.add(location, BorderLayout.CENTER);
            cp.add(money, BorderLayout.CENTER);

            view.setLocationRelativeTo(null);
            view.setVisible(true);
            view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        Runnable run = new Runnable() {
            public void run() {
                if (player.getSuccessfulTravel()) {
                    for (int i = 0; i < region.size(); i++) {
                        buts.update(buttons, location, region, i, ship, labels);
                        cp.revalidate();
                        cp.repaint();
                    }
                }
            }
        };
        SwingUtilities.invokeLater(run);

    }

    public static class PageActionListener implements ActionListener {
        private Region region;
        private Boolean banditWon;
        private Region regionPrev;
        private ArrayList<JButton> buttons;
        private JLabel money;
        private Button buts;
        private JLabel location;
        private JLabel[] shiplabels;
        private Ship ship;

        public PageActionListener(Region region, Boolean banditWon, Region regionPrev,
                                  ArrayList<JButton> buttons, Button buts,
                                  JLabel[] shiplabels, Ship ship) {
            this.region = region;
            this.banditWon = banditWon;
            this.regionPrev = regionPrev;
            this.buttons = buttons;
            this.buts = buts;
            this.shiplabels = shiplabels;
            this.ship = ship;
        }

        public void actionPerformed(ActionEvent e) {
            int banditNum = (int) (Math.random() * 99);
            int traderNum = (int) (Math.random() * 99);
            int policeNum = (int) (Math.random() * 99);
            double pilotFactor = (player.getPilot() > 0) ? 1.0 / player.getPilot() : 1;
            int fuelCost = (int) Math.ceil(
                    distance(player.getRegion1(), region) / 5.0 * pilotFactor);

                if (fuelCost != 0 && banditNum < player.getBanditChance()) {
                    Bandit bandit = new Bandit();
                    regionPrev = player.getRegion1();
                    System.out.println("LOOK AT THIS" + regionPrev);
                    player.setDialogOpen(true);
                    player.setSuccessfulTravel(true);
                    try {
                        bandit.banditMain(buttons, view, region, shiplabels);
                        System.out.println("SHIP HEALTH" + ship.getHealth());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    view.setVisible(false);
                    view.revalidate();
                    view.repaint();

                } else if (fuelCost != 0 && traderNum < player.getTraderChance()) {
                    player.setSuccessfulTravel(true);
                    Trader trader = new Trader();
                    try {
                        ship.setFuelCapacity(ship.getFuelCapacity()
                                - fuelCost);
                        player.setRegionPrev(player.getRegion1());
                        player.setRegion1(region);
                        trader.displayTrader(region);
                        view.revalidate();
                        view.repaint();

                    } catch (Exception j) {
                        j.printStackTrace();
                    }
                } else if (fuelCost != 0 && ship.getCargoSpace() < 17
                        && policeNum < (player.getPoliceChance())
                        && !player.getRegionPrev().equals(region)) {
                    player.setSuccessfulTravel(true);
                    Police police = new Police();
                    try {
                        ship.setFuelCapacity(ship.getFuelCapacity()
                                - fuelCost);
                        player.setRegionPrev(player.getRegion1());
                        player.setRegion1(region);
                        police.policeMain(buttons, view, region, shiplabels);
                        view.revalidate();
                        view.repaint();

                    } catch (Exception j) {
                        j.printStackTrace();
                    }
                } else {

                    player.setSuccessfulTravel(true);
                    next = new TravelUI();
                    try {
                        ship.setFuelCapacity(ship.getFuelCapacity()
                                - fuelCost);
                        player.setRegionPrev(player.getRegion1());
                        player.setRegion1(region);
                        next.display(region);
                        view.revalidate();
                        view.repaint();

                    } catch (IOException j) {
                        j.printStackTrace();
                    }
                }
        }

        public static int distance(Region r1, Region r2) {
            int x = (r1.getX() - r2.getX());
            int y = (r1.getY() - r2.getY());
            return (int) Math.sqrt(((x * x) + (y * y)));

        }

    }

}



