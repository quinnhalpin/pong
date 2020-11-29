package src;

import java.util.ArrayList;

public class PhysicsObj {
	private ArrayList<Integer> pos;
	private int velocity;
	private int acc;

	public PhysicsObj() {
		this.pos.add(0);
		this.pos.add(0);
		this.velocity = 0;
		this.acc = 0;
	}

	public ArrayList<Integer> getPos() {
		return this.pos;
	}

	public void setPos(int x, int y) {
		pos.set(0, Integer.valueOf(x));
		pos.set(1, Integer.valueOf(y));
	}

	public int getVel() {
		return this.velocity;
	}

	public void setVel(int v) {
		this.velocity = v;
	}

	public int getAcc() {
		return this.acc;
	}

	public void setAcc(int acc) {
		this.acc = acc;
	}
}
