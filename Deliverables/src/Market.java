import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.*;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import java.util.LinkedList;


public class Market extends Game {
    protected static JDialog view = new JDialog();

    public static void displayMarket(Region region) {
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
        cargo.setBounds(200, 30, 100, 100);
        cp.add(cargo, BorderLayout.CENTER);

        JLabel money = new JLabel("Money: " + player.getMoney());
        money.setBounds(200, 0, 100, 100);
        cp.add(money, BorderLayout.CENTER);

        JButton buy = new JButton("Buy");
        buy.setBounds(30, 90, 60,20);
        cp.add(buy, BorderLayout.CENTER);

        JButton sell = new JButton("Sell");
        sell.setBounds(200, 90, 60,20);
        cp.add(sell, BorderLayout.CENTER);

        JList jList = new JList();
        jList.setBounds(30, 120, 150,250);
        cp.add(jList, BorderLayout.CENTER);
        JList jList2 = new JList();
        jList2.setBounds(200, 120, 150,250);
        cp.add(jList2, BorderLayout.CENTER);


        DefaultListModel marketside = new DefaultListModel();
        for (int i = 0; i < region.getItems().size();i++) {
            marketside.addElement(region.getItems().get(i));
        }
        jList.setModel(marketside);
        jList2.setModel(inventory);
        buy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!(ship.getCargoSpace() - 1 < 0) || !(player.getMoney() - 10 < 0)) {
                    if (jList.getSelectedValue() != null) {
                        if (jList.getSelectedValue() != region.getItems().get(0)) {
                            inventory.addElement(jList.getSelectedValue());
                            marketside.removeElement(jList.getSelectedValue());
                            System.out.println(region.getItems().remove(jList.getSelectedValue()));
                            //add sellected item to player invetory;
                            jList2.setModel(inventory);
                            ship.setCargoSpace(ship.getCargoSpace() - 1);
                            cargo.setText("Cargo: " + ship.getCargoSpace());
                        } else {
                            ship.setFuelCapacity(ship.getFuelCapacity() + 10);
                            ship.setCargoSpace(ship.getCargoSpace() - 1);
                            fuel.setText("Fuel: " + ship.getFuelCapacity());
                            cargo.setText("Cargo: " + ship.getCargoSpace());
                        }
                        player.setMoney(player.getMoney() - 10);
                        money.setText("Money: " + player.getMoney());
                    }
                }
            }
        });


        sell.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(jList2.getSelectedValue() != null) {
                    marketside.addElement(jList2.getSelectedValue());
                    inventory.removeElement(jList2.getSelectedValue());
                    //remove sellected item to player invetory;
                    jList.setModel(marketside);
                    player.setMoney(player.getMoney() + 10);
                    ship.setCargoSpace(ship.getCargoSpace() + 1);
                    cargo.setText("Cargo: " + ship.getCargoSpace());
                    money.setText("Money: " + player.getMoney());

                }
            }
        });




        view.setLocation(1000, 300);
        view.setVisible(true);

    }
}