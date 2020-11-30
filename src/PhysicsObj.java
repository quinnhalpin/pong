package src;

public class PhysicsObj {
	private Pair<Integer> pos;
	private Vec vel;
	private Vec acc;

	public PhysicsObj() {
		this.pos = new Pair<Integer>(0, 0);
		this.vel = new Vec(0, 0);
		this.acc = new Vec(0, 0);
	}

	public Pair<Integer> getPos() {
		return this.pos;
	}

	public void setPos(int x, int y) {
		this.pos = new Pair<Integer>(x, y);
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
