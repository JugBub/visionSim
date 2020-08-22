package com.jugbub;

import javax.imageio.ImageIO;
import java.awt.*;
import java.util.ArrayList;

public class Pixel {
    boolean visible = true;
    Color visibleColor = new Color(160, 194, 82);
    Color hiddenColor = new Color(20, 60, 173);
    Color playerColor = new Color(255, 4, 4);
    boolean hasPlayer = false;

    int x,y;

    int sizeX,sizeY,size;

    Pixel(int x, int y,int sizeX,int sizeY, int size){
        this.x = x;
        this.y = y;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.size = size;
    }

    public void paint(Graphics g,Pixel p) {
        if(p.hasPlayer)
            g.setColor(playerColor);
        else {
            if (p.visible)
                g.setColor(visibleColor);
            else
                g.setColor(hiddenColor);
        }
        g.fillRect(sizeX,sizeY, size, size);
    }
}
