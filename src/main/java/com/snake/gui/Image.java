package com.snake.gui;

import javax.swing.*;
import java.awt.*;

public class Image extends javax.swing.JPanel {
    int x, y;

    public Image(JPanel jPanel1) {
        this.x = jPanel1.getWidth();
        this.y = jPanel1.getHeight();
        this.setSize(x, y);
    }

    @Override
    public void paint(Graphics g) {
        ImageIcon Img = new ImageIcon("src/main/resources/images/snake.png");
        g.drawImage(Img.getImage(), 0, 0, 600, 600, null);
    }

}


