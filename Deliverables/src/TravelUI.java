import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;


public class TravelUI extends Game {
    private static JDialog view = new JDialog();
    private static TravelUI current;
    private static Market next;
    private static final String IMAGE_URL = "/resource/bar.png";


    public static void display(Region region) throws IOException {
        ArrayList<String> images = new ArrayList<>();
        view.setModal(true);
        view.setSize(400, 500);
        Container cp = view.getContentPane();
        cp.removeAll();
        cp.setLayout(new BorderLayout());

        JLabel name = new JLabel("Location: " + region.getName());
        name.setBounds(0, 50, 400, 200);
        cp.add(name, BorderLayout.CENTER);

        JButton marketplace = new JButton("Market Place");
        marketplace.setBounds(0, 0, 200, 30);
        cp.add(marketplace, BorderLayout.CENTER);

        JLabel regionTech = new JLabel("Tech: " + region.getTechLevel());
        name.setBounds(0, 40, 400, 200);
        cp.add(regionTech, BorderLayout.CENTER);

        /*BufferedImage image;
        switch (region.getTechLevel()) {
        case PREAG :
            image = ImageIO.read(new File("Stone.jpg"));
            break; // optional

        case AGRICULTURE :
            image = ImageIO.read(new File("PreAg.jpg"));
            break; // optional

        case MEDIEVAL :
            image = ImageIO.read(new File("Medieval.jpg"));
            break;

        case RENAISSANCE :
            image = ImageIO.read(new File("R.jpg"));
            break;

        case INDUSTRIAL :
            image = ImageIO.read(new File("I.jpg"));
            break;

        case MODERN :
            image = ImageIO.read(new File("M.jpg"));
            break;

        case FUTURISTIC :
            image = ImageIO.read(new File("f.jpg"));
            break;

        case DARK :
            image = ImageIO.read(new File("d.jpg"));
            break;

        case STEAMPUNK :
            image = ImageIO.read(new File("s.jpg"));
            break;

        // You can have any number of case statements.
        default : // Optional
            image = ImageIO.read(new File("c.jpg"));
        }

        JLabel label = new JLabel(new ImageIcon(image));
        cp.add(label);
        label.setBounds(200,200,150,150);
*/
        marketplace.addActionListener(new PageActionListener(region));

        view.setLocation(1000, 300);
        view.setVisible(true);

    }

    private static class PageActionListener implements ActionListener {
        private int intValue;
        private Region region;


        public PageActionListener(Region region) {
            this.region = region;
        }

        public void actionPerformed(ActionEvent e) {
            current.view.setVisible(false);
            next = new Market();
            next.displayMarket(region);
            current.view.setVisible(true);
        }

    }
}