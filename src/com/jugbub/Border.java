package com.jugbub;

import java.util.ArrayList;

public class Border {
    ArrayList<Vertex> vertices = new ArrayList<>();
    ArrayList<Line> lines = new ArrayList<>();
    float radius;
    int x,y;

    Border(float r){

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

    public ArrayList<Vertex> verticesWithinBorder(BlockBreakerPanel panel){
        ArrayList<Vertex> verticesWithin = new ArrayList<>();
        for (int i = 0; i < panel.vertices.size(); i++) {
            float x = panel.vertices.get(i).x;
            float y = panel.vertices.get(i).y;
            float distance = (float) Math.sqrt((float)  (Math.pow(x-this.x,2))  +   (float)    (Math.pow(y-this.y,2))  );

            if(distance < this.radius)
                verticesWithin.add(panel.vertices.get(i));
        }

        return verticesWithin;
    }
    public void pixelsWithinBorder(BlockBreakerPanel panel){
        for (int i = 0; i < panel.pixels.size(); i++) {
            float x = panel.pixels.get(i).x;
            float y = panel.pixels.get(i).y;
            float distance = (float) Math.sqrt((float)  (Math.pow(x-this.x,2))  +   (float)    (Math.pow(y-this.y,2))  );

            if(distance <= this.radius)
                panel.pixels.get(i).visible = true;
        }
    }

    public void pixelWithinBorder(Pixel p){
        float x = p.x;
        float y =p.y;
        float distance = (float) Math.sqrt((float)  (Math.pow(x-this.x,2))  +   (float)    (Math.pow(y-this.y,2))  );

        if(distance <= this.radius)
            p.visible = true;
    }
}
