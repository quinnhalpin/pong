package src;

import java.util.ArrayList;

public class PongModel {
	private Pair<Player> players;
	private Ball ball;
	private double width;
	private double height;
	private Pair<Integer> score;

	public PongModel() {
		this.players = new Pair<Player>(new Player(), new Player());
		
		this.ball = new Ball();
		this.width = 100;
		this.height = 50;
		this.score = new Pair<Integer>(Integer.valueOf(0), Integer.valueOf(0));
	}

	public Pair<Integer> getScore() {
		return score;
	}

	public void setScore(Pair<Integer> score) {
		this.score = score;
	}

	public void setPlayersScore(int index, int score) {
		this.score.set(index, score);
	}

	public void setBallPos(int x, int y) {
		this.ball.setPos(x, y);
	}

	public Pair<Integer> getBallPos() {
		return this.ball.getPos();
	}

	public Pair<Integer> getPlayerPos(int index) {
		return this.players.get(index).getPos();
	}
	
	public Vec getBallVel() {
		return this.ball.getVel();
	}

	public Vec getPlayerVel(int index) {
		return this.players.get(index).getVel();
	}

	public Vec getBallAcc() {
		return this.ball.getAcc();
	}

	public Vec getPlayerAcc(int index) {
		return this.players.get(index).getAcc();
	}
}
