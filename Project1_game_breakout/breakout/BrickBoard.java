import java.util.ArrayList;
import java.awt.Graphics2D;


/**
 * A BrickBoard is a container for the Brick instances in the breakout game.  This class is responsible for 
 * coordinating the creation and drawing of bricks and some collision detection.
 */
public class BrickBoard {
    public static final int ROWS = 5;  // number of rows of bricks
    public static final int COLUMNS = 10;  // number of bricks in each row
    public static final int BOARD_WIDTH = 550;  // width of the brickboard in the game 
    public static final int BOARD_HEIGHT = 200; // height of the brickboard in the game
    public static final int BOARD_X_LOCATION = 70; // horizontal location of the brickboard in the game
    public static final int BOARD_Y_LOCATION = 70; // verticle location of the brickboard in the game
    
    private Game game;
    
    
    //********************************************************************************************
    // The basic methods below are provided for you and need no additional changes. 
    
    /** calculates the width of a single brick based from the width of the BrickBoard and the number of columns */
    public double getBrickWidth() {
        return (double)BOARD_WIDTH / (double)COLUMNS;
    }
    
    
    //********************************************************************************************
    // TODO:  You must complete the bodies of the methods below, up until the note to STOP
    
    /** calculates the height of a single brick based from the height of the BrickBoard and the number of rows */
    public double getBrickHeight() {
        return (double)BOARD_HEIGHT / (double)COLUMNS;
    } //height for the bircks, using rows would have made the brick too big
    
    /** returns the color that should be associated with a Brick based on its position in the BrickBoard */
      public String getBrickColor(int row, int column) {   //the method returns the color for the board, thanks to the array it's a chess-like board
        int[] greenodd= {1,3,5,7,9};
        int[] greeneven= {0,2,4,6,8};
        
            
        
        for(int i=0; i<greenodd.length; i++) {
            if(row==0 && column== greenodd[i]) {
            return "green";
        }else if(row== 1 && column== greeneven[i]) {
            return "green";
        }else if(row== 2 && column== greenodd[i]) {
            return "green";
        }else if(row== 3 && column== greeneven[i]) {
            return "green";
        }else if(row== 4 && column== greenodd[i]) {
            return "green";
        }
        
    }

    return "magenta";
}
    
    
    // STOP!  No changes need to be made past this point. 
    //********************************************************************************************
    // ADVANCED CODE:  The code below completes this class's implementation and uses some 
    // techniques that haven't been covered yet in class.  You do not need to provide any 
    // changes to the code below.  You may read it if you wish, but be aware that you may
    // not have the background to comprehend all of it.  
    
    private ArrayList<Brick> bricks; // holds references to each brick in the brickboard in no particular order
    
    /** constructs a new BrickBoard component associated with the indicated game instance 
     * also, constructs each Brick object to be held by this BrickBoard
     */
    public BrickBoard(Game game) {
        this.game = game;
        bricks = new ArrayList<Brick>();
        for(int row = 0; row < ROWS; row++) {
            for(int col = 0; col < COLUMNS; col++) {
                Brick brick = new Brick(game, row, col);
                brick.setColor(getBrickColor(row, col));
                bricks.add(brick);
            }
        }
    }
    
    /** resets the state of each Brick so that they are all visible */
    public void reset() {
        for(Brick brick : bricks)
            brick.reset();
    }
    
    /** draws each of the Bricks to the off-screen buffer */
    public void draw() {
        for(Brick brick : bricks) {
            brick.draw();
        }
    }
    
    /** returns true if and only if each Brick has been destroyed (turned off ) */
    public boolean allBricksDestroyed() {
        for(Brick brick : bricks)
            if(brick.isVisible()) return false;
        return true;
    }
    
    /** finds the Brick that will collide with the ball given its heading and the distance it will cover this frame.
     * if no Brick will collide with the Ball, null is returned.
     */
    public Brick findCollidingBrick(Ball ball, double nextX, double nextY)
    {
        Brick closestBrick = null;  // closest brick found so far
        for(Brick brick : bricks) {  // examine each brick
            
            if(!brick.isVisible()) continue;  // don't check destroyed bricks
            
            if(closestBrick != null) { // if there's already a candidate, compare this brick to it
                if(nextX > ball.getXLocation()) { // if we're moving right...
                    if(brick.getXLocation() < ball.getXLocation() ||  // make sure the brick is not to the left
                        brick.getXLocation() > closestBrick.getXLocation()) // or further than the closest brick
                        continue;
                } else {  // if we're moving left...
                    if(brick.getXLocation() > ball.getXLocation() ||
                        brick.getXLocation() < closestBrick.getXLocation())
                        continue;
                }
                
                if(nextY > ball.getYLocation()) { // if we're moving up...
                    if(brick.getYLocation() < ball.getYLocation() ||
                        brick.getYLocation() > closestBrick.getYLocation())
                        continue;
                } else {   // if we're moving down...
                    if(brick.getYLocation() > ball.getYLocation() ||
                        brick.getYLocation() < closestBrick.getYLocation())
                        continue;
                }
            }
            
            // determine if this brick will collide with the ball
            if(brick.isCollidingWithBall(game.getBall(), nextX, nextY)) {
                closestBrick = brick;
            }
        }
        return closestBrick;
    }
    
}
