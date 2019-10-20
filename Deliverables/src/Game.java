import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.*;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.util.ArrayList;

public class Game {
    protected static ArrayList<Region> region;
    private static Region currRegion;
    private static JFrame view = new JFrame("Click on a region on the map to travel there");
    protected static JComboBox regionList;
    protected static Player player;
    private static String[] configArgs = new String[1000];
    protected static Ship ship;
    protected static DefaultListModel<String> inventory = new DefaultListModel<>();
    private static String[] names = new String[] {
        "Alpha-20", "Beta-43", "Charlie-28",
        "Delta-8", "EEEEE-E", "Falcon-69",
        "Gamma-Hamma", "Helix-Felix", "I-99", "Ben-10"
    };
    private boolean noTravel = false;

    private static void startGame(String[] args) {
        Universe universe = new Universe(names);
        region = universe.region;
        if (args[1].equals("Easy")) {
            player.setMoney(1000);
        } else if (args[1].equals("Medium")) {
            player.setMoney(500);
        } else {
            player.setMoney(100);
        }
    }

    public static void start(String[] args) {
        player = new Player(args);
        startGame(args);
        String difficulty = args[1];
        view.setSize(1000, 600);
        Container cp = view.getContentPane();
        cp.setLayout(null);
        ship = new Ship();

        int sgbX = view.getWidth() / 2 - 50;
        int sgbY = view.getHeight() / 2 - 40;

        JLabel welcome = new JLabel("Current Difficulty: " + difficulty);
        welcome.setBounds(0, 0, 200, 40);
        JLabel location = new JLabel("Current Location: " + player.getRegion());
        location.setBounds(400, 0, 200, 40);
        JLabel listR = new JLabel("List of Regions: ");
        listR.setBounds(765, 0, 200, 40);
        JLabel money = new JLabel("Current money: " + player.getMoney());
        money.setBounds(200, 0, 200, 40);
      
        JLabel shipInfo = new JLabel("Player Ship information:"
                + " Ship type: " + ship.getShipType()
                + " Ship cargo space: " + ship.getCargoSpace()
                + " Ship fuel capacity: " + ship.getFuelCapacity()
                + " Ship health: " + ship.getHealth());

        shipInfo.setBounds(0, 20, 800, 40);
        cp.add(shipInfo, BorderLayout.CENTER);

        ArrayList<JButton> buttons = new ArrayList<>();
        Button buts = new Button();
      
        for (int id = 0; id < region.size(); id++) {
            JButton btn = new JButton();
            int x = (player.getX() - region.get(id).getX());
            int y = (player.getY() - region.get(id).getY());
            int distance = (int) Math.sqrt(((x * x) + (y * y)));

            btn.setText(region.get(id).toString() + "/ " + "distance: " + distance
                    + "/ " + "Fuel Cost: -" + (distance / 10));
            btn.setBounds(40, (id * 20) + 50, 400, 18);
            location.setText("Current Location: " + player.getRegion());
          
            int newx = region.get(id).getX();
            int newy = region.get(id).getY();
            currRegion = region.get(id);
            buttons.add(btn);
            cp.add(buttons.get(id), BorderLayout.CENTER);

            buts.update(buttons, location, region, id, ship, shipInfo, money);
            btn.addActionListener(new PageActionListener(currRegion));
        }

        JButton market = new JButton("Go to Market");
        market.setBounds(view.getWidth() / 2, view.getHeight() - 100, 100, 50);
        market.addActionListener(new SegueListener(currRegion));

        regionList.setBounds(860, 10, 130, 20);
        cp.add(welcome, BorderLayout.CENTER);
        //view.add(map);
        cp.add(location, BorderLayout.CENTER);
        cp.add(money, BorderLayout.CENTER);
        cp.add(listR);
        cp.add(regionList, BorderLayout.CENTER);
        cp.add(market, BorderLayout.SOUTH);

        view.setLocationRelativeTo(null);
        view.setVisible(true);
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static class PageActionListener implements ActionListener {
        private int intValue;
        private Region region;

        public PageActionListener(Region region) {
            this.region = region;
        }

        public void actionPerformed(ActionEvent e) {
            ship.setFuelCapacity(ship.getFuelCapacity()
                    - Math.abs(distance(player.getRegion1(), region) / 10));
            player.setRegion1(region);
        }

        public static int distance(Region r1, Region r2) {
            int x = (r1.getX() - r2.getX());
            int y = (r1.getY() - r2.getY());
            return (int) Math.sqrt(((x * x) + (y * y)));
        }

    }

    public static class SegueListener implements ActionListener {
        private Region region;

        public SegueListener(Region region) {
            this.region = region;
        }

        public void actionPerformed(ActionEvent e) {
            TravelUI tui = new TravelUI();
            tui.display(region);
        }
    }
}