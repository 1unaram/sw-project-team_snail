package final_project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;

public class Place_Check extends JPanel implements ActionListener {
    Dialog dialog_res[] = new Dialog[5];
    Dialog dialog_cafe[] = new Dialog[5];
    Dialog dialog_play[] = new Dialog[5];

    JFrame f = new JFrame();

    JPanel p1 = new JPanel();
    JPanel p1_center = new JPanel();

    JPanel p2_rast = new JPanel();
    JPanel p2_cafe = new JPanel();
    JPanel p2_play = new JPanel();

    // 타겟 패널들
    CategoryPanel p3_rest;
    CategoryPanel p3_cafe;
    CategoryPanel p3_play;

    JPanel p3_center_rest = new JPanel();
    JPanel p3_east_rest = new JPanel();

    JPanel p3_center_cafe = new JPanel();
    JPanel p3_east_cafe = new JPanel();

    JPanel p3_center_play = new JPanel();
    JPanel p3_east_play = new JPanel();

    JButton but_finish = new JButton("FINISH");

    // 카테고리 버튼
    MyButton but_cat1;
    MyButton but_cat2;
    MyButton but_cat3;

    JButton[] place_rest = new JButton[5];
    JButton[] place_cafe = new JButton[5];
    JButton[] place_play = new JButton[5];

    JLabel[] mapimage = new JLabel[5];

    NaverMap[] map_rast = new NaverMap[5];
    NaverMap[] map_cafe = new NaverMap[5];
    NaverMap[] map_play = new NaverMap[5];

    String str1;
    String str2;
    String str3;

    ButtonGroup group1 = new ButtonGroup();
    JRadioButton radio1 = new JRadioButton();
    JRadioButton radio2 = new JRadioButton();
    JRadioButton radio3 = new JRadioButton();
    JRadioButton radio4 = new JRadioButton();
    JRadioButton radio5 = new JRadioButton();

    ButtonGroup group2 = new ButtonGroup();
    JRadioButton radio11 = new JRadioButton();
    JRadioButton radio22 = new JRadioButton();
    JRadioButton radio33 = new JRadioButton();
    JRadioButton radio44 = new JRadioButton();
    JRadioButton radio55 = new JRadioButton();

    ButtonGroup group3 = new ButtonGroup();
    JRadioButton radio111 = new JRadioButton();
    JRadioButton radio222 = new JRadioButton();
    JRadioButton radio333 = new JRadioButton();
    JRadioButton radio444 = new JRadioButton();
    JRadioButton radio555 = new JRadioButton();

    StoreInfo fin_1;
    StoreInfo fin_2;
    StoreInfo fin_3;

    ArrayList<StoreInfo> restList;
    ArrayList<StoreInfo> cafeList;
    ArrayList<StoreInfo> cultureList;

    Place_Check(ArrayList<StoreInfo> restList, ArrayList<StoreInfo> cafeList, ArrayList<StoreInfo> cultureList) {
        this.restList = restList;
        this.cafeList = cafeList;
        this.cultureList = cultureList;

        setMyButton();

        Color color = new Color(126, 173, 84);

        this.add(p1);

        this.add(p2_rast);
        this.add(p2_cafe);
        this.add(p2_play);
        this.setLayout(null);

        // p1
        // category & finish
        p1.setBackground(color);
        p1.setLayout(new BorderLayout());
        p1.setBorder(new LineBorder(Color.white, 2));
        p1.setBounds(0, 0, 1440, 50);

        p1_center.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 5));
        p1_center.setBackground(color);

        but_finish.setPreferredSize(new Dimension(110, 40));

        p1_center.add(but_cat1);
        p1_center.add(but_cat2);
        p1_center.add(but_cat3);

        p1.add(p1_center, BorderLayout.CENTER);
        p1.add(but_finish, BorderLayout.EAST);

        // p2_rast map panel
        p2_rast.setBackground(color);
        p2_rast.setLayout(new BorderLayout());
        p2_rast.setBorder(new LineBorder(Color.white, 2));
        p2_rast.setBounds(0, 50, 1000, 750);

        p2_cafe.setBackground(color);
        p2_cafe.setLayout(new BorderLayout());
        p2_cafe.setBorder(new LineBorder(Color.white, 2));
        p2_cafe.setBounds(0, 50, 1000, 750);

        p2_play.setBackground(color);
        p2_play.setLayout(new BorderLayout());
        p2_play.setBorder(new LineBorder(Color.white, 2));
        p2_play.setBounds(0, 50, 1000, 750);

        // p3
        p3_rest = new CategoryPanel("rest");
        p3_cafe = new CategoryPanel("cafe");
        p3_play = new CategoryPanel("play");

        this.add(p3_rest);
        this.add(p3_cafe);
        this.add(p3_play);

        p3_center_rest.setLayout(new GridLayout(5, 1));
        p3_center_rest.setBackground(Color.white);
        p3_east_rest.setLayout(new GridLayout(5, 1));
        p3_east_rest.setBackground(Color.white);

        p3_center_cafe.setLayout(new GridLayout(5, 1));
        p3_center_cafe.setBackground(Color.white);
        p3_east_cafe.setLayout(new GridLayout(5, 1));
        p3_east_cafe.setBackground(Color.white);

        p3_center_play.setLayout(new GridLayout(5, 1));
        p3_center_play.setBackground(Color.white);
        p3_east_play.setLayout(new GridLayout(5, 1));
        p3_east_play.setBackground(Color.white);

        // rest
        Marker[] markers_rest = new Marker[5];

        int restIndex = 0;
        for (StoreInfo s : restList) {
            markers_rest[restIndex] = new Marker("mid", s.mapx, s.mapy, "n", restIndex + 1);
            restIndex++;
        }

        NaverMap map1 = new NaverMap("vhj6gj3o1t", "obFecuAlLF4uD1wByfjI8ymkm8j0lGfQLMI0WKB4", 900, 600, markers_rest);
        JLabel mapLabel1 = new JLabel(new ImageIcon(map1.mapImg));
        p2_rast.add(mapLabel1, BorderLayout.CENTER);

        for (int i = 0; i < 5; i++) {
            map_rast[i] = new NaverMap("vhj6gj3o1t", "obFecuAlLF4uD1wByfjI8ymkm8j0lGfQLMI0WKB4", 500, 500,
                    markers_rest[i]);
            mapimage[i] = new JLabel(new ImageIcon(map_rast[i].mapImg));
        }

        group1.add(radio1);
        group1.add(radio2);
        group1.add(radio3);
        group1.add(radio4);
        group1.add(radio5);
        radio1.addActionListener(this);
        radio2.addActionListener(this);
        radio3.addActionListener(this);
        radio4.addActionListener(this);
        radio5.addActionListener(this);
        p3_east_rest.add(radio1);
        p3_east_rest.add(radio2);
        p3_east_rest.add(radio3);
        p3_east_rest.add(radio4);
        p3_east_rest.add(radio5);

        for (int i = 0; i < 5; i++) {

            place_rest[i] = new JButton();
            place_rest[i].setBorder(new LineBorder(color, 2, true));
            place_rest[i].setBackground(Color.white);
            place_rest[i].setForeground(color);
            place_rest[i].setFont(new Font("SansSerif", Font.BOLD, 20));

            p3_center_rest.add(place_rest[i]);
        }

        int rest = 0;
        for (StoreInfo s : restList) {
            place_rest[rest].setText("<html>" + Integer.toString(rest + 1) + ". " + s.title + "</html>");
            dialog_res[rest] = new Dialog(f, place_rest[rest].getText(), mapimage[rest], s);
            rest++;

        }

        place_rest[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dialog_res[0].setVisible(true);
            }
        });

        place_rest[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dialog_res[1].setVisible(true);
            }
        });

        place_rest[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dialog_res[2].setVisible(true);
            }
        });
        place_rest[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dialog_res[3].setVisible(true);
            }
        });

        place_rest[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dialog_res[4].setVisible(true);
            }
        });

        // cafe
        Marker[] markers_cafe = new Marker[5];

        int cafeIndex = 0;
        for (StoreInfo s : cafeList) {
            markers_cafe[cafeIndex] = new Marker("mid", s.mapx, s.mapy, "n", cafeIndex + 1);
            cafeIndex++;
        }

        NaverMap map2 = new NaverMap("vhj6gj3o1t", "obFecuAlLF4uD1wByfjI8ymkm8j0lGfQLMI0WKB4", 900, 600, markers_cafe);
        JLabel mapLabel2 = new JLabel(new ImageIcon(map2.mapImg));
        p2_cafe.add(mapLabel2, BorderLayout.CENTER);

        for (int i = 0; i < 5; i++) {
            map_cafe[i] = new NaverMap("vhj6gj3o1t", "obFecuAlLF4uD1wByfjI8ymkm8j0lGfQLMI0WKB4", 500, 500,
                    markers_cafe[i]);
            mapimage[i] = new JLabel(new ImageIcon(map_cafe[i].mapImg));
        }

        group2.add(radio11);
        group2.add(radio22);
        group2.add(radio33);
        group2.add(radio44);
        group2.add(radio55);
        radio11.addActionListener(this);
        radio22.addActionListener(this);
        radio33.addActionListener(this);
        radio44.addActionListener(this);
        radio55.addActionListener(this);
        p3_east_cafe.add(radio11);
        p3_east_cafe.add(radio22);
        p3_east_cafe.add(radio33);
        p3_east_cafe.add(radio44);
        p3_east_cafe.add(radio55);

        for (int i = 0; i < 5; i++) {

            place_cafe[i] = new JButton();

            place_cafe[i].setBorder(new LineBorder(color, 2, true));
            place_cafe[i].setBackground(Color.white);
            place_cafe[i].setForeground(color);
            place_cafe[i].setFont(new Font("SansSerif", Font.BOLD, 20));

            p3_center_cafe.add(place_cafe[i]);
        }

        int cafe = 0;
        for (StoreInfo s : cafeList) {
            place_cafe[cafe].setText("<html>" + Integer.toString(cafe + 1) + ". " + s.title + "</html>");
            dialog_cafe[cafe] = new Dialog(f, place_cafe[cafe].getText(), mapimage[cafe], s);
            cafe++;

        }

        place_cafe[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dialog_cafe[0].setVisible(true);
            }
        });

        place_cafe[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dialog_cafe[1].setVisible(true);
            }
        });

        place_cafe[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dialog_cafe[2].setVisible(true);
            }
        });

        place_cafe[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dialog_cafe[3].setVisible(true);
            }
        });

        place_cafe[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dialog_cafe[4].setVisible(true);
            }
        });

        // play
        Marker[] markers_play = new Marker[5];

        int playIndex = 0;
        for (StoreInfo s : restList) {
            markers_play[playIndex] = new Marker("mid", s.mapx, s.mapy, "n", playIndex + 1);
            playIndex++;
        }

        NaverMap map3 = new NaverMap("vhj6gj3o1t", "obFecuAlLF4uD1wByfjI8ymkm8j0lGfQLMI0WKB4", 900, 600, markers_play);
        JLabel mapLabel3 = new JLabel(new ImageIcon(map3.mapImg));
        p2_play.add(mapLabel3, BorderLayout.CENTER);

        for (int i = 0; i < 5; i++) {
            map_play[i] = new NaverMap("vhj6gj3o1t", "obFecuAlLF4uD1wByfjI8ymkm8j0lGfQLMI0WKB4", 500, 500,
                    markers_play[i]);
            mapimage[i] = new JLabel(new ImageIcon(map_play[i].mapImg));
        }

        group3.add(radio111);
        group3.add(radio222);
        group3.add(radio333);
        group3.add(radio444);
        group3.add(radio555);
        radio111.addActionListener(this);
        radio222.addActionListener(this);
        radio333.addActionListener(this);
        radio444.addActionListener(this);
        radio555.addActionListener(this);
        p3_east_play.add(radio111);
        p3_east_play.add(radio222);
        p3_east_play.add(radio333);
        p3_east_play.add(radio444);
        p3_east_play.add(radio555);

        for (int i = 0; i < 5; i++) {

            place_play[i] = new JButton();

            place_play[i].setBorder(new LineBorder(color, 2, true));
            place_play[i].setBackground(Color.white);
            place_play[i].setForeground(color);
            place_play[i].setFont(new Font("SansSerif", Font.BOLD, 20));

            p3_center_play.add(place_play[i]);
        }

        // 이따 정보 들어오면 for-each 구문으로 변경

        int cul = 0;
        for (StoreInfo s : cultureList) {
            place_play[cul].setText("<html>" + Integer.toString(cul + 1) + ". " + s.title + "</html>");
            dialog_play[cul] = new Dialog(f, place_play[cul].getText(), mapimage[cul], s);
            cul++;

        }

        place_play[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dialog_play[0].setVisible(true);
            }
        });

        place_play[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dialog_play[1].setVisible(true);
            }
        });

        place_play[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dialog_play[2].setVisible(true);
            }
        });

        place_play[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dialog_play[3].setVisible(true);
            }
        });

        place_play[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dialog_play[4].setVisible(true);
            }
        });

        but_finish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Marker[] final_markers = new Marker[3];

                Marker final_1 = new Marker("mid", fin_1.mapx, fin_1.mapy, "n", 1);
                Marker final_2 = new Marker("mid", fin_2.mapx, fin_2.mapy, "n", 2);
                Marker final_3 = new Marker("mid", fin_3.mapx, fin_3.mapy, "n", 3);

                final_markers[0] = final_1;
                final_markers[1] = final_2;
                final_markers[2] = final_3;

                NaverMap final_map = new NaverMap("vhj6gj3o1t", "obFecuAlLF4uD1wByfjI8ymkm8j0lGfQLMI0WKB4", 300, 300,
                        final_markers);
                JLabel final_mapLabel = new JLabel(new ImageIcon(final_map.mapImg));
                ListDialog listdialog = new ListDialog(f, final_mapLabel, fin_1, fin_2, fin_3);
                listdialog.setVisible(true);

            }
        });

        this.setSize(1440, 800);
        this.setVisible(true);
    }

    public void setMyButton() {
        but_cat1 = new MyButton("rastaurant");
        but_cat2 = new MyButton("cafe");
        but_cat3 = new MyButton("play");

        // rest button
        but_cat1.addActionListener(new MyButtonListener());
        // cafe button
        but_cat2.addActionListener(new MyButtonListener());
        // play button
        but_cat3.addActionListener(new MyButtonListener());
    }

    public void actionPerformed(ActionEvent e) {
        if (radio1.isSelected()) {
            fin_1 = restList.get(0);

        } else if (radio2.isSelected()) {
            fin_1 = restList.get(1);
        } else if (radio3.isSelected()) {
            fin_1 = restList.get(2);
        } else if (radio4.isSelected()) {
            fin_1 = restList.get(3);
        } else if (radio5.isSelected()) {
            fin_1 = restList.get(4);
        }

        if (radio11.isSelected()) {
            fin_2 = cafeList.get(0);

        } else if (radio22.isSelected()) {
            fin_2 = cafeList.get(1);
        } else if (radio33.isSelected()) {
            fin_2 = cafeList.get(2);
        } else if (radio44.isSelected()) {
            fin_2 = cafeList.get(3);
        } else if (radio55.isSelected()) {
            fin_2 = cafeList.get(4);
        }

        if (radio111.isSelected()) {
            fin_3 = cultureList.get(0);
        } else if (radio222.isSelected()) {
            fin_3 = cultureList.get(1);
        } else if (radio333.isSelected()) {
            fin_3 = cultureList.get(2);
        } else if (radio444.isSelected()) {
            fin_3 = cultureList.get(3);
        } else if (radio555.isSelected()) {
            fin_3 = cultureList.get(4);
        }

    }

    class MyButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // 음식점 버튼 처리
            if (e.getSource() == but_cat1) {
                p2_rast.setVisible(true);
                p2_cafe.setVisible(false);
                p2_play.setVisible(false);

                p3_rest.setVisible(true);
                p3_cafe.setVisible(false);
                p3_play.setVisible(false);
            }
            // 카페 버튼 처리
            else if (e.getSource() == but_cat2) {
                p2_rast.setVisible(false);
                p2_cafe.setVisible(true);
                p2_play.setVisible(false);

                p3_rest.setVisible(false);
                p3_cafe.setVisible(true);
                p3_play.setVisible(false);
            }
            // 문화예술 버튼 처리
            else if (e.getSource() == but_cat3) {
                p2_rast.setVisible(false);
                p2_cafe.setVisible(false);
                p2_play.setVisible(true);

                p3_rest.setVisible(false);
                p3_cafe.setVisible(false);
                p3_play.setVisible(true);
            }
        }
    }

    class MyButton extends JButton {
        MyButton(String title) {
            this.setText(title);
            this.setPreferredSize(new Dimension(110, 40));
        }
    }

    class CategoryPanel extends JPanel {

        // 카테고리 변수
        String category;

        CategoryPanel(String category) {
            this.category = category;
            this.setLayout(new BorderLayout());
            this.setBorder(new LineBorder(Color.white, 2));
            this.setBounds(1000, 50, 440, 750);

            putPanel();

        }

        public void putPanel() {
            if (this.category.equals("rest")) {
                this.add(p3_center_rest, BorderLayout.CENTER);
                this.add(p3_east_rest, BorderLayout.EAST);
            } else if (this.category.equals("cafe")) {
                this.add(p3_center_cafe, BorderLayout.CENTER);
                this.add(p3_east_cafe, BorderLayout.EAST);
            } else {
                this.add(p3_center_play, BorderLayout.CENTER);
                this.add(p3_east_play, BorderLayout.EAST);
            }
        }
    }
}