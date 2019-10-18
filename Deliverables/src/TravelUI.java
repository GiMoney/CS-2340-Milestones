import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.*;

public class TravelUI extends ViewController {
    protected static JDialog view = new JDialog();

    public static void display(Region region) {
        view.setModal(true);
        view.setSize(400, 500);
        Container cp = view.getContentPane();
        cp.removeAll();
        cp.setLayout(new BorderLayout());

        JLabel name = new JLabel("Location: " + region.getName());
        name.setBounds(0, 50, 150, 150);
        cp.add(name, BorderLayout.CENTER);

        JLabel regionTech = new JLabel("Tech: " + region.getTechLevel());
        name.setBounds(0, 40, 150, 150);
        cp.add(regionTech, BorderLayout.CENTER);

        view.setLocation(1000, 300);
        view.setVisible(true);

    }
}