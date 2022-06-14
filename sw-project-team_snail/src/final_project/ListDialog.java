package final_project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ListDialog extends JDialog {

	JPanel p = new JPanel();
	JPanel p_center = new JPanel();
	JPanel p_center_center = new JPanel();
	JPanel p_center_south = new JPanel();

	JLabel togo = new JLabel();
	JLabel rau_content = new JLabel();
	JLabel empty1 = new JLabel("    ");
	JLabel empty2 = new JLabel("    ");

	JButton okButton = new JButton("OK");

	ListDialog(JFrame frame, JLabel map, String str1, String str2, String str3) {
		super(frame);

		this.setSize(400, 800);
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		this.add(p);

		Color color = new Color(126, 173, 84);

		// panel setting
		p.setBackground(Color.white);
		p.setLayout(new BorderLayout(40, 10));
		p.add(togo, BorderLayout.NORTH);
		p.add(okButton, BorderLayout.SOUTH);
		p.add(p_center, BorderLayout.CENTER);
		p.add(empty1, BorderLayout.EAST);
		p.add(empty2, BorderLayout.WEST);

		okButton.setPreferredSize(new Dimension(40, 40));

		// title setting
		togo.setText("︎♥︎ ♥︎ ♥ ︎♥ ︎︎TO GO ♥ ︎♥ ︎︎♥ ︎♥");
		togo.setForeground(color);
		togo.setHorizontalAlignment(JLabel.CENTER);
		togo.setPreferredSize(new Dimension(1000, 40));
		togo.setFont(new Font("SansSerif", Font.BOLD, 30));

		//
		ListPanel listpanel1 = new ListPanel(str1);
		ListPanel listpanel2 = new ListPanel(str2);
		ListPanel listpanel3 = new ListPanel(str3);

		p_center.setLayout(new BorderLayout());

		p_center_center.setLayout(new GridLayout(3, 1, 0, 10));
		p_center_center.setBackground(Color.white);
		p_center_south.setBackground(Color.white);
		// p_center_south.setBorder(null);

		map.setPreferredSize(new Dimension(300, 300));

		p_center_center.add(listpanel1);
		p_center_center.add(listpanel2);
		p_center_center.add(listpanel3);
		p_center_south.add(map);

		p_center.add(p_center_center, BorderLayout.CENTER);
		p_center.add(p_center_south, BorderLayout.SOUTH);

		revalidate();

		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				setVisible(false);
			}
		});

	}

}