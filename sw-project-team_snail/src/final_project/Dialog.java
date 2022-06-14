package final_project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Dialog extends JDialog {
    JPanel p = new JPanel();

    JLabel title1 = new JLabel();
    JLabel rau_content = new JLabel();

    JButton okButton = new JButton("OK");

    Dialog(JFrame frame, String title, JLabel map) {
        super(frame, title);

        this.setSize(1000, 600);
        this.add(p);

        Color color = new Color(126, 173, 84);
        Color color2 = new Color(90, 163, 100);

        // panel setting
        p.setBackground(Color.white);
        p.setLayout(new BorderLayout());
        p.add(title1);
        p.add(rau_content);
        p.add(okButton);
        okButton.setBounds(480, 500, 80, 40);

        // title setting
        title1.setText(title);
        title1.setForeground(color);
        title1.setHorizontalAlignment(JLabel.CENTER);
        title1.setBounds(0, 20, 1000, 40);
        title1.setFont(new Font("SansSerif", Font.BOLD, 30));

        // content setting
        rau_content.setText("CONTENTS");
        rau_content.setForeground(color2);
        rau_content.setBounds(50, 70, 300, 400);
        rau_content.setFont(new Font("SansSerif", Font.PLAIN, 20));

        // map image

        p.add(map);
        map.setBounds(500, 70, 400, 400);

        revalidate();

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
            }
        });

    }

}

class DialogEx extends JFrame {
    Dialog dialog;

    DialogEx() {
        super("DialogEx frame");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton btn = new JButton("Show Dialog");

        NaverMap map = new NaverMap("vhj6gj3o1t", "obFecuAlLF4uD1wByfjI8ymkm8j0lGfQLMI0WKB4", 400, 400, "308518",
                "545497");
        JLabel mapLabel1 = new JLabel(new ImageIcon(map.mapImg));
        dialog = new Dialog(this, "선택된 곳 ", mapLabel1);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dialog.setVisible(true);
            }
        });

        this.add(btn);
        this.setSize(250, 200);
        this.setVisible(true);
    }
}
