package com.jugbub;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Inet4Address;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {
        JFrame frame  = new JFrame("Test");

        BlockBreakerPanel panel = new BlockBreakerPanel();

        frame.getContentPane().add(panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //System.out.println((new Line(new Vertex(1, 1), new Vertex(2, 2))));
        //System.out.println((new Line(new Vertex(1, 1), new Vertex(2, 2)).vertices[0]));

        createLevel(panel);

        frame.setUndecorated(true);

        frame.setVisible(true);
        vertexMenu(panel);
        lineMenu(panel);

        float test = 1F;

        for (int i = 0; i <= 16; i++) {
            System.out.print(String.format("%.16f",test/Math.pow(10,i)));
            System.out.println(":" + i);
        }

        /*float n = 78.7232345677927645F;
        float fakeN = 0F;
        float lowN = 0F;
        float highN = 100F;
        int i = 0;

        while (true) {
            if(fakeN < n) {
                lowN = fakeN;
                fakeN = (lowN + highN) / 2;
            }else if (fakeN > n){
                highN = fakeN;
                fakeN = (lowN + highN) / 2;
            }else
                break;
            i++;
            //System.out.println(n);
        }//////implement this!!!*/




        //System.out.println(i);

        //System.out.println(Line.doTheyMeet());
        Border border = new Border(3);

        Ray ray = new Ray(/*panel.vertices.get(0)*/ new Vertex(0.1f,1f));

        System.out.println("Intersect With Border: " + border.rayIntersect(ray).x + " " + border.rayIntersect(ray).y);

        System.out.println("ray result (y == 2): " + ray.yFunction(3));

        //System.out.println( panel.pixels.get(panel.pixels.size()-1).getX() + " " +  panel.pixels.get(panel.pixels.size()-1).getY());
        frame.setSize(/*(int)(panel.pixels.get(panel.pixels.size()-1).x),(int)panel.pixels.get(panel.pixels.size()-1).y*/ 500,500);
        //System.out.println(frame.getSize());

        frame.setResizable(true);
        //Line line = createLine(new Vertex(1,1),new Vertex(2,2),panel);
        //System.out.println(Line.doesItHit(line, 1.0012f, 1.0012f));
    }

    public static void createLevel(BlockBreakerPanel panel){
        for (int i = 0; i < 10; i++) {
            panel.vertices.add(new Vertex((int) (Math.random() * 100),(int) (Math.random() * 100)));
        }
        for (int i = 0; i < 9; i++) {
            panel.lines.add(Line.createLine(panel.vertices.get(i), panel.vertices.get(i), panel));
        }
        printLines(panel);
    }

    private static void printLines(BlockBreakerPanel panel){
        for (int i = 0; i < panel.lines.size(); i++) {
            System.out.println("v1: " + panel.lines.get(i).vertices[0].x + " , " + panel.lines.get(i).vertices[0].y + " v2: " + panel.lines.get(i).vertices[1].x + " , " + panel.lines.get(i).vertices[1].y);
        }
    }

    public static void lineMenu(BlockBreakerPanel panel){
        JFrame frame = new JFrame();
        frame.setTitle("Line Menu");
        JComboBox v1ComboBox = new JComboBox();
        JComboBox v2ComboBox = new JComboBox();
        JButton update = new JButton("Update");
        JButton add = new JButton("add");

        panel.vertices.forEach((n) -> v1ComboBox.addItem("X: "+ n.x + "Y: " +n.y));
        panel.vertices.forEach((n) -> v2ComboBox.addItem("X: "+ n.x + "Y: " +n.y));

        v1ComboBox.setBounds(30,0,100,25);
        v2ComboBox.setBounds(30,25,100,25);

        add.setBounds(130,0,100,25);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.lines.add(Line.createLine(panel.vertices.get(v1ComboBox.getSelectedIndex()),panel.vertices.get(v2ComboBox.getSelectedIndex()),panel));
                //panel.lines.add(Line.createLine(panel.vertices.get(v1ComboBox.getSelectedIndex()),panel.vertices.get(v2ComboBox.getSelectedIndex()),panel));
                panel.lines.forEach((n) -> System.out.println(n.vertices[0] + " " + n.vertices[1]));
            }
        });
        update.setBounds(130,25,100,25);
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lineUpdate(panel,frame);
            }
        });

        frame.add(v1ComboBox);
        frame.add(v2ComboBox);
        frame.add(update);
        frame.add(add);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(250,105);
    }

    public static void vertexMenu(BlockBreakerPanel panel){
        JFrame frame = new JFrame();
        frame.setTitle("Vertex Menu");
        JTextField x = new JTextField();
        JTextField y = new JTextField();
        JButton add = new JButton();

        x.setBounds(30,0,50,25);
        y.setBounds(80,0,50,25);

        add.setBounds(130,0,100,25);

        add.setText("add");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.vertices.add(new Vertex(Integer.parseInt(x.getText()),Integer.parseInt(y.getText())));
                x.setText("");
                y.setText("");
            }
        });

        frame.add(x);
        frame.add(y);
        frame.add(add);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(250,80);
    }

    private static void lineUpdate(BlockBreakerPanel panel,JFrame frame){
        frame.dispose();
        lineMenu(panel);
    }
}
