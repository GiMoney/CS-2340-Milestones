import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Trader extends Game {
    private static JDialog view = new JDialog();
    private int traderCost = 4;
    protected double robNum = (Math.random() * 99);
    protected double negotiateNum = (Math.random() * 99);


    public void displayTrader(Region region) throws IOException {
        view.setModal(true);
        view.setSize(400, 750);
        Container cp = view.getContentPane();
        cp.removeAll();
        cp.setLayout(null);

        BufferedImage image;
        image = ImageIO.read(getClass().getResource("/resource/trader.jpg"));

        player.setRegionPrev(region);
        //player.setRegion1(region);
        JLabel label = new JLabel(new ImageIcon(image));
        cp.add(label);
        label.setBounds(0, 500, 400, 221);

        JLabel marketplace = new JLabel("TRADER");
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
        if(player.getKarma() >= 3){
            cost = new JLabel("Cost of each item: " + (this.traderCost - 1));
        } else if(player.getKarma() <= -3){
            cost = new JLabel("Cost of each item: " + (this.traderCost + 1));
        } else {
            cost = new JLabel("Cost of each item: " + (this.traderCost));
        }
        cost.setBounds(200, 30, 200, 100);
        cp.add(cost, BorderLayout.CENTER);

        JButton buy = new JButton("Buy");
        buy.setBounds(30, 110, 60, 20);
        cp.add(buy, BorderLayout.CENTER);

        JButton sell = new JButton("Sell");
        sell.setBounds(200, 110, 60, 20);
        cp.add(sell, BorderLayout.CENTER);

        JButton neg = new JButton("Negotiate");
        neg.setBounds(100, 110, 60, 20);
        cp.add(neg, BorderLayout.CENTER);

        JButton rob = new JButton("Rob");
        rob.setBounds(150, 110, 60, 20);
        cp.add(rob, BorderLayout.CENTER);

        JList jList = new JList();
        jList.setBounds(30, 140, 150, 310);
        cp.add(jList, BorderLayout.CENTER);
        JList jList2 = new JList();
        jList2.setBounds(200, 140, 150, 310);
        cp.add(jList2, BorderLayout.CENTER);


        DefaultListModel marketside = new DefaultListModel();
        for (int i = 0; i < region.getItems().size(); i++) {
            marketside.addElement(region.getItems().get(i));
        }
        jList.setModel(marketside);
        jList2.setModel(inventory);
        int remainingMon;
        if(player.getKarma() >= 3){
            remainingMon = player.getMoney() - (this.traderCost - 1);
        } else if(player.getKarma() <= -3){
            remainingMon = player.getMoney() - (this.traderCost + 1);
        } else {
            remainingMon = (player.getMoney() - this.traderCost);
        }
        buy.addActionListener(e -> {
            if (jList.getSelectedValue() != null && remainingMon >= 0
                    && ship.getCargoSpace() - 1 >= 0) {
                if (!jList.getSelectedValue().equals(currRegion.getItems().get(0))) {
                    inventory.addElement(jList.getSelectedValue());
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

        neg.addActionListener(e -> {
            if (negotiateNum < player.getNegotiateChance()) {
                traderCost = traderCost - 2;
                cost.setText("Cost of each item: " + traderCost);
            } else {
                traderCost = traderCost + 2;
                cost.setText("Cost of each item: " + traderCost);
            }
            neg.setEnabled(false);
        });

        rob.addActionListener(e -> {
            if (robNum < player.getRobChance()) {
                inventory.addElement(marketside.get(1));
                marketside.removeElementAt(1);
                ship.setCargoSpace(ship.getCargoSpace() - 1);
                cargo.setText("Cargo: " + ship.getCargoSpace());
            } else {
                ship.setHealth(ship.getHealth() - 20);
            }
            rob.setEnabled(false);
        });

        view.setLocation(1000, 300);
        view.setVisible(true);

    }
}