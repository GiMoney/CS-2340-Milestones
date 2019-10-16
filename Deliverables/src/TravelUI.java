import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.*;

public class TravelUI extends ViewController {
    protected static JFrame view = new JFrame();

    public static void main(String[] args) {
        view.setSize(400, 500);
        Container cp = view.getContentPane();
        cp.setLayout(new BorderLayout());

        JLabel name = new JLabel("Location: " + args[0]);
        name.setBounds(0, 50, 150, 150);
        cp.add(name, BorderLayout.CENTER);

        JLabel name = new JLabel(": " + args[1]);
        name.setBounds(0, 50, 150, 150);
        cp.add(name, BorderLayout.CENTER);

        view.setLocation(1000, 300);
        view.setVisible(true);
       // view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}