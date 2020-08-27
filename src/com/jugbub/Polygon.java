package com.jugbub;

import javax.print.attribute.standard.PDLOverrideSupported;
import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Float.NaN;
import static java.lang.Float.floatToIntBits;

public class Polygon {
    ArrayList<Line> lines = new ArrayList<>();
    ArrayList<Vertex> vertices = new ArrayList<>();

    float top,left,right,bot;

    float x,y;


    Polygon(Line a,Line b,Line c){
        this.lines.add(a);
        this.lines.add(b);
        this.lines.add(c);
        this.setVertices();
        this.setPos();
        this.setBounds();
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
    private void setBounds(){
        this.setVerticalBounds();
        this.setHorizontalBounds();
    }
    private void setVerticalBounds(){
        float a = this.vertices.get(0).y;
        float b = this.vertices.get(1).y;
        float c = this.vertices.get(2).y;

        this.top = Math.max(Math.max(a,b),Math.max(b,c));
        this.bot = Math.min(Math.min(a,b),Math.min(b,c));
    }
    private void setHorizontalBounds(){
        float a = this.vertices.get(0).x;
        float b = this.vertices.get(1).x;
        float c = this.vertices.get(2).x;

        this.right = Math.max(Math.max(a,b),Math.max(b,c));
        this.left = Math.min(Math.min(a,b),Math.min(b,c));
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

        float a = Math.abs(p.y - this.lines.get(0).function(p.x));
        float b = Math.abs(p.y - this.lines.get(1).function(p.x));
        float c = Math.abs(p.y - this.lines.get(2).function(p.x));

        if(Float.isNaN(a))
            a = p.x - this.lines.get(0).vertices[0].x;
        if(Float.isNaN(b))
            b = p.x - this.lines.get(1).vertices[0].x;
        if(Float.isNaN(c))
            c = p.x - this.lines.get(2).vertices[0].x;

        System.out.println("\na: "+a+"\nb: "+b+"\nc: "+c);

        float min = Math.min(Math.min(a, b), Math.min(b, c));

        System.out.print("min: " + min);

        if ((min == a))
            return this.lines.get(0);
        else if ((min == b))
            return this.lines.get(1);
        else
            return this.lines.get(2);
    }

    public boolean isWithin(Pixel p){
        if((this.left <= p.x && p.x <= this.right)&&(this.bot <= p.y && p.y <= this.top)) {
            if(this.checkIfUnderLine(p)){
                return true;
            }
        }
        return false;
    }

    private boolean checkIfUnderLine(Pixel p){
        for (int i = 0; i < 3; i++) {
            if(this.lines.get(i).straightUp()){
                if(this.lines.get(i).vertices[0].x == this.left){
                    if(p.x < this.left)
                        return false;
                }else {
                    if(p.x > this.right)
                        return false;
                }
            }
            else if(this.hasTop(this.lines.get(i))){
                if(this.lines.get(i).function(p.x)<p.y)
                    return false;
            }else{
                if(this.lines.get(i).function(p.x)>p.y)
                    return false;
            }
        }
        return true;
    }

    private boolean hasTop(Line line){
        for (int i = 0; i < 2; i++) {
            if(line.vertices[i].y == this.top)
                return true;
        }
        return false;
    }

    private boolean isLeft(Line line){
        for (int i = 0; i < 2; i++) {
            if(line.vertices[i].x == this.left)
                return true;
        }
        return false;
    }

    private static float pyth(float a, float b){
        a = (float) Math.pow(a,2);
        b = (float) Math.pow(b,2);

        return (float) Math.sqrt(a+b);
    }
    private Line closestLine(Vertex p){

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

    public boolean isWithin(Vertex p){
        Line line = this.closestLine(p);

        float pDistance = pyth(p.x-this.x,p.y-this.y);
        float lineDistance = pyth(p.x-this.x,this.x-line.function(p.x));

        if(pDistance <= lineDistance)
            return true;

        return false;
    }

}
