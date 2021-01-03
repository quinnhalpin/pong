
import java.awt.Dimension;
import java.util.HashSet;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author halpin
 */
public class PongModel {
    public final Pair<Player> players;
    public final Ball ball;
    public Dimension d;
    public Pair<Integer> score;
    public final Board board;

    public PongModel(Dimension d) {
        // private
        this.d = d;
        
        // public
        int radius = 10;
        Point center = new Point((int)d.width/2, (int) d.height/2);
        
        ball = new Ball(radius, center, getBallStartingVec());
        
        
        score = new Pair(0, 0);
        int scoreWidth = (int) (d.width*0.1);
        board = new Board(d, scoreWidth);
    
        int centerHeight = d.height/2;
        Dimension playerDimension = new Dimension(5, 30);
        Player player0 = new Player(playerDimension, new Point(scoreWidth, centerHeight));
        Player player1 = new Player(playerDimension, new Point(d.width - scoreWidth, centerHeight));
        players = new Pair(player0, player1);
    }
    
    public Vec getBallStartingVec() {
        int magnitude = 12;
        int quadrent = (int) (Math.random()*4);
        double ballDegrees = (quadrent*90) + (Math.random()*60 + 15);
        return new Vec(ballDegrees, magnitude);
    }
    
    public void resetGame() {
        reset();
        
        // set score to 00
        score = new Pair(0, 0);
    }
    
    public void reset() {
        // set ball position
        ball.setCenter(new Point((int)d.width/2, (int) d.height/2));
        ball.setVel(getBallStartingVec());
        
        // reset player position
        int scoreWidth = (int) (d.width*0.1);
        int centerHeight = d.height/2;
        
        Player mainPlayer = players.get(0);
        Player robot = players.get(1);
        mainPlayer.setCenter(new Point(scoreWidth, centerHeight));
        mainPlayer.setAcc(new Vec(0, 0));
        mainPlayer.setVel(new Vec(0, 0));
        
        robot.setCenter(new Point(d.width - scoreWidth, centerHeight));
        robot.setAcc(new Vec(0, 0));
        robot.setVel(new Vec(0, 0));
    }
    
}
