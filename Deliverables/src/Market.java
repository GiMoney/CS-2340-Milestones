import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;

public class Market extends Game {
    private static JFrame view = new JFrame();
    private static Region currRegion;

    public void displayMarket(Region region) {
        //view.setModal(true);
        view.setSize(400, 500);
        Container cp = view.getContentPane();
        cp.removeAll();
        cp.setLayout(null);

        currRegion = region;

        JLabel marketplace = new JLabel("Market Place");
        marketplace.setBounds(0, 0, 200, 30);
        cp.add(marketplace, BorderLayout.CENTER);

        JLabel regionTech = new JLabel("Tech: " + currRegion.getTechLevel());
        regionTech.setBounds(0, 10, 150, 100);
        cp.add(regionTech, BorderLayout.CENTER);

        JLabel fuel = new JLabel("Fuel: " + ship.getFuelCapacity());
        fuel.setBounds(30, 30, 100, 100);
        cp.add(fuel, BorderLayout.CENTER);

        JLabel cargo = new JLabel("Cargo: " + ship.getCargoSpace());
        cargo.setBounds(200, 50, 100, 100);
        cp.add(cargo, BorderLayout.CENTER);

        JLabel money = new JLabel("Money: " + player.getMoney());
        money.setBounds(200, 10, 100, 100);
        cp.add(money, BorderLayout.CENTER);

        JLabel cost = new JLabel("Cost of each item: " + currRegion.priceCalculator(20));
        cost.setBounds(200, 30, 200, 100);
        cp.add(cost, BorderLayout.CENTER);

        JButton buy = new JButton("Buy");
        buy.setBounds(30, 110, 60, 20);
        cp.add(buy, BorderLayout.CENTER);

        JButton sell = new JButton("Sell");
        sell.setBounds(200, 110, 60, 20);
        cp.add(sell, BorderLayout.CENTER);

        JList<String> jList = new JList<>();
        jList.setBounds(30, 140, 150, 250);
        cp.add(jList, BorderLayout.CENTER);
        JList<String> jList2 = new JList<>();
        jList2.setBounds(200, 140, 150, 250);
        cp.add(jList2, BorderLayout.CENTER);


        DefaultListModel<String> marketside = new DefaultListModel<>();
        for (int i = 0; i < currRegion.getItems().size(); i++) {
            marketside.addElement(currRegion.getItems().get(i));
        }
        jList.setModel(marketside);
        jList2.setModel(inventory);
        buy.addActionListener(e -> {
            int remainingMon = player.getMoney() - currRegion.priceCalculator(20);
            if (jList.getSelectedValue() != null && remainingMon >= 0) {
                if (!jList.getSelectedValue().equals(currRegion.getItems().get(0))) {
                    inventory.addElement(jList.getSelectedValue());
                    marketside.removeElement(jList.getSelectedValue());
                    currRegion.getItems().remove(jList.getSelectedValue());
                    //add selected item to player inventory;
                    jList2.setModel(inventory);
                    ship.setCargoSpace(ship.getCargoSpace() - 1);
                    cargo.setText("Cargo: " + ship.getCargoSpace());
                } else {
                    ship.setFuelCapacity(ship.getFuelCapacity() + 10);
                    ship.setCargoSpace(ship.getCargoSpace() - 1);
                    fuel.setText("Fuel: " + ship.getFuelCapacity());
                    cargo.setText("Cargo: " + ship.getCargoSpace());
                }
                player.setMoney(remainingMon);
                money.setText("Money: " + player.getMoney());
            }
        });

        sell.addActionListener(e -> {
            if (jList2.getSelectedValue() != null) {
                marketside.addElement(jList2.getSelectedValue());
                inventory.removeElement(jList2.getSelectedValue());
                //remove selected item to player inventory;
                jList.setModel(marketside);
                player.setMoney(player.getMoney() + currRegion.priceCalculator(20));
                ship.setCargoSpace(ship.getCargoSpace() + 1);
                cargo.setText("Cargo: " + ship.getCargoSpace());
                money.setText("Money: " + player.getMoney());

            }
        });

        view.setLocation(1000, 300);
        view.setVisible(true);
    }

    public static Region getCurrRegion() {
        return currRegion;
    }
}