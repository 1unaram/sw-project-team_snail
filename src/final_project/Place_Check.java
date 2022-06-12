package final_project;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class Place_Check {

    JFrame f = new JFrame();

    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();
    JPanel p3 = new JPanel();
    JPanel center = new JPanel();

    JButton but_finish = new JButton("FINISH");
    JButton but_cat1 = new JButton("1");
    JButton but_cat2 = new JButton("2");
    JButton but_cat3 = new JButton("3");
    JButton but_cat4 = new JButton("4");

    Place_Check() {
        Color color = new Color(126, 173, 84);

        f.add(p1);
        f.add(p2);
        f.add(p3);

        f.setLayout(null);

        // category & finish
        p1.setBackground(color);
        p1.setLayout(new BorderLayout());
        p1.setBorder(new LineBorder(Color.white, 2));
        p1.setBounds(0, 0, 1440, 50);

        center.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 5));
        center.setBackground(color);

        but_cat1.setPreferredSize(new Dimension(110, 40));
        but_cat2.setPreferredSize(new Dimension(110, 40));
        but_cat3.setPreferredSize(new Dimension(110, 40));
        but_cat4.setPreferredSize(new Dimension(110, 40));

        but_finish.setPreferredSize(new Dimension(110, 40));

        center.add(but_cat1);
        center.add(but_cat2);
        center.add(but_cat3);
        center.add(but_cat4);

        p1.add(center, BorderLayout.CENTER);
        p1.add(but_finish, BorderLayout.EAST);

        // map
        p2.setBackground(color);
        p2.setBorder(new LineBorder(Color.white, 2));
        p2.setBounds(0, 50, 1000, 670);

        // list
        p3.setBackground(Color.white);
        p3.setBorder(new LineBorder(Color.white, 2));
        p3.setBounds(1000, 50, 440, 670);

        f.setSize(1440, 800);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setTitle("Place Check");
        f.setVisible(true);
    }

}
