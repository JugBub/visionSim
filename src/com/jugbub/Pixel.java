package com.jugbub;

import javax.imageio.ImageIO;
import java.awt.*;
import java.util.ArrayList;

public class Pixel {
    boolean visible = true;
    Color visibleColor = new Color(160, 194, 82);
    Color hiddenColor = new Color(20, 60, 173);

    int x,y,size;

    Pixel(int x, int y, int size){
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public void paint(Graphics g,Pixel p) {
        if(p.visible)
            g.setColor(visibleColor);
        else
            g.setColor(hiddenColor);
        g.fillRect(x, y, size, size);
    }
}
