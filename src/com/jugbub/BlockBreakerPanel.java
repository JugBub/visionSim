package com.jugbub;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class BlockBreakerPanel extends JPanel implements KeyListener {

    ArrayList<Block> blocks = new ArrayList<>();
    Block ball = new Block(237,435,25,25,"mums.png");
    Block paddle = new Block(175,480,150,25,"mums.png");
    ArrayList<Pixel> pixels;
    ArrayList<Vertex> vertices = new ArrayList<>();
    ArrayList<Line> lines = new ArrayList<>();

    //Animate animate;

    BlockBreakerPanel(){


        addKeyListener(this);
        setFocusable(true);
    }

    private ArrayList<Pixel> initPixels(int screenW,int screenH,int size){
        ArrayList<Pixel> pixels = new ArrayList<>();

        for (int i = 0; i < screenW; i++) {
            for (int j = 0; j < screenH; j++) {
                //System.out.println("i:"+i+"j:"+j);
                pixels.add(new Pixel(i*size,j*size,size));
                pixels.get(i*screenH+j).size = size;
                //pixels.get(i*screenH+j).width = size;
                if(i == 0 || j == 99 || j == 0 || i == 99)
                    pixels.get(i*screenH+j).visible=false;
                if(isThereVertexAtPos(vertices,i,j))
                    pixels.get(i*screenH+j).visibleColor = new Color(0,0,0);
            }
        }
        return pixels;
    }

    private static boolean isThereVertexAtPos(ArrayList<Vertex> vertices,int x, int y){

        for (int i = 0; i < vertices.size(); i++) {
            if(vertices.get(i).x == x && vertices.get(i).y == y)
                return true;
        }

        return false;
    }

    public void paintComponent(Graphics g){
        //paddle.draw(g, this);
        pixels = initPixels(100,100,5);
        pixels.forEach((n) -> n.paint(g,n));
    }

    public void update() {
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            new Thread(() -> {
                while (true){
                    update();




                    System.out.println("running");
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException err) {
                        err.printStackTrace();
                    }
                }
            }).start();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public boolean isVertexInList(Vertex vertex){
        if(vertices.contains(vertex))
            return true;
        return false;
    }
}
