package com.nrj.game;

import javax.swing.*;
import java.awt.*;

public class StartGame {
    public static void main(String[] args) {
        // create a frame
        JFrame jf = new JFrame();

        // set a title for the frame
        jf.setTitle("Gluttonous Snake by Renjie");

        // get the width and height of the screen
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;

        // set the location and the size of the frame
        jf.setBounds((width-700)/2, (height-700)/2, 710, 695);

        // fix the size of the frame
        jf.setResizable(false);

        // set closing the frame as a close operation
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // show the frame(it is invisible by default)
        jf.setVisible(true);

        // create a game panel and add it into the frame
        GamePanel gp = new GamePanel();
        jf.add(gp);

        Dimension size = jf.getSize();

    }
}
