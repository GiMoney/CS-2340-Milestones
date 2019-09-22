import javax.swing.*;
import java.awt.event.*;

public class PlayerInfoView extends ViewController {
	public static JFrame view = new JFrame();
	static PlayerInfoView current;

	public static void main(String[] args) {
		System.out.println("Player view");
		JButton b = new JButton("Player view");
		b.setBounds(130,100,100, 40);
		b.addActionListener(new SegueListener());

		view.add(b);

		view.setSize(400,500);
		view.setLayout(null);
		view.setVisible(true);
	}

	public static class SegueListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			current.view.setVisible(false);
			current.view.dispose();
		}
	}
}