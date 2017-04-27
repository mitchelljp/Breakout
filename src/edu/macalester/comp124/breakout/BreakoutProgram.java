package edu.macalester.comp124.breakout;

import acm.graphics.GObject;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;
import java.awt.*;
import java.awt.event.MouseEvent;

public class BreakoutProgram extends GraphicsProgram {

    private RandomGenerator rand;
    private Ball ball;
    private Paddle paddle;
    private BrickWall brickwall;
    private double dx;
    private double dy;
    private int brickCount;
    private int lives;

    public void init() {
    setBackground(Color.BLACK);
    rand=new RandomGenerator();
    ball =new Ball();
    brickCount=100;
    lives=3;
    }

    /**
     * Creates a random speed to start dx with.
     * @return the double for the random speed
     */
    private double randomSpeed(){

        double num =rand.nextDouble(-2.0,2.0);
        return num;
    }

    /**
     * Basically returns a 1 or 0, for 50/50 probability.
     * @return int 1 or 0.
     */
    private int oneOrZero(){
        int num2=rand.nextInt(0,1);
        return num2;
    }

    /**
     * Makes a random speed to add to dx so direction changes slightly on impact with paddle.
     * @return the double for the random speed.
     */
    private double randomAngle(){
        double num3 =rand.nextDouble(-.75,.75);
        return num3;
    }

    /**
     * Checks if the ball hits a brick, and removes it if that happens. Also reduces the brick counter by 1.
     */
    private void checkBrick(){
        if (brickwall.getElementAt(ball.getX(), ball.getY()) != null) {
            //GObject e1=getElementAt(ball.getX(),ball.getY());
            brickwall.removeBrick(brickwall.getElementAt(ball.getX(), ball.getY()));
            brickCount=brickCount-1;
        }
        else if (brickwall.getElementAt(ball.getX()+BALL_SIZE, ball.getY()+BALL_SIZE) != null) {
            //GObject e1=getElementAt(ball.getX(),ball.getY());
            brickwall.removeBrick(brickwall.getElementAt(ball.getX()+BALL_SIZE, ball.getY()+BALL_SIZE));
            brickCount=brickCount-1;
        }
        else if (brickwall.getElementAt(ball.getX(), ball.getY()+BALL_SIZE) != null) {
            //GObject e1=getElementAt(ball.getX(),ball.getY());
            brickwall.removeBrick(brickwall.getElementAt(ball.getX(), ball.getY()+BALL_SIZE));
            brickCount=brickCount-1;
        }
        else if (brickwall.getElementAt(ball.getX()+BALL_SIZE, ball.getY()) != null) {
            //GObject e1=getElementAt(ball.getX(),ball.getY());
            brickwall.removeBrick(brickwall.getElementAt(ball.getX()+BALL_SIZE, ball.getY()));
            brickCount=brickCount-1;
        }
    }

    /**
     * Determines if the game is won or not. Prints result.
     * @return a boolean if you win.
     */
    private boolean checkWin(){
        if(brickCount==0) {
            println("You Win!");
            return true;
        }
        return false;



    }

    /**
     * Resets the ball and changes the lives if the ball hits the bottom. Prints out how many lives are left.
     */
    private void reset(){
        double windowHeight = getHeight();


        if (ball.getY() > (windowHeight - BALL_SIZE)) {
            ball.setLocation((getWidth() / 2) - 30, (getHeight() / 2 - 60));
            lives -= 1;

            if (lives == 0) {
                System.out.println("Whoops! You have " + lives + " lives left. This is your last chance to win!");
                waitForClick();
            } else if (lives == -1) {
                removeAll();
                System.out.println("Sorry, you lose! Close the window and reopen to try again!");
                waitForClick();
            } else {
                System.out.println("Whoops! You have " + lives + " lives left. Try again!");
                waitForClick();
            }
        }
    }

    /**
     * Animates the ball and tells it how to bounce off the walls, paddle, and bricks. checkBrick is called inside this method.
     * @param object called on the ball object.
     */
    public void animateBall(GObject object) {
        dx = randomSpeed();
        dy = 2;

        double windowWidth = getWidth();

        while (true) {
            if(checkWin()){
                break;
            }

            object.move(dx, dy);
            pause(5);

            reset();

            if (ball.getX() > (windowWidth - BALL_SIZE)) {
                dx *= -1;
            } else if (ball.getY() < 0) {
                dy *= -1;
            } else if (ball.getX() < 0) {
                dx *= -1;
            }

            if (getElementAt(ball.getX() + BALL_SIZE, ball.getY() + BALL_SIZE) != null) {
                dy *= -1;
                int oneZero=oneOrZero();
                double rAngle=randomAngle();
                if(oneZero==0){;
                    dx=(dx*-1)+rAngle;
                }
                else if (oneZero==1){
                    dx=(dx*1)+rAngle;
                }
                if(getElementAt(ball.getX() + BALL_SIZE, ball.getY() + BALL_SIZE) instanceof Paddle ||ball.getY()>(getHeight()-40-BALL_SIZE)){
                    ball.setLocation(ball.getX(),(getHeight()-40-BALL_SIZE));
                }


            } else if (getElementAt(ball.getX(), ball.getY()) != null) {
                dy *= -1;
                if(getElementAt(ball.getX() + BALL_SIZE, ball.getY() + BALL_SIZE)instanceof Paddle ||ball.getY()>(getHeight()-40-BALL_SIZE)){
                    ball.setLocation(ball.getX(),(getHeight()-40-BALL_SIZE));
                }
            } else if (getElementAt(ball.getX() + BALL_SIZE, ball.getY()) != null) {
                dy *= -1;
                if(getElementAt(ball.getX() + BALL_SIZE, ball.getY() + BALL_SIZE) instanceof Paddle ||ball.getY()>(getHeight()-40-BALL_SIZE) ){
                    ball.setLocation(ball.getX(),(getHeight()-40-BALL_SIZE));
                }
            } else if (getElementAt(ball.getX(), ball.getY() + BALL_SIZE) != null) {
                dy *= -1;
                if(getElementAt(ball.getX() + BALL_SIZE, ball.getY() + BALL_SIZE)instanceof Paddle ||ball.getY()>(getHeight()-40-BALL_SIZE) ){
                    ball.setLocation(ball.getX(),(getHeight()-40-BALL_SIZE));
                }
            }

            checkBrick();



//            System.out.println(brickCount);

            }


        }

    /**
     * Tells the paddle how to move based off mouses movements.
     * @param e mouse event.
     */
    public void mouseMoved(MouseEvent e) {
        paddle.move(e.getX()-paddle.getX()-getWidth()/2, 0);
    }

    /**
     * Sets up some basic parameters, makes a ball, wall, paddle, adds mouse listeners,calls animateBall.
     */
    public void setup(){
        addMouseListeners();

        add(ball,(getWidth()/2)-30,(getHeight()/2-60));

        brickwall=new BrickWall();
        add(brickwall);

        paddle=new Paddle((getWidth()/2)-50,(getHeight()-40));
        add(paddle);
        waitForClick();
        animateBall(ball);

    }

    /**
     * This is the play game that starts the game and displays a message to start.
     */
    public void playGame(){
        System.out.println("Welcome to BREAKOUT! You have three lives. Once your three lives are up you lose. Click to begin. Good Luck!");
        setup();


    }

    public void run() {
        playGame();


    }
private static final double BALL_SIZE =20;
}




