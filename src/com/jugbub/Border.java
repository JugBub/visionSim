package com.jugbub;

import java.util.ArrayList;

public class Border {
    ArrayList<Vertex> vertices = new ArrayList<>();
    ArrayList<Line> lines = new ArrayList<>();

    Border(int r){
        this.vertices.add(new Vertex(r,r));
        this.vertices.add(new Vertex(-r,r));
        this.vertices.add(new Vertex(r,-r));
        this.vertices.add(new Vertex(-r,-r));

        this.lines.add(new Line(this.vertices.get(0),this.vertices.get(1)));
        this.lines.add(new Line(this.vertices.get(1),this.vertices.get(2)));
        this.lines.add(new Line(this.vertices.get(2),this.vertices.get(3)));
        this.lines.add(new Line(this.vertices.get(3),this.vertices.get(0)));
    }
}
