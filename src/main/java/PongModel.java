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
    private Pair<Player> players;
    private Ball ball;
    private double width;
    private double height;
    private Pair<Integer> score;

    public PongModel() {
        this.players = new Pair(new Player(), new Player());

        this.ball = new Ball();
        this.width = 100;
        this.height = 50;
        this.score = new Pair(0, 0);
    }
}
