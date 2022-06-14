package final_project;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class ListPanel extends JPanel {

	ListPanel(String name, JLabel map) {
		JLabel la_name = new JLabel(name);

		this.setLayout(null);
		this.setBorder(new LineBorder(Color.white, 3, true));
		this.setForeground(Color.white);

		this.add(map);
		this.add(la_name);
		la_name.setBounds(20, 20, 100, 40);
		map.setBounds(325, 25, 250, 250);

	}

	ListPanel(String name) {
		JLabel la_name = new JLabel(name);
		Color color = new Color(126, 173, 84);

		this.setLayout(null);
		this.setBorder(new LineBorder(Color.white, 3, true));
		this.setForeground(Color.white);
		this.setBackground(color);

		la_name.setForeground(Color.white);
		la_name.setFont((new Font("SansSerif", Font.PLAIN, 12)));

		this.add(la_name);
		la_name.setBounds(20, 0, 300, 100);
	}

}