import java.awt.Graphics2D;
import java.awt.Color;

/**
 * The Brick class models instances of the bricks which can be destroyed by the ball in the breakout game
 * 
 * No changes need to be made by the student for this class
 */
public class Brick {
    private int row;     // the row in the BrickBoard in which this Brick lies
    private int column;  // the column in the BrickBoard in which this Brick lies
    private boolean isVisible; // whether or not the Brick is visible
    private BrickBoard board;  // the BrickBoard this Brick belongs to
    private Game game;   // the Game this Brick belongs to
    private Color color = Color.BLUE;  // color of this Brick
    
    // STOP!  No changes need to be made past this point. 
    //********************************************************************************************
    // ADVANCED CODE:  The code below completes this class's implementation and uses some 
    // techniques that haven't been covered yet in class.  You do not need to provide any 
    // changes to the code below.  You may read it if you wish, but be aware that you may
    // not have the background to comprehend all of it.  
    
    /** constructs a new Brick object within the specified game at the specified location. */
    public Brick(Game newGame, int newRow, int newColumn) {
        isVisible = true;
        game = newGame;
        board = game.getBrickBoard();
        row = newRow;
        column = newColumn;
    }
    
    /** returns whether or not this Brick is visible */
    public boolean isVisible() {
        return isVisible;
    }
    
    /** the row within the BrickBoard this Brick resides at.
     * the first row is row #0.  
     */
    public int getRow() { 
        return row; 
    }
    
    
    /** the column within the BrickBoard this Brick resides at.
     * the first column is column #0.  
     */
    public int getColumn() {
        return column;
    }
    
    /** returns this Brick to visible status */
    public void reset() {
        isVisible = true;
    }
    
    /** calculates the horizontal position of this brick
     * This location is calculated relative to the Brick's position on the BrickBoard, the 
     * size of a Brick, and the location of the BrickBoard
     */
    public double getXLocation() {
        return game.getBrickBoard().getBrickWidth() * column + 
               BrickBoard.BOARD_X_LOCATION;
    }
    
    /** destroys this brick (makes it invisible and no longer able to collide with the ball '
     * Additionally, the user's score is updated by 10 points. 
     */
    public void turnOff() {
        isVisible = false;
        game.scorePoints(10);
    }
    
    /** returns true if the ball will collide with the left side of this Brick.
     * nextX and nextY are the coordinates of the location the Ball is moving to.  
     */
    public boolean isCollidingWithBallLeft(Ball ball, double nextX, double nextY) {
        if(nextX < getXLocation()) {
            return false;
        }
        
        return game.linesIntersect(ball.getXLocation(), ball.getYLocation(), 
                                   nextX, nextY,
                                   getXLocation(), 
                                   getYLocation(),
                                   getXLocation(), 
                                   getYLocation() + game.getBrickBoard().getBrickHeight());
    }
    
    /** returns true if the ball will collide with the right side of this Brick.
     * nextX and nextY are the coordinates of the location the Ball is moving to.  
     */
    public boolean isCollidingWithBallRight(Ball ball, double nextX, double nextY) {
        if(nextX > getXLocation() + game.getBrickBoard().getBrickWidth()) {
            return false;
        }
        
        return game.linesIntersect(ball.getXLocation(), ball.getYLocation(), 
                                   nextX, nextY,
                                   getXLocation() + game.getBrickBoard().getBrickWidth(), 
                                   getYLocation(),
                                   getXLocation() + game.getBrickBoard().getBrickWidth(), 
                                   getYLocation() + game.getBrickBoard().getBrickHeight());
    }
    
    /** returns true if the ball will collide with the top side of this Brick.
     * nextX and nextY are the coordinates of the location the Ball is moving to.  
     */
    public boolean isCollidingWithBallTop(Ball ball, double nextX, double nextY) {
        if(nextY < getYLocation()) {
            return false;
        }
        
        return game.linesIntersect(ball.getXLocation(), ball.getYLocation(), 
                                   nextX, nextY,
                                   getXLocation(), 
                                   getYLocation(),
                                   getXLocation() + game.getBrickBoard().getBrickWidth(), 
                                   getYLocation());
    }
    
    /** calculates the vertical position of this brick
     * This location is calculated relative to the Brick's position on the BrickBoard, the 
     * size of a Brick, and the location of the BrickBoard
     */
    public double getYLocation() {
        return game.getBrickBoard().getBrickHeight() * row + 
               BrickBoard.BOARD_Y_LOCATION;
    }
    
    
    
    /** returns true if the ball will collide with the bottom side of this Brick.
     * nextX and nextY are the coordinates of the location the Ball is moving to.  
     */
    public boolean isCollidingWithBallBottom(Ball ball, double nextX, double nextY) {
        if(nextY > getYLocation() + game.getBrickBoard().getBrickHeight()) {
            return false;
        }
        
        return game.linesIntersect(ball.getXLocation(), ball.getYLocation(), 
                                   nextX, nextY,
                                   getXLocation(), 
                                   getYLocation() + game.getBrickBoard().getBrickHeight(),
                                   getXLocation() + game.getBrickBoard().getBrickWidth(), 
                                   getYLocation() + game.getBrickBoard().getBrickHeight());
    }
    
    
    
    public boolean isCollidingWithBall(Ball ball, double nextX, double nextY) {
        return isCollidingWithBallLeft(ball, nextX, nextY) ||
               isCollidingWithBallRight(ball, nextX, nextY) ||
               isCollidingWithBallTop(ball, nextX, nextY) ||
               isCollidingWithBallBottom(ball, nextX, nextY);
    }
    
    /** sets the color of the Brick.
     * Possibilities are magenta, cyan, blue, red, green, or yellow
     */
    public void setColor(String color) {
        if(color.equals("magenta")) this.color = Color.MAGENTA;
        else if(color.equals("cyan")) this.color = Color.CYAN;
        else if(color.equals("blue")) this.color = Color.BLUE;
        else if(color.equals("red")) this.color = Color.RED;
        else if(color.equals("green")) this.color = Color.GREEN;
        else if(color.equals("yellow")) this.color = Color.YELLOW;
        else throw new IllegalArgumentException("No color available with name: " + color);
    }
    
    /** draws this Brick to the off-screen buffer */
    public void draw() {
        if(!isVisible) return;
        Graphics2D g = game.getGraphics();
        g.setColor(color);
        g.fillRect((int)getXLocation(), (int)getYLocation(),
                   (int)game.getBrickBoard().getBrickWidth(),
                   (int)game.getBrickBoard().getBrickHeight());
        g.setColor(Color.BLACK);
        g.drawRect((int)getXLocation(), (int)getYLocation(),
                   (int)game.getBrickBoard().getBrickWidth(),
                   (int)game.getBrickBoard().getBrickHeight());
        
    }
}
