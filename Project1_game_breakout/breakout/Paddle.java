import java.awt.Graphics2D;
import java.awt.Color;
import java.util.ArrayList;

/**
 * A Paddle models the pong-like paddle the player uses in breakout to bounce the ball back toward
 * the bricks.  This class handles movement and drawing of the paddle.
 */
public class Paddle {
    /** width, in pixels, of the paddle */
    public static final int WIDTH = 50;  
    
    /** height, in pixels, of the paddle */
    public static final int HEIGHT = 5;
    
    /** vertical location of the top of the paddle */
    public static final int Y_LOCATION = 400;

    private Game game;
    private int xLocation;         // horizontal location of the left side of the paddle
    private double speed;          // in pixels per second
    private boolean movingLeft;
    private boolean movingRight;
    private long startMovingTime;

    
    //********************************************************************************************
    // The basic methods below are provided for you and need no additional changes. 
    
    /** constructs a new paddle associated with the specified Game instance */
    public Paddle(Game game) {
        this.game = game;
    }
    
    /** returns the horizontal location of the left side of the paddle */
    public double getXLocation() {
        return xLocation;
    }
    
    /** sets the speed of the paddle in pixels / second */
    public void setSpeed(double newSpeed) {
        speed = newSpeed;
    }
    
    
    
    //********************************************************************************************
    // TODO:  You must complete the bodies of the methods below, up until the note to STOP
    
    /** move the paddle to the edge of the screen if it has moved off of that side of the screen */
   public void ensurePaddleIsInBounds() {
        // check to see if the paddle moved off the left side of the screen
        // ... if it did, move it flush with the left side of the screen 
        // xLocation = ....?
        if(xLocation<0) {
            xLocation= 0;
        }
        if(xLocation> Game.GAME_WIDTH) {   //We could have used only the width of the game but it appears to be too long, therefore the length of the paddle is subtracted 
            xLocation= Game.GAME_WIDTH-WIDTH;
        }
        // check to see if the paddle moved off the right side of the screen
        // ... if it did, move it flush with the right side of the screen 
        // xLocation = ....?
    }

    
    // STOP!  No changes need to be made past this point. 
    //********************************************************************************************
    // ADVANCED CODE:  The code below completes this class's implementation and uses some 
    // techniques that haven't been covered yet in class.  You do not need to provide any 
    // changes to the code below.  You may read it if you wish, but be aware that you may
    // not have the background to comprehend all of it.  
    
    /** draws this paddle to the off-screen buffer */
    public void draw() {
        game.getGraphics().setColor(Color.BLACK);
        game.getGraphics().fillRect(xLocation, Y_LOCATION, 
                                    WIDTH, HEIGHT);
                                    
    }
    
    /** starts this paddle moving left */
    public synchronized void startMovingLeft() {
        startMovingTime = System.currentTimeMillis();
        movingLeft = true;
    }
    
    /** starts this paddle moving right */
    public synchronized void startMovingRight() {
        startMovingTime = System.currentTimeMillis();
        movingRight = true;
    }
    
    /** stops this paddle from moving further left */
    public synchronized void stopMovingLeft() {
        xLocation -= speed * (1.0 / 1000.0) * (System.currentTimeMillis() - startMovingTime);
        movingLeft = false;
    }
    
    /** stops this paddle from moving further right */
    public synchronized void stopMovingRight() {
        xLocation += speed * (1.0 / 1000.0) * (System.currentTimeMillis() - startMovingTime);
        movingRight = false;
    }
    
    /** moves the paddle one frame according to its heading */
    public synchronized void advance() {
        if(movingLeft) 
            xLocation -= speed * (1.0 / 1000.0) * (System.currentTimeMillis() - startMovingTime);
        if(movingRight) 
            xLocation += speed * (1.0 / 1000.0) * (System.currentTimeMillis() - startMovingTime);
        startMovingTime = System.currentTimeMillis();
        ensurePaddleIsInBounds();
    }
}
