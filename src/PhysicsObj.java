package src;

import java.util.ArrayList;

public class PhysicsObj {
	private ArrayList<Integer> pos;
	private Vec vel;
	private Vec acc;

	public PhysicsObj() {
		this.pos = new ArrayList<Integer>();
		this.pos.add(0);
		this.pos.add(0);
		this.vel = new Vec(0, 0);
		this.acc = new Vec(0, 0);
	}

	public ArrayList<Integer> getPos() {
		return this.pos;
	}

	public void setPos(int x, int y) {
		pos.set(0, Integer.valueOf(x));
		pos.set(1, Integer.valueOf(y));
	}

	public Vec getVel() {
		return this.vel;
	}

	public void setVel(Vec v) {
		this.vel = v;
	}

	public Vec getAcc() {
		return this.acc;
	}

	public void setAcc(Vec acc) {
		this.acc = acc;
	}
}
