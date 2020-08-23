package com.jugbub;

import javax.print.attribute.standard.PDLOverrideSupported;
import java.util.ArrayList;
import java.util.Arrays;

public class Polygon {
    ArrayList<Line> lines = new ArrayList<>();
    ArrayList<Vertex> vertices = new ArrayList<>();

    float x,y;


    Polygon(Line a,Line b,Line c){
        this.lines.add(a);
        this.lines.add(b);
        this.lines.add(c);
        this.setVertices();
        this.setPos();
    }

    private void setVertices(){
        this.vertices.addAll(Arrays.asList(this.lines.get(0).vertices));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                if(!(this.vertices.contains(this.lines.get(i).vertices[j])))
                    this.vertices.add(this.lines.get(i).vertices[j]);
            }
        }
    }

    private void setPos(){
        this.setX();
        this.setY();


    }

    private void setX(){
        float sum = 0f;

        for (int i = 0; i < 3; i++) {
            sum += this.vertices.get(i).x;
        }
        this.x = sum/3;
    }

    private void setY(){
        float sum = 0f;

        for (int i = 0; i < 3; i++) {
            sum += this.vertices.get(i).y;
        }
        this.y = sum/3;
    }

    private Line closestLine(Pixel p){

        float a = p.y - this.lines.get(0).function(p.x);
        float b = p.y - this.lines.get(1).function(p.x);
        float c = p.y - this.lines.get(2).function(p.x);

        float min = Math.min(Math.min(a, b), Math.min(b, c));

        if ((min == a))
            return this.lines.get(0);
        else if ((min == b))
            return this.lines.get(1);
        else
            return this.lines.get(2);
    }

    public boolean isWithin(Pixel p){
        Line line = this.closestLine(p);

        float pDistance = pyth(p.x-this.x,p.y-this.y);
        float lineDistance = pyth(p.x-this.x,this.x-line.function(p.x));

        if(pDistance <= lineDistance)
            return true;

        return false;
    }

    private static float pyth(float a, float b){
        a = (float) Math.pow(a,2);
        b = (float) Math.pow(b,2);

        return (float) Math.sqrt(a+b);
    }

}
