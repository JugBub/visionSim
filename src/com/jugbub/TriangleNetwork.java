package com.jugbub;

import java.util.ArrayList;

public class TriangleNetwork {
    ArrayList<Polygon> polygons = new ArrayList<>();


    TriangleNetwork(){

    }

    public void addPolygon(Polygon polygon){
        this.polygons.add(polygon);
    }
}
