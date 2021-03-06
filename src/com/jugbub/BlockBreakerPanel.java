package com.jugbub;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class BlockBreakerPanel extends JPanel implements KeyListener {



    ArrayList<Pixel> pixels;
    Player player;
    ArrayList<Vertex> vertices = new ArrayList<>();
    ArrayList<Line> lines = new ArrayList<>();
    boolean init = true;
    int width,height;
    int pixelSize;
    Polygon polygon;

/*
    ArrayList<Block> blocks = new ArrayList<>();
    Block ball = new Block(237,435,25,25,"mums.png");
    Block paddle = new Block(175,480,150,25,"mums.png");
    //Animate animate;
*/

    BlockBreakerPanel(){
        Line line1 = new Line(new Vertex(10,0),new Vertex(10,30));
        Line line2 = new Line(line1.vertices[1],new Vertex(70,0));
        Line line3 = new Line(line2.vertices[1],line1.vertices[0]);

        this.polygon = new Polygon(line1,line2,line3);

        this.player = new Player(50,50, (float)(Math.min(width,height)*.3));

        addKeyListener(this);
        setFocusable(true);
    }



    private ArrayList<Pixel> initPixels(int width,int height,int size){
        ArrayList<Pixel> pixels = new ArrayList<>();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                pixels.add(new Pixel(i,j,i*size,j*size,size));
                pixels.get(i*height+j).size = size;
                if(isThereVertexAtPos(vertices,i,j))
                    pixels.get(i*height+j).visibleColor = new Color(0,0,0);
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

    private void isPixelVisible(Pixel p,Polygon polygon){
        this.player.border.pixelWithinBorder(p);
        if(!polygon.isWithin(p)) {
            p.visible = false;
        }
    }

    private void resetPixels(Polygon polygon){
        System.out.println("start Pixels");
        this.pixels.forEach((n)-> n.visible=false);
        System.out.println("done Pixels");

/*        System.out.println("start border search");
        //this.player.border.pixelsWithinBorder(this);
        System.out.println("done border search");*/
        System.out.println("start Visibility");
        this.pixels.forEach((n)->this.isPixelVisible(n,this.polygon));
        System.out.println("done Visibility");
/*        for (int i = 0; i < this.pixels.size(); i++) {

            //this.paintPlayer(i);



            System.out.println("start within polygon");

            System.out.println("done within polygon");
            //System.out.println(polygon.isWithin(this.pixels.get(i)));
        }*/

    }

    private void paintBorder(ArrayList<Pixel> visible,int i){
        if (!visible.contains(this.pixels.get(i)))
            this.pixels.get(i).visible = false;
    }

    private void paintPlayer(int i){
        if(this.pixels.get(i).hasPlayer){
            if(!(this.pixels.get(i).x == this.player.x) || !(this.pixels.get(i).y == this.player.y))
                this.pixels.get(i).hasPlayer = false;
        }else {
            //check if same pos as player
            if(this.pixels.get(i).x == this.player.x && this.pixels.get(i).y == this.player.y)
                this.pixels.get(i).hasPlayer = true;
        }
    }

    private static boolean isThereVertexAtPos(ArrayList<Vertex> vertices,int x, int y){

        for (int i = 0; i < vertices.size(); i++) {
            if(vertices.get(i).x == x && vertices.get(i).y == y)
                return true;
        }

        return false;
    }

    private void initBorder(){
        this.player.border.x = this.player.x;
        this.player.border.y = this.player.y;
    }

    private void initGame(){
        this.pixels = initPixels(this.width, this.height, this.pixelSize);
        initBorder();
    }

    public void paintComponent(Graphics g){
        if(this.init) {
            initGame();
            this.init = false;

        }

        this.resetPixels(this.polygon);

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
        this.movement(e);

    }

    public void runGame(){
        new Thread(() -> {
            while (true){

                this.update();
                //System.out.println("running");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException err) {
                    err.printStackTrace();
                }
            }
        }).start();
    }

    private void movement(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_D){
            this.player.x = (100 +(this.player.x + 1)) % 100;
            this.player.border.x = this.player.x;
        }if(e.getKeyCode() == KeyEvent.VK_A){
            this.player.x = (100 +(this.player.x - 1)) % 100;
            this.player.border.x = this.player.x;
        }if(e.getKeyCode() == KeyEvent.VK_W){
            this.player.y = (100 +(this.player.y - 1)) % 100;
            this.player.border.y = this.player.y;
        }if(e.getKeyCode() == KeyEvent.VK_S){
            this.player.y = (100 +(this.player.y + 1)) % 100;
            this.player.border.y = this.player.y;
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
