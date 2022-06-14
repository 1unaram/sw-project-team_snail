package final_project;

import java.awt.Container;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;

class Naver_GUI extends JFrame {

	Startp startp;
	Keywordp keywordp;
	Place_Check place_check;

	Container container = getContentPane();

	Naver_GUI() {
		// 순위 매긴후 순위별로 0부터 저장된 트랜드 키워드 리스트

		// JFrame 설정
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.getImage("img/Naver_logo.png");
		this.setIconImage(img);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(1440, 800);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
		this.setTitle("Naver Trend Search API");


		// 컴포넌트 붙이기
		init();

		// 새로고침
		revalidate();
	}

	public void init() {
		ArrayList<String> rankingList = new ArrayList<String>();

		this.startp = new Startp(this);
		this.keywordp = new Keywordp(this, rankingList);

		container.add(this.startp);
	}

	// 버튼 누를 때 화면 전환해주는 함수
	public void change(String panelName) {
		container.removeAll();
		if (panelName.equals("keywordp")) {
			container.add(keywordp);
		} else if (panelName.equals("startp")) {
			container.add(startp);
		} else if (panelName.equals("place_check")) {
			place_check = new Place_Check(keywordp.restList, keywordp.cafeList, keywordp.cultureList);
			container.add(place_check);
		}

		revalidate();
		repaint();
	}

}