package itg.ch5;

import sun.security.provider.Sun;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Main {
    private static Subject proxySubject;

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("dynamicSubject");
        jFrame.setSize(500, 400);
        jFrame.setLocation(400,400);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        jFrame.pack();
        JPanel jPanel = new JPanel();
        jFrame.add(jPanel);
        placeComponents(jPanel);
        dynamic();
        jFrame.setVisible(true);
    }


    public static void placeComponents(JPanel panel) {
        JButton jButton = new JButton("点击");
        jButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                proxySubject.request();
            }
        });
        panel.add(jButton);
    }

    private static void dynamic() {
        Subject rs = new RealSubject();
        InvocationHandler ds = new DynamicSubject(rs);
        proxySubject = (Subject) Proxy.newProxyInstance(rs.getClass().getClassLoader(), new Class<?>[]{Subject.class}, ds);
    }
}
