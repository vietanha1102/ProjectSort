package com.example.demokotlin.model;

public class SimpleRandom {
    //private int max;
    private int last;


    public SimpleRandom() {
        //this.max = max;
        last = (int) (System.currentTimeMillis() % 10);
    }

    public int nextInt(int max) {
        last = (last * 32719 + 3) % 32749;
        int temp = last % max;
        return temp ;
    }
}
