import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.*;

public class EndGame {
    protected static JDialog view;
    protected static StartGameView newGame;

    public static void display(boolean win) {
        view = new JDialog();
        view.setSize(500, 600);
        JLabel congrats;
        if(win) {
            congrats = new JLabel("Congrats YOU WON");
        } else {
        congrats = new JLabel("YOU LOST");
        }
        JButton yes = new JButton("Yes");
        JButton no = new JButton("No");
        JLabel cont = new JLabel("Start new game?");
        yes.setBounds(200, 350, 200, 40);
        no.setBounds(400, 350, 200, 40);
        cont.setBounds(300, 400,200,40);
        congrats.setBounds(100,350,200,40);
        view.add(congrats);
        view.add(yes);
        view.add(no);
        view.add(cont);
        view.setLocationRelativeTo(null);
        view.setLayout(new FlowLayout());
        view.setVisible(true);
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newGame = new StartGameView();
        yes.addActionListener(e -> {
            view.dispose();
            view.setVisible(false);
            newGame.main(null);
        });
        no.addActionListener(e -> {
            System.exit(0);
        });
    }
}