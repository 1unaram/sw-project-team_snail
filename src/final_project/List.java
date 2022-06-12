package final_project;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class List extends JFrame {

    JPanel list = new JPanel();

    JLabel title = new JLabel("♥ ︎♥ ︎♥︎ ♥ ︎♥︎ ♥ ︎♥ ︎︎TO GO ♥ ︎♥ ︎♥ ︎♥ ︎♥ ︎♥ ︎♥");

    JLabel go1 = new JLabel();
    JLabel go2 = new JLabel();
    JLabel go3 = new JLabel();
    JLabel go4 = new JLabel();

    Container mainContainer = this.getContentPane();

    public List() {
        Color color = new Color(126, 173, 84);

        // container
        mainContainer.setBackground(color);
        mainContainer.setLayout(null);

        // title
        mainContainer.add(title);
        title.setBounds(0, 30, 1430, 30);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 30));
        title.setForeground(Color.white);

        // map Image
        NaverMap map = new NaverMap("vhj6gj3o1t", "obFecuAlLF4uD1wByfjI8ymkm8j0lGfQLMI0WKB4", 250, 250, "308518",
                "545497");

        JLabel mapLabel1 = new JLabel(new ImageIcon(map.mapImg));

        JLabel mapLabel2 = new JLabel(new ImageIcon(map.mapImg));

        JLabel mapLabel3 = new JLabel(new ImageIcon(map.mapImg));

        JLabel mapLabel4 = new JLabel(new ImageIcon(map.mapImg));

        // panel
        ListPanel panel1 = new ListPanel("수목 식당 ", mapLabel1);
        panel1.setBounds(100, 100, 600, 300);
        this.add(panel1);

        ListPanel panel2 = new ListPanel("CAU", mapLabel2);
        panel2.setBounds(730, 100, 600, 300);
        this.add(panel2);

        ListPanel panel3 = new ListPanel("효사정", mapLabel3);
        panel3.setBounds(100, 430, 600, 300);
        this.add(panel3);

        ListPanel panel4 = new ListPanel("엣더블랑", mapLabel4);
        panel4.setBounds(730, 430, 600, 300);
        this.add(panel4);

        revalidate();

        // frame
        this.setSize(1600, 900);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("List");
        this.setVisible(true);
    }

}