package net.intt.stock.desktop;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Gui{

    private static Gui instance;

    public static Gui getInstance() {
        if (instance == null) {
            synchronized (Gui.class) {
                instance = new Gui();
            }
        }
        return instance;
    }

    public void error() {
        JFrame f = new JFrame();

        f.setSize(300, 150);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setTitle("error");

        JLabel label = new JLabel("This OS has not support");
        label.setBounds(75, 25, 300, 20);

        f.getContentPane().setLayout(null);

        JButton button = new JButton("close");
        button.setBounds(115, 75, 75, 25);
        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        f.add(label);
        f.add(button);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    public void test() {
        JFrame f = new JFrame();

        f.setSize(300, 150);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setTitle("test");

        JLabel label = new JLabel("only test");
        label.setBounds(75, 25, 300, 20);

        f.getContentPane().setLayout(null);

        JButton button = new JButton("close");
        button.setBounds(115, 75, 75, 25);
        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        f.add(label);
        f.add(button);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
