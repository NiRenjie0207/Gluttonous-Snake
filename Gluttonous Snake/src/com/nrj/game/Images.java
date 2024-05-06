package com.nrj.game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class Images {
    public static URL bodyUrl = Images.class.getResource("/images/body.png");
    public static ImageIcon bodyImg = new ImageIcon(bodyUrl);

    public static URL downUrl = Images.class.getResource("/images/down.png");
    public static ImageIcon downImg = new ImageIcon(downUrl);

    public static URL foodUrl = Images.class.getResource("/images/food.png");
    public static ImageIcon foodImg = new ImageIcon(foodUrl);

    public static URL headerUrl = Images.class.getResource("/images/header.png");
    public static ImageIcon headerImg;

    static {
        try {
            headerImg = new ImageIcon(ImageIO.read(headerUrl).
                    getScaledInstance(675, 80, Image.SCALE_SMOOTH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static URL leftUrl = Images.class.getResource("/images/left.png");
    public static ImageIcon leftImg = new ImageIcon(leftUrl);

    public static URL rightUrl = Images.class.getResource("/images/right.png");
    public static ImageIcon rightImg = new ImageIcon(rightUrl);

    public static URL upUrl = Images.class.getResource("/images/up.png");
    public static ImageIcon upImg = new ImageIcon(upUrl);
}
