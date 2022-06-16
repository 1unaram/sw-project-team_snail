package final_project;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

class Keywordp extends JPanel { // 키워드 검색시 순위 출력->여기서 검색 버튼 누르면 해당 키워드 관련 음식점, 카페등의 리스트 출력해줌

	/* 전역변수 선언 */
	private JLabel ranking; // label1~5와 textField1~5는
							// 키워드 입력관련 컴포넌트, st1~5는 가게이름,ad1~5는 가게 주소
	private JButton add, compare;// rl1~5,rf1~5,sb1~5는 순위매기는거 관련 컴포넌트,ca1~3은 맛집,카페, 문화생활 버튼
	private Naver_GUI win;
	private ArrayList<String> rankingList; // 순위 매긴 키워드 리스트 저장
	private int cnt = 2;
	static String keyword;
	static String title;
	static String addre;
	static Search_What SW;
	static Compare_Trends CT;

	// Union 객체
	public Union union;
	ArrayList<StoreInfo> restList;
	ArrayList<StoreInfo> cafeList;
	ArrayList<StoreInfo> cultureList;

	// Image
	ImageIcon img6 = new ImageIcon("img/add.png");
	ImageIcon img7 = new ImageIcon("img/compare.png");
	ImageIcon iranking = new ImageIcon("img/ranking.png");
	ImageIcon home = new ImageIcon("img/home.png");
	ImageIcon select = new ImageIcon("img/select.png");

	// Color
	static Color color = new Color(255, 255, 255); // 흰 배경색
	static Color color2 = new Color(112, 173, 71); // naver 녹색

	// JButton
	JButton homeBtn = new JButton("back to main"); // 메인화면으로 돌아가는 버튼, 누르면 키워드검색들 다 초기화 됨
	JButton selectBtn = new JButton(select); // 갈 장소 선택하는 버튼, 누르면 place_Check로 넘어감

	// JTextField
	JTextField tempField = new JTextField();

	/* class 분리 */
	// keyword 번호 이미지
	KeywordImageLabel[] kil = new KeywordImageLabel[5];

	// Keyword 텍스트 필드
	KeywordTextField[] ktf = new KeywordTextField[5];

	// Ranking 번호 이미지
	RankImageBtn[] ril = new RankImageBtn[5];

	// Ranking 텍스트 필드
	RankTextField[] rtf = new RankTextField[5];

	// Ranking 버튼
	RankSearchBtn[] rsb = new RankSearchBtn[5];

	public void paintComponent(Graphics g) {
		Color color = new Color(112, 173, 71); // naver 녹색
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(2));
		g2d.setColor(color);
		g2d.drawLine(720, 0, 720, 760);
		g2d.drawLine(720, 380, 1440, 380);
	}

	/* 생성자 */
	public Keywordp(Naver_GUI win, ArrayList<String> rankingList) {

		// 기본 설정
		this.win = win;
		this.rankingList = rankingList;
		setBackground(color);
		setVisible(true);

		// 컴포넌트 붙이기
		init();
	}

	// 컴포넌트 붙이는 메서드
	public void init() {
		setLayout(null);

		// Keyword Image
		for (int i = 0; i < 5; i++) {
			kil[i] = new KeywordImageLabel(Integer.toString(i + 1));
			kil[i].setBounds(50, 130 + 103 * i, 145, 88);
			add(kil[i]);

			// 3~5는 추가누르기 전까지 보이지 않게 하기
			if (i >= 2)
				kil[i].setVisible(false);
		}

		// TextField
		for (int i = 0; i < 5; i++) {
			ktf[i] = new KeywordTextField(color2);
			ktf[i].setBounds(210, 130 + i * 103, 290, 88);
			add(ktf[i]);

			// 3~5는 추가누르기 전까지 보이지 않게 하기
			if (i >= 2)
				ktf[i].setVisible(false);
		}

		// Rank Image
		for (int i = 0; i < 5; i++) {
			ril[i] = new RankImageBtn(Integer.toString(i + 1), i);
			ril[i].setBounds(830, 93 + 50 * i, 54, 44);
			add(ril[i]);

			// 3~5는 추가누르기 전까지 보이지 않게 하기
			if (i >= 2)
				ril[i].setVisible(false);
		}

		// Rank TextField
		for (int i = 0; i < 5; i++) {
			rtf[i] = new RankTextField(color2);
			rtf[i].setBounds(890, 93 + i * 50, 326, 44);
			add(rtf[i]);

			// 3~5는 추가누르기 전까지 보이지 않게 하기
			if (i >= 2)
				rtf[i].setVisible(false);
		}

		// Rank Search Button
		for (int i = 0; i < 5; i++) {
			rsb[i] = new RankSearchBtn(i);
			rsb[i].setBounds(1222, 93 + i * 50, 108, 44);
			rsb[i].addActionListener(new SearchActionListener());
			add(rsb[i]);

			// 추가누르기 전까지 보이지 않게 하기
			if (i >= 2)
				rsb[i].setVisible(false);
		}

		// 그 외의 버튼들
		add = new JButton(img6);
		add.setBounds(570, 25, 100, 88);
		add.addActionListener(new MyActionListener());

		compare = new JButton(img7);
		compare.setBounds(570, 647, 100, 88);
		compare.addActionListener(new MyActionListener());

		ranking = new JLabel(iranking);
		ranking.setBounds(890, 43, 326, 44);

		homeBtn = new JButton(home);
		homeBtn.setSize(120, 40);
		homeBtn.setLocation(10, 10);
		homeBtn.addActionListener(new MyActionListener());

		selectBtn.setBounds(1300, 10, 120, 40);
		selectBtn.addActionListener(new SelectRankListener());

		tempField.setBounds(1215, 10, 80, 40);

		add(add);
		add(compare);
		add(ranking);
		add(homeBtn);
		add(selectBtn);
		add(tempField);

		myRevalidate();
	}

	// 나머지 버튼 액션 처리
	class MyActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			// back to main 버튼
			if (e.getSource() == homeBtn) {
				win.change("startp");
				cnt = 2;

				resetAll();
				rankingList.clear();
			}
			// add 버튼 누르면 keyword 입력칸 추가하기
			else if (e.getSource() == add) {
				kil[cnt].setVisible(true);
				ktf[cnt].setVisible(true);
				ril[cnt].setVisible(true);
				rtf[cnt].setVisible(true);
				rsb[cnt].setVisible(true);
				cnt++;
			}
			// compare 버튼 누르면 트렌드 간 비교해주기
			else if (e.getSource() == compare) {
				ArrayList<String> strList = new ArrayList<>();
				for (KeywordTextField k : ktf) {
					strList.add(k.getText());
				}
				CT = new Compare_Trends(strList, rtf, cnt, rankingList);
			}
		}

	}

	class SelectRankListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			// String keyword = rtf[((RankImageBtn) (e.getSource())).index].getText();

			restList = union.restInfoPanel.sw.storeinfo;
			cafeList = union.cafeInfoPanel.sw.storeinfo;
			cultureList = union.cultureInfoPanel.sw.storeinfo;

			win.change("place_check");
		}
	}

	// 검색 버튼 액션 처리
	class SearchActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String str = rtf[((RankSearchBtn) (e.getSource())).index].getText();
			keyword = str;

			tempField.setText(str);

			addUnion();
			myRevalidate();
		}
	}

	// 추가된 패널 지우기
	public void resetAll() {
		for (int i = 2; i < 5; i++) {
			kil[i].setVisible(false);
			ktf[i].setVisible(false);
			ril[i].setVisible(false);
			rsb[i].setVisible(false);
			rtf[i].setVisible(false);
		}
	}

	// Union 객체 붙이기
	public void addUnion() {
		if (union != null) {
			this.remove(union);
			revalidate();
		}

		union = new Union(keyword);
		union.setBackground(Color.white);
		union.setBounds(725, 390, 750, 450);

		this.add(union);
	}

	// 새로고침 메소드
	public void myRevalidate() {
		revalidate();
	}
}

/* J컴포넌트 클래스 분리 */
class KeywordImageLabel extends JLabel {
	KeywordImageLabel(String number) {
		ImageIcon img = new ImageIcon("img/keyword" + number + ".png");
		this.setIcon(img);
	}
}

class KeywordTextField extends JTextField {
	KeywordTextField(Color color) {
		Border lineBorder = BorderFactory.createLineBorder(color, 3);
		this.setBorder(lineBorder);
	}
}

class RankImageBtn extends JLabel {
	int index;

	RankImageBtn(String number, int index) {
		this.index = index;
		ImageIcon img = new ImageIcon("img/rank" + number + ".png");
		this.setIcon(img);
	}
}

class RankSearchBtn extends JButton {
	int index;

	RankSearchBtn(int index) {
		ImageIcon search = new ImageIcon("img/search.png");
		setIcon(search);
		this.index = index;
	}
}

class RankTextField extends JTextField {
	RankTextField(Color color) {
		Border lineBorder = BorderFactory.createLineBorder(color, 3);
		this.setBorder(lineBorder);
	}
}