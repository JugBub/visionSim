package com.jugbub;

public class Animate implements Runnable {

    BlockBreakerPanel blockBreaker;

    public Animate(BlockBreakerPanel bp) {
        this.blockBreaker = bp;

    }

    @Override
    public void run() {
        while (true){
            blockBreaker.update();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
