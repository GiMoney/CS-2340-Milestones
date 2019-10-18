import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.*;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import java.util.ArrayList;

public class Market extends ViewController {
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
        regionTech.setBounds(0, 10, 150, 150);
        cp.add(regionTech, BorderLayout.CENTER);

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

        ArrayList<String> items = new ArrayList<>(); // NEED to add quantity
        items.add("Fuel"); // Important need to update fuel afterwards
        items.add("Drugs");
        items.add("Dirt");
        items.add("Food");
        items.add("Guns");
        items.add("Clout");
        items.add("Weed");
        items.add("ALC");
        items.add("Water");
        items.add("Gold");


        DefaultListModel marketside = new DefaultListModel();
        for (int i = 0; i < 10;i++) {
            marketside.addElement(items.get(i));
        }
        jList.setModel(marketside);

        DefaultListModel inventory = new DefaultListModel();
        buy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inventory.addElement(jList.getSelectedValue());
                marketside.removeElement(jList.getSelectedValue());
                //add sellected item to player invetory;
                jList2.setModel(inventory);
            }
        });


        sell.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                marketside.addElement(jList2.getSelectedValue());
                inventory.removeElement(jList2.getSelectedValue());
                //remove sellected item to player invetory;
                jList.setModel(marketside);
            }
        });




        view.setLocation(1000, 300);
        view.setVisible(true);

    }
}