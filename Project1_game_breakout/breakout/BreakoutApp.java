/**
 * This class holds the main method for running a game of 'Breakout'
 * 
 * The code for this project was adapted from code for an assignment in CSC121 in 2012 at 
 * DePauw University, originally developed by Joseph Kendall-Morwick. It has been modified 
 * for use in CM111 in 2023 at Washburn University. 
 * 
 * Known issues to fix in future versions:
 * 1) rendering time is considered negligable when calculating movement of game elements.  This will affect 
 *    game play on slower computers, but doesn't present a problem for reasonable frame-rates on fast computers
 *    
 * 2) collisions with the ball do not take into account the size of the ball and only determine if its center 
 *    point is colliding with another element in the game.  The effect of this omission is more obvious when the 
 *    ball size is increased, but negligable for a very small ball.
 *    
 * 3) the ball only collides with the top of the paddle, but not the sides.  For a sufficiently thin paddle, there
 *    will not be a noticable issue, but for a larger paddle, the user may expect to see a reflection against the paddle
 *    as the ball drops off the screen were it to strike the side of the paddle.
 *    
 * 4) the paddle moves slightly faster when moving left than when it moves right.  This is a bug of unknown origin
 * 
 */
public class BreakoutApp {
    public static void main(String[] args) {
        Game game = new Game();
        game.runGame();
    }
}
//final commitment.
