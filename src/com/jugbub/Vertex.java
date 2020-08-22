package com.jugbub;

import java.util.ArrayList;

public class Vertex {
    float x,y;
    ArrayList<Line> lines = new ArrayList<>();

    Vertex(float x, float y){
        this.x = x;
        this.y = y;
    }
}
