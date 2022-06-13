package finalterm;

import java.awt.BorderLayout;
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
		this.keyword = keyword;
		this.setLayout(new BorderLayout());

		// 버튼 할당
		setButton();

		// 매장 정보 패널
		restInfoPanel = new StoreInfoPanel(keyword, "맛집");
		cafeInfoPanel = new StoreInfoPanel(keyword, "카페");
		cultureInfoPanel = new StoreInfoPanel(keyword, "문화생활");

		// // 레이아웃 설정
		// btnPanel.setLayout(null);
		// btnPanel.setBounds(20, 30, 820, 48);

		// 패널에 붙이기
		this.add(btnPanel, BorderLayout.NORTH);
	}

	public void setButton() {
		// 맛집
		restBtn = new JButton(restaurant);
		restBtn.setPressedIcon(restaf);
		restBtn.setBounds(500, 10, 108, 44);
		restBtn.addActionListener(new MyButtonListener());

		// 카페
		cafeBtn = new JButton(cafe);
		cafeBtn.setPressedIcon(cafeaf);
		cafeBtn.setBounds(150, 10, 108, 44);
		cafeBtn.addActionListener(new MyButtonListener());

		// 문화
		cultureBtn = new JButton(culture);
		cultureBtn.setPressedIcon(cultureaf);
		cultureBtn.setBounds(300, 10, 153, 44);
		cultureBtn.addActionListener(new MyButtonListener());

		btnPanel.add(restBtn);
		btnPanel.add(cafeBtn);
		btnPanel.add(cultureBtn);
	}

	public void putPanel(StoreInfoPanel panel) {
		this.add(panel, BorderLayout.CENTER);
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

		/* 상속자 */
		StoreInfoPanel(String keyword, String category) {
			this.setLayout(null);
			this.setBounds(20, 80, 820, 48);

			Search_What sw = new Search_What(keyword, category);

			int i = 0;
			for (StoreInfo st : sw.storeinfo) {
				JLabel title, address;

				title = new JLabel("<html>" + Integer.toString(i + 1) + ". " + st.title + "</html>");
				address = new JLabel("<html>" + "  주소: " + st.address + "</html>");

				title.setBounds(170, 100 + i * 62, 500, 20);
				address.setBounds(170, 120 + i * 62, 500, 40);

				this.add(title);
				this.add(address);

				i++;
			}
		}
	}
}
