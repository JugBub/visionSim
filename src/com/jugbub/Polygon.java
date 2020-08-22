package com.jugbub;

import java.util.ArrayList;

public class Polygon {
    ArrayList<Line> lines = new ArrayList<>();


    Polygon(Line a,Line b,Line c){
        this.lines.add(a);
        this.lines.add(b);
        this.lines.add(c);
    }
}
