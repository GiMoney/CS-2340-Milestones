import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Bandit extends Game {
    protected static JFrame view = new JFrame("WARNING: YOU HAVE BEEN APPROACHED BY A BANDIT");
    protected static int creditsDemanded;

    public static void main(String[] args) {
        view.setSize(1000, 600);
        Container cp = view.getContentPane();
        cp.setLayout(new BorderLayout());

        if (player.getDifficulty().equals("Hard")) {
            creditsDemanded = 50;
        } else {
            creditsDemanded = 100;
        }

        JButton pay = new JButton("Pay the demand");
        pay.setBounds(0, 290, 200, 40);
        cp.add(pay, BorderLayout.CENTER);

        JButton flee = new JButton("Attempt to flee");
        flee.setBounds(400, 290, 200, 40);
        cp.add(flee, BorderLayout.CENTER);

        JButton fight = new JButton("Engage in Combat");
        fight.setBounds(800, 290, 200, 40);
        cp.add(fight, BorderLayout.CENTER);

        JLabel welcome = new JLabel("Current Difficulty: " + player.getDifficulty());
        welcome.setBounds(20, 0, 200, 40);
        cp.add(welcome, BorderLayout.CENTER);
        JLabel location = new JLabel("Currently traveling from: " + player.getRegion());
        location.setBounds(750, 0, 300, 40);
        cp.add(location, BorderLayout.CENTER);
        JLabel money = new JLabel("Current money: " + player.getMoney());
        money.setBounds(400, 0, 200, 40);
        cp.add(money, BorderLayout.CENTER);
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
        JLabel shipHealth = new JLabel(" Ship health: " + ship.getHealth());
        shipHealth.setBounds(0, 220, 200, 40);
        cp.add(shipHealth, BorderLayout.CENTER);

        JLabel demand = new JLabel("A bandit has attacked your ship! He demands " + creditsDemanded
                + " credits. What do you decide to do?");
        demand.setBounds(0, 260, 100, 40);
        cp.add(demand, BorderLayout.CENTER);


        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }
}