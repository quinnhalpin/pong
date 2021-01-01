
import java.awt.Dimension;

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
        int magnitude = 5;
//        ball = new Ball(radius, center, magnitude);
        ball = new Ball(radius, center, new Vec(50, magnitude));
        score = new Pair(0, 0);
        int scoreWidth = (int) (d.width*0.1);
        board = new Board(d, scoreWidth);
    
        int centerHeight = d.height/2;
        Dimension playerDimension = new Dimension(5, 30);
        Player player0 = new Player(playerDimension, new Point(scoreWidth, centerHeight));
        Player player1 = new Player(playerDimension, new Point(d.width - scoreWidth, centerHeight));
        
        
        players = new Pair(player0, player1);
        
    }
    
    
}
