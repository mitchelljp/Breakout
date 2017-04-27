package edu.macalester.comp124.breakout;

import acm.graphics.GCompound;
import acm.graphics.*;
import java.awt.*;


public class Paddle extends  GCompound{
    /**
     * Paddle constructor. Makes a paddle.
     * @param x x pos
     * @param y y pos
     */
    public Paddle(int x, int y){

        GRect paddle=new GRect(x,y,100,20);
        paddle.setFilled(true);
        paddle.setFillColor(Color.BLUE);
        add(paddle);
    }

}
