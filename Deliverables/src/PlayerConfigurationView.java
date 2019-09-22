import javax.swing.*;
import java.awt.event.*;

public class PlayerConfigurationView extends ViewController {
	public static JFrame view = new JFrame();
	static PlayerConfigurationView current;
	static PlayerInfoView next;

	public static void main(String[] args) {
		System.out.println("Player configuration");
		JButton b = new JButton("Player configuration");
		b.setBounds(130, 100, 100, 40);
		b.addActionListener(new PlayerConfigurationView.SegueListener());

		view.add(b);

		view.setSize(400,500);
		view.setLayout(null);
		view.setVisible(true);
	}

	public static class SegueListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			current.view.setVisible(false);
			current.view.dispose();
			next = new PlayerInfoView();
			next.main(null);
		}
	}
}