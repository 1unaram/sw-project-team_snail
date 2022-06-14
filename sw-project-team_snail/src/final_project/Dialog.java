package final_project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
	JPanel p_center = new JPanel();
	JPanel empty = new JPanel();

	JLabel title1 = new JLabel();
	JLabel rau_content = new JLabel();
	// JLabel link = new JLabel("LINK");
	JButton okButton = new JButton("OK");

	Dialog(JFrame frame, String title, JLabel map, StoreInfo info) {
		super(frame);
		this.setTitle("Information");

		this.setSize(700, 700);
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		this.add(p);

		Color color = new Color(126, 173, 84);
		Color color2 = new Color(90, 163, 100);

		// panel setting
		p.setBackground(Color.white);
		p.setLayout(new BorderLayout());
		p.add(title1, BorderLayout.NORTH);
		p.add(p_center, BorderLayout.CENTER);
		p.add(okButton, BorderLayout.SOUTH);

		p_center.setBackground(Color.white);
		p_center.setLayout(new BorderLayout(40, 60));
		p_center.add(empty, BorderLayout.NORTH);
		empty.setBackground(Color.white);
		p_center.add(map, BorderLayout.CENTER);
		p_center.add(rau_content, BorderLayout.SOUTH);

		okButton.setPreferredSize(new Dimension(40, 40));

		// title setting
		title1.setText(title);
		title1.setForeground(color);
		title1.setHorizontalAlignment(JLabel.CENTER);
		title1.setPreferredSize(new Dimension(1000, 40));
		title1.setFont(new Font("SansSerif", Font.BOLD, 30));

		// content setting
		rau_content.setHorizontalAlignment(JLabel.CENTER);
		rau_content.setText(
				"<html> address : " + info.address + "<br>" + " category : " + info.category + "<br><br><br></html>");
		rau_content.setForeground(color2);
		rau_content.setBounds(50, 70, 300, 400);
		rau_content.setFont(new Font("SansSerif", Font.PLAIN, 20));

		revalidate();

		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				setVisible(false);
			}
		});

	}

}