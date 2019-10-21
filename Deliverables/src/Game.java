import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.*;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.util.ArrayList;
import java.io.IOException;



public class Game extends ViewController {
    protected static ArrayList<Region> region;
    protected static JFrame view = new JFrame("Click on a region on the map to travel there");
    protected static Game current;
    protected static Region currRegion;
    protected static Universe universe;
    protected static Player player;
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

    public void start(String[] args) {
        player = new Player(args);
        startGame(args);
        String difficulty = args[1];
        view.setSize(1000, 600);
        Container cp = view.getContentPane();
        cp.setLayout(null);
        ship = new Ship();

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

        cp.add(shipInfo, BorderLayout.CENTER);
        cp.add(shipType, BorderLayout.CENTER);
        cp.add(shipCargo, BorderLayout.CENTER);
        cp.add(shipFuel, BorderLayout.CENTER);
        cp.add(shipHealth, BorderLayout.CENTER);

        ArrayList<JButton> buttons = new ArrayList<>();
        Button buts = new Button();

        for (int id = 0; id < region.size(); id++) {

            JButton btn = new JButton();
            int x = (player.getX() - region.get(id).getX());
            int y = (player.getY() - region.get(id).getY());
            int distance = (int) Math.sqrt(((x * x) + (y * y)));
            btn.setText(region.get(id).toString() + " / " + "distance: " + distance
                    + " / " + "Fuel Cost: -" + (distance / 2 / player.getPilot()));
            btn.setBounds(view.getWidth() / 2 - 100, (id * 40) + 100, 500, 40);
            location.setText("Current Location: " + player.getRegion());
            currRegion = region.get(id);
            buttons.add(btn);
            cp.add(buttons.get(id), BorderLayout.CENTER);
            JLabel[] labels = new JLabel[] {
                shipInfo, shipType, shipCargo,
                shipFuel, shipHealth, money};
            buts.update(buttons, location, region, id, ship, labels);
            btn.addActionListener(new PageActionListener(currRegion));

        }
        cp.add(welcome, BorderLayout.CENTER);
        //view.add(map);
        cp.add(location, BorderLayout.CENTER);
        cp.add(money, BorderLayout.CENTER);

        view.setLocationRelativeTo(null);
        view.setVisible(true);
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static class PageActionListener implements ActionListener {
        private Region region;


        public PageActionListener(Region region) {
            this.region = region;
        }

        public void actionPerformed(ActionEvent e) {
            next = new TravelUI();
            try {
                ship.setFuelCapacity(ship.getFuelCapacity()
                        - Math.abs(distance(player.getRegion1(), region) / 2 / player.getPilot()));
                player.setRegion1(region);
                next.display(region);

            } catch (IOException j) {
                j.printStackTrace();
            }

        }

        public static int distance(Region r1, Region r2) {
            int x = (r1.getX() - r2.getX());
            int y = (r1.getY() - r2.getY());
            return (int) Math.sqrt(((x * x) + (y * y)));

        }

    }

}



