package edu.macalester.comp124.breakout;


import acm.graphics.GCompound;
import acm.graphics.*;
import java.awt.*;

public class BrickWall extends GCompound {
    //10 rows, 10 columns
    private int width = 71;
    private int height = 15;
    private int xStart = 15;
    private int yStart = 15;

    /**
     * Makes a brick wall using the brick class.
     */
    public BrickWall() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Brick brick = new Brick(xStart + (i * width), yStart + (j * height));
                add(brick,(xStart+(i * width)),(yStart+(j * height)));
                brick.setFilled(true);
                if (j==0||j==1){
                    brick.setFillColor(Color.RED);
                }
                if (j==2||j==3){
                    brick.setFillColor(Color.ORANGE);
                }
                if (j==4||j==5){
                    brick.setFillColor(Color.YELLOW);
                }
                if (j==6||j==7){
                    brick.setFillColor(Color.GREEN);
                }
                if (j==8||j==9){
                    brick.setFillColor(Color.CYAN);
                }

            }
        }
        }

    /**
     * Removes a brick.
     * @param brick brick object.
     */
    public void removeBrick(GObject brick){
        remove(brick);
    }

}
