package com.jugbub;

public class Player {
    int x,y;
    TriangleNetwork triangleNetwork = new TriangleNetwork();
    Border border;

    Player(int x,int y, int radius){
        this.x = x;
        this.y = y;
        border = new Border(radius);


    }

}
