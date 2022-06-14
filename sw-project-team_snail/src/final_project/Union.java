package final_project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Union extends JPanel {

	/* 전역 변수 선언 */
	// 이미지 아이콘
	ImageIcon restaurant = new ImageIcon("img/restaurant.png");
	ImageIcon cafe = new ImageIcon("img/cafe.png");
	ImageIcon culture = new ImageIcon("img/culture.png");
	ImageIcon restaf = new ImageIcon("img/restafter.png");
	ImageIcon cafeaf = new ImageIcon("img/cafeafter.png");
	ImageIcon cultureaf = new ImageIcon("img/cultureafter.png");

	// 패널
	JPanel btnPanel = new JPanel(); // category 맛집 클릭 버튼 모음집
	StoreInfoPanel restInfoPanel, cafeInfoPanel, cultureInfoPanel; // 카테고리별 매장 정보 패널

	// JButton
	JButton restBtn, cafeBtn, cultureBtn; // 맛집, 카페, 문화생활 버튼

	// 그 외의 변수
	Font font;
	String keyword;

	/* 생성자 */
	Union(String keyword) {

		this.setLayout(new BorderLayout(0, 20));

		this.keyword = keyword;
		font = new Font("한컴 말랑말랑 Regular", Font.BOLD, 30);

		// 버튼 할당
		setButton();

		// 매장 정보 패널
		restInfoPanel = new StoreInfoPanel(keyword, "맛집");
		cafeInfoPanel = new StoreInfoPanel(keyword, "카페");
		cultureInfoPanel = new StoreInfoPanel(keyword, "문화생활");

		// 레이아웃 설정
		btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
		btnPanel.setBackground(Keywordp.color);

		// 패널에 붙이기
		this.add(btnPanel, BorderLayout.NORTH);
	}

	public void setButton() {
		// 맛집
		restBtn = new JButton(restaurant);
		restBtn.setBackground(Color.white);
		restBtn.setBorderPainted(false);
		restBtn.setPressedIcon(restaf);
		restBtn.setPreferredSize(new Dimension(108, 44));
		restBtn.addActionListener(new MyButtonListener());

		// 카페
		cafeBtn = new JButton(cafe);
		cafeBtn.setBackground(Color.white);
		cafeBtn.setBorderPainted(false);
		cafeBtn.setPressedIcon(cafeaf);
		cafeBtn.setPreferredSize(new Dimension(108, 44));
		cafeBtn.addActionListener(new MyButtonListener());

		// 문화
		cultureBtn = new JButton(culture);
		cultureBtn.setBackground(Color.white);
		cultureBtn.setBorderPainted(false);
		cultureBtn.setPressedIcon(cultureaf);
		cultureBtn.setPreferredSize(new Dimension(153, 44));
		cultureBtn.addActionListener(new MyButtonListener());

		btnPanel.add(restBtn);
		btnPanel.add(cafeBtn);
		btnPanel.add(cultureBtn);
	}

	// 카테고리별 패널 붙이기
	public void putPanel(StoreInfoPanel panel) {

		panel.setBackground(Keywordp.color);
		this.add(panel);
	}

	// 모든 버튼 액션 처리
	class MyButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// 맛집 버튼 처리
			if (e.getSource() == restBtn) {
				restInfoPanel.setVisible(true);
				cafeInfoPanel.setVisible(false);
				cultureInfoPanel.setVisible(false);

				putPanel(restInfoPanel);
			}
			// 카페 버튼 처리
			else if (e.getSource() == cafeBtn) {
				restInfoPanel.setVisible(false);
				cafeInfoPanel.setVisible(true);
				cultureInfoPanel.setVisible(false);

				putPanel(cafeInfoPanel);
			}
			// 문화 생활 버튼 처리
			else if (e.getSource() == cultureBtn) {
				restInfoPanel.setVisible(false);
				cafeInfoPanel.setVisible(false);
				cultureInfoPanel.setVisible(true);

				putPanel(cultureInfoPanel);
			}

			// 새로고침
			revalidate();
		}

	}

	// 가게의 정보를 보여주는 패널
	class StoreInfoPanel extends JPanel {

		Search_What sw;

		/* 상속자 */
		StoreInfoPanel(String keyword, String category) {

			this.setLayout(null);
			this.setBackground(Color.white);

			this.sw = new Search_What(keyword, category);
			font = new Font("한컴 말랑말랑 Regular", Font.BOLD, 17);
			int i = 0;
			for (StoreInfo st : sw.storeinfo) {
				JLabel title, address;

				title = new JLabel(
						"<html>" + Integer.toString(i + 1) + ". " + st.title + " (" + st.category + ")" + "</html>");
				address = new JLabel("<html>" + "  주소: " + st.address + "</html>");

				title.setBounds(30, 0 + i * 58, 1000, 30);
				address.setBounds(30, 15 + i * 58, 1000, 40);

				title.setFont(font);
				address.setFont(font);

				this.add(title);
				this.add(address);

				i++;
			}
		}
	}
}
