package finalterm;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Startp extends JPanel { // 네이버 검색창에서 통합 검색어 트랜드 검색
	private JButton jButton1;
	private JLabel jLabel1, jLabel2;

	public Startp(Naver_GUI win) {
		Color color = new Color(255, 255, 255);
		setLayout(null);
		setBackground(color);

		ImageIcon img1 = new ImageIcon("img/Naver_logo.png");
		ImageIcon img2 = new ImageIcon("img/start.png");
		ImageIcon img3 = new ImageIcon("img/where_to_go.png");
		jLabel1 = new JLabel(img1);
		jLabel1.setBounds(500, 386, 100, 88);
		add(jLabel1);

		jLabel2 = new JLabel(img3);
		jLabel2.setBounds(615, 386, 370, 88);
		add(jLabel2);

		jButton1 = new JButton(img2); // 시작 버튼, 누르면 키워드 검색으로 넘어가서 순위랑 목록들 보여줌
		jButton1.setBounds(1000, 386, 100, 88);
		add(jButton1);

		jButton1.addActionListener(new ActionListener() {// 검색 버튼 누르면 키워드 차트나오는 곳으로 옮겨감
			@Override
			public void actionPerformed(ActionEvent e) {
				win.change("keywordp");
			}
		});
	}

}
