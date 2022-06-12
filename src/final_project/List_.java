package final_project;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class List_ extends JFrame {

    JFrame f = new JFrame();
    JPanel list = new JPanel();

    JLabel title = new JLabel("♥ ︎♥ ︎♥︎ ♥ ︎♥︎ ♥ ︎♥ ︎︎TO GO ♥ ︎♥ ︎♥ ︎♥ ︎♥ ︎♥ ︎♥");

    JLabel go1 = new JLabel();
    JLabel go2 = new JLabel();
    JLabel go3 = new JLabel();
    JLabel go4 = new JLabel();

    public List_() {
        Color color = new Color(126, 173, 84);
        f.add(list);

        list.setBackground(color);
        list.setLayout(null);

        list.add(title);
        title.setBounds(0, 30, 1430, 30);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 30));
        title.setForeground(Color.white);

        list.add(go1);
        go1.setBounds(100, 100, 600, 300);
        go1.setBorder(new LineBorder(Color.white, 3, true));
        go1.setForeground(Color.white);

        try {
            JLabel im1 = new JLabel();
            ImageIcon image1 = new ImageIcon(List_.class.getResource("data/temp.jpeg"));
            im1.setIcon(image1);
            im1.setBounds(400, 120, 300, 300);
        } catch (NullPointerException e) {

        }

        list.add(go2);
        go2.setBounds(730, 100, 600, 300);
        go2.setBorder(new LineBorder(Color.white, 3, true));
        go2.setForeground(Color.white);

        list.add(go3);
        go3.setBounds(100, 430, 600, 300);
        go3.setBorder(new LineBorder(Color.white, 3, true));
        go3.setForeground(Color.white);

        list.add(go4);
        go4.setBounds(730, 430, 600, 300);
        go4.setBorder(new LineBorder(Color.white, 3, true));
        go4.setForeground(Color.white);

        go1.setText("수목 식당");
        go2.setText("CAU");
        go3.setText("효사정 공원 ");
        go4.setText("엣더 블랑");

        f.setSize(1600, 900);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setTitle("List");
        f.setVisible(true);
    }

}
