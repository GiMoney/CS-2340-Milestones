import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Container;

public class TravelUI {
    protected static JDialog view = new JDialog();

    public static void main(String[] args) {
        view.setModal(true);
        view.setSize(400, 500);
        Container cp = view.getContentPane();
        cp.removeAll();
        cp.setLayout(new BorderLayout());

        JLabel name = new JLabel("Location: " + args[0]);
        name.setBounds(0, 50, 150, 150);
        cp.add(name, BorderLayout.CENTER);

        view.setLocation(1000, 300);
        view.setVisible(true);

    }
}