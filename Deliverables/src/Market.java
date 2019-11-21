import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;

public class Market extends Game {
    private static JDialog view;
    protected static EndGame end;

    public static void displayMarket(Region region) {
        view = new JDialog();
        view.setModal(true);
        view.setSize(400, 500);
        Container cp = view.getContentPane();
        cp.removeAll();
        cp.setLayout(null);

        JLabel marketplace = new JLabel("Market Place");
        marketplace.setBounds(0, 0, 200, 30);
        cp.add(marketplace, BorderLayout.CENTER);

        JLabel regionTech = new JLabel("Tech: " + region.getTechLevel());
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

        JLabel cost;
        if (player.getKarma() >= 3) {
            cost = new JLabel("Cost of each item: " + currRegion.priceCalculator(10, player));
        } else if (player.getKarma() <= -3) {
            cost = new JLabel("Cost of each item: " + currRegion.priceCalculator(30, player));
        } else {
            cost = new JLabel("Cost of each item: " + currRegion.priceCalculator(20, player));
        }
        cost.setBounds(200, 30, 200, 100);
        cp.add(cost, BorderLayout.CENTER);

        JButton buy = new JButton("Buy");
        buy.setBounds(30, 110, 60, 20);
        cp.add(buy, BorderLayout.CENTER);

        JButton sell = new JButton("Sell");
        sell.setBounds(200, 110, 60, 20);
        cp.add(sell, BorderLayout.CENTER);

        JList jList = new JList();
        jList.setBounds(30, 140, 150, 310);
        cp.add(jList, BorderLayout.CENTER);
        JList jList2 = new JList();
        jList2.setBounds(200, 140, 150, 310);
        cp.add(jList2, BorderLayout.CENTER);


        DefaultListModel marketside = new DefaultListModel();
        for (int i = 0; i < region.getItems().size(); i++) {
            marketside.addElement(region.getItems().get(i));
            System.out.println(region.getTechLevel());
        }
        if (region.getTechLevel() == TechLevel.FUTURISTIC) {
            marketside.addElement(player.getName() + "'s Universe ($10000)");
        }
        jList.setModel(marketside);
        jList2.setModel(inventory);
        buy.addActionListener(e -> {
            String val = (String) jList.getSelectedValue();
            System.out.println(val);
            int remainingMon = player.getMoney() - currRegion.priceCalculator(20, player);
            if (jList.getSelectedValue()
                    .equals(player.getName() + "'s Universe ($10000)")) {
                remainingMon = player.getMoney() - 10000;
            } else if (player.getKarma() >= 3) {
                remainingMon = player.getMoney() - currRegion.priceCalculator(10, player);
            } else if (player.getKarma() <= -3) {
                remainingMon = player.getMoney() - currRegion.priceCalculator(30, player);
            }
            if (jList.getSelectedValue() != null && remainingMon >= 0
                    && ship.getCargoSpace() - 1 >= 0) {
                if (!jList.getSelectedValue().equals(currRegion.getItems().get(0))) {
                    inventory.addElement(jList.getSelectedValue());
                    if (jList.getSelectedValue()
                            .equals(player.getName() + "'s Universe ($10000)")) {
                        end.display(true);
                        Game.view.setVisible(false);
                        inventory.removeAllElements();
                        view.setVisible(false);
                        view.dispose();
                    }
                    marketside.removeElement(jList.getSelectedValue());
                    currRegion.getItems().remove(jList.getSelectedValue());
                    //add selected item to player inventory;
                    jList2.setModel(inventory);
                    ship.setCargoSpace(ship.getCargoSpace() - 1);
                    cargo.setText("Cargo: " + ship.getCargoSpace());
                    player.setMoney(remainingMon);
                } else {
                    if (!(ship.getFuelCapacity() + 10 > 100)) {
                        ship.setFuelCapacity(ship.getFuelCapacity() + 10);
                        player.setMoney(remainingMon);
                    }
                    if (!jList.getSelectedValue().equals(currRegion.getItems().get(0))) {
                        ship.setCargoSpace(ship.getCargoSpace() - 1);
                    }
                    fuel.setText("Fuel: " + ship.getFuelCapacity());
                    cargo.setText("Cargo: " + ship.getCargoSpace());
                }
                money.setText("Money: " + player.getMoney());
            }
        });

        sell.addActionListener(e -> {
            if (jList2.getSelectedValue() != null) {
                //marketside.addElement(jList2.getSelectedValue());
                inventory.removeElement(jList2.getSelectedValue());
                //remove selected item to player inventory;
                jList.setModel(marketside);
                player.setMoney(player.getMoney() + currRegion.priceCalculator(20, player));
                ship.setCargoSpace(ship.getCargoSpace() + 1);
                cargo.setText("Cargo: " + ship.getCargoSpace());
                money.setText("Money: " + player.getMoney());

            }
        });




        view.setLocation(1000, 300);
        view.setVisible(true);

    }
}