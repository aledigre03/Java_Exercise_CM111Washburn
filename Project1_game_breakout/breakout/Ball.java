import java.awt.Color;
import java.awt.Graphics2D;


/**
 * The Ball class models the ball in the breakout game and includes functionality for updating position
 * and collision detection.
 * 
 * No changes need to be made by the student for this class
 */
public class Ball {

    public static final int RADIUS = 3;

    private Game game;
    private boolean isLaunched;    // whether or not the ball has been launched from the paddle 
    private boolean isVisible;     // whether or not the ball can be seen on the screen
    private double xLocation;      // the horizontal position of the center of this ball on the screen
    private double yLocation;      // the vertical position of the center of this ball on the screen
    private double xVelocity = 0;  // the speed this ball is traveling horizontally (pixels per seccond)
    private double yVelocity = 0;  // the speed this ball is traveling vertically (pixels per seccond)
    private double launchSpeed; 
    private Color color = Color.BLUE; // color of the ball
    private double distanceLeftToCover = 1.0;
    
    // STOP!  No changes need to be made past this point. 
    //********************************************************************************************
    // ADVANCED CODE:  The code below completes this class's implementation and uses some 
    // techniques that haven't been covered yet in class.  You do not need to provide any 
    // changes to the code below.  You may read it if you wish, but be aware that you may
    // not have the background to comprehend all of it.  
    
    /** constructs a new ball associated with the indicated game */
    public Ball(Game game) {
        this.game = game;
    }
    
    /** returns the x coordinate of the center of this ball within the game.  
     */
    public double getXLocation() {
        return xLocation;
    }
    
    /** returns the y coordinate of the center of this ball within the game.
     */
    public double getYLocation() {
        return yLocation;
    }
    
    /** true if and only if the ball is not resting on the paddle
     */
    public boolean isLaunched() {
        return isLaunched;
    }
    
    /** resets the ball to its initial spot on the paddle, waiting to be launched */
    public void reset() {
        isLaunched = false;
        isVisible = true;
    }
    
    /** determines whether or not the ball will collide with the top of the paddle */
    public boolean willCollideWithPaddleTop(double nextXForBall, double nextYForBall) {
        return game.linesIntersect(getXLocation(), getYLocation(), nextXForBall, nextYForBall,
                                   game.getPaddle().getXLocation(), Paddle.Y_LOCATION,
                                   game.getPaddle().getXLocation() + Paddle.WIDTH, Paddle.Y_LOCATION);
                                   
    }
    
    /** launches the ball from the paddle */
    public void launch(double speed) {
        xVelocity = 0;
        yVelocity = -speed;
        isLaunched = true;
    }
    
    /** collides the ball with the brick
     * This method moves the ball up to the point of its collision with this brick and returns the percentage 
     * of the distance the ball intended to travel which it traveled to get to the brick.  This method also
     * updates the velocity of the ball so that it has reflected off of the brick. 
     */
    public double collideWithBrick(Brick brick, double nextX, double nextY) {
        boolean movingRight = nextX > getXLocation();
        boolean movingDown = nextY > getYLocation();
        
        double travelX = Math.abs(nextX - xLocation);  // the distance the ball will travel horizontally
        double travelY = Math.abs(nextY - yLocation);  // the distance the ball will travel vertically
        if(movingRight && brick.isCollidingWithBallLeft(this, nextX, nextY)) { // collide with left side of brick
            double percentageAdvanced = (brick.getXLocation() - getXLocation()) / travelX;
            xLocation = (xLocation + nextX) / 2.0;
            yLocation = (yLocation + nextY) / 2.0;
            xVelocity = -xVelocity;
            brick.turnOff();
            return percentageAdvanced;
        } else if(!movingRight && brick.isCollidingWithBallRight(this, nextX, nextY)) {// collide with right side 
                                                                                      // of brick
            double percentageAdvanced =  (getXLocation() - brick.getXLocation() - 
                                          game.getBrickBoard().getBrickWidth()) / travelX;
            xLocation = (xLocation + nextX) / 2.0;
            yLocation = (yLocation + nextY) / 2.0;
            xVelocity = -xVelocity;
            brick.turnOff();
            return percentageAdvanced;
        } else if(movingDown && brick.isCollidingWithBallTop(this, nextX, nextY)) { // collide with top of brick
            double percentageAdvanced =  (brick.getYLocation() - getYLocation()) / travelY;
            xLocation = (xLocation + nextX) / 2.0;
            yLocation = (yLocation + nextY) / 2.0;
            yVelocity = -yVelocity;
            brick.turnOff();
            return percentageAdvanced;
        } else if(!movingDown && brick.isCollidingWithBallBottom(this, nextX, nextY)) { // collide with bottom of brick
            double percentageAdvanced =  (brick.getYLocation() + game.getBrickBoard().getBrickHeight() - 
                                          getYLocation()) / travelY;
            xLocation = (xLocation + nextX) / 2.0;
            yLocation = (yLocation + nextY) / 2.0;
            yVelocity = -yVelocity;
            brick.turnOff();
            return percentageAdvanced;
        } 
        
        return 0.0; // no bricks were hit, so the ball wasn't advanced by this method
    }
    
    /** changes the color of this ball */
    public void setColor(String color) {
        if(color.equals("magenta")) this.color = Color.MAGENTA;
        else if(color.equals("cyan")) this.color = Color.CYAN;
        else if(color.equals("blue")) this.color = Color.BLUE;
        else if(color.equals("red")) this.color = Color.RED;
        else if(color.equals("green")) this.color = Color.GREEN;
        else if(color.equals("yellow")) this.color = Color.YELLOW;
        else if(color.equals("rainbow")) this.color = new Color((float)Math.random(), (float)Math.random(), (float)Math.random());  //the set of rainbow color for the ball has been added
        else throw new IllegalArgumentException("No color available with name: " + color);
    }
    
    /** move the ball, reflecting off the sides of the screen, the paddle, or the bricks.
     * Any bricks hit are destroyed (turned off)
     */
    public void advance() {
        double percentageOfFrame = 1.0;  // percentage of movement left to perform
        Paddle paddle = game.getPaddle();
        
        if(!isLaunched) { // if it's not launched, move it to the center of the paddle
            xLocation = paddle.getXLocation() + Paddle.WIDTH/2.0;
            yLocation = Paddle.Y_LOCATION - RADIUS;
            return;
        }
        
        while(percentageOfFrame > 0) { // while there's still some movement left to do...
            double nextX = xLocation + percentageOfFrame * xVelocity / Game.FRAMES_PER_SECOND;
            double nextY = yLocation + percentageOfFrame * yVelocity / Game.FRAMES_PER_SECOND;
            Brick collidingBrick = game.getBrickBoard().findCollidingBrick(this, nextX, nextY);
            if(nextX < 0) {
                double nextPercentage = xLocation / (xLocation - nextX);
                percentageOfFrame -= nextPercentage;
                advance(nextPercentage);
                xVelocity = -xVelocity;
            } else if(nextX > Game.GAME_WIDTH) {
                double nextPercentage = (Game.GAME_WIDTH - xLocation) / (nextX - xLocation);
                percentageOfFrame -= nextPercentage;
                advance(nextPercentage);
                xVelocity = -xVelocity;
            } else if(nextY > Game.GAME_HEIGHT) {  //the ball went off the bottom of the screen
                double nextPercentage = (Game.GAME_HEIGHT - yLocation) / (nextY - yLocation);
                percentageOfFrame = 0;  //break out of loop
                isVisible = false;
                game.resetBall();
            } else if(nextY < 0) {
                double nextPercentage = yLocation / (yLocation - nextY);
                percentageOfFrame -= nextPercentage;
                advance(nextPercentage);
                yVelocity = -yVelocity;
            } else if(willCollideWithPaddleTop(nextX, nextY) && getYLocation() < Paddle.Y_LOCATION) {
                double nextPercentage = (Paddle.Y_LOCATION - getYLocation())/(nextY - getYLocation());
                advance(nextPercentage);
                
                //reflect relative to the position struck on the paddle
                double speed = Math.sqrt(xVelocity*xVelocity + yVelocity*yVelocity);
                xVelocity = speed * (xLocation - (paddle.getXLocation() + Paddle.WIDTH/2)) / 
                                                   (double)(Paddle.WIDTH / 2.0);
                yVelocity = -Math.sqrt(speed*speed - xVelocity*xVelocity);
                percentageOfFrame -= nextPercentage;
            } else if(collidingBrick != null) {
                double nextPercentage = percentageOfFrame * collideWithBrick(collidingBrick, nextX, nextY);
                
                //check to see if the last brick has been destroyed.
                if(game.getBrickBoard().allBricksDestroyed()) {
                    game.winGame();
                }
            } else {
                advance(percentageOfFrame);
                percentageOfFrame = 0;
            }
        }
    }
    
    /** advances the ball according to its velocity for the specified fraction of a frame */
    private void advance(double percentageOfFrame) { //since the ball is constantly moving the method is always called, when teh sarcounter reaches 10 then the color is instantly changed
        xLocation += percentageOfFrame * xVelocity / Game.FRAMES_PER_SECOND;
        yLocation += percentageOfFrame * yVelocity / Game.FRAMES_PER_SECOND;
        if(game.starcounter>=10) {
           setColor("rainbow"); 
           
        }
    }
    
    /** draws the ball to the off-screen buffer */
    public void draw() {
        if(!isVisible) return;
        Graphics2D g = game.getGraphics();
        g.setColor(color);
        g.fillOval((int)xLocation, (int)yLocation, 2*RADIUS, 2*RADIUS);
    }

}
