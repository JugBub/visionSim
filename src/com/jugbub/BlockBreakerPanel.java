package com.jugbub;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class BlockBreakerPanel extends JPanel implements KeyListener {



    ArrayList<Pixel> pixels;
    Player player = new Player(50,50, 10);
    ArrayList<Vertex> vertices = new ArrayList<>();
    ArrayList<Line> lines = new ArrayList<>();
    boolean init = true;

/*
    ArrayList<Block> blocks = new ArrayList<>();
    Block ball = new Block(237,435,25,25,"mums.png");
    Block paddle = new Block(175,480,150,25,"mums.png");
    //Animate animate;
*/

    BlockBreakerPanel(){


        addKeyListener(this);
        setFocusable(true);
    }

    private ArrayList<Pixel> initPixels(int screenW,int screenH,int size){
        ArrayList<Pixel> pixels = new ArrayList<>();

        for (int i = 0; i < screenW; i++) {
            for (int j = 0; j < screenH; j++) {
                //System.out.println("i:"+i+"j:"+j);
                pixels.add(new Pixel(i,j,i*size,j*size,size));
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

    private void updatePixels(){

        for (int i = 0; i < this.pixels.size(); i++) {

            //check if same pos as player
            if(this.pixels.get(i).x == this.player.x && this.pixels.get(i).y == this.player.y)
                this.pixels.get(i).hasPlayer = true;

        }

    }

    private void resetPixels(){

        for (int i = 0; i < this.pixels.size(); i++) {
            this.pixels.get(i).visible = true;
            if(this.pixels.get(i).hasPlayer){
                if(!(this.pixels.get(i).x == this.player.x) || !(this.pixels.get(i).y == this.player.y))
                    this.pixels.get(i).hasPlayer = false;
            }else {
                //check if same pos as player
                if(this.pixels.get(i).x == this.player.x && this.pixels.get(i).y == this.player.y)
                    this.pixels.get(i).hasPlayer = true;
            }

        }

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

        if(this.init) {
            this.pixels = initPixels(100, 100, 5);
            this.init = false;
        }
        this.resetPixels();
        //this.updatePixels();
        this.pixels.forEach((n) -> n.paint(g,n));
    }

    public void update() {
        System.out.println(this.player.x + "  " + this.player.y);
        this.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            new Thread(() -> {
                while (true){

                    this.update();
                    System.out.println("running");
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException err) {
                        err.printStackTrace();
                    }
                }
            }).start();
        }
        this.movement(e);

    }

    private void movement(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_D){
            this.player.x = (100 +(this.player.x + 1)) % 100;
        }if(e.getKeyCode() == KeyEvent.VK_A){
            this.player.x = (100 +(this.player.x - 1)) % 100;
        }if(e.getKeyCode() == KeyEvent.VK_W){
            this.player.y = (100 +(this.player.y - 1)) % 100;
        }if(e.getKeyCode() == KeyEvent.VK_S){
            this.player.y = (100 +(this.player.y + 1)) % 100;
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
