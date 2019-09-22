import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.*;

public class PlayerInfoView extends ViewController {
    public static JFrame view = new JFrame();
    static PlayerInfoView current;

    public static void main(String[] args) {
        view.setSize(500,600);



        JButton b = new JButton("Player view");
        int bLocX = view.getWidth() / 2 - 50;
        int bLocY = view.getHeight() / 2 - 40;

        b.setBounds(bLocX,bLocY,100, 40);
        b.addActionListener(new SegueListener());

        view.add(b);

        view.setLocationRelativeTo(null);
        view.setLayout(new FlowLayout());
        view.setVisible(true);
    }

    public static class SegueListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            current.view.setVisible(false);
            current.view.dispose();
        }
    }
}