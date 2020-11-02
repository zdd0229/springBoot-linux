package com.z.designpatterns.create.singleton.example.bajie;

import javax.swing.*;
import java.awt.*;

public class Bajie extends JPanel {

    private static final Bajie bajieImg = new Bajie();

    private Bajie() {
        JLabel label = new JLabel(new ImageIcon("src/main/resources/static/img/Bajie.jpg"));
        this.add(label);
    }

    public static Bajie getInstance() {
        return bajieImg;
    }

    public static void main(String[] args) {

        JFrame jf = new JFrame("饿汉单例模式测试");
        jf.setLayout(new GridLayout(1, 2));
        Container contentPane = jf.getContentPane();
        Bajie obj1 = Bajie.getInstance();
        contentPane.add(obj1);
        Bajie obj2 = Bajie.getInstance();
        contentPane.add(obj2);
        if (obj1 == obj2) {
            System.out.println("他们是同一人！");
        } else {
            System.out.println("他们不是同一人！");
        }
        jf.pack();
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
