package com.jugbub;

import java.util.ArrayList;

public class Vertex {
    int x,y;
    ArrayList<Line> lines = new ArrayList<>();

    Vertex(int x, int y){
        this.x = x;
        this.y = y;
    }
}
