package com.jugbub;

import java.awt.event.MouseAdapter;
import java.nio.file.LinkOption;

public class Line {
    Vertex[] vertices;
    float length;

    Line(Vertex v1, Vertex v2){
        this.vertices = new Vertex[]{v1, v2};
        this.length = (float) Math.sqrt((float)  (Math.pow(v1.x-v2.x,2))   +   (float)  (Math.pow(v1.y-v2.y,2)) );
        //System.out.println(1);
    }

    public static Line createLine(Vertex v1, Vertex v2, BlockBreakerPanel panel){
        Line line = new Line(v1,v2);

        v1.lines.add(line);
        v2.lines.add(line);

        panel.vertices.add(line.vertices[0]);

        if(!panel.isVertexInList(v1)) {
            panel.vertices.add(v1);
        }
        if(!panel.isVertexInList(v2))
            panel.vertices.add(v2);

        return line;
    }

/*    public boolean doesItHit(Ray ray){
        *//*Line tmpLine = new Line(this.vertices[0],this.vertices[1]);
        while (true){
            if(withinRange(tmpLine,ray.x,ray.y)){
                if (inMiddle(tmpLine,ray.x,ray.y))
                    return true;
            }else
                return false;
            tmpLine = subdivide(tmpLine,ray.x,ray.y);
            //System.out.println("Ray == X: "+ray.x+" Y: "+ ray.y);
            //System.out.println("V1 == X:"+tmpLine.vertices[0].x+" Y:"+tmpLine.vertices[0].y);
            //System.out.println("V2 == X:"+tmpLine.vertices[1].x+" Y:"+tmpLine.vertices[1].y);
        }*//*
        int lowN = 0;
        int highN;

        for (int i = 0; i < Math.pow(10,16)*100; i++) {
            if(i == Math.pow(10,16)*100)
                System.out.println("G");
        }
    }*/

    public static boolean doTheyIntersect(Line line1,Line line2){

        float n = 0f;
        float high = 100f;
        float low = 0f;

        float tmpN = n;

        int i = 0;

        while (true){
            i++;
            //System.out.println("X="+n+": " + "function 1: "+ line1.function(n) + "function 2: "+ line2.function(n));
            //System.out.println("constraint: " + checkConstraint(line1, line2, low, high));
            if(checkConstraint(line1, line2, low, high))
                return false;
            if(line1.function(n) > line2.function(n)){
                low = n;
                n  = (high+low)/2;
                //System.out.println(1 + " " + n);
            }else if(line1.function(n) < line2.function(n)){
                high = n;
                n  = (high+low)/2;
                //System.out.println(2 + " " + (high));
            }else {
                //System.out.println(i);
                //System .out.println(n);
                return true;
            }

            //System.out.println(n);
            //System.out.println(tmpN);

            if(tmpN == n) {
                //System.out.println(i);
                return false;
            }
            tmpN = n;
        }
    }

    private static boolean checkConstraint(Line line1, Line line2,float low,float high){
        boolean[] gate = new boolean[2];

        gate[0] = line1.isPastConstraint(low, high);
        //System.out.println("gate 0: " + gate[0]);
        gate[1] = line2.isPastConstraint(low, high);
        //System.out.println("gate 1: " + gate[1]);

        return gate[0] || gate[1];
    }

    private boolean isPastConstraint(float low, float high){
        Vertex bigV = this.bigV();
        Vertex smallV = this.smallV();

        if(bigV.x < low)
            return true;
        if(smallV.x > high)
            return true;

        return false;
    }

    private Vertex bigV(){
        Vertex v1 = this.vertices[0];
        Vertex v2 = this.vertices[1];

        if(v2.x < v1.x)
                return v1;

        return v2;
    }
    private Vertex smallV(){
        Vertex v1 = this.vertices[0];
        Vertex v2 = this.vertices[1];

        if(v2.x>v1.x)
                return v1;

        return v2;
    }


    public float function(float x){
        if(!(x == 0))
            return xFunction(x);
        else
            return yFunction(x);

    }

    public boolean straightUp(){
        if(this.vertices[0].x==this.vertices[1].x)
            return true;
        else
            return false;
    }

    private float xFunction(float x){
        float x1 = this.vertices[0].x;
        float y1 = this.vertices[0].y;
        float x2 = this.vertices[1].x;
        float y2 = this.vertices[1].y;

        float k = ((float)y1-y2)/((float)x1-x2);
        float m =y1-x1*k;
        if(Float.isNaN(k*x+m))
            return this.vertices[0].x;
        return k*x+m;
    }
    private float yFunction(float y){
        float x1 = this.vertices[0].x;
        float y1 = this.vertices[0].y;
        float x2 = this.vertices[1].x;
        float y2 = this.vertices[1].y;

        float k = ((float)(x1-x2))/((float)(y1-y2));
        float m =x1-y1*k;
        System.out.println(y2);
        return k*y+m;
    }

/*    public static boolean withinRange(Line line, int x, int y){
        if(mostLeft(line.vertices)<=x && x<=mostRight(line.vertices))
            if(mostLow(line.vertices)<=y && y<=mostHigh(line.vertices))
                return true;
        return false;
    }

    public static boolean inMiddle(Line line,int x, int y){
        int middleX = (line.vertices[0].x+line.vertices[1].x)/2;
        int middleY = (line.vertices[0].y+line.vertices[1].y)/2;

        return x == middleX && y == middleY;
    }

    public static Line subdivide(Line line,float x, float y){
        Line[] products = new Line[2];
        Vertex median = new Vertex((line.vertices[0].x+line.vertices[1].x)/2,(line.vertices[0].y+line.vertices[1].y)/2);

        products[0] = new Line(line.vertices[0], median);
        products[1] = new Line(line.vertices[1], median);

        if(pyth(line.vertices[0].x-x,line.vertices[0].y-y) < pyth(line.vertices[1].x-x,line.vertices[1].y-y))
            return products[0];
        return products[1];
    }

    private static float mostRight(Vertex[] vertices){
        return Math.max(vertices[0].x, vertices[1].x);
    }
    private static float mostLeft(Vertex[] vertices){
        return Math.min(vertices[0].x, vertices[1].x);
    }
    private static float mostHigh(Vertex[] vertices){
        return Math.max(vertices[0].y, vertices[1].y);
    }
    private static float mostLow(Vertex[] vertices){
        return Math.min(vertices[0].y, vertices[1].y);
    }

    private static float pyth(float a,float b){
        float c;

        c = (float) Math.sqrt(Math.pow(a,2)+Math.pow(b,2));

        return c;
    }*/
}
