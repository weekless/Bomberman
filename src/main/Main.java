package main;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setTitle("JBomberman"); // set title
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE); // close window when hitting exit button
        frame.setResizable(false); // not resizable

        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);

        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        gamePanel.startGameThread();

    }
}