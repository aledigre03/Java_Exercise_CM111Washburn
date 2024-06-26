import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.image.BufferStrategy;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/**
 * The Game class contains all the event-handling and game management functionality for the Breakout game.  It 
 * holds instances of various other classes which assist in the game implementation and coordinates between them.
 * It also tracks game statistics and provides the window for the application.
 */
public class Game {

    public static final int GAME_WIDTH = 650;    // width of the main window
    public static final int GAME_HEIGHT = 500;   // height of the main window
    public static final int HEADER_INFORMATION_Y = 60;
    
    private BrickBoard board;
    private Paddle paddle;
    private Ball ball;
    
    private int ballsRemaining;      // number of balls remaining for the player in the game
    private int score;               // current score of the player (or score from the last game)
    private boolean winner;          // true if the player won the last game
    private double ballLaunchSpeed;  // in pixels per second
    private String difficulty;       // description of the selected difficulty
    
    
    //********************************************************************************************
    // The basic methods below are provided for you and need no additional changes. 
    // Use them as examples to help you complete the methods assigned for the project
    
    /** constructs a new Game instance. 
     * Most initialization is handled by startGame rather than this consturctor
     */
    public Game() {
        difficulty = "";
    }

    /** returns the BrickBoard which contains each of the Bricks in the game */
    public BrickBoard getBrickBoard() {
        return board;
    }
    
    /** returns the ball curently being used */
    public Ball getBall() {
        return ball;
    }
    
    /** returns the paddle used in the game */
    public Paddle getPaddle() {
        return paddle;
    }
    
    public void initializeGame() {
        winner = false;
        score = 0;
        ball.reset();
        board.reset();
    }
    
    /** ends the currently running game, if one is running, and retuns the user to the title screen */
    public void endGame() {
        stopRunning = true;
        difficulty = "";
    }
    
    /** declare the user is the winner and end the game */
    public void winGame() {
        winner = true;
        endGame();
    }
    
    /** start a game with easy difficulty settings */
    public void startEasy() {
        initializeGame();
        ballLaunchSpeed = 75;
        ballsRemaining = 5;
        paddle.setSpeed(200);
        isRunning = true;
        difficulty = "easy";
    }
    
    /** start a game with normal difficulty settings */
    public void startNormal() {
        initializeGame();
        ballLaunchSpeed = 150;
        ballsRemaining = 4;
        paddle.setSpeed(300);
        isRunning = true;
        difficulty = "normal";
    }
    
    
    //********************************************************************************************
    // TODO:  You must complete the bodies of the methods below, up until the note to STOP
    
    /** adds the specified points to the user's score */
    public void scorePoints(int points) {   //the method count the points scored, in addition, starcounter is the veariable needed to count 10 ppints in a row
                                            // for the "star rush" (the ball goes multicolor)
        starcounter+= 1;
        score+= 1;
        
    }
    
    /** reduces the number of balls remaining (if any left) and reset the ball to rest on the paddle.
     *  if no balls remain, end the game
     */
  public void resetBall() {   //the ball count goes down when ball != visible, starcounter return 0 because the player failed teh condition to unlock the star rush
        ballsRemaining -= 1;
        starcounter= 0;
        ball.reset();
        if(ballsRemaining == 0) {
            endGame();
        }
    }
    
    /** start a game with advanced difficulty settings */
    public void startAdvanced() {  //the game is harder 
        initializeGame();
        ballLaunchSpeed = 400;
        ballsRemaining = 3;
        paddle.setSpeed(350);
        isRunning = true;
        difficulty = "Advanced";
    }
    
    
    // **********
    // ********** Extra Challenge:
    // **********
    // You have learned enough Java at this point to write the method below on your own. 
    // However, it is a challenging method, especially to write efficiently! If you want 
    // an extra challenge, delete the contents of the method below and write it from 
    // scratch. I will consider your unique submissions for extra participation credit,
    // but you can simply leave the code I placed there if you don't intend to try. 
    
    /** determines whether or not two line segments described by 4 points intersect.  
     * (x1, y1) and (x2, y2) signify the first line while (x3, y3) and (x4, y4)
     * This  method is modified from source at: http://www.java-gaming.org/index.php?topic=22590.0
     */
    public static boolean linesIntersect(double x1, double y1, double x2, double y2, 
                                         double x3, double y3, double x4, double y4) {
      // Return false if either of the lines have zero length
      if (x1 == x2 && y1 == y2 ||
            x3 == x4 && y3 == y4) {
         return false;
      }
      // Fastest method, based on Franklin Antonio's "Faster Line Segment Intersection" topic 
      // from "Graphics Gems III" book (http://www.graphicsgems.org/)
      double ax = x2-x1;
      double ay = y2-y1;
      double bx = x3-x4;
      double by = y3-y4;
      double cx = x1-x3;
      double cy = y1-y3;

      double alphaNumerator = by*cx - bx*cy;
      double commonDenominator = ay*bx - ax*by;
      if (commonDenominator > 0) {
         if (alphaNumerator < 0 || alphaNumerator > commonDenominator){
            return false;
         }
      }else if (commonDenominator < 0) {
         if (alphaNumerator > 0 || alphaNumerator < commonDenominator){
            return false;
         }
      }
      double betaNumerator = ax*cy - ay*cx;
      if (commonDenominator > 0) {
         if (betaNumerator < 0 || betaNumerator > commonDenominator){
            return false;
         }
      }else if (commonDenominator < 0) {
         if (betaNumerator > 0 || betaNumerator < commonDenominator){
            return false;
         }
      }
      if (commonDenominator == 0) {
         // This code wasn't in Franklin Antonio's method. It was added by Keith Woodward.
         // The lines are parallel.
         // Check if they're collinear.
         double y3LessY1 = y3-y1;
         double collinearityTestForP3 = x1*(y2-y3) + x2*(y3LessY1) + x3*(y1-y2);   // see http://mathworld.wolfram.com/Collinear.html
         // If p3 is collinear with p1 and p2 then p4 will also be collinear, since p1-p2 is parallel with p3-p4
         if (collinearityTestForP3 == 0){
            // The lines are collinear. Now check if they overlap.
            if (x1 >= x3 && x1 <= x4 || x1 <= x3 && x1 >= x4 ||
                  x2 >= x3 && x2 <= x4 || x2 <= x3 && x2 >= x4 ||
                  x3 >= x1 && x3 <= x2 || x3 <= x1 && x3 >= x2){
               if (y1 >= y3 && y1 <= y4 || y1 <= y3 && y1 >= y4 ||
                     y2 >= y3 && y2 <= y4 || y2 <= y3 && y2 >= y4 ||
                     y3 >= y1 && y3 <= y2 || y3 <= y1 && y3 >= y2){
                  return true;
               }
            }
         }
         return false;
      }
      return true;
   }
    
    // STOP!  No changes need to be made past this point. 
    //********************************************************************************************
    // ADVANCED CODE:  The code below completes this class's implementation and uses some 
    // techniques that haven't been covered yet in class.  You do not need to provide any 
    // changes to the code below.  You may read it if you wish, but be aware that you may
    // not have the background to comprehend all of it.  
    
    public static final String WINDOW_TITLE = "Breakout!";  // displayed at the top of the window
    public static final int FRAMES_PER_SECOND = 60;   // maximum number of animation frames per second
                                                      // reduce this to lessen the draw on system resources
    
    private JFrame frame;          // the game window
    private BufferStrategy buff;   // used for off-line drawing to reduce flickering in the animation
    private boolean isRunning;     // a flag that is raised while the main game loop is running
    private boolean stopRunning;   // a flag that is raised to exit the main game loop and reset the game
    private boolean quit;          // a flag that is raised when the user requests to leave the game
    
    
    
    /** creates the game window, initializes all game variables and enters the rendering / processing loop for the game
     */
    public synchronized void runGame() {
        
        frame = new JFrame(WINDOW_TITLE);
        
        // create a key-listener to listen for player commands
        frame.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                if(!isRunning) return; // don't move the paddle if the game hasn't started
                switch(e.getKeyChar()) { // start moving the paddle
                    case 'j':
                        paddle.startMovingLeft();
                        break;
                    case 'l':
                        paddle.startMovingRight();
                        break;
                }
            }
            
            public void keyReleased(KeyEvent e) {
                if(!isRunning) return; // don't move the paddle if the game hasn't started
                switch(e.getKeyChar())  // stop moving the paddle
                {
                    case 'j':
                        paddle.stopMovingLeft();
                        break;
                    case 'l':
                        paddle.stopMovingRight();
                        break;
                }
            }
            
            public void keyTyped(KeyEvent e) {
                switch(e.getKeyChar()) {
                    case ' ':  // launch the ball if it's resting on the paddle 
                        if(!isRunning) break;
                        ball.launch(ballLaunchSpeed);
                        break;
                    case '1':
                        startEasy();
                        break;
                    case '2':
                        startNormal();
                        break;
                    case '3':
                        startAdvanced();
                        break;
                    case 'q':  // quit a running game, or if none is running, quit the application entirely
                        if(isRunning)  {
                            endGame();
                        } else {
                            quit = true;
                        }
                }
            }
        });
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // quit if the window is closed
        frame.setSize(GAME_WIDTH, GAME_HEIGHT);  // create a window for the game
        frame.setVisible(true);  // make the window visible
        
        // set up double buffering
        frame.createBufferStrategy(2);
        buff = frame.getBufferStrategy();
        
        // create and initialize game components
        board = new BrickBoard(this);
        paddle = new Paddle(this);
        ball = new Ball(this);
        ball.setColor("red");
        
        while(!quit) {  // loop indefinitely until the user chooses to quit 
            ball.reset();   // set the ball back to the paddle
            
            while(!isRunning && !quit) { //wait until the user starts a new game
                try {
                    Thread.sleep(1000 / FRAMES_PER_SECOND);  // sleep until next frame should be drawn
                } catch(Exception e) {} // do nothing if an exception occurs while sleeping
                drawGameState();  // re-draw the screen 
            }
            
            while(!stopRunning && !quit) {  // run the game loop
                try {
                    Thread.sleep(1000 / FRAMES_PER_SECOND);  // sleep until next frame should be drawn
                } catch(Exception e) {} // do nothing if an exception occurs while sleeping
                
                ball.advance();  // move the ball and destroy any bricks it hits
                paddle.advance();  // move the paddle if necessary
                drawGameState();  // re-draw the screen with updated components
            }
            
            // reset flags
            isRunning = false;
            stopRunning = false;
            
            // stop the paddle if it's moving
            paddle.stopMovingLeft();
            paddle.stopMovingRight();
        }
        
        System.exit(1); // for BlueJ, insures the Game object and window are destroyed
    }
    
    /** returns the offline graphics buffer for double-buffered rendering
     */
    public Graphics2D getGraphics() {
        return (Graphics2D)buff.getDrawGraphics();
    }
    
    /** draws the entirety of the state of a game for a frame
     */
    public void drawGameState() {
        Graphics2D g = getGraphics();
        
        // clear the screen
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        
        // display game components
        board.draw();
        paddle.draw();
        ball.draw();
        
        // display game status messages
        g.setColor(Color.BLACK);
        g.drawString("Score: " + score, 20, HEADER_INFORMATION_Y);
        g.drawString("Balls Remaining: " + ballsRemaining, 300, HEADER_INFORMATION_Y);
        g.drawString(difficulty, 500, HEADER_INFORMATION_Y);
        
        // display the title-screen if the game hasn't been started
        if(!isRunning) {
            drawTitleScreen(getGraphics());
        }
        
        // display movement and game-play instructions before the ball has been launched
        if(!ball.isLaunched()) {
            g.drawString("Press j or the left arrow key to move the paddle left", 200, 280);
            g.drawString("Press l or the left arrow key to move the paddle right", 200, 300);
            g.drawString("Press the spacebar to launch a ball", 200, 320);
        }
        
        // display quit instructions differently depending on whether a game is running
        g.drawString("Press q to quit " + (isRunning ? " to the menu" : "entirely"), 200, 460);
        
        buff.show(); // flip offscreen buffer to make drawings visible
    }
    
    /** draws extra information on the screen explaining how to start a game
     */
    public void drawTitleScreen(Graphics2D g) {
        // display instructions for starting the game
        g.drawString("Game Over - Start a new one by pressing 1, 2, or 3", 200, 200);
        g.drawString("1 - Start easy game", 200, 220);
        g.drawString("2 - Start normal game", 200, 240);
        g.drawString("3 - Start hard game", 200, 260);
        
        // draw the winner message with a quickly, randomly changing color
        g.setColor(new Color((float)Math.random(), (float)Math.random(), (float)Math.random()));
        if(winner) {
            g.drawString("WINNER!!!!!!", 200, 180);
        }
    }
}
