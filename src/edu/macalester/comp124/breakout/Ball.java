package edu.macalester.comp124.breakout;

import acm.graphics.GCompound;
import acm.graphics.GOval;
import java.awt.*;


public class Ball extends GCompound{

    GOval ball;

    /**
     * Ball constructor, makes a ball.
     */
    public Ball() {
        ball = new GOval(20, 20);
        ball.setFilled(true);
        ball.setFillColor(Color.red);
        add(ball);

    }






}
