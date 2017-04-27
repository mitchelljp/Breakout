package edu.macalester.comp124.breakout;


import acm.graphics.GRect;

public class Brick extends GRect {
    private static int width=71;
    private static int height=15;

    /**
     * Brick constructor.
     * @param x x pos
     * @param y y pos
     */
    public Brick(int x, int y){
        super(x,y,width, height);
    }
}
