package com.jugbub;

import java.util.ArrayList;

public class Border {
    ArrayList<Vertex> vertices = new ArrayList<>();
    ArrayList<Line> lines = new ArrayList<>();
    int radius;
    int x,y;

    Border(int r){

        this.radius = r;

        this.vertices.add(new Vertex(-r,r));
        this.vertices.add(new Vertex(r,r));
        this.vertices.add(new Vertex(r,-r));
        this.vertices.add(new Vertex(-r,-r));

        this.lines.add(new Line(this.vertices.get(0),this.vertices.get(1)));
        this.lines.add(new Line(this.vertices.get(1),this.vertices.get(2)));
        this.lines.add(new Line(this.vertices.get(2),this.vertices.get(3)));
        this.lines.add(new Line(this.vertices.get(3),this.vertices.get(0)));
    }

    public Vertex rayIntersect(Ray ray){
        //int r = this.radius;
        //ArrayList<Vertex> vertices = this.vertices;
        Vertex tl = this.vertices.get(0);
        Vertex tr = this.vertices.get(1);
        Vertex br = this.vertices.get(2);
        Vertex bl = this.vertices.get(3);

        System.out.println("tl: X:" + tl.x + "Y:" + tl.y);
        System.out.println(ray.yFunction(tl.y));

        //hits top
        if((ray.yFunction(tl.y) + this.x) < tr.x && (ray.yFunction(tl.y) + this.x) > tl.x)
            return new Vertex(ray.yFunction(tl.y),tl.y);

        return new Vertex(0,0);
    }
}
