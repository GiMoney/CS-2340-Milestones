import javax.swing.*;
import java.awt.event.*;

public class StartGameView extends ViewController {
	public static JFrame view = new JFrame();
	static StartGameView current;
	static PlayerConfigurationView next;

	public static void main(String[] args) {
		System.out.println("Start game");
		JButton b = new JButton("Start Game!");
		b.setBounds(130, 100, 100, 40);
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
			next = new PlayerConfigurationView();
			next.main(null);
		}
	}
}