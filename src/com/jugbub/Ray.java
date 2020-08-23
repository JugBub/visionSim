package com.jugbub;

public class Ray {
    Vertex target;
    int x = 0;
    int y = 0;

    //float[] formula = new float[2];


    Ray(int x,int y,Vertex target){
        this.x = x;
        this.y = y;
        this.target = target;
    }

    public float function(float x){
        if (!(x == 0))
            return xFunction(x);
        else
            return yFunction(x);
    }

    public float xFunction(float x){
        float k = ((float)(this.y - this.target.y)) / ((float)(this.x - this.target.x));
        float m = this.y - this.x * k;

        return k * x + m;
    }

    public float yFunction(float y){
        float k = ((float)(this.x - this.target.x)) / ((float)(this.y - this.target.y));
        float m = this.x - this.y*k;

        return k * y + m;
    }

    public Line toLine(){
        return new Line(new Vertex(this.x,this.y), this.target);
    }
    /*    public void createFormula(){
        int x = this.target.x;
        int y = this.target.y;
        float nOfDecimals = 16;

        if(x>=y)
            this.formula = new float[]{(float) ((x/x)/Math.pow(10,nOfDecimals)), (float) ((y/x)/Math.pow(10,nOfDecimals))};
        else
            this.formula = new float[]{(float) ((x/y)/Math.pow(10,nOfDecimals)), (float) ((y/y)/Math.pow(10,nOfDecimals))};
        System.out.println(String.format("%.16f %.16f",this.formula[0] * Math.pow(10, 16) * x, this.formula[1] * Math.pow(10,16) * x));
    }*/

/*    public void move(BlockBreakerPanel panel){
        int i = 0;
        do{
            i++;

            this.x =(int) (this.formula[0] * i * Math.pow(10,16));
            this.y =(int) (this.formula[1] * i * Math.pow(10,16));

            for (int j = 0; j < panel.lines.size(); j++) {
                if(panel.lines.get(j).doesItHit(this)) {
                    System.out.println("HIT!");
                    break;
                }
            }
            if(i % Math.pow(10,16) == 0)
                System.out.println("working" + i);
        }while(this.x <= 100 && this.y <= 100);
        System.out.println("MISS!");
    }*/

/*    private static int commonDivisor(int a, int b){
        int divisor = 1;
        while (true){
            if(a % divisor == 0 && b % divisor == 0)
                break;
            if(divisor>a || divisor > b)

            divisor++;
        }


        return divisor;
    }*/
}
