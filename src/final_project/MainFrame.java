package final_project;

import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainFrame extends JFrame {

    /* 변수 선언 */
    // Container
    Container mainContainer = this.getContentPane();

    MainFrame() {
        setSize(1600, 900);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        init();

        revalidate();
    }

    public void init() {
        // NaverMap map = new NaverMap(Main.clientId, Main.clientSecret, 700, 700,
        // "308518", "545497");
        Marker[] markers = new Marker[3];
        Marker marker1 = new Marker("mid", "308518", "545497", "n", 1);
        Marker marker2 = new Marker("mid", "306967", "546049", "n", 2);
        Marker marker3 = new Marker("mid", "307160", "545426", "n", 3);
        markers[0] = marker1;
        markers[1] = marker2;
        markers[2] = marker3;

        NaverMap map = new NaverMap(Main.clientId, Main.clientSecret, 700, 700, markers);
        JLabel mapLabel = new JLabel(new ImageIcon(map.mapImg));

        mainContainer.add(mapLabel);
    }
}