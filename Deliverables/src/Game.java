import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.*;
import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.util.ArrayList;



public class Game extends ViewController {
    protected static ArrayList<Region> region;
    protected static JFrame view = new JFrame("Click on a region on the map to travel there");
    protected static Game current;
    protected static JComboBox regionList;
    protected static Universe universe;
    protected static Player player;
    protected static Ship ship;
    protected static TravelUI next;
    private static String[] configArgs = new String[1000];
    protected static String[] names = new String[] {
            "Alpha-20", "Beta-43", "Charlie-28",
            "Delta-8", "EEEEE-E", "Falcon-69",
            "Gamma-Hamma", "Helix-Felix", "I-99", "Ben-10"
    };
    boolean noTravel = false;

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
        cp.setLayout(new FlowLayout());
        ship = new Ship();

        int sgbX = view.getWidth() / 2 - 50;
        int sgbY = view.getHeight() / 2 - 40;

        JLabel welcome = new JLabel("Current Difficulty:" + difficulty);
        welcome.setBounds(sgbX - 25, sgbY - 100, 200, 40);
        JLabel location = new JLabel("Current Location:" + player.getRegion());
        location.setBounds(500, 300, 200, 40);
        JLabel listR = new JLabel("List of Regions:");
        listR.setBounds(500, 300, 200, 40);
        JLabel money = new JLabel("Current money:" + player.getMoney());
        money.setBounds(sgbX - 25, sgbY - 400, 200, 40);
        JLabel shipInfo = new JLabel("Player Ship information:"
                + " Ship type: " + ship.getShipType()
                + " Ship cargo space: " + ship.getCargoSpace()
                + " Ship fuel capacity: " + ship.getFuelCapacity()
                + " Ship health: " + ship.getHealth());
        shipInfo.setBounds(500, 500, 200, 40);
        cp.add(shipInfo, BorderLayout.CENTER);


        ArrayList<JButton> buttons = new ArrayList<>();
        Button buts = new Button();
        String name = null;
        for (int id = 0; id < 10; id++) {
            JButton btn = new JButton();
            int x = (player.getX() - region.get(id).getX());
            int y = (player.getY() - region.get(id).getY());
            int distance = (int) Math.sqrt(((x * x) + (y * y)));
            btn.setText(region.get(id).toString() + "/ " + "distance:" + distance +
                    "/ " + "Fuel Cost: -" + (distance / 10));
            location.setText("Current Location:" + player.getRegion());
            int newx = region.get(id).getX();
            int newy = region.get(id).getY();
            name = region.get(id).getName();
            buttons.add(btn);
            cp.add(buttons.get(id), BorderLayout.CENTER);
            buts.addbuttons(buttons, name, location, region, id,ship,shipInfo);
                btn.addActionListener(new PageActionListener(name));
        }

        regionList.setBounds(500, 500, 200, 50);
        cp.add(welcome, BorderLayout.CENTER);
        //view.add(map);
        cp.add(location, BorderLayout.CENTER);
        cp.add(money, BorderLayout.CENTER);
        cp.add(listR);
        cp.add(regionList, BorderLayout.CENTER);

        view.setLocationRelativeTo(null);
        view.setVisible(true);
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static class PageActionListener implements ActionListener {
        private String stringValue;
        private int intValue;

        public PageActionListener(String stringValue) {
            this.stringValue = stringValue;
        }

        public void actionPerformed(ActionEvent e) {
            configArgs[0] = stringValue;
            next = new TravelUI();
            next.main(configArgs);
        }

    }

    /*
    public static class SegueListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            current.view.setVisible(false);
            current.view.dispose();
            //next = new PlayerConfigurationView();
            //next.main(null);
        }
    }
    */
}