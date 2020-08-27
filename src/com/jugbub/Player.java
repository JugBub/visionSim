package com.jugbub;

import java.util.ArrayList;

public class Player {
    int x,y;
    TriangleNetwork triangleNetwork = new TriangleNetwork();
    Border border;

    Player(int x,int y, float radius){
        this.x = x;
        this.y = y;
        border = new Border(radius);
    }

    public void shootRays(BlockBreakerPanel panel){
        ArrayList<Vertex> targetList = new ArrayList<>(this.border.vertices);
        
        targetList.addAll(this.border.verticesWithinBorder(panel));

        for (int i = 0; i < targetList.size(); i++) {
            Ray ray = new Ray(this.x,this.y,targetList.get(i));
        }



    }

}
